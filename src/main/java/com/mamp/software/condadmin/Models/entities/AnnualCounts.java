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
@Table(name = "ANNUALCOUNTS")
public class AnnualCounts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDANNUALCOUNTS")
    private Integer idannualcounts;

    @Column(name = "YEAR")
    @NotNull
    private Integer year;

    @Column(name = "INCOME", precision=8, scale = 2)
    private float income;

    @Column(name = "EXPENSES", precision=8, scale = 2)
    private float expenses;

    //Relations
    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    @JsonIgnore
    @OneToMany(mappedBy = "annualCounts", fetch = FetchType.LAZY)
    private List<MonthlyAccounts> monthlyAccountsList;

    /**/
    public AnnualCounts(){
        super();
    }

    public AnnualCounts(Integer idannualcounts) {
		super();
		this.idannualcounts = idannualcounts;
	}



	/**/
	public Integer getIdannualcounts() {
		return idannualcounts;
	}

	public void setIdannualcounts(Integer idannualcounts) {
		this.idannualcounts = idannualcounts;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Condominium getCondominium() {
		return condominium;
	}

	public void setCondominium(Condominium condominium) {
		this.condominium = condominium;
	}

	public List<MonthlyAccounts> getMonthlyAccountsList() {
		return monthlyAccountsList;
	}

	public void setMonthlyAccountsList(List<MonthlyAccounts> monthlyAccountsList) {
		this.monthlyAccountsList = monthlyAccountsList;
	}
}
