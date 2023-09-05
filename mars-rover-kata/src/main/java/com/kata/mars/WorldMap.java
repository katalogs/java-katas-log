package com.kata.mars;

import java.util.ArrayList;
import java.util.List;

public record WorldMap(Position bottomLeft, Position topRight, List<Obstacle> obstacles) {

  public WorldMap(Position bottomLeft, Position topRight) {
    this(bottomLeft, topRight, new ArrayList<>());
  }

  public boolean isNextPositionIsAnObstacle(Position position) {
    for (Obstacle obstacle : obstacles) {
      if (obstacle.x() == position.x() && obstacle.y() == position.y()) {
        return true;
      }
    }
    return true;
  }
}
