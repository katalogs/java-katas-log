package com.kata.mars;


public class Rover {

    private Position position;
    private Direction direction;

    public Rover(String startPosition) {
        this.position = Position.build(startPosition);
        this.direction = Direction.build(startPosition.split(":")[2]);
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
        if(nextPosition.y() > 4){
            return new Position(nextPosition.x(),0);
        }
        return nextPosition;
    }

    private String reportError() {
        return "E:"+reportPosition();
    }

    private String reportPosition() {
        return (position.x() + ":" + position.y() + ":") + direction;
    }
}