package com.kata.diamond;

import java.util.Locale;

public class Diamond {

  public static String print(char letter) {
    if (letter == 'a') {
      letter = toUpperCase(letter);
    }
    if (letter < 65 || letter > 90) {
      return "";
    }
    return "A";
  }

  private static char toUpperCase(char letter) {
    return String.valueOf(letter).toUpperCase(Locale.ROOT).charAt(0);
  }
}