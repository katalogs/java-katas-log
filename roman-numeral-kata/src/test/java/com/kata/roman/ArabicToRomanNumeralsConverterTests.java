package com.kata.roman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ArabicToRomanNumeralsConverterTests {

  @Test
  void shouldConvert1IntoI() {
    assertThat(ArabicToRomanNumeralsConverter.convert(1)).isEqualTo("I");
  }

  @Test
  void shouldConvert2IntoII() {
    assertThat(ArabicToRomanNumeralsConverter.convert(2)).isEqualTo("II");
  }

  @Test
  void shouldConvert3IntoIII() {
    assertThat(ArabicToRomanNumeralsConverter.convert(3)).isEqualTo("III");
  }

}