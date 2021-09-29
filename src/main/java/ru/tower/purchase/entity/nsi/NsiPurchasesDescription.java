package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Наименование закупки
 */
@Table(name = "nsi_purchases_description")
@Entity
public class NsiPurchasesDescription extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean actual;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
}
