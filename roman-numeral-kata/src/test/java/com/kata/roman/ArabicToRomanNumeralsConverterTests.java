package com.kata.roman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ArabicToRomanNumeralsConverterTests {

  private ArabicToRomanNumeralsConverter sut = new ArabicToRomanNumeralsConverter();

  @ParameterizedTest
  @CsvSource({"1,I", "2,II", "3,III", "11,XI", "12,XII", "13,XIII"})
  void shouldConvertSuccessiveI(int arabicNumber, String romanNumber) {
    assertThat(sut.convert(arabicNumber)).isEqualTo(romanNumber);
  }

  @ParameterizedTest
  @CsvSource({"5,V", "6,VI", "7,VII", "8,VIII"})
  void shouldConvertSuccessiveV(int arabicNumber, String romanNumber) {
    assertThat(sut.convert(arabicNumber)).isEqualTo(romanNumber);
  }

  @ParameterizedTest
  @CsvSource({"10,X", "20,XX", "30,XXX", "22,XXII", "27,XXVII"})
  void shouldConvertSuccessiveX(int arabicNumber, String romanNumber) {
    assertThat(sut.convert(arabicNumber)).isEqualTo(romanNumber);
  }

  @ParameterizedTest
  @CsvSource({"50,L"})
  void shouldConvertL(int arabicNumber, String romanNumber) {
    assertThat(sut.convert(arabicNumber)).isEqualTo(romanNumber);
  }


  @ParameterizedTest
  @CsvSource({"100,C", "200,CC", "300,CCC", "202,CCII", "207,CCVII"})
  void shouldConvertSuccessiveC(int arabicNumber, String romanNumber) {
    assertThat(sut.convert(arabicNumber)).isEqualTo(romanNumber);
  }


}