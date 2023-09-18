package com.kata.mars;

public class Reporter {

  public String report(Sequence sequence, Position position, Direction direction) {

    String prefix = "";
    if (sequence.getError().getClass().equals(ObstacleError.class)) {
      prefix = "O:";
    }
    if (sequence.getError().getClass().equals(CommandError.class)) {
      prefix = "E:";
    }
    return prefix + (position.x() + ":" + position.y() + ":") + direction;
  }
}
