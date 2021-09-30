package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiBidPurchaseCategorySMSP;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
}
