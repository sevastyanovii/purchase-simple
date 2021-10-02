package ru.tower.json1c.parse;

import java.math.BigDecimal;

public class AmountYear {

    private Integer year;
    private String budget_section;
    private BigDecimal amount;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBudget_section() {
        return budget_section;
    }

    public void setBudget_section(String budget_section) {
        this.budget_section = budget_section;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
