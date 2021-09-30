package ru.tower.json1c.db;

import ru.tower.purchase.entity.nsi.NsiBidPurchaseCategorySMSP;

import static java.lang.String.format;

@SuppressWarnings("ALL")
public class NsiBidPurchaseCategorySMSPFacade extends AbstractFacade <NsiBidPurchaseCategorySMSP, Long> {

    public NsiBidPurchaseCategorySMSP findByGuid(String guid) throws Throwable {
        return (NsiBidPurchaseCategorySMSP) em.createNamedQuery("NsiBidPurchaseCategorySMSP.findByGuid").setParameter("guid", guid).setMaxResults(1).getResultList()
                .stream().findFirst().orElseThrow(() -> new RuntimeException(format("Не найдена строка 'NSI_BID_PURCHASE_CATEGORY_SMSP' по guid '%s'", guid)));
    }
}
