package ru.tower.purchase.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Позиция плана закупки
 * User: sevdokimov
 * Date: 14.09.2020
 * Time: 10:00
 */
@Entity
@Table(name = "purchase_plan_223_items")
public class PurchasePlan223Item extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * План закупки
     */
    @JoinColumn(name = "plan_id", nullable = false)
    @ManyToOne
    
    private PurchasePlan223 plan;

    /**
     * Номер позиции в плане закупке
     */
    @Column(name = "ordinal_number")
    private int ordinalNumber;

    /**
     * Связка с потребностями
     */
    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL}, orphanRemoval = true)
    
    private List<PurchasePlan223ItemLink> links = new ArrayList<>();

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

    public PurchasePlan223 getPlan() {
        return plan;
    }

    public void setPlan(PurchasePlan223 plan) {
        this.plan = plan;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public List<PurchasePlan223ItemLink> getLinks() {
        return links;
    }

    public void setLinks(List<PurchasePlan223ItemLink> links) {
        if(links != null && ! links.isEmpty()) {
            for(PurchasePlan223ItemLink link : links) {
                link.setItem(this);
            }
        }
        this.links = links;
    }
}
