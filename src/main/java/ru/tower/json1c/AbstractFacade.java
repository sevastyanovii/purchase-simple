package ru.tower.json1c;

import ru.tower.purchase.entity.AbstractEntity;

import java.io.Serializable;

import static ru.tower.json1c.PersistenceSupport.getEntityManager;

public class AbstractFacade <T extends AbstractEntity,K extends Serializable>{

    public T find(Class<T> clazz, K key) {
        return getEntityManager().find(clazz, key);
    }

    public T persist(T object) {
        getEntityManager().persist(object);
        return object;
    }
}
