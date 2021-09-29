package ru.tower.json1c.db;

import ru.tower.purchase.entity.Organization;
import ru.tower.purchase.entity.PurchasePlan223;

import static java.lang.String.format;

@SuppressWarnings("ALL")
public class PurchasePlan223Facade extends AbstractFacade <PurchasePlan223, Long> {

    public PurchasePlan223 findPlan(Organization organization, Integer yearPurchase) throws Throwable {
        return (PurchasePlan223) em.createQuery("from PurchasePlan223 p where p.organization = :org and p.type = :type and year = :year")
                .setParameter("org", organization)
                .setParameter("type", PurchasePlan223.Type.PLAN)
                .setParameter("year", yearPurchase)
                .setMaxResults(1)
                .getResultList().stream().findFirst()
                    .orElseThrow(() -> new Json1cException(format("Не найден план закупок для организации %s, год плана закупок %s"
                            , organization.getOrgId(), yearPurchase)));
    }
}
