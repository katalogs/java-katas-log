package com.kata.telldontask.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Amount {

  private final BigDecimal amount;

  public Amount(double amount) {
    this.amount = BigDecimal.valueOf(amount);
  }

  public Amount(BigDecimal amount) {
    this.amount = amount;
  }

  public Amount multiplyBy(int multiplier) {
    return new Amount(amount.multiply(BigDecimal.valueOf(multiplier)));
  }

  public Amount add(Amount anotherAmount) {
    return new Amount(this.amount.add(anotherAmount.amount));
  }

  public Amount applyPercentage(Percentage percentage) {
    return new Amount(this.amount.multiply(percentage.getPercentageValue())
        .setScale(2, RoundingMode.HALF_UP));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Amount amount1 = (Amount) o;
    return getFormattedAmount().equals(amount1.getFormattedAmount());
  }

  private BigDecimal getFormattedAmount() {
    return amount.setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }

  public BigDecimal value() {
    return amount.setScale(2, RoundingMode.HALF_UP);
  }
}
