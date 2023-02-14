package com.kata.telldontask.domain;

import java.math.BigDecimal;

public class Category {
    private String name;
    private Percentage taxPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Percentage getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = new Percentage(taxPercentage.doubleValue());
    }
}
