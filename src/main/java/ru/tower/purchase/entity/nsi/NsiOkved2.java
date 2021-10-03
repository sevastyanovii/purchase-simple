package ru.tower.purchase.entity.nsi;

import org.hibernate.annotations.Type;
import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Общероссийский классификатор видов экономической деятельности ОК 029-2014 (ОКВЭД2)
 */
@NamedQuery(name = "NsiOkved2.findByCode", query = "from NsiOkved2 o where o.code = :code and o.actual = true")
@Entity
@Table(name = "nsi_okved2",
       indexes = {@Index(columnList = "code", unique = true)})
public class NsiOkved2 extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Код
     */
    @Column(name = "code", length = 10, unique = true, nullable = false)
    private String code;

    /**
     * Код раздела
     */
    @Column(name = "section", length = 1)
    private String section;

    /**
     * Наименование
     */
    @Column(name = "name", length = 500, nullable = false)
    private String name;


    /**
     * id на Сбер А
     */
    @Column(name = "ast_id")
    private Long astId;

    /**
     * Признак актуальности
     */
    @Column(name = "actual", nullable = false)
    private boolean actual;

    /**
     * Узел предка в иерархии
     */
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private NsiOkved2 parentNsiOkved2;

    /**
     * Комментарий
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;


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

    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
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

    public NsiOkved2 getParentNsiOkved2() {
        return parentNsiOkved2;
    }

    public void setParentNsiOkved2(NsiOkved2 parentNsiOkved2) {
        this.parentNsiOkved2 = parentNsiOkved2;
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
