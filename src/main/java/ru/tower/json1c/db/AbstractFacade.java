package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static ru.tower.json1c.PersistenceSupport.getEntityManager;
import static ru.tower.json1c.db.QueryParam.param;

@SuppressWarnings("All")
public class AbstractFacade <T extends AbstractEntity,K extends Serializable>{

    protected static final EntityManager em = getEntityManager();

    protected final Class<T> entityType;

    public AbstractFacade(Class<T> entityType) {
        this.entityType = entityType;
    }

    public T find(K key) {
        return em.find(entityType, key);
    }

    public T persist(T object) {
        em.persist(object);
        flush();
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

    public T selectFirst(String query, QueryParam ... params) {
        Query jpaQuery = em.createQuery(query).setMaxResults(1);
        applyParams(jpaQuery, params);
        return getFirstResult(jpaQuery);
    }

    public T selectFirstNamed(String queryName, QueryParam ... params) throws Throwable {
        Query jpaQuery = em.createNamedQuery(queryName).setMaxResults(1);
        applyParams(jpaQuery, params);
        return getFirstResult(jpaQuery);
    }

    protected T getFirstResult(Query jpaQuery) {
        try {
            Set<Parameter<?>> paramSet = jpaQuery.getParameters();
            List<QueryParam> params = paramSet.stream().map(p -> param(p.getName(), jpaQuery.getParameterValue(p))).collect(Collectors.toList());
            return (T) jpaQuery.getResultList().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException(format("No rows for query, params: %s"
                            , params.stream().map(e -> e.getName() + "=" + e.getValue()).collect(Collectors.joining(";","<", ">")))));
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<T> selectNamed(String queryName, QueryParam ... params) {
        Query jpaQuery = em.createNamedQuery(queryName);
        applyParams(jpaQuery, params);
        return jpaQuery.getResultList();
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    protected void applyParams(Query jpaQuery, QueryParam ... params) {
        if (null != params) {
            for (QueryParam param : params){
                jpaQuery.setParameter(param.getName(), param.getValue());
            }
        }
    }

    public void flush() {
        em.flush();
    }

}
