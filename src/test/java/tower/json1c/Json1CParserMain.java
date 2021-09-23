package tower.json1c;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tower.json1c.map.SimpleEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Json1CParserMain {

    private static final String PERSISTENCE_UNIT_NAME = "psunit1";
    private static EntityManagerFactory factory;

    @Test public void test() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        SimpleEntity entity = new SimpleEntity();
        entity.setName(System.currentTimeMillis()+"");
        em.persist(entity);
        em.getTransaction().commit();
        em.close();

        Assertions.assertNotNull(entity.getId());
    }

}


