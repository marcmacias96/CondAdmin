package com.mamp.software.condadmin.Models.entities;

import java.io.Serializable;
import java.math.BigInteger;

public class RepTypeOfIncomes implements Serializable {

    private static final long serialVersionUID = 1L;
    private int house;
    private Double value;

    public RepTypeOfIncomes(int house, Double value) {
        this.house = house;
        this.value = value;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
