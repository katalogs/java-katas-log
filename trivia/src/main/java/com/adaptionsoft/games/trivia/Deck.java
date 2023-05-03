package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Question> questions = new ArrayList<>();
    private Category category;

    public Deck(Category category) {
        this.category = category;
    }

    public void addQuestion(Question question) {
       this.questions.add(question);
    }
}
