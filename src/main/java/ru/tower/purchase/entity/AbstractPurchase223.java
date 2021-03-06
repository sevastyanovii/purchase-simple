package ru.tower.purchase.entity;

import ru.tower.purchase.entity.nsi.NsiAstCurrency;
import ru.tower.purchase.entity.nsi.NsiAstPurchaseType;
import ru.tower.purchase.entity.nsi.NsiPurchasesDescription;
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
    @ManyToOne
    @JoinColumn(name = "purchases_description_id", referencedColumnName = "id")
    private NsiPurchasesDescription purchasesDescription;

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
    @JoinColumn(name = "purchase_method", referencedColumnName = "id")
    @ManyToOne
    private NsiAstPurchaseType purchaseMethod;

    /**
     * Признак закупки малого объема
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "small_volumes")
    private SmallVolumes smallVolumes;

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
    private Boolean highTech;

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

    /**
     * Валюта Сбербанк
     */
    @JoinColumn(name = "ast_currency_id", referencedColumnName = "id")
    @ManyToOne
    private NsiAstCurrency astCurrency;

    /**
     * Адрес региона поставки, если общий для всех позиций
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliv_place_templ_id", referencedColumnName = "id")
    private DeliveryPlace223Templ deliveryPlace223Templ;

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

    public NsiPurchasesDescription getPurchasesDescription() {
        return purchasesDescription;
    }

    public void setPurchasesDescription(NsiPurchasesDescription purchasesDescription) {
        this.purchasesDescription = purchasesDescription;
    }

    public String getMinRequirements() {
        return minRequirements;
    }

    public void setMinRequirements(String minRequirements) {
        this.minRequirements = minRequirements;
    }

    public NsiAstPurchaseType getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(NsiAstPurchaseType purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public SmallVolumes getSmallVolumes() {
        return smallVolumes;
    }

    public void setSmallVolumes(SmallVolumes smallVolumes) {
        this.smallVolumes = smallVolumes;
    }

    public boolean isHighTech() {
        return highTech;
    }

    public void setHighTech(boolean highTech) {
        this.highTech = highTech;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Date getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Date contractTerm) {
        this.contractTerm = contractTerm;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getStartPriceRUR() {
        return startPriceRUR;
    }

    public void setStartPriceRUR(BigDecimal startPriceRUR) {
        this.startPriceRUR = startPriceRUR;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public NsiAstCurrency getAstCurrency() {
        return astCurrency;
    }

    public void setAstCurrency(NsiAstCurrency astCurrency) {
        this.astCurrency = astCurrency;
    }

    public Date getExchangeRateTime() {
        return exchangeRateTime;
    }

    public void setExchangeRateTime(Date exchangeRateTime) {
        this.exchangeRateTime = exchangeRateTime;
    }

    public DeliveryPlace223Templ getDeliveryPlace223Templ() {
        return deliveryPlace223Templ;
    }

    public void setDeliveryPlace223Templ(DeliveryPlace223Templ deliveryPlace223Templ) {
        this.deliveryPlace223Templ = deliveryPlace223Templ;
    }
}
