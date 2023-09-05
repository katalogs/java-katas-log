package com.kata.mars;


public class Rover {

  private final WorldMap worldMap;
  private Position position;
  private Direction direction;
  private GPS gps;

  public Rover(String startPosition, WorldMap worldMap) {
    this.position = Position.build(startPosition);
    this.direction = Direction.build(startPosition.split(":")[2]);
    this.worldMap = worldMap;
    this.gps = new GPS(worldMap);
  }

  public String execute(String command) {
    Sequence sequence = new Sequence(command);
    while (sequence.hasNext()) {
      try {
        applyNextCommand(sequence);
      } catch (ObstacleFoundException e) {
        return reportObstacle();
      }
    }

    if (sequence.hasError()) {
      return reportError();
    }

    return reportPosition();
  }

  private String reportObstacle() {
    return "O:" + reportPosition();
  }

  private void applyNextCommand(Sequence sequence) {
    String step = sequence.next();
    switch (step) {
      case "L" -> this.direction = direction.left();
      case "R" -> this.direction = direction.right();
      case "F" -> move();
      default -> sequence.stop();
    }
  }

  private void move() {
    Position nextPosition = gps.move(position, direction);
    if (gps.hasObstacleAt(nextPosition)) {
//TODO : stop the sequence with an obstacle error
    }
    this.position = nextPosition;
  }

  private String reportError() {
    return "E:" + reportPosition();
  }

  private String reportPosition() {
    return (position.x() + ":" + position.y() + ":") + direction;
  }
}