package com.kata.mars.direction;

import com.kata.mars.Direction;
import com.kata.mars.Position;

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
  public Position move(Position position) {
    return new Position(position.x() + 1, position.y());
  }

  @Override
  public String toString() {
    return "E";
  }
}
