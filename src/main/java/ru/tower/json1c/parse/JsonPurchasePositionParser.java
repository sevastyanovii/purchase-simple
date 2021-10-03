package ru.tower.json1c.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.tower.json1c.Purchase223Facade;
import ru.tower.json1c.db.*;
import ru.tower.purchase.entity.*;
import ru.tower.purchase.entity.nsi.NsiOkato;
import ru.tower.purchase.entity.nsi.NsiStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private final NsiAstCurrencyFacade nsiAstCurrencyFacade = new NsiAstCurrencyFacade();
    private final NsiOkved2Facade nsiOkved2Facade = new NsiOkved2Facade();
    private final PurchaseItem223Facade purchaseItem223Facade = new PurchaseItem223Facade();
    private final NsiOkeiFacade nsiOkeiFacade = new NsiOkeiFacade();
    private final NsiOkpd2Facade nsiOkpd2Facade = new NsiOkpd2Facade();
    private final NsiOkatoFacade nsiOkatoFacade = new NsiOkatoFacade();
    private final NsiFederalDistrictFacade nsiFederalDistrictFacade = new NsiFederalDistrictFacade();
    private final DeliveryPlace223Facade deliveryPlace223Facade = new DeliveryPlace223Facade();
    private final DeliveryPlace223TemplFacade deliveryPlace223TemplFacade = new DeliveryPlace223TemplFacade();

    private final Request purchaseRequest;
    private final Purchase223 purchase223;

    public JsonPurchasePositionParser(String jsonString) {
        purchaseRequest = parseJson(jsonString);
        purchase223 = new Purchase223();
    }

    public Purchase223 createPurchase(Organization organization) throws Throwable {

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

        purchase223.setStartPrice(purchaseRequest.getPlan_position().getContract_amount());
        purchase223.setStartPriceRUR(purchaseRequest.getPlan_position().getContract_amount_rub());
        purchase223.setExchangeRate(purchaseRequest.getPlan_position()
                .getCurrency_exchange_rate().divide(BigDecimal.valueOf(purchaseRequest.getPlan_position().getMultiplicity()), 4, RoundingMode.HALF_UP).toString());
        purchase223.setAstCurrency(nsiAstCurrencyFacade.findCurrency(purchaseRequest.getPlan_position().getCurrency()));
        purchase223.setExchangeRateTime(purchaseRequest.getPlan_position().getCourse_date());
        purchase223Facade.persist(purchase223);

        setYearDiffData();

        setClassifiers();

        return purchase223;
    }

    private void setYearDiffData() {
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
                yearVolumeLong.setYearPriceRUR(new BigDecimal("0"));
                yearVolumeLongFacade.persist(yearVolumeLong);
            }
        }
    }

    private void setClassifiers() {
        purchase223.setGeneralAddress(purchaseRequest.getPlan_position().getClassifier_codes()
                .stream().map(ClassifierCode::getRegion).distinct().count() <= 1);
        if (purchase223.isGeneralAddress()) {
            purchaseRequest.getPlan_position().getClassifier_codes().stream().findFirst().ifPresent(c -> {
                DeliveryPlace223Templ place223 = new DeliveryPlace223Templ();
                place223.setOkato(c.getOkato());
                place223.setRegion(c.getRegion());
                place223.setNsiOkato(nsiOkatoFacade.findByCode(NsiOkato.class, c.getOkato()));
                place223.setAddress("НЕТ");
                deliveryPlace223TemplFacade.persist(place223);
                purchase223.setDeliveryPlace223Templ(place223);
                purchase223Facade.update(purchase223);
            });
        }
        purchaseRequest.getPlan_position().getClassifier_codes().forEach(e -> {
            PurchaseItem223 item223 = new PurchaseItem223();
            item223.setPurchase(purchase223);
            item223.setOkved2(nsiOkved2Facade.findByCode(e.getOkved_2()));
            item223.setNsiOkei(nsiOkeiFacade.findByCode(e.getUnit_measurement()));
            item223.setPrice(new BigDecimal("0"));
            item223.setGuid(plan223ItemFacade.randomUUID());
            item223.setNsiOkpd2(nsiOkpd2Facade.findByCode(e.getOkpd_2()));
            item223.setQuantity(e.getQuantity().doubleValue());

            DeliveryPlace223 place223 = new DeliveryPlace223();
            place223.setOkato(e.getOkato());
            place223.setRegion(e.getRegion());
            place223.setNsiOkato(nsiOkatoFacade.findByCode(NsiOkato.class, e.getOkato()));
            place223.setAddress("НЕТ");
            deliveryPlace223Facade.persist(place223);
            item223.setDeliveryPlace(place223);
            purchaseItem223Facade.persist(item223);
        });
    }

    public static Request parseJson(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
        return gson.fromJson(jsonString, Request.class);
    }
}
