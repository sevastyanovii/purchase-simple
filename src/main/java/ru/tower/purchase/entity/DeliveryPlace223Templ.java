/*
 * Copyright (c) "Tower Networks and Technology" LTD 2019. All Rights Reserved. No part of this code may be reproduced without "Tower Networks and Technology" LTD's express consent.
 */

package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiFederalDistrict;
import ru.tower.purchase.entity.nsi.NsiOkato;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 04.10.18
 * Time: 15:59
 */

/**
 * Место доставки товара 223-ФЗ
 */
@Entity
@Table(name = "delivery_places_223_templ")
public class DeliveryPlace223Templ extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ОКАТО
     */
    @Column(length = 11)
    private String okato;

    /**
     * Место поставки (субъект РФ)\Федеральный округ
    */
    @Column(name = "state", length = 2000)
    private String state;

    /**
     * Место поставки (субъект РФ)\Федеральный округ
     */
    @JoinColumn(name = "nsi_federal_district_id")
    @ManyToOne
    private NsiFederalDistrict nsiFederalDistrict;

    /**
     * Место поставки (субъект РФ)\Регион
     */
    @Column(name = "region", length = 2000)
    private String region;

    /**
     * Место поставки (субъект РФ)\Регион
     */
    @JoinColumn(name = "nsi_okato_id")
    @ManyToOne
    private NsiOkato nsiOkato;

    /**
     * Место доставки
     */
    @Lob
    @Column(name="address", nullable = false)
    private String address;

    @Override
    public Object getEntityId() {
        return getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public NsiFederalDistrict getNsiFederalDistrict() {
        return nsiFederalDistrict;
    }

    public void setNsiFederalDistrict(NsiFederalDistrict nsiFederalDistrict) {
        this.nsiFederalDistrict = nsiFederalDistrict;
    }

    public NsiOkato getNsiOkato() {
        return nsiOkato;
    }

    public void setNsiOkato(NsiOkato nsiOkato) {
        this.nsiOkato = nsiOkato;
    }
}
