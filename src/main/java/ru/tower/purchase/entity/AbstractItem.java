package ru.tower.purchase.entity;

//import org.apache.commons.lang3.StringUtils;
import ru.tower.purchase.entity.nsi.NsiOkei;
import ru.tower.purchase.entity.nsi.NsiOkpd2;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Базовый класс для позиций закупки
 */
@MappedSuperclass
public abstract class AbstractItem extends AbstractEntity {

    /**
     * Номер по порядку
     */
    @Column(name = "position")
    private int position;

    /**
     * Наименование позиции закупки
     */
    //@Column(length = 2000, nullable = false)
    @Column(length = 2000, nullable = true)
    private String name;

    /**
     * Международное непатентованное наименование (химическое, группировочное наименование) лекарственного средства
     */
    @Column(name = "international_name", length = 2000)
    private String internationalName;

    /**
     * ОКПД
     */
//    @JoinColumn(name = "nsi_okpd_id")
//    @ManyToOne
//    private NsiOkpd nsiOkpd;

    /**
     * ОКПД 2
     */
    @JoinColumn(name = "nsi_okpd2_id")
    @ManyToOne
    private NsiOkpd2 nsiOkpd2;

    /**
     * СПГЗ
     */
//    @Deprecated
//    @JoinColumn(name = "nsi_spgz_id")
//    @ManyToOne
//    private NsiSPGZ nsiSPGZ;

    /**
     * СПГЗ (Версия ЕАИСТ)
     */
//    @JoinColumn(name = "nsi_spgz2_id")
//    @ManyToOne
//    private NsiSpgz2 nsiSpgz2;

    /**
     * Количество поставляемого товара, объёма выполняемых работ, оказываемых услуг (всего)
     */
    @Column(name = "quantity")
    private Double quantity;

    /**
     * Единицы измерения
     */
    @JoinColumn(name = "nsi_okei_id", nullable = false)
    @ManyToOne
    private NsiOkei nsiOkei;

    /**
     * Цена за единицу в валюте контракта
     */
    @Column(name = "price", scale = 2, precision = 19, nullable = false)
    private BigDecimal price;

    /**
     * Минимальные требования, предъявляемые к объекту закупки
     */
    @Lob
    @Column(name = "requirements")
    private String requirements;

    /**
     * Цена за единицу в рублевом эквиваленте
     */
    @Column(name = "price_rur", scale = 2, precision = 19)
    private BigDecimal priceRUR;

    /**
     * Стоимость в валюте контракта
     */
    @Column(name = "cost", scale = 2, precision = 19)
    private BigDecimal cost;

    /**
     * Стоимость в рублевом эквиваленте
     */
    @Column(name = "cost_rur", scale = 2, precision = 19)
    private BigDecimal costRUR;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

//    public NsiOkpd getNsiOkpd() {
//        return nsiOkpd;
//    }
//
//    public void setNsiOkpd(NsiOkpd nsiOkpd) {
//        this.nsiOkpd = nsiOkpd;
//    }

    public NsiOkpd2 getNsiOkpd2() {
        return nsiOkpd2;
    }

    public void setNsiOkpd2(NsiOkpd2 nsiOkpd2) {
        this.nsiOkpd2 = nsiOkpd2;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public NsiOkei getNsiOkei() {
        return nsiOkei;
    }

    public void setNsiOkei(NsiOkei nsiOkei) {
        this.nsiOkei = nsiOkei;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public BigDecimal getPriceRUR() {
        return priceRUR;
    }

    public void setPriceRUR(BigDecimal priceRUR) {
        this.priceRUR = priceRUR;
    }

    public BigDecimal getCost() {
        if(!isQuantityUndefined()) {
            return getAutoCost();
        }
        return cost;
    }

    public void setCost(BigDecimal cost) {
        if(cost == null) {
            cost = getAutoCost();
        }
        this.cost = cost;
    }

    public BigDecimal getCostRUR() {
        if(!isQuantityUndefined()) {
            return getAutoCostRUR();
        }
        return costRUR;
    }

    public void setCostRUR(BigDecimal costRUR) {
        if(costRUR == null) {
            costRUR = getAutoCostRUR();
        }
        this.costRUR = costRUR;
    }


    /**
     * @return расчетная стоимость позиции закупки в валюте контракта
     */
    public BigDecimal getAutoCost() {
        return (getPrice() != null && !isQuantityUndefined() && getQuantity()!=null ? getPrice().multiply(BigDecimal.valueOf(getQuantity())) : new BigDecimal(0));
    }

    public void setAutoCost(BigDecimal cost){
        //заглушка
    }

    /**
     * @return стоимость позиции закупки в рублевом эквиваленте
     */
    public BigDecimal getAutoCostRUR() {
        return (priceRUR != null && !isQuantityUndefined() && getQuantity()!=null ? priceRUR.multiply(BigDecimal.valueOf(getQuantity())) : new BigDecimal(0));
    }

    public void setAutoCostRUR(BigDecimal cost){
        //заглушка
    }

    /**
     * @return невозможно определить количество товара, объем подлежащих выполнению работ, оказанию услуг
     */
    public boolean isQuantityUndefined() {
        return getQuantity() == null||getQuantity() == 0;
    }

    public void setQuantityUndefined(boolean quantityUndefined) {
        //заглушка
    }

/*
    public boolean isHasInternationalName() {
        return StringUtils.isNotEmpty(internationalName);
    }

    public void setHasInternationalName(boolean hasInternationalName) {
        //заглушка
    }

    public NsiSPGZ getNsiSPGZ() {
        return nsiSPGZ;
    }

    public void setNsiSPGZ(NsiSPGZ nsiSPGZ) {
        this.nsiSPGZ = nsiSPGZ;
    }

    public NsiSpgz2 getNsiSpgz2() {
        return nsiSpgz2;
    }

    public void setNsiSpgz2(NsiSpgz2 nsiSpgz2) {
        this.nsiSpgz2 = nsiSpgz2;
    }
*/
}
