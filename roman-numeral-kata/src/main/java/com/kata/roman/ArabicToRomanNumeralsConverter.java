package com.kata.roman;

public class ArabicToRomanNumeralsConverter {

  private static final String V = "V";
  private static final String X = "X";
  public static final String I = "I";

  static String convert(int arabicNumber) {
    String result = "";

    if (arabicNumber >= 1) {
      result = I + I.repeat(arabicNumber - 1);
    }

    if (arabicNumber >= 5) {
      result = V + I.repeat(arabicNumber - 5);
    }

    if (arabicNumber >= 10) {
      result = X + I.repeat(arabicNumber - 10);
    }

    return result;
  }
}
