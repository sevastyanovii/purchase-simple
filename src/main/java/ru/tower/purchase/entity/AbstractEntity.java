package ru.tower.purchase.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Бизнес-сущность
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Глобальный идентификатор
     * [a-fA-F0-9]{8}\-[a-fA-F0-9]{4}\-[a-fA-F0-9]{4}\-[a-fA-F0-9]{4}\-[a-fA-F0-9]{12}
     */
    @Column(length = 36)
    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return идентификатор сущности
     */
    public abstract Object getEntityId();

    /**
     * Пустой метод для обеспечения работоспособности json-маппингов
     */
    public void setEntityId(Object id) {}

    @Override
    public int hashCode() {
        return (getEntityId() != null ? getEntityId().hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        //null или несовпадение классов
        if (object == null || !this.getClass().equals(object.getClass())) {
            return false;
        }
        Object thisId = this.getEntityId();
        Object otherId = ((AbstractEntity) object).getEntityId();
        //в случае неперсистентных объектов, сравниваем их как ссылки
        if (thisId == null && otherId == null) {
            return super.equals(object);
        }
        //в остальных случаях сравниваем идентификаторы
        return thisId != null && thisId.equals(otherId);
    }

    @Override
    public String toString() {
        return getClass()+"["+getEntityId()+"]";
    }
}
