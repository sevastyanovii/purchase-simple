package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Optional;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

@SuppressWarnings("All")
public abstract class AbstractNsiFacade<E extends AbstractEntity, K extends Serializable> extends AbstractFacade {

    public AbstractNsiFacade(Class entityType) {
        super(entityType);
    }

    public E findByCode(String code) {
        try {
            return Optional.ofNullable(getNamedQuery()).map(q -> {
                applyParams(q, param("code", code));
                return (E) getFirstResult(q);
            }).orElseGet(() -> {
                return (E) selectFirst(format("from %s n where n.code = :code and actual = true", entityType.getSimpleName()), param("code", code));
            });
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Query getNamedQuery() {
        try {
            return em.createNamedQuery(entityType.getSimpleName() + ".findByCode");
        } catch (Exception e) {
            return null;
        }
    }
}
