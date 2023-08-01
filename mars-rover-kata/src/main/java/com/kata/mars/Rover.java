package com.kata.mars;

import com.kata.mars.direction.West;

public class Rover {

    private Position position;
    private Direction direction;
    private boolean hasError;

    public Rover(String startPosition) {
        this.position = Position.build(startPosition);
        this.direction = Direction.build(startPosition.split(":")[2]);
    }

    public String execute(String command) {
        this.hasError = false;

        String[] commands = command.split("");
        for (String step : commands) {
          switch (step) {
            case "L" -> this.direction = direction.left();
            case "R" -> this.direction = direction.right();
            case "F" -> this.position = direction.move(this.position);
            default -> this.hasError = true;
          }
          if (this.hasError) break;
        }
        return reportPosition();
    }

    private String reportPosition() {
        return (this.hasError ? "E:" : "") + (position.x() + ":" + position.y() + ":") + direction;
    }
}