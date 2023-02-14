package com.kata.telldontask.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

public class OrderItem {

  private Product product;
  private int quantity;
  private Amount taxedAmount;
  private Amount tax;

  public OrderItem(Product product, int quantity) {
    final BigDecimal unitaryTax = product
        .getPrice()
        .divide(valueOf(100))
        .multiply(product.getCategory().getTaxPercentage())
        .setScale(2, HALF_UP);
    final BigDecimal unitaryTaxedAmount = product
        .getPrice()
        .add(unitaryTax)
        .setScale(2, HALF_UP);
    final Amount taxedAmount = new Amount(unitaryTaxedAmount.multiply(valueOf(quantity)));
    final Amount taxAmount = new Amount(unitaryTax.multiply(valueOf(quantity)));


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
