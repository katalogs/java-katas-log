package com.kata.mars;

public record Position(String x, String y){

    public static Position build(String startPosition) {
        String[] split = startPosition.split(":");
        return new Position(split[0], split[1]);
    }
}
