package com.kata.mars.direction;

import com.kata.mars.Direction;
import com.kata.mars.Position;

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
  public Position move(Position position) {
    return new Position(position.x(), position.y() + 1);
  }

  @Override
  public String toString() {
    return "N";
  }
}
