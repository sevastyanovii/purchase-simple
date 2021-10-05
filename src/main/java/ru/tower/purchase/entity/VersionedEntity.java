package ru.tower.purchase.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class VersionedEntity extends AbstractEntity {

    @EmbeddedId
    private VersionId id = new VersionId();


    @Override
    public Object getEntityId() {
        return getId().getId();
    }

    public VersionId getId() {
        return id;
    }

    public void setId(VersionId id) {
        this.id = id;
    }
}
