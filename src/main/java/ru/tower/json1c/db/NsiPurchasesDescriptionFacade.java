package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiPurchasesDescription;

@SuppressWarnings("ALL")
public class NsiPurchasesDescriptionFacade extends AbstractFacade <NsiPurchasesDescription, Long> {

    public NsiPurchasesDescriptionFacade() {
        super(NsiPurchasesDescription.class);
    }

    public NsiPurchasesDescription findByName(String name) {
        return (NsiPurchasesDescription) em.createNamedQuery("NsiPurchasesDescription.findByName")
                .setParameter("like", name).setMaxResults(1).getResultList().stream().findFirst().orElseGet(() -> {
                    return createNew(name);
                });
    }

    private NsiPurchasesDescription createNew(String name) {
        NsiPurchasesDescription description = new NsiPurchasesDescription();
        description = new NsiPurchasesDescription();
        description.setActual(true);
        description.setGuid(randomUUID());
        description.setName(name);
        return persist(description);
    }
}
