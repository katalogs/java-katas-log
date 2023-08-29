package com.kata.mars;


public class Rover {

  private final WorldMap worldMap;
  private Position position;
  private Direction direction;

  public Rover(String startPosition, WorldMap worldMap) {
    this.position = Position.build(startPosition);
    this.direction = Direction.build(startPosition.split(":")[2]);
    this.worldMap = worldMap;
  }

  public String execute(String command) {
    Sequence sequence = new Sequence(command);
    while (sequence.hasNext()) {
      applyNextCommand(sequence);
    }

    if (sequence.hasError()) {
      return reportError();
    }

    return reportPosition();
  }

  private void applyNextCommand(Sequence sequence) {
    String step = sequence.next();
    switch (step) {
      case "L" -> this.direction = direction.left();
      case "R" -> this.direction = direction.right();
      case "F" -> this.position = move();
      default -> sequence.stop();
    }
  }

  private Position move() {
    Position nextPosition = direction.move(this.position);

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

  private String reportError() {
    return "E:" + reportPosition();
  }

  private String reportPosition() {
    return (position.x() + ":" + position.y() + ":") + direction;
  }
}