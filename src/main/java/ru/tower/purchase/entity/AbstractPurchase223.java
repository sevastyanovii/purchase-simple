package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Абстракция заявки 223-ФЗ
 * User: sevdokimov
 * Date: 21.08.20
 * Time: 11:11
 */
@MappedSuperclass
public abstract class AbstractPurchase223<E extends AbstractEntity> extends CommonEntity<E> {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Пользователь, создавший карточку
     */
//    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
//    @ManyToOne
//    private User owner;

    /**
     * Дата изменения карточки
     */
//    @Version
//    @Column(name="update_date")
//    private Timestamp updateDate;

    /**
     * Дата создания карточки
     */
    @Column(name="creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Timestamp creationDate;

    /**
     * Статус
     */
    @JoinColumn(name = "nsiStatus", referencedColumnName = "id")
    @ManyToOne
    private NsiStatus nsiStatus;

    /**
     * Наименование предмета договора
     */
//    @JoinColumn(name = "purchases_description_id", referencedColumnName = "id")
//    @ManyToOne
//    private NsiPurchasesDescription purchasesDescription;

    /**
     * Полное наименование предмета договора
     */
    @Lob
    @Column(name = "additional_info")
    private String additionalInfo;

    /**
     * Минимально необходимые требования, предъявляемые к предмету закупки
     */
    @Lob
    @Column(name = "min_requirements")
    private String minRequirements;

    /**
     * Cпособ закупки
     */
//    @JoinColumn(name = "purchase_method", referencedColumnName = "id")
//    @ManyToOne
//    private NsiAstPurchaseType purchaseMethod;

    /**
     * Признак закупки малого объема
     */
//    @Enumerated(EnumType.STRING)
//    @Column(name = "small_volumes")
//    private SmallVolumes smallVolumes;

    /**
     * Закупка в электронной форме
     */
    @Column(name = "electronic", columnDefinition = "boolean default true", nullable = false)
    private boolean electronicPurchase;

    /**
     * Закупка, участниками которой являются только субъекты малого и среднего предпринимательства
     */
    @Column(name = "small_business", columnDefinition = "boolean default false", nullable = false)
    private boolean smallBusiness;

    /**
     * Закупка товаров, работ, услуг, удовлетворяющих критериям отнесения к инновационной продукции, высокотехнологичной продукции
     */
    @Column(name = "high_tech", columnDefinition = "boolean default false", nullable = false)
    private boolean highTech;

    /**
     * Срок размещения извещения (плановый)
     */
    @Temporal(TemporalType.DATE)
    @Column(name="contract_time")
    private Date contractTime;

    /**
     * Планируемый срок исполнения договора
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "contract_term")
    private Date contractTerm;

    /**
     * Начальная (максимальная) цена договора
     */
    @Column(name = "start_price", scale = 2, precision = 22)
    private BigDecimal startPrice;

    /**
     * c НДС
     */
    @Column(name = "with_vat", columnDefinition = "boolean default true")
    private boolean withVAT = true;

    /**
     * Начальная (максимальная) цена договора в рублевом эквиваленте
     */
    @Column(name = "start_price_rur", scale = 2, precision = 22)
    private BigDecimal startPriceRUR;

    /**
     * Курс валюты
     */
    @Column(name = "exchange_rate")
    private String exchangeRate;

    /**
     * Дата, на которую установлен курс валюты
     */
    @Temporal(TemporalType.DATE)
    @Column(name="exchange_rate_time")
    private Date exchangeRateTime;

    /**
     * Формула цены
     */
    @Column(name = "price_formula", length = 4000)
    private String priceFormula;

    /**
     * Способ указания начальной (максимальной) цены договора
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "nmck_instruction", length = 16, nullable = false)
    private NMCKInstructions nmckInstruction;

    @Override
    public NsiStatus getNsiStatus() {
        return nsiStatus;
    }

    public void setNsiStatus(NsiStatus nsiStatus) {
        this.nsiStatus = nsiStatus;
    }

    public Long getId() {
        return id;
    }

    public NMCKInstructions getNmckInstruction() {
        return nmckInstruction;
    }

    public void setNmckInstruction(NMCKInstructions nmckInstruction) {
        this.nmckInstruction = nmckInstruction;
    }
}
