package ru.tower.purchase.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User: sevdokimov
 * Date: 10.05.16 15:59
 */
@MappedSuperclass
public abstract class CommonEntity<E extends AbstractEntity> extends AbstractProcessEntity {

    /**
     * Наименование таблицы БД
     * @return
     */
    public abstract String getTableName();

    public static enum VersionStatus {

        N("Новая"),
        P("Размещена"),
        C("Изменена"),
        A("Аннулирована")
        ;

        private String name;

        private VersionStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return name();
        }

        public static VersionStatus fromValue(String v) {
            return valueOf(v);
        }
    }

    /**
     * Текущая версия
     */
    @Version
    private int version;

    /**
     * Статус размещения
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "version_status", length = 1, columnDefinition="character varying default 'N'", nullable = false)
    private VersionStatus versionStatus = VersionStatus.N;

    /**
     * Время создания текущей версии
     */
    @Column(name="version_date", columnDefinition="timestamp default current_timestamp", insertable = false, updatable = false)
    private Timestamp versionDate;

    /**
     * Признак опубликованной версии сущности
     */
    @Column(name = "public_version", columnDefinition = "boolean default false", nullable = false)
    private boolean publicVersion = false;

    /**
     * Признак удаления или создания новой версии сущности
     */
    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    /**
     * Родитель для текущей версии
     */

    @ManyToOne
    @JoinColumn(name = "parent_version_id")
    private E parentVersion;

    /**
     * Ребенок для текущей версии
     */
    
    @OneToOne(mappedBy = "parentVersion")
    private E childVersion;

    /**
     * Заказывающая организация
     */
    @JoinColumn(name = "org_id", nullable = false)
    @ManyToOne
    private Organization organization;

    public abstract Long getId();

    public boolean isNewVersion() {
        return versionStatus == VersionStatus.N;
    }

    public boolean isPlacedVersion() {
        return versionStatus == VersionStatus.P;
    }

    public boolean isChangedVersion() {
        return versionStatus == VersionStatus.C;
    }

    public boolean isAnnuledVersion() {
        return versionStatus == VersionStatus.A;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Timestamp getVersionDate() {
        return versionDate;
    }

    public VersionStatus getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(VersionStatus versionStatus) {
        this.versionStatus = versionStatus;
    }

    public E getParentVersion() {
        return parentVersion;
    }

    public void setParentVersion(E parentVersion) {
        this.parentVersion = parentVersion;
    }

    public E getChildVersion() {
        return childVersion;
    }

    public void setChildVersion(E childVersion) {
        this.childVersion = childVersion;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isPublicVersion() {
        return publicVersion;
    }

    public void setPublicVersion(boolean publicVersion) {
        this.publicVersion = publicVersion;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    
    public String getCommonId(boolean extCondition) {
        StringBuffer sb = new StringBuffer();
        Organization org = getCommonOrg();
        // ID организации
        if(org != null) {
            sb.append(org.getOrgId());
        }
        // Буква
        sb.append(".").append(getCommonLetter(extCondition));
        //
        Object cardId = getEntityId();
        if(cardId instanceof Long) {
            sb.append(".").append(cardId);
        }
        return sb.toString();
    }

    
    protected abstract Organization getCommonOrg();

    
    protected abstract String getCommonLetter(boolean extCondition);

    
    public String getSystemEventDescription() {
        return "";
    }


}
