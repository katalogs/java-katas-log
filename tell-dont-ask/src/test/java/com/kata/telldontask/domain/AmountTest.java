package com.kata.telldontask.domain;

import org.junit.jupiter.api.Test;

class AmountTest {

  @Test
  void should_be_multiplied_by_a_quantity() {
    Amount amount = new Amount(100.0);

    Amount multipliedAmount = amount.multiplyBy(3);
  }
}