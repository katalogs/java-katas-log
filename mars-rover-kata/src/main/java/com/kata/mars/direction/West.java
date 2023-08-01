package com.kata.mars.direction;

import com.kata.mars.Direction;

public class West implements Direction {

  @Override
  public Direction left() {
    return new South();
  }

  @Override
  public Direction right() {
    return new North();
  }

  @Override
  public String toString() {
    return "W";
  }
}
