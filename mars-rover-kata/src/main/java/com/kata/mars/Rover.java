package com.kata.mars;

public class Rover {

    private final Position position;
    private Direction direction;

    public Rover(String startPosition) {
        this.position =  Position.build(startPosition);
        this.direction = Direction.build(startPosition.split(":")[2]);
    }

    public String execute(String command) {

        String coordinates = position.x() + ":" + position.y() + ":" ;

        String[] commands = command.split("");
        for(String step : commands){
            if(step.equals("L")){
                this.direction = direction.left();
            }else{
                this.direction = direction.right();
            }
        }
        return coordinates + direction;
    }
}