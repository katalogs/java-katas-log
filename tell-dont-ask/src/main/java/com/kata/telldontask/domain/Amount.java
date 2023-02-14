package com.kata.telldontask.domain;

import java.math.BigDecimal;

public class Amount {

  private final BigDecimal amount;

  public Amount(double amount) {
    this.amount = BigDecimal.valueOf(amount);
  }

  private Amount(BigDecimal amount) {
    this.amount = amount;
  }

  public Amount multiplyBy(int multiplier) {
    return new Amount(amount.multiply(BigDecimal.valueOf(multiplier)));
  }
}
