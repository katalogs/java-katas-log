package com.kata.roman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ArabicToRomanNumeralsConverterTests {

  @ParameterizedTest
  @CsvSource({"1,I", "2,II", "3,III"})
  void shouldConvertSuccessiveI(int arabicNumber, String romanNumber) {
    assertThat(ArabicToRomanNumeralsConverter.convert(arabicNumber)).isEqualTo(romanNumber);
  }

  @ParameterizedTest
  @CsvSource({"5,V", "6,VI", "7,VII", "8,VIII"})
  void shouldConvertSuccessiveV(int arabicNumber, String romanNumber) {
    assertThat(ArabicToRomanNumeralsConverter.convert(arabicNumber)).isEqualTo(romanNumber);
  }

}