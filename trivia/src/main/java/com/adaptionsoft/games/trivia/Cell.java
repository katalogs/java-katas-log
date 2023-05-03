package com.adaptionsoft.games.trivia;

public class Cell {

    public Category getCategory() {
        return category;
    }

    private Category category;

    public int getPosition() {
        return position;
    }

    private int position;

    public Cell(Category category, int position) {
        this.category = category;
        this.position = position;
    }
}
