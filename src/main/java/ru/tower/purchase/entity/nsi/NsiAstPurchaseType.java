package ru.tower.purchase.entity.nsi;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * способы закупок
 */
@NamedQuery(name = "NsiAstPurchaseType.findPurchaseType", query = "from NsiAstPurchaseType where guid = :guid")
@Entity
@Table(name = "nsi_ast_purchase_type")
public class NsiAstPurchaseType extends NsiAstPurchaseTypeTreeAbstract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "purchase_type_id")
	private Long purchaseTypeId;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean electronic;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean smp;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean open;

	@ManyToMany(targetEntity = NsiAdditionalConditionType.class)
	@JoinTable(name = "nsi_ast_purchase_type_to_nsi_additional_condition_types", joinColumns = {
			@JoinColumn(name = "nsi_ast_purchase_type_id") }, inverseJoinColumns = {
					@JoinColumn(name = "nsi_additional_condition_types_id") })
	Collection<NsiAdditionalConditionType> nsiAdditionalConditionType;

	/**
	 * Идентификатор объекта ЕИС
	 */
	@Column(name = "eis_id")
	private Long eisId;

	/**
	 * Идентификатор объекта тестового ЕИС
	 */
	@Column(name = "test_eis_id")
	private Long testEisId;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean actual;

	/**
	 * Список ОКПД2 для применения ограничения и запрета
	 */
	@JoinTable(name = "nsi_ast_purchase_type_to_nsi_okpd2_new", joinColumns = @JoinColumn(name = "nsi_ast_purchase_type_id", table = "nsi_ast_purchase_type"), inverseJoinColumns = @JoinColumn(name = "nsi_okpd2_id", table = "nsi_okpd2"), indexes = {
			@Index(columnList = "nsi_ast_purchase_type_id") })
	@ManyToMany
	private List<NsiOkpd2> nsiOkpd2List;

	/**
	 * Список исключенний ОКПД2 для применения ограничения и запрета
	 */
	@JoinTable(name = "nsi_ast_purchase_type_to_nsi_okpd2_excluded", joinColumns = @JoinColumn(name = "nsi_ast_purchase_type_id", table = "nsi_ast_purchase_type"), inverseJoinColumns = @JoinColumn(name = "nsi_okpd2_id", table = "nsi_okpd2"), indexes = {
			@Index(columnList = "nsi_ast_purchase_type_id") })
	@ManyToMany
	private List<NsiOkpd2> nsiOkpd2ExcludedList;

	/**
	 * максимальная Начальная (максимальная) цена договора
	 */
	@Column(name = "max_start_price", scale = 2, precision = 22)
	private BigDecimal maxStartPrice;

	/**
	 * минимальная Начальная (максимальная) цена договора
	 */
	@Column(name = "min_start_price", scale = 2, precision = 22)
	private BigDecimal minStartPrice;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(Long purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public boolean isElectronic() {
		return electronic;
	}

	public void setElectronic(boolean electronic) {
		this.electronic = electronic;
	}

	public boolean isSmp() {
		return smp;
	}

	public void setSmp(boolean smp) {
		this.smp = smp;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Long getParentId() {
		return groupId;
	}

	public Long getTreeId() {
		return id;
	}

	public Long getEisId() {
		return eisId;
	}

	public void setEisId(Long eisId) {
		this.eisId = eisId;
	}

	public Long getTestEisId() {
		return testEisId;
	}

	public void setTestEisId(Long testEisId) {
		this.testEisId = testEisId;
	}

	public List<NsiOkpd2> getNsiOkpd2List() {
		return nsiOkpd2List;
	}

	public void setNsiOkpd2List(List<NsiOkpd2> nsiOkpd2List) {
		this.nsiOkpd2List = nsiOkpd2List;
	}

	public List<NsiOkpd2> getNsiOkpd2ExcludedList() {
		return nsiOkpd2ExcludedList;
	}

	public void setNsiOkpd2ExcludedList(List<NsiOkpd2> nsiOkpd2ExcludedList) {
		this.nsiOkpd2ExcludedList = nsiOkpd2ExcludedList;
	}

	public BigDecimal getMaxStartPrice() {
		return maxStartPrice;
	}

	public void setMaxStartPrice(BigDecimal maxStartPrice) {
		this.maxStartPrice = maxStartPrice;
	}

	public BigDecimal getMinStartPrice() {
		return minStartPrice;
	}

	public void setMinStartPrice(BigDecimal minStartPrice) {
		this.minStartPrice = minStartPrice;
	}

	public Collection<NsiAdditionalConditionType> getNsiAdditionalConditionType() {
		return nsiAdditionalConditionType;
	}

	public void setNsiAdditionalConditionType(Collection<NsiAdditionalConditionType> nsiAdditionalConditionType) {
		this.nsiAdditionalConditionType = nsiAdditionalConditionType;
	}

	public enum TYPE {
		/**
		 * Конкурс в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства
		 */
		CONTEST_MSP(
				"Конкурс в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства",
				1L, 3L, true, true, true, 200608L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Аукцион в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства
		 */
		AUCTION_MSP(
				"Аукцион в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства",
				1L, 8L, true, true, true, 200609L, new BigDecimal(0), new BigDecimal(0)),
		QUOTATION_REQUEST_MSP(
				"Запрос котировок в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства",
				1L, 9L, true, true, true, 200610L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос предложений в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства
		 */
		OFFER_REQUEST_MSP(
				"Запрос предложений в электронной форме, участниками которого могут являться только субъекты малого и среднего предпринимательства",
				1L, 10L, true, true, true, 200611L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Конкурс
		 */
		CONTEST("Конкурс", 2L, 11L, true, false, true, 4689L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Аукцион
		 */
		AUCTION("Аукцион", 2L, 12L, true, false, true, 200354L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос предложений
		 */
		OFFER_REQUEST("Запрос предложений", 2L, 13L, true, false, true, 200357L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос котировок
		 */
		QUOTATION_RREQUEST("Запрос котировок", 2L, 14L, true, false, true, 207091L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Конкурс (заявка из 2-х частей)
		 */
		CONTEST_TWO_PARTS("Конкурс (заявка из 2-х частей)", 3L, 15L, true, false, true, 200352L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Аукцион (заявка из 2-х частей)
		 */
		AUCTION_TWO_PARTS("Аукцион (заявка из 2-х частей)", 3L, 16L, true, false, true, 200354L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Запрос котировок (заявка из 2-х частей)
		 */
		QUOTATION_REQUEST_TWO_PARTS("Запрос котировок (заявка из 2-х частей)", 3L, 17L, true, false, true, 207091L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос предложений (заявка из 2-х частей)
		 */
		OFFER_REQUEST_TWO_PARTS("Запрос предложений (заявка из 2-х частей)", 3L, 18L, true, false, true, 200357L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый аукцион в электронной форме
		 */
		AUCTION_OPEN_ELECTRONIC("Открытый аукцион в электронной форме", 4L, 41L, true, false, false, 5621L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Аукцион с ограниченным участием в электронной форме
		 */
		AUCTION_LIMIT_ELECTRONIC("Аукцион с ограниченным участием в электронной форме", 4L, 42L, true, false, false,
				19879L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый конкурс в электронной форме
		 */
		CONTEST_OPEN_ELECTRONIC("Открытый конкурс в электронной форме", 5L, 43L, true, false, false, 5619L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Конкурс с ограниченным участием в электронной форме
		 */
		CONTEST_LIMIT_ELECTRONIC("Конкурс с ограниченным участием в электронной форме", 5L, 44L, true, false, false,
				19880L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый конкурс с возможностью подачи заявки в бумажной форме
		 */
		CONTEST_OPEN_PAPER("Открытый конкурс с возможностью подачи заявки в бумажной форме", 5L, 45L, false, false,
				false, 45201L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Конкурс с ограниченным участием с возможностью подачи заявки в бумажной форме
		 */
		CONTEST_LIMIT_PAPER("Конкурс с ограниченным участием с возможностью подачи заявки в бумажной форме", 5L, 46L,
				false, false, false, 45202L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос цен в электронной форме
		 */
		PRICE_REQUEST_OPEN_ELECTRONIC("Открытый запрос цен в электронной форме	", 6L, 47L, true, false, false, 5624L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос цен с ограниченным участием в электронной форме
		 */
		PRICE_REQUEST_LIMIT_ELECTRONIC("Запрос цен с ограниченным участием в электронной форме", 6L, 48L, true, false,
				false, 19881L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос цен с возможностью подачи заявки в бумажной форме
		 */
		PRICE_REQUEST_OPEN_PAPER("Открытый запрос цен с возможностью подачи заявки в бумажной форме", 6L, 49L, false,
				false, false, 45205L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос цен с ограниченным участием с возможностью подачи заявки в бумажной форме
		 */
		PRICE_REQUEST_LIMIT_PAPER("Запрос цен с ограниченным участием с возможностью подачи заявки в бумажной форме",
				6L, 50L, false, false, false, 45207L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос предложений в электронной форме
		 */
		OFFER_REQUEST_OPEN_ELECTRONIC("Открытый запрос предложений в электронной форме", 7L, 51L, true, false, false,
				5622L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос предложений с ограниченным участием в электронной форме
		 */
		OFFER_REQUEST_LIMIT_ELECTRONIC("Запрос предложений с ограниченным участием в электронной форме", 7L, 52L, true,
				false, false, 19882L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос предложений с возможностью подачи заявки в бумажной форме
		 */
		OFFER_REQUEST_OPEN_PAPER("Открытый запрос предложений с возможностью подачи заявки в бумажной форме", 7L, 53L,
				false, false, false, 45203L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос предложений с ограниченным участием с возможностью подачи заявки в бумажной форме
		 */
		OFFER_REQUEST_LIMIT_PAPER(
				"Запрос предложений с ограниченным участием с возможностью подачи заявки в бумажной форме", 7L, 54L,
				false, false, false, 45204L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос котировок в электронной форме
		 */
		QUOTATION_REQUEST_OPEN_ELECTRONIC("Открытый запрос котировок в электронной форме", 8L, 55L, true, false, false,
				34169L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос котировок с ограниченным участием в электронной форме
		 */
		QUOTATION_REQUEST_LIMIT_ELECTRONIC("Запрос котировок с ограниченным участием в электронной форме", 8L, 56L,
				true, false, false, 34170L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Открытый запрос котировок с возможностью подачи заявки в бумажной форме
		 */
		QUOTATION_REQUEST_OPEN_PAPER("Открытый запрос котировок с возможностью подачи заявки в бумажной форме", 8L, 57L,
				false, false, false, 45208L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Запрос котировок с ограниченным участием с возможностью подачи заявки в бумажной форме
		 */
		QUOTATION_REQUEST_LIMIT_PAPER(
				"Запрос котировок с ограниченным участием с возможностью подачи заявки в бумажной форме", 8L, 58L,
				false, false, false, 45209L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Квалификационный отбор в электронной форме
		 */
		QUALIFYING_SELECTION_ELECTRONIC("Квалификационный отбор в электронной форме", 8L, 59L, true, false, false,
				39000L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Квалификационный отбор в электронной форме
		 */
		QUALIFYING_SELECTION_PAPER("Квалификационный отбор с возможностью подачи заявки в бумажной форме", 8L, 60L,
				false, false, false, 39000L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Конкурентные переговоры
		 */
		CONCURRENT_NEGOTIATION("Конкурентные переговоры", 8L, 61L, true, false, true, 56231L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Закупка у единственного поставщика
		 */
		SINGLE_SUPPLIER_PURCHASE("Закупка у единственного поставщика", 8L, 62L, true, false, true, 33505L,
				new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Аукцион с двумя частями заявок
		 */
		AUCTION_TWO_PARTS_2("Аукцион с двумя частями заявок", 4L, 63L, true, false, true, 70870L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Редукцион
		 */
		REDUCTION("Редукцион", 4L, 64L, true, false, true, 70871L, new BigDecimal(0), new BigDecimal(0)),
		/**
		 * Аукцион в электронной форме
		 */
		AUCTION_ELECTRONIC("Аукцион в электронной форме", 4L, 68L, true, false, true, 56211L, new BigDecimal(0),
				new BigDecimal(0)),
		/**
		 * Квалификационный отбор
		 */
		QUALIFYING_SELECTION("Квалификационный отбор", 5L, 59L, true, false, true, 56229L, new BigDecimal(0),
				new BigDecimal(0));

		String typeName;
		Long groupID;
		Long purchaseTypeId;
		Boolean electronic;
		Boolean smp;
		Boolean actual;
		Long eisId;
		BigDecimal minStartPrice;
		BigDecimal maxStartPrice;

		private TYPE(String typeName, Long groupID, Long purchaseTypeId, Boolean electronic, Boolean smp,
				Boolean actual, Long eisId, BigDecimal minStartPrice, BigDecimal maxStartPrice) {
			this.typeName = typeName;
			this.groupID = groupID;
			this.purchaseTypeId = purchaseTypeId;
			this.electronic = electronic;
			this.smp = smp;
			this.actual = actual;
			this.eisId = eisId;
			this.maxStartPrice = maxStartPrice;
			this.minStartPrice = minStartPrice;
		}

		public String getTypeName() {
			return typeName;
		}

		public Long getGroupID() {
			return groupID;
		}

		public Long getPurchaseTypeId() {
			return purchaseTypeId;
		}

		public Boolean getElectronic() {
			return electronic;
		}

		public Boolean getSmp() {
			return smp;
		}

		public Boolean getActual() {
			return actual;
		}

		public Long getEisId() {
			return eisId;
		}

		public BigDecimal getMinStartPrice() {
			return minStartPrice;
		}

		public BigDecimal getMaxStartPrice() {
			return maxStartPrice;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public void setGroupID(Long groupID) {
			this.groupID = groupID;
		}

		public void setPurchaseTypeId(Long purchaseTypeId) {
			this.purchaseTypeId = purchaseTypeId;
		}

		public void setElectronic(Boolean electronic) {
			this.electronic = electronic;
		}

		public void setSmp(Boolean smp) {
			this.smp = smp;
		}

		public void setActual(Boolean actual) {
			this.actual = actual;
		}

		public void setEisId(Long eisId) {
			this.eisId = eisId;
		}

		public void setMinStartPrice(BigDecimal minStartPrice) {
			this.minStartPrice = minStartPrice;
		}

		public void setMaxStartPrice(BigDecimal maxStartPrice) {
			this.maxStartPrice = maxStartPrice;
		}
	}
}
