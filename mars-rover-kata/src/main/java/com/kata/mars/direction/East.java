package com.kata.mars.direction;

import com.kata.mars.Direction;

public class East implements Direction {

  @Override
  public Direction left() {
    return new North();
  }

  @Override
  public Direction right() {
    return new South();
  }

  @Override
  public String toString() {
    return "E";
  }
}
