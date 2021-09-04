package com.mamp.software.condadmin.Models.entities;

import java.io.Serializable;
import java.math.BigInteger;

public class RepTypeOfExpensesMonthly implements Serializable {
    private static final long serialVersionUID = 1L;
    private int month;
    private Double value;
    private String tipo;

    public RepTypeOfExpensesMonthly(int month, Double value, String tipo){
        this.month = month;
        this.value = value;
        this.tipo = tipo;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
