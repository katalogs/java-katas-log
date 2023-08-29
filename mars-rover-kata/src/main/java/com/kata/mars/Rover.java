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
    return gps.move(position, direction);
  }

  private String reportError() {
    return "E:" + reportPosition();
  }

  private String reportPosition() {
    return (position.x() + ":" + position.y() + ":") + direction;
  }
}