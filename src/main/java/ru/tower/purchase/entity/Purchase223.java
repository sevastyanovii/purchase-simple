package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiBidPurchaseCategorySMSP;

import javax.persistence.*;

@Entity
@Table(name = Purchase223.TABLE_NAME)
public class Purchase223 extends AbstractPurchase223<Purchase223> {

    public static final String TABLE_NAME = "purchases_223";

    /**
     *   Категория закупки
     */
    @JoinColumn(name = "purchasing_category", referencedColumnName = "id")
    @ManyToOne
    private NsiBidPurchaseCategorySMSP purchasingCategory;

    /**
     * Способ указания региона поставки, по умолчанию отдельный для каждого ТРУ, вынесено из позиций для улучшения ясности понимания ;)
     */
    @Column(name = "general_address", columnDefinition = "boolean default true", nullable = false)
    private boolean generalAddress;

    @Override
    public Object getEntityId() {
        return getId();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Organization getCommonOrg() {
        return null;
    }

    @Override
    protected String getCommonLetter(boolean extCondition) {
        return null;
    }

    public NsiBidPurchaseCategorySMSP getPurchasingCategory() {
        return purchasingCategory;
    }

    public void setPurchasingCategory(NsiBidPurchaseCategorySMSP purchasingCategory) {
        this.purchasingCategory = purchasingCategory;
    }

    public boolean isGeneralAddress() {
        return generalAddress;
    }

    public void setGeneralAddress(boolean generalAddress) {
        this.generalAddress = generalAddress;
    }
}
