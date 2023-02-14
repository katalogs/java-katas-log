package com.kata.telldontask.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.kata.telldontask.domain.common.Amount;
import com.kata.telldontask.domain.common.Percentage;
import org.junit.jupiter.api.Test;

class AmountTest {

  @Test
  void should_be_multiplied_by_a_quantity() {
    Amount amount = new Amount(100.0);

    Amount multipliedAmount = amount.multiplyBy(3);

    assertThat(multipliedAmount).isEqualTo(new Amount(300.0));
  }

  @Test
  void should_be_added_to_another_amount() {
    Amount amount = new Amount(100.0);

    Amount addedAmount = amount.add(new Amount(10.0));

    assertThat(addedAmount).isEqualTo(new Amount(110.0));
  }

  @Test
  void should_apply_percentage() {
    Amount amount = new Amount(100.0);

    Amount percentageAmount = amount.applyPercentage(new Percentage(20.0));

    assertThat(percentageAmount).isEqualTo(new Amount(20.0));
  }
}