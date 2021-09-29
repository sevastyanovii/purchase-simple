package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Общероссийский классификатор продукции по видам экономической деятельности ОК 034-2014 (ОКПД2)
 */
@Entity
@Table(name = "nsi_okpd2",
        indexes = {@Index(columnList = "code", unique = true)})
public class NsiOkpd2 extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Код товара, работы или услуги
     */
    @Column(name = "code", length = 12, unique = true, nullable = false)
    private String code;

    /**
     * Наименование товара, работы или услуги
     */
    @Column(name = "name", length = 2000)
    private String name;

    /**
     * Признак актуальности
     */
    @Column(name = "actual")
    private boolean actual;

    /**
     * Узел предка в иерархии
     */
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private NsiOkpd2 parentNsiOkpd2;

    /**
     * Комментарий
     */
    @Lob
    private String description;

    /**
     * id на Сбер А
     */
    @Column(name = "ast_id")
    private Long astId;


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

    public boolean isActual() {
        return actual;
    }
    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public NsiOkpd2 getParentNsiOkpd2() {
        return parentNsiOkpd2;
    }

    public void setParentNsiOkpd2(NsiOkpd2 parentNsiOkpd2) {
        this.parentNsiOkpd2 = parentNsiOkpd2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAstId() {
        return astId;
    }

    public void setAstId(Long astId) {
        this.astId = astId;
    }

}
