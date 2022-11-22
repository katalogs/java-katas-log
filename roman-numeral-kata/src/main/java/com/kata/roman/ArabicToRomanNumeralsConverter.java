package com.kata.roman;

public class ArabicToRomanNumeralsConverter {

  private static final String V = "V";
  private static final String X = "X";
  public static final String I = "I";
  public static final String L = "L";

  static String convert(int arabicNumber) {
    String result = "";

    int fifties = arabicNumber / 50;
    if (fifties >= 1) {
      result += L.repeat(fifties);
      arabicNumber -= fifties * 50;
    }

    int tens = arabicNumber / 10;
    if (tens >= 1) {
      result += X.repeat(tens);
      arabicNumber -= tens * 10;
    }

    int fives = arabicNumber / 5;
    if (fives >= 1) {
      result += V.repeat(fives);
      arabicNumber -= fives * 5;
    }

    int one = arabicNumber / 1;
    if (one >= 1) {
      result += I.repeat(one);
      arabicNumber -= one * 1;
    }

    return result;
  }
}
