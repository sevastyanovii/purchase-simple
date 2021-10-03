/*
 * Copyright (c) "Tower Networks and Technology" LTD 2018. All Rights Reserved. No part of this code may be reproduced without "Tower Networks and Technology" LTD's express consent.
 */

package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiOkved2;

import javax.persistence.*;


/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 04.10.18
 * Time: 16:18
 */
@Entity
@Table(name = "purchase_items_223")
public class PurchaseItem223 extends AbstractItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Проект закупки
     */
    @JoinColumn(name = "purchase_id", nullable = false)
    @ManyToOne
    
    private Purchase223 purchase;

    /**
     * Наименование позиции закупки
     */
    @Column(name = "additional_info", length = 2000)
    private String additionalInfo;

    /**
     * Способ указания региона поставки, по умолчанию отдельный для каждого ТРУ
     * весьма странно видет это поле здесь, но схема интеграции с ЕИС предусматривает его именно здесь...
     */
    @Column(name = "general_address", columnDefinition = "boolean default false", nullable = false)
    private boolean generalAddress;

    /**
     * Место доставки товара
     */
    @JoinColumn(name = "delivery_place_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private DeliveryPlace223 deliveryPlace;

    /**
     * ОКВЭД 2
     */
    @JoinColumn(name = "nsi_okved2_id")
    @ManyToOne
    private NsiOkved2 okved2;

    /**
     * Невозможно определить количество товара, объем подлежащих выполнению работ, оказанию услуг
     */
    @Column(name = "not_det_goods_amount", columnDefinition = "boolean default false", nullable = false)
    private boolean notDetermineGoodsAmount;

    /**
     * Доп. адреса
     */
//    private Collection<String> addresses;

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

    public boolean isGeneralAddress() {
        return generalAddress;
    }

    public void setGeneralAddress(boolean generalAddress) {
        this.generalAddress = generalAddress;
    }

    public DeliveryPlace223 getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(DeliveryPlace223 deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public NsiOkved2 getOkved2() {
        return okved2;
    }

    public void setOkved2(NsiOkved2 okved2) {
        this.okved2 = okved2;
    }

    public boolean isNotDetermineGoodsAmount() {
        return notDetermineGoodsAmount;
    }

    public void setNotDetermineGoodsAmount(boolean notDetermineGoodsAmount) {
        this.notDetermineGoodsAmount = notDetermineGoodsAmount;
    }

    public Purchase223 getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase223 purchase) {
        this.purchase = purchase;
    }

    public boolean isQuantityUndefined() {
        return this.isNotDetermineGoodsAmount();
    }

    public void setQuantityUndefined(boolean quantityUndefined) {
        //this.setNotDetermineGoodsAmount(quantityUndefined);
    }

//    public Collection<String> getAddresses() {
//        return addresses;
//    }

//    public void setAddresses(Collection<String> addresses) {
//        this.addresses = addresses;
//    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
