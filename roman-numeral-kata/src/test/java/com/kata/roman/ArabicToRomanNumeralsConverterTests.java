package com.kata.roman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ArabicToRomanNumeralsConverterTests {

  @Test
  void shouldConvert1IntoI() {
    int number = 1;

    String actuel = ArabicToRomanNumeralsConverter.convert(number);

    assertThat(actuel).isEqualTo("I");
  }

}