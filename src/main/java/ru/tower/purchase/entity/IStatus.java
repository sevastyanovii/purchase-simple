package ru.tower.purchase.entity;


import ru.tower.purchase.entity.nsi.NsiStatus;

/**
 * User: sevdokimov
 * Date: 07.05.19 11:39
 */
public interface IStatus {

    public NsiStatus getNsiStatus();

    public void setNsiStatus(NsiStatus nsiStatus);

}
