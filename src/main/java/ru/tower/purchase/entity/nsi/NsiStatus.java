package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Статус
 */
@Table(name = "nsi_statuses")
@Entity
public class NsiStatus extends AbstractEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "boolean default true")
    private boolean fz44 = true;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "short_name", length = 255)
    private String shortName;

    @Column(columnDefinition = "boolean default true")
    private boolean fz223 = true;

    @Size(max = 255)
    @Column(name = "name223", length = 255)
    private String name223;

    @Size(max = 255)
    @Column(name = "short_name223", length = 255)
    private String shortName223;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean actual = true;


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

    public boolean isFz44() {
        return fz44;
    }

    public void setFz44(boolean fz44) {
        this.fz44 = fz44;
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

    public boolean isFz223() {
        return fz223;
    }

    public void setFz223(boolean fz223) {
        this.fz223 = fz223;
    }

    public String getName223() {
        return name223;
    }

    public void setName223(String name223) {
        this.name223 = name223;
    }

    public String getShortName223() {
        return shortName223;
    }

    public void setShortName223(String shortName223) {
        this.shortName223 = shortName223;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
