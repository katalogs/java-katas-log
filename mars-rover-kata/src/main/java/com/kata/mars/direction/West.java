package com.kata.mars.direction;

import com.kata.mars.Direction;
import com.kata.mars.Position;

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
  public Position move(Position position) {
    return new Position(position.x() - 1, position.y());
  }

  @Override
  public String toString() {
    return "W";
  }
}
