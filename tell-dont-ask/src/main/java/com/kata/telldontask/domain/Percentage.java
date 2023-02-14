package com.kata.telldontask.domain;

import java.math.BigDecimal;

public class Percentage {

  private BigDecimal value;

  public Percentage(double value) {
    if (value < 0) {
      throw new IllegalArgumentException();
    }
    this.value = BigDecimal.valueOf(value);
  }

  public BigDecimal getPercentageValue() {
    return value.divide(new BigDecimal("100"));
  }
}
