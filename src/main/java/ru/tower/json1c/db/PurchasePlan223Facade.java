package ru.tower.json1c.db;

import ru.tower.purchase.entity.Organization;
import ru.tower.purchase.entity.PurchasePlan223;

import static java.lang.String.format;

@SuppressWarnings("ALL")
public class PurchasePlan223Facade extends AbstractFacade <PurchasePlan223, Long> {

    public PurchasePlan223Facade() {
        super(PurchasePlan223.class);
    }

    public PurchasePlan223 findPlan(Organization organization, Integer yearPurchase) throws Throwable {
        return (PurchasePlan223) em.createNamedQuery("PurchasePlan223.findPlan")
                .setParameter("org", organization)
                .setParameter("type", PurchasePlan223.Type.PLAN)
                .setParameter("year", yearPurchase)
                .setMaxResults(1)
                .getResultList().stream().findFirst()
                    .orElseThrow(() -> new Json1cException(format("Не найден план закупок для организации %s, год плана закупок %s"
                            , organization.getOrgId(), yearPurchase)));
    }
}
