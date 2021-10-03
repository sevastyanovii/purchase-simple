package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import java.io.Serializable;

import static java.lang.String.format;
import static ru.tower.json1c.db.QueryParam.param;

@SuppressWarnings("All")
public abstract class AbstractNsiFacade<E extends AbstractEntity, K extends Serializable> extends AbstractFacade {

    public E findByCode(Class<E> clazz, String code) {
        try {
            return (E) selectFirst(format("from %s n where n.code = :code and actual = true", clazz.getSimpleName()), param("code", code));
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
