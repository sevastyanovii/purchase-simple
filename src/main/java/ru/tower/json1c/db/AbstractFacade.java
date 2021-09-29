package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.UUID;

import static ru.tower.json1c.PersistenceSupport.getEntityManager;

public class AbstractFacade <T extends AbstractEntity,K extends Serializable>{

    protected static final EntityManager em = getEntityManager();

    public T find(Class<T> clazz, K key) {
        return em.find(clazz, key);
    }

    public T persist(T object) {
        em.persist(object);
        return object;
    }

    public String randomUUID() {
        return UUID.randomUUID().toString();
    }

}
