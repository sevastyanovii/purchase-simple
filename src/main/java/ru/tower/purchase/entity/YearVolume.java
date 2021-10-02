package ru.tower.purchase.entity;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 02.11.18
 * Time: 12:09
 */
@MappedSuperclass
public abstract class YearVolume extends AbstractEntity{

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false)
    private Purchase223 purchase;

    /**
     * Объем оплаты/Год
     */
    @Column(name = "year_price", scale = 2, precision = 22)
    private BigDecimal yearPrice;

    /**
     * Объем оплаты в рублевом эквиваленте/Год
     */
    @Column(name = "year_price_rur", scale = 2, precision = 22)
    private BigDecimal yearPriceRUR;

    /**
     * Год
     */
    @Column(name="year")
    private int year;

    @Override
    public Long getEntityId() {
        return getId();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getYearPrice() {
        return yearPrice;
    }

    public void setYearPrice(BigDecimal yearPrice) {
        this.yearPrice = yearPrice;
    }

    public BigDecimal getYearPriceRUR() {
        return yearPriceRUR;
    }

    public void setYearPriceRUR(BigDecimal yearPriceRUR) {
        this.yearPriceRUR = yearPriceRUR;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Purchase223 getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase223 purchase) {
        this.purchase = purchase;
    }
}
