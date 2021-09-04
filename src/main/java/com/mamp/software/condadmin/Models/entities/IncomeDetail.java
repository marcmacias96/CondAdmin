package com.mamp.software.condadmin.Models.entities;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;

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

    //BITACORA
    @Column(name = "CREADOPOR")
    @Size(max = 35)
    private String creadoPor;

    @Column(name = "CREADOEN")
    private Calendar creadoEn;

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Calendar getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Calendar creadoEn) {
        this.creadoEn = creadoEn;
    }

    @PrePersist
    public void prePersist() {
        creadoEn = Calendar.getInstance();
        SecurityContext context = SecurityContextHolder.getContext();
        creadoPor = context.getAuthentication().getName();
    }

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
