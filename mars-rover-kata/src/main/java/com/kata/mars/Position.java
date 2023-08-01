package com.kata.mars;

public record Position(int x, int y) {

    public static Position build(String startPosition) {
        String[] split = startPosition.split(":");
        return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
