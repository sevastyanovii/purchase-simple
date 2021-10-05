package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 *  Валюта
 */
@NamedQuery(name = "NsiAstCurrency.findByCode", query = "from NsiAstCurrency c where c.code = :code and actual = true")
@Entity
@Table(name = "nsi_ast_currency")
public class NsiAstCurrency extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ast_id")
    private Long astId;

    @Column(name = "sort_order")
    private Long sortOrder;


    @Column(name = "code")
    private String code;

    @Column(name = "name", length = 2000)
    private String name;

    @Column(columnDefinition = "boolean default true")
    private boolean actual;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAstId() {
        return astId;
    }

    public void setAstId(Long astId) {
        this.astId = astId;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
}
