package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "INCOMEDETAIL")
public class IncomeDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="IDDETAIL")
    private Integer idIncomeDetail;

    @Column(name = "DETALLE")
    @NotEmpty
    private String detail;

    @Column(name = "PRICE")
    @NotNull
    private Float value;
    
    @Column(name = "TYPE")
    @NotEmpty
    private String type;


    public IncomeDetail() {
    }

    public Integer getIdIncomeDetail() {
        return idIncomeDetail;
    }

    public void setIdIncomeDetail(Integer idIncomeDetail) {
        this.idIncomeDetail = idIncomeDetail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	
}
