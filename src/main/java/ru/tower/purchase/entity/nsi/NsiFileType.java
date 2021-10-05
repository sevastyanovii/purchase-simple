package ru.tower.purchase.entity.nsi;

import ru.tower.purchase.entity.AbstractEntity;
import ru.tower.purchase.entity.Organization;

import javax.persistence.*;

/**
 * Типы файлов
 */
@Entity
@Table(name = "nsi_file_types",
       indexes = {@Index(columnList = "code", unique = true)})
public class NsiFileType extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование типа файла
     */
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    /**
     * Код типа файла
     */
    @Column(name = "code", length = 50, nullable = true, unique = true)
    private String code;

    /**
     * Признак актульности
     */
    @Column(name = "actual", nullable = false)
    private boolean actual;

//    /**
//     * Ассоциированные группы
//     */
//    @JoinTable(name = "nsi_file_types_to_nsi_file_type_groups")
//    private List<NsiFileTypeGroup> groups;

    /**
     * Признак видимости для пользователей
     */
    @Column(name = "visible", columnDefinition = "boolean default true", nullable = false)
    private boolean visible;

    /**
     * Добавление повторного значения в карточку
     */
    @Column(name = "repeatable", columnDefinition = "boolean default false", nullable = false)
    private boolean repeatable;

    /**
     * Редактируемый пользователем
     */
    @Column(name = "editable", columnDefinition = "boolean default true", nullable = false)
    private boolean editable;

    /**
     * Организация, которой принадлежит данный тип.
     * Если поле пустое, то тип общедоступен.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


    @Override
    public Object getEntityId() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

//    public List<NsiFileTypeGroup> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<NsiFileTypeGroup> groups) {
//        this.groups = groups;
//    }
//
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
