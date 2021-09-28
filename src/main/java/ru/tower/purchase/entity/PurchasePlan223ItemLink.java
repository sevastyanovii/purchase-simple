package ru.tower.purchase.entity;

import javax.persistence.*;


/**
 * Связка позиции плана закупки и заявки
 * User: sevdokimov
 * Date: 14.09.2020
 * Time: 10:00
 */
@Entity
@Table(name = "purchase_plan_223_item_links")
public class PurchasePlan223ItemLink extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Основная связка
     */
    @Column
    private boolean main = false;

    /**
     * Позиция плана закупки
     */
    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne
    private PurchasePlan223Item item;

    /**
     * Потребность
     */
    @JoinColumn(name = "purchase_id", nullable = false)
    @ManyToOne
    private Purchase223 purchase;

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

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public PurchasePlan223Item getItem() {
        return item;
    }

    public void setItem(PurchasePlan223Item item) {
        this.item = item;
    }

    public Purchase223 getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase223 purchase) {
        this.purchase = purchase;
    }
}
