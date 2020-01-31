package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "EXPENSES")
public class Expenses implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEXPENSES")
    private Integer idexpenses;

    @Column(name = "VALUE",precision=8, scale = 2)
    private float value;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
    private Calendar date;


    //Relations
    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    @JoinColumn(name = "IDMONTHCOUNTS", referencedColumnName = "IDMONTHCOUNTS")
    @ManyToOne
    private MonthlyAccounts monthlyAccounts;

    @JoinColumn(name = "IDANNUALCOUNTS", referencedColumnName = "IDANNUALCOUNTS")
    @ManyToOne
    private AnnualCounts annualCounts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "IDEXPENSES")
    private List<ExpenseDetail> expenseDetailList;

    /**/
    public Expenses(){
        super();
    }

    public Expenses(Integer idexpenses) {
		super();
		this.idexpenses = idexpenses;
	}

    public List<ExpenseDetail> getExpenseDetailList() {
        return expenseDetailList;
    }

    public void setExpenseDetailList(List<ExpenseDetail> expenseDetailList) {
        this.expenseDetailList = expenseDetailList;
    }

    public Integer getIdexpenses() {
        return idexpenses;
    }

    public void setIdexpenses(Integer idexpenses) {
        this.idexpenses = idexpenses;
    }

    public float getValue() {
        float total =0;
        for (ExpenseDetail det : this.expenseDetailList) {
            total+=det.getValue();
        }
        return total;
    }

    public void setValue(float value) {
        this.value = value;
    }

   public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }

    public MonthlyAccounts getMonthlyAccounts() {
        return monthlyAccounts;
    }

    public void setMonthlyAccounts(MonthlyAccounts monthlyAccounts) {
        this.monthlyAccounts = monthlyAccounts;
    }


}

