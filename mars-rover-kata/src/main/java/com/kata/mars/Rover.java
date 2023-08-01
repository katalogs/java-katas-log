package com.kata.mars;

import com.kata.mars.direction.West;

public class Rover {

    private Position position;
    private Direction direction;

    public Rover(String startPosition) {
        this.position = Position.build(startPosition);
        this.direction = Direction.build(startPosition.split(":")[2]);
    }

    public String execute(String command) {

        String[] commands = command.split("");
        for (String step : commands) {
            if (step.equals("L")) {
                this.direction = direction.left();
            } else if (step.equals("R")) {
                this.direction = direction.right();
            } else {
                if (this.direction instanceof West) {
                    this.position = new Position(position.x() - 1, position.y());
                } else {
                    this.position = new Position(position.x(), position.y() + 1);
                }
            }

        }
        return (position.x() + ":" + position.y() + ":") + direction;
    }
}