package com.kata.mars.direction;

import com.kata.mars.Direction;

public class South implements Direction {

  @Override
  public Direction left() {
    return new East();
  }

  @Override
  public Direction right() {
    return new West();
  }

  @Override
  public String toString() {
    return "S";
  }
}
