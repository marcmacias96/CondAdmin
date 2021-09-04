package com.mamp.software.condadmin.Models.entities;

import java.io.Serializable;

public class RepTypeOfExpenses implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private Double value;

    public RepTypeOfExpenses(String type, Double value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
