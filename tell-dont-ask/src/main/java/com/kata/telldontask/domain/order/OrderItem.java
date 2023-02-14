package com.kata.telldontask.domain.order;

import com.kata.telldontask.domain.Product;
import com.kata.telldontask.domain.common.Amount;

import static java.math.BigDecimal.valueOf;

public class OrderItem {

    private final Product product;
    private final int quantity;
    private final Amount taxedAmount;
    private final Amount tax;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.tax = product.getUnitaryTax().multiplyBy(quantity);
        this.taxedAmount = product.getUnitaryTaxedAmount().multiplyBy(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Amount getTaxedAmount() {
        return taxedAmount;
    }

    public Amount getTax() {
        return tax;
    }
}
