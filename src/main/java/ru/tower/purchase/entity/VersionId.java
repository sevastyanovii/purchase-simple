package ru.tower.purchase.entity;

/**
 * User: sevdokimov
 * Date: 21.10.15
 */

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Идентификатор для сущностей с версионностью
 */
@Embeddable
public class VersionId implements Serializable {

    /**
     * Если id == null, то EclipseLink сгенерирует новый на основе таблицы sequence.
     * Если id != null, то он и будет использован, что позволит менять версию у версионной сущности, сохраняя id.
     */
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private int version;

    public VersionId() {
    }

    public VersionId(Long id, int version) {
        this.id = id;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VersionId versionId = (VersionId) o;

        if(id==null&&versionId.id==null){
           return version == versionId.version;
        }else{
            if(id==null||versionId.id==null){
                return false;
            }
        }
        return !((long)id != versionId.id || version != versionId.version);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + version;
        return result;
    }

    @Override
    public String toString() {
        return id+" "+version;
    }
}
