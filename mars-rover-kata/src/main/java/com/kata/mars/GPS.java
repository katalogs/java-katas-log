package com.kata.mars;

public class GPS {

  private final WorldMap worldMap;

  public GPS(WorldMap worldMap) {
    this.worldMap = worldMap;
  }

  public Position move(Position position, Direction direction) {
    Position nextPosition = direction.move(position);
    int x = nextPosition.x();
    int y = nextPosition.y();

    if (isNorthCrossed(nextPosition)) {
      y = worldMap.bottomLeft().y();
    }

    if (isSouthCrossed(nextPosition)) {
      y = worldMap.topRight().y();
    }

    if (isWestCrossed(nextPosition)) {
      x = worldMap.topRight().x();
    }

    if (isEastCrossed(nextPosition)) {
      x = worldMap.bottomLeft().x();
    }

    return new Position(x, y);
  }

  private boolean isEastCrossed(Position nextPosition) {
    return nextPosition.x() > worldMap.topRight().x();
  }

  private boolean isWestCrossed(Position nextPosition) {
    return nextPosition.x() < worldMap.bottomLeft().x();
  }

  private boolean isSouthCrossed(Position nextPosition) {
    return nextPosition.y() < worldMap.bottomLeft().y();
  }

  private boolean isNorthCrossed(Position nextPosition) {
    return nextPosition.y() > worldMap.topRight().y();
  }

  public boolean hasObstacleAt(Position position) {
    return worldMap.isNextPositionIsAnObstacle(position);
  }
}
