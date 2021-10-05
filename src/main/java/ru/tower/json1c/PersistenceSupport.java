package ru.tower.json1c;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceSupport {

    private static final String PERSISTENCE_UNIT_NAME = "psunit1";

    public static void startTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void commitTransaction() {
        if (getEntityManager().getTransaction().isActive()){
            getEntityManager().getTransaction().commit();
        } else {
            throw new RuntimeException("Transaction is not active");
        }
    }

    public static final ThreadLocal<EntityManager> entityManagerThreadLocal = ThreadLocal.withInitial(() -> {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return factory.createEntityManager();
    });

    public static EntityManager getEntityManager() {
        return entityManagerThreadLocal.get();
    }

}
