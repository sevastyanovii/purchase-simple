package ru.tower.purchase.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Purchase223.TABLE_NAME)
public class Purchase223 extends AbstractPurchase223<Purchase223> {

    public static final String TABLE_NAME = "purchases_223";

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
}
