package ru.tower.json1c.db;

import ru.tower.purchase.entity.Organization;
import ru.tower.purchase.entity.PurchasePlan223;

@SuppressWarnings("ALL")
public class PurchasePlan223Facade extends AbstractFacade <PurchasePlan223, Long> {

    public PurchasePlan223 findPlan(Organization organization) {
        return (PurchasePlan223) em.createQuery("from PurchasePlan223 p where p.organization = :org and p.type = :type")
                .setParameter("org", organization)
                .setParameter("type", PurchasePlan223.Type.PLAN)
                .setMaxResults(1)
                .getResultList().stream().findFirst().orElse(null);
    }
}
