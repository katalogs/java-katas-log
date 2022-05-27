package com.kata.telldontask.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import com.kata.telldontask.useCase.SellItemRequest;
import java.math.BigDecimal;

public class OrderItem {

  private final Product product;
  private final int quantity;
  private final BigDecimal taxedAmount;
  private final BigDecimal tax;

  public OrderItem(Product product, SellItemRequest itemRequest) {
    final BigDecimal unitaryTax = calculateUnitaryTax(product);
    final BigDecimal unitaryTaxedAmount = calculateUnitaryTaxedAmount(product, unitaryTax);
    this.taxedAmount = calculateTaxedAmount(itemRequest, unitaryTaxedAmount);
    this.tax = calculateTax(itemRequest, unitaryTax);
    this.product = product;
    this.quantity = itemRequest.getQuantity();
  }

private BigDecimal calculateUnitaryTaxedAmount(Product product, final BigDecimal unitaryTax) {
	return product.getPrice().add(unitaryTax).setScale(2, HALF_UP);
}

private BigDecimal calculateTax(SellItemRequest itemRequest, final BigDecimal unitaryTax) {
	return unitaryTax.multiply(valueOf(itemRequest.getQuantity()));
}

private BigDecimal calculateTaxedAmount(SellItemRequest itemRequest, final BigDecimal unitaryTaxedAmount) {
	return calculateTax(itemRequest, unitaryTaxedAmount)
        .setScale(2, HALF_UP);
}

private BigDecimal calculateUnitaryTax(Product product) {
	return product.getPrice().divide(valueOf(100))
        .multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
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
