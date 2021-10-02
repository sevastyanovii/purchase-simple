package ru.tower.json1c.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.tower.json1c.Purchase223Facade;
import ru.tower.json1c.db.*;
import ru.tower.purchase.entity.Organization;
import ru.tower.purchase.entity.Purchase223;
import ru.tower.purchase.entity.SmallVolumes;
import ru.tower.purchase.entity.YearVolumeLong;
import ru.tower.purchase.entity.nsi.NsiStatus;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.util.stream.IntStream.rangeClosed;
import static ru.tower.json1c.ParseUtils.assertThat;
import static ru.tower.json1c.ParseUtils.year;
import static ru.tower.purchase.entity.NMCKInstructions.INFORMATION;

public class JsonPurchasePositionParser {

    private final NsiStatusFacade nsiStatusFacade = new NsiStatusFacade();
    private final Purchase223Facade purchase223Facade = new Purchase223Facade();
    private final PurchasePlan223Facade purchasePlan223Facade = new PurchasePlan223Facade();
    private final OrganizationFacade organizationFacade = new OrganizationFacade();
    private final PurchasePlan223ItemFacade plan223ItemFacade = new PurchasePlan223ItemFacade();
    private final PurchasePlan223ItemLinkFacade purchasePlan223ItemFacade = new PurchasePlan223ItemLinkFacade();
    private final NsiPurchasesDescriptionFacade purchasesDescriptionFacade = new NsiPurchasesDescriptionFacade();
    private final NsiAstPurchaseTypeFacade nsiAstPurchaseTypeFacade = new NsiAstPurchaseTypeFacade();
    private final NsiBidPurchaseCategorySMSPFacade nsiBidPurchaseCategorySMSPFacade = new NsiBidPurchaseCategorySMSPFacade();
    private final YearVolumeLongFacade yearVolumeLongFacade = new YearVolumeLongFacade();

    private final Request purchaseRequest;

    public JsonPurchasePositionParser(String jsonString) {
        purchaseRequest = parseJson(jsonString);
    }

    public Purchase223 createPurchase(Organization organization) throws Throwable {

        Purchase223 purchase223 = new Purchase223();
        purchase223.setNsiStatus(nsiStatusFacade.find(NsiStatus.class, Long.parseLong(purchaseRequest.getPlan_position().getPosition_status())));
        purchase223.setOrganization(organization);
        purchase223.setNmckInstruction(INFORMATION);
        purchase223.setPurchasesDescription(purchasesDescriptionFacade.findByName(purchaseRequest.getPlan_position().getSubject_contract()));
        purchase223.setMinRequirements(purchaseRequest.getPlan_position().getRequirements());
        purchase223.setPurchaseMethod(nsiAstPurchaseTypeFacade.findPurchaseType(purchaseRequest.getPlan_position().getId_purchase_method()));
        BigDecimal rubAmount = purchaseRequest.getPlan_position().getContract_amount_rub();
        if (rubAmount.compareTo(new BigDecimal("100000")) <=0 ) {
            purchase223.setSmallVolumes(SmallVolumes.UP_TO_100);
        } else if (rubAmount.compareTo(new BigDecimal("500000")) <=0 ){
            purchase223.setSmallVolumes(SmallVolumes.UP_TO_500);
        } else {
            purchase223.setSmallVolumes(SmallVolumes.NONE);
        }

        if (purchaseRequest.getPlan_position().getUnaccounted_purchase_1352()) {
            purchase223.setPurchasingCategory(nsiBidPurchaseCategorySMSPFacade.findByGuid(purchaseRequest.getPlan_position().getId_unaccounted_purchase()));
        }

        purchase223.setHighTech(purchaseRequest.getPlan_position().getHightech_procurement());
        purchase223.setContractTime(purchaseRequest.getPlan_position().getDate_notification());
        purchase223.setContractTerm(purchaseRequest.getPlan_position().getTerm_execution_contract());

        purchase223Facade.persist(purchase223);

        if (!year(purchase223.getContractTime()).equals(year(purchase223.getContractTerm()))) {
            assertThat(purchase223.getContractTerm().after(purchase223.getContractTime())
                    , () -> new RuntimeException("Срок размещения плановый должен быть меньше срока исполнения"));

            BigDecimal sumLong = purchaseRequest.getPlan_position().getAmounts_by_year()
                    .stream().map(AmountYear::getAmount).reduce(BigDecimal::add).get();
            assertThat(sumLong.equals(purchaseRequest.getPlan_position().getContract_amount())
                    , () -> new RuntimeException(format("Сумма по годам планирования '%s' не равна НМЦ: '%s'", sumLong, purchaseRequest.getPlan_position().getContract_amount())));

            int[] years = rangeClosed(year(purchase223.getContractTime()), year(purchase223.getContractTerm())).toArray();
            if (purchaseRequest.getPlan_position().getAmounts_by_year().size() != years.length) {
                throw new RuntimeException(format("Разбивка по годам планирования ('%s') не соответствует '%s'"
                        , purchaseRequest.getPlan_position().getAmounts_by_year().size(), years.length));
            }
            for (int year : years){
                AmountYear amountYear = purchaseRequest.getPlan_position().getAmount(year);
                YearVolumeLong yearVolumeLong = new YearVolumeLong();
                yearVolumeLong.setGuid(yearVolumeLongFacade.randomUUID());
                yearVolumeLong.setPurchase(purchase223);
                yearVolumeLong.setYear(amountYear.getYear());
                yearVolumeLong.setYearPrice(amountYear.getAmount());
                yearVolumeLongFacade.persist(yearVolumeLong);
            }
        }

//        commitTransaction();
        return purchase223;
    }

    public static Request parseJson(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
        return gson.fromJson(jsonString, Request.class);
    }
}
