package tower.json1c;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tower.json1c.PersistenceSupport;
import ru.tower.json1c.Purchase223Facade;
import ru.tower.json1c.db.*;
import ru.tower.json1c.map.SimpleEntity;
import ru.tower.json1c.parse.PlanPositionFile;
import ru.tower.json1c.parse.Request;
import ru.tower.purchase.entity.*;
import ru.tower.purchase.entity.nsi.NsiStatus;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.tower.json1c.PersistenceSupport.commitTransaction;
import static ru.tower.json1c.PersistenceSupport.startTransaction;
import static ru.tower.purchase.entity.NMCKInstructions.INFORMATION;

public class Json1CParserMain {

    private final NsiStatusFacade nsiStatusFacade = new NsiStatusFacade();
    private final Purchase223Facade purchase223Facade = new Purchase223Facade();
    private final PurchasePlan223Facade purchasePlan223Facade = new PurchasePlan223Facade();
    private final OrganizationFacade organizationFacade = new OrganizationFacade();
    private final PurchasePlan223ItemFacade plan223ItemFacade = new PurchasePlan223ItemFacade();
    private final PurchasePlan223ItemLinkFacade purchasePlan223ItemFacade = new PurchasePlan223ItemLinkFacade();

    @Test public void test() {
        EntityManager em = PersistenceSupport.getEntityManager();
        em.getTransaction().begin();

        SimpleEntity entity = new SimpleEntity();
        String name = System.currentTimeMillis()+"абвгйиъ";
        System.out.println(name);
        entity.setName(name);
        em.persist(entity);
        em.getTransaction().commit();
        em.close();

        assertNotNull(entity.getId());
    }

    @Test public void test2() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("1.json");
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        char[] buff = new char[1024];
        int count = 0; StringBuilder jsonBuilder = new StringBuilder();
        while ((count = reader.read(buff, 0, 1024)) > 0) {
            jsonBuilder.append(buff, 0, count);
        }
        String json = jsonBuilder.toString();

        System.out.println(json);
    }

    @Test public void test3() {
        Request request = parseJson(getExampleBody());
        assertNotNull(request);
        assertEquals("d5ddbc50-e385-4a85-af75-30f5c65378fd", request.getPlan_position().getId_sbkr());
        assertEquals("Оказание услуг по привлечению клиентов и услуг по выдаче ипотечных кредитов для нужд АО Банк ДОМ.РФ", request.getPlan_position().getSubject_contract());
        assertEquals(true, request.getPlan_position().getElectronic_form());
        assertEquals(new BigDecimal("88.7545"), request.getPlan_position().getCurrency_exchange_rate());
        assertEquals(new BigDecimal("19800000.00"), request.getPlan_position().getContract_amount_rub());
        assertEquals(2, request.getPlan_position().getBudget_applications().size());
        assertNotNull(request.getPlan_position().getExecuting_division().getCode_zup());
        assertEquals(2, request.getPlan_position().getInitiating_units().size());
        assertEquals(2, request.getPlan_position().getClassifier_codes().size());
        assertEquals(2, request.getPlan_position().getFiles().size());
        assertEquals("документ2.pdf", request.getPlan_position().getFiles().toArray(new PlanPositionFile[]{})[1].getName());
    }

    @Test public void test5() {
        PurchasePlan223 plan223 = purchasePlan223Facade.find(PurchasePlan223.class, 1L);
    }

    @Test public void test4() {
        Organization organization = organizationFacade.find(Organization.class, 23L);
        Assertions.assertNotNull(organization);
        Purchase223 purchase223 = createPurchase(organization);
        Assertions.assertTrue(purchase223.getId() > 0);
    }

    private Purchase223 createPurchase(Organization organization) {
        Request request = parseJson(getExampleBody());
        startTransaction();
        Purchase223 purchase223 = new Purchase223();
        purchase223.setNsiStatus(nsiStatusFacade.find(NsiStatus.class, Long.parseLong(request.getPlan_position().getPosition_status())));
        purchase223.setOrganization(organization);
        purchase223.setNmckInstruction(INFORMATION);

        PurchasePlan223 plan223 = purchasePlan223Facade.findPlan(organization);
        PurchasePlan223Item item = new PurchasePlan223Item();
        item.setGuid(UUID.randomUUID().toString());
        item.setPlan(plan223);
        plan223ItemFacade.persist(item);

        purchase223Facade.persist(purchase223);

        PurchasePlan223ItemLink link = new PurchasePlan223ItemLink();
        link.setMain(true);
        link.setPurchase(purchase223);
        link.setItem(item);
        purchasePlan223ItemFacade.persist(link);

        commitTransaction();
        return purchase223;
    }

    private Request parseJson(String jsonString) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
        return gson.fromJson(jsonString, Request.class);
    }

    private String getExampleBody() {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("1.json");
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            char[] buff = new char[1024];
            int count = 0;
            StringBuilder jsonBuilder = new StringBuilder();
            while ((count = reader.read(buff, 0, 1024)) > 0) {
                jsonBuilder.append(buff, 0, count);
            }
            return jsonBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}


