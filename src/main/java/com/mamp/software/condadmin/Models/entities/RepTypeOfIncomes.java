package com.mamp.software.condadmin.Models.entities;

import java.io.Serializable;
import java.math.BigInteger;

public class RepTypeOfIncomes implements Serializable {

    private static final long serialVersionUID = 1L;
    private int house;
    private BigInteger value;

    public RepTypeOfIncomes(int house, BigInteger value) {
        this.house = house;
        this.value = value;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
