package com.kata.mars;


public class Rover {

  private final Reporter reporter;
  private Position position;
  private Direction direction;
  private final GPS gps;

  public Rover(String startPosition, WorldMap worldMap) {
    this.position = Position.build(startPosition);
    this.direction = Direction.build(startPosition.split(":")[2]);
    this.gps = new GPS(worldMap);
    this.reporter = new Reporter();
  }

  public String execute(String command) {
    Sequence sequence = new Sequence(command);
    while (sequence.hasNext()) {
      applyNextCommand(sequence);
    }

    return this.reporter.report(sequence, position, direction);
  }

  private void applyNextCommand(Sequence sequence) {
    String step = sequence.next();
    switch (step) {
      case "L" -> this.direction = direction.left();
      case "R" -> this.direction = direction.right();
      case "F" -> move(sequence);
      default -> sequence.stop();
    }
  }

  private void move(Sequence sequence) {
    Position nextPosition = gps.move(position, direction);
    if (gps.hasObstacleAt(nextPosition)) {
      sequence.stop(new ObstacleError());
      return;
    }
    this.position = nextPosition;
  }
}