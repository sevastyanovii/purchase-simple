package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: AnDrunkYeti
 * Date: 17.10.18
 * Time: 16:28
 */
@Entity
@Table(name = "nsi_federal_district")
/**
 * Справочник ОКЭР по сути
 */
public class NsiFederalDistrict extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "code", length = 11)
    private String code;

    @Column(name = "okato", length = 2000)
    private String okato;

    @Column(name = "short_name", length = 100)
    private String shortName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }
}
