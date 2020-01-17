package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "EXPENSEDETAIL")
public class ExpenseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="IDDETAIL")
    private Integer idExpenseDetail;

    @Column(name = "DETALLE")
    @NotEmpty
    private String detail;

    @Column(name = "PRICE")
    @NotNull
    private Float value;


    public ExpenseDetail() {
    }

    public Integer getIdExpenseDetail() {
        return idExpenseDetail;
    }

    public void setIdExpenseDetail(Integer idExpenseDetail) {
        this.idExpenseDetail = idExpenseDetail;
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


}
