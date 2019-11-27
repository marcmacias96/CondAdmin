package com.mamp.software.condadmin.Models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "ANNUALCOUNTS")
public class AnnualCounts implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDANNUALCOUNTS")
    private Integer Id;

    @Column(name = "YEAR")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar year;

    @Column(name = "INCOME",precision=8, scale = 2)
    private float income;

    @Column(name = "EXPENSES",precision=8, scale = 2)
    private float expenses;

    //Relations
    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    @OneToMany(mappedBy = "annualCounts", fetch = FetchType.LAZY)
    private List<MonthlyAccounts> monthlyAccountsList;

    public AnnualCounts(){
        super();
    }

    private AnnualCounts(Integer id){
        super();
        this.Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Calendar getYear() {
        return year;
    }

    public void setYear(Calendar year) {
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
}
