package com.mamp.software.condadmin.Models.entities;

import java.io.Serializable;

public class RepIncVsExp implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer month;
    private Double incomes;
    private Double expenses;

    public RepIncVsExp(Integer month, Double incomes, Double expenses) {
        this.month = month;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public Double getIncomes() {
        return incomes;
    }

    public void setIncomes(Double incomes) {
        this.incomes = incomes;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
