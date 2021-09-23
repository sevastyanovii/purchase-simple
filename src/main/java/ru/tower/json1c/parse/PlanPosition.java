package ru.tower.json1c.parse;

import com.google.gson.annotations.JsonAdapter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class PlanPosition {
    private String id_sbkr;
    private String number;

    @JsonAdapter(SimpleDateAdapter.class)
    private Date date;

    private Integer year_purchase;
    private String item_number;
    private Boolean purchase_no_place;
    private String position_status;
    private Division executing_division;

    private String subject_contract;
    private String requirements;
    private BigDecimal contract_amount;
    private Integer currency;
    private BigDecimal contract_amount_rub;
    private BigDecimal currency_exchange_rate;
    private Integer multiplicity;
    private Date course_date;
    private Date date_notification;
    private Date term_execution_contract;
    private String id_purchase_method;
    private Boolean electronic_form;
    private Boolean smp;
    private Boolean hightech_procurement;
    private Boolean no_monitoring_smp;
    private Boolean no_monitoring_budget;
    private Boolean unaccounted_purchase_1352;
    private String id_unaccounted_purchase;
    private String small_volume;
    private String application_number_sed;
    private String document_number_sed;

    private Collection<BudgetApplication> budget_applications;

    private Collection<Division> initiating_units;

    private Collection<ClassifierCode> classifier_codes;

    private Collection<PlanPositionFile> files;

    public String getId_sbkr() {
        return id_sbkr;
    }

    public void setId_sbkr(String id_sbkr) {
        this.id_sbkr = id_sbkr;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getYear_purchase() {
        return year_purchase;
    }

    public void setYear_purchase(Integer year_purchase) {
        this.year_purchase = year_purchase;
    }

    public String getItem_number() {
        return item_number;
    }

    public void setItem_number(String item_number) {
        this.item_number = item_number;
    }

    public Boolean getPurchase_no_place() {
        return purchase_no_place;
    }

    public void setPurchase_no_place(Boolean purchase_no_place) {
        this.purchase_no_place = purchase_no_place;
    }

    public String getPosition_status() {
        return position_status;
    }

    public void setPosition_status(String position_status) {
        this.position_status = position_status;
    }

    public Division getExecuting_division() {
        return executing_division;
    }

    public void setExecuting_division(Division executing_division) {
        this.executing_division = executing_division;
    }

    public String getSubject_contract() {
        return subject_contract;
    }

    public void setSubject_contract(String subject_contract) {
        this.subject_contract = subject_contract;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public BigDecimal getContract_amount() {
        return contract_amount;
    }

    public void setContract_amount(BigDecimal contract_amount) {
        this.contract_amount = contract_amount;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public BigDecimal getContract_amount_rub() {
        return contract_amount_rub;
    }

    public void setContract_amount_rub(BigDecimal contract_amount_rub) {
        this.contract_amount_rub = contract_amount_rub;
    }

    public BigDecimal getCurrency_exchange_rate() {
        return currency_exchange_rate;
    }

    public void setCurrency_exchange_rate(BigDecimal currency_exchange_rate) {
        this.currency_exchange_rate = currency_exchange_rate;
    }

    public Integer getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(Integer multiplicity) {
        this.multiplicity = multiplicity;
    }

    public Date getCourse_date() {
        return course_date;
    }

    public void setCourse_date(Date course_date) {
        this.course_date = course_date;
    }

    public Date getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(Date date_notification) {
        this.date_notification = date_notification;
    }

    public Date getTerm_execution_contract() {
        return term_execution_contract;
    }

    public void setTerm_execution_contract(Date term_execution_contract) {
        this.term_execution_contract = term_execution_contract;
    }

    public String getId_purchase_method() {
        return id_purchase_method;
    }

    public void setId_purchase_method(String id_purchase_method) {
        this.id_purchase_method = id_purchase_method;
    }

    public Boolean getSmp() {
        return smp;
    }

    public void setSmp(Boolean smp) {
        this.smp = smp;
    }

    public Boolean getHightech_procurement() {
        return hightech_procurement;
    }

    public void setHightech_procurement(Boolean hightech_procurement) {
        this.hightech_procurement = hightech_procurement;
    }

    public Boolean getNo_monitoring_smp() {
        return no_monitoring_smp;
    }

    public void setNo_monitoring_smp(Boolean no_monitoring_smp) {
        this.no_monitoring_smp = no_monitoring_smp;
    }

    public Boolean getNo_monitoring_budget() {
        return no_monitoring_budget;
    }

    public void setNo_monitoring_budget(Boolean no_monitoring_budget) {
        this.no_monitoring_budget = no_monitoring_budget;
    }

    public Boolean getUnaccounted_purchase_1352() {
        return unaccounted_purchase_1352;
    }

    public void setUnaccounted_purchase_1352(Boolean unaccounted_purchase_1352) {
        this.unaccounted_purchase_1352 = unaccounted_purchase_1352;
    }

    public String getId_unaccounted_purchase() {
        return id_unaccounted_purchase;
    }

    public void setId_unaccounted_purchase(String id_unaccounted_purchase) {
        this.id_unaccounted_purchase = id_unaccounted_purchase;
    }

    public String getSmall_volume() {
        return small_volume;
    }

    public void setSmall_volume(String small_volume) {
        this.small_volume = small_volume;
    }

    public String getApplication_number_sed() {
        return application_number_sed;
    }

    public void setApplication_number_sed(String application_number_sed) {
        this.application_number_sed = application_number_sed;
    }

    public String getDocument_number_sed() {
        return document_number_sed;
    }

    public Boolean getElectronic_form() {
        return electronic_form;
    }

    public void setElectronic_form(Boolean electronic_form) {
        this.electronic_form = electronic_form;
    }

    public void setDocument_number_sed(String document_number_sed) {
        this.document_number_sed = document_number_sed;
    }

    public Collection<BudgetApplication> getBudget_applications() {
        return budget_applications;
    }

    public void setBudget_applications(Collection<BudgetApplication> budget_applications) {
        this.budget_applications = budget_applications;
    }

    public Collection<Division> getInitiating_units() {
        return initiating_units;
    }

    public void setInitiating_units(Collection<Division> initiating_units) {
        this.initiating_units = initiating_units;
    }

    public Collection<ClassifierCode> getClassifier_codes() {
        return classifier_codes;
    }

    public void setClassifier_codes(Collection<ClassifierCode> classifier_codes) {
        this.classifier_codes = classifier_codes;
    }

    public Collection<PlanPositionFile> getFiles() {
        return files;
    }

    public void setFiles(Collection<PlanPositionFile> files) {
        this.files = files;
    }
}
