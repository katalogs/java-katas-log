package com.kata.roman;

public class ArabicToRomanNumeralsConverter {

  private static final String V = "V";

  static String convert(int arabicNumber) {
    if (arabicNumber >= 5) {
      return V + "I".repeat(arabicNumber - 5);
    }

    return "I".repeat(arabicNumber);
  }
}
