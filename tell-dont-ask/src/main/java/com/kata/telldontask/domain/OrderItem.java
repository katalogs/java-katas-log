package com.kata.telldontask.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

public class OrderItem {

  private Product product;
  private int quantity;
  private BigDecimal taxedAmount;
  private BigDecimal tax;

  public OrderItem() {
  }

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
    final BigDecimal taxedAmount = unitaryTaxedAmount
        .multiply(valueOf(quantity))
        .setScale(2, HALF_UP);
    final BigDecimal taxAmount = unitaryTax.multiply(valueOf(quantity));

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

  public BigDecimal getTaxedAmount() {
    return taxedAmount;
  }

  public BigDecimal getTax() {
    return tax;
  }

}
