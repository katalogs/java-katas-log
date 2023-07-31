package com.kata.mars;

import java.util.ArrayList;
import java.util.List;

public class Direction {

    private final String state;

    ArrayList<String> directions = new ArrayList<>(List.of("N","E","S","W"));

    public Direction(String s) {
        this.state = s;
    }

    public static Direction build(String s) {
        return new Direction(s);
    }

    public Direction left() {
        int index = directions.indexOf(this.state);
        if(index == 0){
            return new Direction("W");
        }
        return new Direction(this.directions.get(index - 1));
    }

    public Direction right() {
        int index = directions.indexOf(this.state);
        if(index == 3){
            return new Direction("N");
        }
        return new Direction(this.directions.get(index + 1));

    }

    @Override
    public String toString() {
        return state;
    }
}
