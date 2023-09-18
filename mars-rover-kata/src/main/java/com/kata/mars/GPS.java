package com.kata.mars;

public class GPS {

  private WorldMap worldMap;

  public GPS(WorldMap worldMap) {
    this.worldMap = worldMap;
  }

  public Position move(Position position, Direction direction) {
    Position nextPosition = direction.move(position);

    if (nextPosition.y() > worldMap.topRight().y()) {
      return new Position(nextPosition.x(), worldMap.bottomLeft().y());
    }

    if (nextPosition.y() < worldMap.bottomLeft().y()) {
      return new Position(nextPosition.x(), worldMap.topRight().y());
    }

    if (nextPosition.x() < worldMap.bottomLeft().x()) {
      return new Position(worldMap.topRight().x(), nextPosition.y());
    }

    if (nextPosition.x() > worldMap.topRight().x()) {
      return new Position(worldMap.bottomLeft().x(), nextPosition.y());
    }

    return nextPosition;
  }

  public boolean hasObstacleAt(Position position) {
    return worldMap.isNextPositionIsAnObstacle(position);
  }
}
