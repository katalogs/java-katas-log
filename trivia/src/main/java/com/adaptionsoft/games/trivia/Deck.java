package com.adaptionsoft.games.trivia;

import java.util.LinkedList;

public class Deck {
    private LinkedList<Question> questions = new LinkedList<>();
    private Category category;

    public Deck(Category category) {
        this.category = category;
    }

    public void addQuestion(Question question) {
       this.questions.addLast(question);
    }

    public Question drawQuestion() {
        return this.questions.removeFirst();
    }
}
