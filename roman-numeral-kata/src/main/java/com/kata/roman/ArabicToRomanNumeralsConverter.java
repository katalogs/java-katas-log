package com.kata.roman;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ArabicToRomanNumeralsConverter {

  public ArabicToRomanNumeralsConverter() {
    initDico();
  }

  public static final Map<Integer, String> DICO = new LinkedHashMap<>();


  String convert(int arabicNumber) {
    StringBuilder result = new StringBuilder();

    for (Entry<Integer, String> val : DICO.entrySet()) {

      int numerator = arabicNumber / val.getKey();
      if (numerator >= 1) {
        result.append(val.getValue().repeat(numerator));
        arabicNumber -= numerator * val.getKey();
      }

    }
    return result.toString();
  }

  private static void initDico() {
    DICO.put(100, "C");
    DICO.put(50, "L");
    DICO.put(10, "X");
    DICO.put(5, "V");
    DICO.put(1, "I");
  }
}
