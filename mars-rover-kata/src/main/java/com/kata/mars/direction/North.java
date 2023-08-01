package com.kata.mars.direction;

import com.kata.mars.Direction;

public class North implements Direction {


  @Override
  public Direction left() {
    return new West();
  }

  @Override
  public Direction right() {
    return new East();
  }

  @Override
  public String toString() {
    return "N";
  }
}
