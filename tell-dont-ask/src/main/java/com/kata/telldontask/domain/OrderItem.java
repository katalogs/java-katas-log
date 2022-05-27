package com.kata.telldontask.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import com.kata.telldontask.useCase.SellItemRequest;
import java.math.BigDecimal;

public class OrderItem {

  private Product product;
  private int quantity;
  private BigDecimal taxedAmount;
  private BigDecimal tax;

  public OrderItem(Product product, SellItemRequest itemRequest) {
    final BigDecimal unitaryTax = product.getPrice().divide(valueOf(100))
        .multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
    final BigDecimal unitaryTaxedAmount = product.getPrice().add(unitaryTax).setScale(2, HALF_UP);
    this.taxedAmount = unitaryTaxedAmount.multiply(valueOf(itemRequest.getQuantity()))
        .setScale(2, HALF_UP);
    this.tax = unitaryTax.multiply(valueOf(itemRequest.getQuantity()));
    this.product = product;
    this.quantity = itemRequest.getQuantity();
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getTaxedAmount() {
    return taxedAmount;
  }

  public void setTaxedAmount(BigDecimal taxedAmount) {
    this.taxedAmount = taxedAmount;
  }

  public BigDecimal getTax() {
    return tax;
  }

  public void setTax(BigDecimal tax) {
    this.tax = tax;
  }
}
