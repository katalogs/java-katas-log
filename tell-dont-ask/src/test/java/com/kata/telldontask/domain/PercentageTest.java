package com.kata.telldontask.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.kata.telldontask.domain.common.Percentage;
import org.junit.jupiter.api.Test;

class PercentageTest {

  @Test
  void should_not_be_a_valid_percentage() {
    assertThatThrownBy(() -> new Percentage(-12.0))
        .isInstanceOf(IllegalArgumentException.class);
  }
}