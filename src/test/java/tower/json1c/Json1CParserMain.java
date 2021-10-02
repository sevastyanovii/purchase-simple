package tower.json1c;

import org.junit.jupiter.api.*;
import ru.tower.json1c.PersistenceSupport;
import ru.tower.json1c.db.OrganizationFacade;
import ru.tower.json1c.db.PurchasePlan223Facade;
import ru.tower.json1c.db.YearVolumeLongFacade;
import ru.tower.json1c.map.SimpleEntity;
import ru.tower.json1c.parse.JsonPurchasePositionParser;
import ru.tower.json1c.parse.PlanPositionFile;
import ru.tower.json1c.parse.Request;
import ru.tower.purchase.entity.*;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.tower.json1c.db.QueryParam.param;
import static ru.tower.json1c.parse.JsonPurchasePositionParser.parseJson;

public class Json1CParserMain {

    private static EntityManager em = PersistenceSupport.getEntityManager();

    @BeforeEach
    public void before() {
        PersistenceSupport.startTransaction();
    }

    @AfterEach
    public void after() {
        try {
            PersistenceSupport.commitTransaction();
        } catch (Exception e) {
            System.out.println("Error on commit: " + e.getMessage());
        }
    }

    @AfterAll
    public static void afterAll() {
        try {
            PersistenceSupport.getEntityManager().close();
        } catch (Exception e) {
            System.out.println("Error on close EM: " + e.getMessage());
        }
    }

    @Test public void test() {

        SimpleEntity entity = new SimpleEntity();
        String name = System.currentTimeMillis()+"абвгйиъ";
        System.out.println(name);
        entity.setName(name);
        em.persist(entity);
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
        PurchasePlan223 plan223 = new PurchasePlan223Facade().find(PurchasePlan223.class, 1L);
    }

    @Test public void test4() throws Throwable {
        Organization organization = new OrganizationFacade().find(Organization.class, 23L);
        assertNotNull(organization);
        Purchase223 purchase223 = new JsonPurchasePositionParser(getExampleBody()).createPurchase(organization);
        Assertions.assertTrue(purchase223.getId() > 0);
        assertNotNull(purchase223.getPurchasesDescription());
        assertNotNull(purchase223.getPurchaseMethod());
        assertEquals(SmallVolumes.NONE, purchase223.getSmallVolumes());
        assertFalse(purchase223.isHighTech());
        assertNotNull(purchase223.getPurchasingCategory());

        List<YearVolumeLong> longs = new YearVolumeLongFacade().select("from YearVolumeLong l where l.purchase = :purchase"
            , param("purchase", purchase223));
        assertEquals(2, longs.size());
        assertTrue(longs.stream().allMatch(l -> l.getYear() > 0 && l.getYearPrice().compareTo(new BigDecimal("0")) > 0));
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


