package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotNull
    private Integer month;
    
    @Column(name = "MONTHNAME")
    @NotEmpty
    private String monthname;    

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

    @JsonIgnore
    @OneToMany(mappedBy = "monthlyAccounts", fetch = FetchType.LAZY)
    private List<Income> incomeList;

    @JsonIgnore
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
    
    public String getMonthname() {
		return monthname;
	}

	public void setMonthname(String monthname) {
		this.monthname = monthname;
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
