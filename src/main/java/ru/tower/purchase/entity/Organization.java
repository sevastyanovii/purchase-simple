package ru.tower.purchase.entity;

//import ru.tower.purchase.entity.audit.Auditable;
//import ru.tower.purchase.entity.audit.OrganizationEventListener;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Организация из Сводного переченя заказчиков
 */
@Entity
@Table(name = "organizations"
//        ,
//       indexes = {@Index(columnList = "regNumber", unique = true)}
)
//@EntityListeners(OrganizationEventListener.class)
public class Organization extends AbstractEntity
//        implements Auditable
{

    @Id
    @Column(name = "org_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;

    /**
     * Реестровый номер в СПЗ
     * Если данное поле не заполнено, организация считается тестовой - созданной нами
     */
    @Column(name = "reg_number", length = 11, nullable = true)
    private String regNumber;

    /**
     * Сокращенное наименование
     */
    @Column(name = "short_name", length = 2000)
    private String shortName;

    @Column(name = "ast_id")
    private Long astId;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", length = 2000)
    private String fullName;

    /**
     * Код организации по перечню главных распорядителей бюджетных средств
     */
    @Column(name = "gbrs_code", length = 3)
    private String gbrsCode;

    /**
     * Фактический адрес
     */
//    @OneToOne(orphanRemoval = true)
//    @JoinColumn(name = "actual_address_id")
//    @CascadeOnDelete
//    private Address actualAddress;

    /**
     * Почтовый адрес
     */
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "postal_address")
    private String postalAddress;

    /**
     * Адрес электронной почты для получения системных уведомлений
     */
    @Column(name = "email", length = 256)
    private String email;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * Факс
     */
    @Column(name = "fax", length = 30)
    private String fax;

    @Override
    public Object getEntityId() {
        return getOrgId();
    }

    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getAstId() {
        return astId;
    }

    public void setAstId(Long astId) {
        this.astId = astId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGbrsCode() {
        return gbrsCode;
    }

    public void setGbrsCode(String gbrsCode) {
        this.gbrsCode = gbrsCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
