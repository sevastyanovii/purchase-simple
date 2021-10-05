package ru.tower.purchase.entity;

import ru.tower.purchase.entity.audit.Auditable;
import ru.tower.purchase.entity.nsi.NsiFileType;
import ru.tower.purchase.entity.nsi.NsiPublicationStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Файлы
 */
@Entity
@Table(name = "files")
@Inheritance(strategy= InheritanceType.JOINED)
@XmlRootElement
public class File extends VersionedEntity implements Auditable {

    @Override
    public Object getEntityId() {
        return getId();
    }

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type")
    private NsiFileType type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    /**
     * Пользователь, загрузивший файл
     */
//    private User user;

    /**
     * Заказывающая организация
     */
    @JoinColumn(name = "org_id", nullable = false)
    @ManyToOne
    private Organization organization;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "files_suppliers", inverseJoinColumns = {@JoinColumn(name = "supplier_id", referencedColumnName = "id"), @JoinColumn(name = "supplier_version", referencedColumnName = "version")})
//    private Collection<Supplier> commercialOffersSuppliers;

    @Column(name = "content_eis_id", length = 32)
    private String contentEisId;

    @Column(name = "ast_id")
    private String astId;

    @Column(name = "ast_hash")
    private String astHash;

    /**
     * Статус размещения информации
     */
    @ManyToOne
    @JoinColumn(name = "nsi_publication_status_id", nullable = false)
    private NsiPublicationStatus nsiPublicationStatus;

    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "files")
//    private Collection<CMIntegration> cmIntegrations;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NsiFileType getType() {
        return type;
    }

    public void setType(NsiFileType type) {
        this.type = type;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getContentEisId() {
        return contentEisId;
    }

    public void setContentEisId(String contentEisId) {
        this.contentEisId = contentEisId;
    }

    public NsiPublicationStatus getNsiPublicationStatus() {
        return nsiPublicationStatus;
    }

    public void setNsiPublicationStatus(NsiPublicationStatus nsiPublicationStatus) {
        this.nsiPublicationStatus = nsiPublicationStatus;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getAstId() {
        return astId;
    }

    public void setAstId(String astId) {
        this.astId = astId;
    }

//    public Collection<Supplier> getCommercialOffersSuppliers() {
//        return commercialOffersSuppliers;
//    }
//
//    public void setCommercialOffersSuppliers(Collection<Supplier> commercialOffersSuppliers) {
//        this.commercialOffersSuppliers = commercialOffersSuppliers;
//    }

    public String getAstHash() {
        return astHash;
    }

    public void setAstHash(String astHash) {
        this.astHash = astHash;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
//    @JsonIgnore
    public String getValueForAudit() {
        return getName();
    }
    //    @JsonIgnore
//    public Collection<CMIntegration> getCmIntegrations() {
//        return cmIntegrations;
//    }
}
