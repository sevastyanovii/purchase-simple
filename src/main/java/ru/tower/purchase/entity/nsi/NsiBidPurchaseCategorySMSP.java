package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Категория закупки
 */
@NamedQuery(name = "NsiBidPurchaseCategorySMSP.findByGuid", query = "from NsiBidPurchaseCategorySMSP n where n.guid = :guid")
@Entity
@Table(name = "nsi_bid_purchase_category_smsp")
public class NsiBidPurchaseCategorySMSP extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ast_id")
    private Long astId;

    @Column(columnDefinition = "boolean default true")
    private boolean actual;


    @Column(name = "code")
    private String code;

    @Column(name = "name", length = 2000)
    private String name;


    @Override
    public Object getEntityId() {
        return getId();
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
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
}
