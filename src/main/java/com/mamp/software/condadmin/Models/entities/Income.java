package com.mamp.software.condadmin.Models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "INCOME")
public class Income implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINCOME")
    private Integer idincome;

    @Column(name = "VALUE", precision=8, scale = 2)
    @NotEmpty
    private float value;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    @Past
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    @NotEmpty
    private Calendar date;

    @Column(name = "DESCRIPTION")
    @Size(max=100)
    @NotEmpty
    private String description;

    @Column(name = "TYPE")
    @Size(max=10)
    @NotEmpty
    private String type;

    //Relations
    @JoinColumn(name = "IDHOUSE", referencedColumnName = "IDHOUSE")
    @ManyToOne
    private House house;

    @JoinColumn(name = "IDMONTHCOUNTS", referencedColumnName = "IDMONTHCOUNTS")
    @ManyToOne
    private MonthlyAccounts monthlyAccounts;

    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;
    
    /**/
    public Income(){
        super();
    }

    

    public Income(Integer idincome) {
		super();
		this.idincome = idincome;
	}



	/**/

    public Integer getIdincome() {
        return idincome;
    }

    public void setIdincome(Integer idincome) {
        this.idincome = idincome;
    }

    public float getValue() {
        return value;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public MonthlyAccounts getMonthlyAccounts() {
        return monthlyAccounts;
    }

    public void setMonthlyAccounts(MonthlyAccounts monthlyAccounts) {
        this.monthlyAccounts = monthlyAccounts;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }
}
