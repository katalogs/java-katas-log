package com.adaptionsoft.games.trivia;

public class Question {
    private String description;

    public Question(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
