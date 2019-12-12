package com.mamp.software.condadmin.Models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;


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
    @NotEmpty
    private float value;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Calendar date;

    @Column(name = "DETAIL")
    @Size(max = 100)
    @NotEmpty
    private String detail;

    //Relations
    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    /**/
    public Expenses(){
        super();
    }

    private Expenses(Integer idexpenses){
        super();
        this.idexpenses = idexpenses;
    }

    /**/
    
	public Integer getIdexpenses() {
		return idexpenses;
	}

	public void setIdexpenses(Integer idexpenses) {
		this.idexpenses = idexpenses;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

    
}

