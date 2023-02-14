package com.kata.telldontask.domain;

import com.kata.telldontask.domain.common.Amount;
import com.kata.telldontask.domain.product.Category;

import java.math.BigDecimal;

public class Product {
    private String name;
    private Amount price;
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = new Amount(price);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Amount getUnitaryTax() {
       return price.applyPercentage(category.getTaxPercentage());
    }

    public Amount getUnitaryTaxedAmount() {
        return price.add(getUnitaryTax());
    }
}
