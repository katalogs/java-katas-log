package com.kata.diamond;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Diamond {

  public static String print(char letter) {

    if (letter >= 'a' && letter <= 'z') {
      letter = toUpperCase(letter);
    }

    if (letter < 65 || letter > 90) {
      return "";
    }

    List<String> lines = new ArrayList<>();

    for (int i = 0; i <= letter - 'A'; i++) {
      String spaces = " ".repeat(letter - 'A' - i);
      if (i != 0) {
        lines.add(spaces + String.valueOf((char) ('A' + i)).repeat(2));
      } else {
        lines.add(spaces + String.valueOf((char) ('A' + i)));
      }
    }

    List<String> collect = lines.stream().sorted(Collections.reverseOrder()).skip(1).collect(Collectors.toList());
    lines.addAll(collect);

    return String.join("\n", lines);
  }

  private static char toUpperCase(char letter) {
    return String.valueOf(letter).toUpperCase(Locale.ROOT).charAt(0);
  }

}