package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * ОКЕИ
 */
@NamedQuery(name = "NsiOkei.findByCode", query = "from NsiOkei o where code = :code and o.actual = true")
@Entity
@Table(name = "nsi_okei")
public class NsiOkei extends AbstractEntity  {

    @Id
    @Column(name = "ed_izm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edIzmId;

    @Column(length = 4, unique = false)
    private String code;

    @Column(length = 1024, name = "full_name")
    private String fullName;

    @Column(name = "local_name")
    private String localName;

    @Column(name = "international_name")
    private String internationalName;

    @Column(name = "local_symbol")
    private String localSymbol;

    @Column(name = "international_symbol")
    private String internationalSymbol;

    private boolean actual;

//    @JoinColumn(name = "section_id", referencedColumnName = "id")
//    @ManyToOne
//
//    NsiOkeiSection nsiOkeiSection;
//
//    @JoinColumn(name = "group_id", referencedColumnName = "id")
//    @ManyToOne
//
//    NsiOkeiGroup nsiOkeiGroup;


    /**
     * id на Сбер А
     */
    @Column(name = "ast_id")
    private Long astId;


    @Override
    public Long getEntityId() {
        return getEdIzmId();
    }

    public Long getEdIzmId() {
        return edIzmId;
    }
    public void setEdIzmId(Long edIzmId) {
        this.edIzmId = edIzmId;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getInternationalName() {
        return internationalName;
    }
    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public String getLocalSymbol() {
        return localSymbol;
    }
    public void setLocalSymbol(String localSymbol) {
        this.localSymbol = localSymbol;
    }

    public String getInternationalSymbol() {
        return internationalSymbol;
    }
    public void setInternationalSymbol(String internationalSymbol) {
        this.internationalSymbol = internationalSymbol;
    }

    public boolean isActual() {
        return actual;
    }
    public void setActual(boolean actual) {
        this.actual = actual;
    }

/*
    public NsiOkeiSection getNsiOkeiSection() {
        return nsiOkeiSection;
    }
    public void setNsiOkeiSection(NsiOkeiSection nsiOkeiSection) {
        this.nsiOkeiSection = nsiOkeiSection;
    }

    public NsiOkeiGroup getNsiOkeiGroup() {
        return nsiOkeiGroup;
    }
    public void setNsiOkeiGroup(NsiOkeiGroup nsiOkeiGroup) {
        this.nsiOkeiGroup = nsiOkeiGroup;
    }

    public Long getAstId() {
        return astId;
    }

    public void setAstId(Long astId) {
        this.astId = astId;
    }

    @Override
    
    public String getValueForAudit() {
        return getFullName();
    }
*/
}
