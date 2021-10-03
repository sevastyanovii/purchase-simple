package ru.tower.json1c.db;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static ru.tower.json1c.PersistenceSupport.getEntityManager;

@SuppressWarnings("All")
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

    public T selectFirst(String query, QueryParam ... params) throws Throwable {
        Query jpaQuery = em.createQuery(query).setMaxResults(1);
        applyParams(jpaQuery, params);
        return (T) jpaQuery.getResultList().stream().findFirst()
                .orElseThrow(() -> new RuntimeException(format("no rows for query '%s', params: %s"
                        , query, Arrays.stream(params).map(e -> e.getName() + "=" + e.getValue()).collect(Collectors.joining(";","<", ">")))));
    }

    public List<T> selectNamed(String queryName, QueryParam ... params) {
        Query jpaQuery = em.createNamedQuery(queryName);
        applyParams(jpaQuery, params);
        return jpaQuery.getResultList();
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    private void applyParams(Query jpaQuery, QueryParam ... params) {
        if (null != params) {
            for (QueryParam param : params){
                jpaQuery.setParameter(param.getName(), param.getValue());
            }
        }
    }

}
