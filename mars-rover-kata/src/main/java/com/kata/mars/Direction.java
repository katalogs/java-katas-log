package com.kata.mars;

import com.kata.mars.direction.East;
import com.kata.mars.direction.North;
import com.kata.mars.direction.South;
import com.kata.mars.direction.West;

public interface Direction {

  Direction left();

  Direction right();

  static Direction build(String s) {
    return switch (s) {
      case "N" -> new North();
      case "E" -> new East();
      case "S" -> new South();
      case "W" -> new West();
      default -> throw new IllegalArgumentException("Invalid direction");
    };
  }

  Position move(Position position);
}
