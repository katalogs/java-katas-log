package com.kata.telldontask.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

public class OrderItem {

  private final Product product;
  private final int quantity;
  private final Amount taxedAmount;
  private final Amount tax;

  public OrderItem(Product product, int quantity) {
    final Amount unitaryTax = new Amount(product
        .getPrice()
        .divide(valueOf(100))
        .multiply(product.getCategory().getTaxPercentage())
        .setScale(2, HALF_UP));
    final Amount unitaryTaxedAmount = new Amount(product
        .getPrice()
        .add(unitaryTax.value())
        .setScale(2, HALF_UP));
    final Amount taxedAmount = unitaryTaxedAmount.multiplyBy(quantity);
    final Amount taxAmount = unitaryTax.multiplyBy(quantity);


    this.product = product;
    this.quantity = quantity;
    this.tax = taxAmount;
    this.taxedAmount = taxedAmount;
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
