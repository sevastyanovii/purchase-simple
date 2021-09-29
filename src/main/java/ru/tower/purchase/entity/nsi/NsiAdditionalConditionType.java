package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Элемент справочника "Дополнительные условия закупки"
 */
@Entity
@Table(name = "nsi_additional_condition_types")
public class NsiAdditionalConditionType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean actual = true;

    @ManyToMany(targetEntity = NsiAstPurchaseType.class, mappedBy = "nsiAdditionalConditionType")
    public List<NsiAstPurchaseType> nsiAstPurchaseType = new ArrayList<>();

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

    public List<NsiAstPurchaseType> getNsiAstPurchaseType() {
        return nsiAstPurchaseType;
    }

    public void setNsiAstPurchaseType(List<NsiAstPurchaseType> nsiAstPurchaseType) {
        this.nsiAstPurchaseType = nsiAstPurchaseType;
    }
}
