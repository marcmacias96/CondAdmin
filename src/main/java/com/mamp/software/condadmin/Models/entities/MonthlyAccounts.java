package com.mamp.software.condadmin.Models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "MONTHLYACCOUNTS")
public class MonthlyAccounts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMONTHCOUNTS")
    private Integer idmonthlyaccounts;

    @Column(name = "MONTH")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Calendar month;

    @Column(name = "INCOME",precision=8, scale = 2)
    @NotEmpty
    private float income;

    @Column(name = "EXPENSES",precision=8, scale = 2)
    @NotEmpty
    private float expenses;

    //Relations
    @JoinColumn(name = "IDANNUALCOUNTS", referencedColumnName = "IDANNUALCOUNTS")
    @ManyToOne
    private AnnualCounts annualCounts;

    @OneToMany(mappedBy = "monthlyAccounts", fetch = FetchType.LAZY)
    private List<Income> incomeList;

    @OneToMany(mappedBy = "monthlyAccounts", fetch = FetchType.LAZY)
    private List<Expenses> expensesList;

    /**/
    public MonthlyAccounts(){
        super();
    }

    private MonthlyAccounts(Integer idmonthlyaccounts){
        super();
        this.idmonthlyaccounts = idmonthlyaccounts;
    }

    /**/

    public Integer getIdmonthlyaccounts() {
        return idmonthlyaccounts;
    }

    public void setIdmonthlyaccounts(Integer idmonthlyaccounts) {
        this.idmonthlyaccounts = idmonthlyaccounts;
    }

    public Calendar getMonth() {
        return month;
    }

    public void setMonth(Calendar month) {
        this.month = month;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getExpenses() {
        return expenses;
    }

    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    public AnnualCounts getAnnualCounts() {
        return annualCounts;
    }

    public void setAnnualCounts(AnnualCounts annualCounts) {
        this.annualCounts = annualCounts;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }
}
