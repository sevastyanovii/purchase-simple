package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

public abstract class NsiAstPurchaseTypeTreeAbstract extends AbstractEntity {
    public abstract String getName();
    public abstract Long getTreeId();
    public abstract Long getParentId();
}
