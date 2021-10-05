package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.Query;
import java.io.Serializable;

import static ru.tower.json1c.db.QueryParam.param;

@SuppressWarnings("All")
public abstract class AbstractNsiFacade<E extends AbstractEntity, K extends Serializable> extends AbstractFacade {

    public AbstractNsiFacade(Class entityType) {
        super(entityType);
    }

    public E findByCode(String code) {
        try {
            Query namedQuery = em.createNamedQuery(entityType.getSimpleName() + ".findByCode");
            applyParams(namedQuery, param("code", code));
            return (E) getFirstResult(namedQuery);
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
