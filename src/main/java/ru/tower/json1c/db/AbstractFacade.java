package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
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

    public List<T> select(String query, QueryParam ... params) {
        Query jpaQuery = em.createQuery(query);
        applyParams(jpaQuery, params);
        return jpaQuery.getResultList();
    }

    public List<T> selectNamed(String queryName, QueryParam ... params) {
        Query jpaQuery = em.createNamedQuery(queryName);
        applyParams(jpaQuery, params);
        return jpaQuery.getResultList();
    }

    private void applyParams(Query jpaQuery, QueryParam ... params) {
        if (null != params) {
            for (QueryParam param : params){
                jpaQuery.setParameter(param.getName(), param.getValue());
            }
        }
    }

}
