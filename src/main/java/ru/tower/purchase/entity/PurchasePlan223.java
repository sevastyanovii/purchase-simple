package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * План (план-график) закупок
 */
@Entity
@Table(name = PurchasePlan223.TABLE_NAME)
public class PurchasePlan223 extends CommonEntity<PurchasePlan223> {

    public static final String TABLE_NAME = "purchase_plans_223";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Тип плана
     */
    public static enum Type {
        DRAFT, // черновик
        PLAN, // план закупок
//        SCHEDULE //план-график
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToMany(mappedBy = "purchasePlan223")
//
//    private Collection<Plan223Process> businessProcesses;

    /**
     * Уберем после перехода на новый механизм ПЗ, не использовать
     */
    @Deprecated
    @ManyToMany
    @JoinTable(name = "purchase_plans_223_to_purchases_223")
    @OrderColumn(name = "ordinal_number")
    private List<Purchase223> purchases = new ArrayList<>();

    /**
     * Позиции плана закупки
     */
    @OneToMany(mappedBy = "plan", cascade = {CascadeType.ALL}, orphanRemoval = true)
 //   @OrderColumn(name = "ordinal_number")

    private List<PurchasePlan223Item> items = new ArrayList<>();

    /**
     * Идентификатор плана-графика на ООС, на ООС - <id>
     */
    @Column(name = "id_eis")
    private Long idEIS;

    /**
     * Реестровый номер плана-графика, на ООС - <planNumber>
     */
    @Column(name = "number_eis", length = 10)
    private String numberEIS;

    @ManyToOne
    @JoinColumn(name = "nsiStatus", referencedColumnName = "id")
    private NsiStatus nsiStatus;

    /**
     * Финансовый год
     */
    @Column(nullable = false)
    private int year;

   /**
    * Время создания первой версии
    */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "creation_date")
   private Date creationDate;

    /**
     * Тип плана
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 8, nullable = false)
    private Type type;

    /**
     * Год начала планового периода
     */
    @Column(name = "period_year_start")
    private Integer periodYearStart;

    /**
     * Год конца планового периода
     */
    @Column(name = "period_year_end")
    private Integer periodYearEnd;

    /**
     * Порядковый номер публикации, null, если не опубликован
     */
    @Column(name = "publication_count")
    private Integer publicationCount;

    /**
     * Дополнительная информация
     */
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    /**
     * Обоснование внесения изменений
     */
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "modification_description")
    private String modificationDescription;

//    @JoinColumn(name = "approver_id", referencedColumnName = "user_id")
//    @ManyToOne
//    private User approver;

//    @JoinColumn(name = "executor_id", referencedColumnName = "user_id")
//    @ManyToOne
//    private User executor;

    @Temporal(TemporalType.DATE)
    @Column(name = "approval_date")
    //Дата утверждения
    private Date approvalDate;

   @Temporal(TemporalType.DATE)
   @Column(name = "publication_date")
   //Дата публикации
   private Date publicationDate;

    /**
     * Связанные документы в СЭДО
     */
   @ElementCollection
   @CollectionTable(name = "purchase_plans_to_sedo_documents")
   private List<Long> sedoDocumentIds;

   @Column(name = "sum_ht_prev")
   private BigDecimal sumHighTechPrev;

   @Column(name = "sum_smp_ht_prev")
   private BigDecimal sumSmallAndMiddleHighTechPrev;

    /**
     * Интеграция с КомпаниМедиа
     */
//    @OneToMany(mappedBy = "purchasePlan223", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    
//    @OrderColumn
//    private List<CMIntegration> cmIntegrations;

    /**
     * Интеграция с СЭД
     */
    @Column(name = "sed_guid", length = 255)
    private String sedGuid;

    /**
     * Документы
     */
//    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
//    @JoinTable(name = "purchase_plan_223_to_files", inverseJoinColumns = {@JoinColumn(name = "file_id", referencedColumnName = "id"), @JoinColumn(name = "file_version", referencedColumnName = "version")})
//    private Collection<File> files;

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

//    public Collection<Plan223Process> getBusinessProcesses() {
//        return businessProcesses;
//    }
//
//    public void setBusinessProcesses(Collection<Plan223Process> businessProcesses) {
//        this.businessProcesses = businessProcesses;
//    }

    public Long getIdEIS() {
        return idEIS;
    }

    public void setIdEIS(Long idEIS) {
        this.idEIS = idEIS;
    }

    public String getNumberEIS() {
        return numberEIS;
    }

    public void setNumberEIS(String numberEIS) {
        this.numberEIS = numberEIS;
    }

    public NsiStatus getNsiStatus() {
        return nsiStatus;
    }
    /**
     * Устанавливает статус плана.
     * Deprecated, т.к. при установке статуса необходимо обновлять его у всех включенных заявок
//     * @see ru.tower.purchase.facade.PurchasePlanFacade#setStatus(ru.tower.purchase.entity.PurchasePlan, ru.tower.purchase.registry.StatusRegistry)
//     * @see ru.tower.purchase.util.StatusChangeHelper#setStatus(ru.tower.purchase.registry.StatusRegistry, ru.tower.purchase.entity.PurchasePlan)
     *
     * @param nsiStatus статус
     */
    public void setNsiStatus(NsiStatus nsiStatus) {
        this.nsiStatus = (NsiStatus)nsiStatus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPeriodYearStart() {
        return periodYearStart;
    }

    public void setPeriodYearStart(Integer periodYearStart) {
        this.periodYearStart = periodYearStart;
    }

    public Integer getPeriodYearEnd() {
        return periodYearEnd;
    }

    public void setPeriodYearEnd(Integer periodYearEnd) {
        this.periodYearEnd = periodYearEnd;
    }

    public Integer getPublicationCount() {
        return publicationCount;
    }

    public void setPublicationCount(Integer publicationCount) {
        this.publicationCount = publicationCount;
    }


//    public User getApprover() {
//        return approver;
//    }

//    public void setApprover(User approver) {
//        this.approver = approver;
//    }
//
//    public User getExecutor() {
//        return executor;
//    }
//
//    public void setExecutor(User executor) {
//        this.executor = executor;
//    }

    @Override
    protected Organization getCommonOrg() {
        return getOrganization();
    }

    @Override
    protected String getCommonLetter(boolean extCondition) {
        return "PP223";
    }

//     public List<Purchase223> getPurchases() {
//         return purchases;
//     }

//     public void setPurchases(List<Purchase223> purchases) {
//         this.purchases = purchases;
//     }

//    @Override
//    public List<CMIntegration> getCmIntegrations() {
//        return cmIntegrations;
//    }
//
//    @Override
//    public void setCmIntegrations(List<CMIntegration> cmIntegrations) {
//        this.cmIntegrations = cmIntegrations;
//    }
//
//    public Collection<File> getFiles() {
//        return files;
//    }
//
//    public void setFiles(Collection<File> files) {
//        this.files = files;
//    }

//    public String getSedGuid() {
//        return sedGuid;
//    }
//
//    public void setSedGuid(String sedGuid) {
//        this.sedGuid = sedGuid;
//    }
//
//    @Override
//    protected Organization getCommonOrg() {
//        return getOrganization();
//    }
//
//    @Override
//    protected String getCommonLetter(boolean extCondition) {
//        return "PP223";
//    }
}
