package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiAstPurchaseType;

import static java.lang.String.format;

@SuppressWarnings("ALL")
public class NsiAstPurchaseTypeFacade extends AbstractFacade <NsiAstPurchaseType, Long> {

    public NsiAstPurchaseTypeFacade() {
        super(NsiAstPurchaseType.class);
    }

    public NsiAstPurchaseType findPurchaseType(String guid) throws Throwable {
        return (NsiAstPurchaseType) em.createNamedQuery("NsiAstPurchaseType.findPurchaseType")
                .setParameter("guid", guid).setMaxResults(1).getResultList()
                . stream().findFirst().orElseThrow(() -> new RuntimeException(format("Не найден метод закупки по guid '%s'", guid)));
    }
}
