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

}
