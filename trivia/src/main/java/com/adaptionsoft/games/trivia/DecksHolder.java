package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.trivia.Category.*;

public class DecksHolder {

    private final List<Deck> decks = new ArrayList<>();

    public DecksHolder() {
        this.decks.add(new Deck(Pop));
        this.decks.add(new Deck(Science));
        this.decks.add(new Deck(Sports));
        this.decks.add(new Deck(Rock));

        for (int i = 0; i < 50; i++) {
            getDeckByCategory(Pop).addQuestion(new Question("Pop Question " + i));
            getDeckByCategory(Science).addQuestion(new Question("Science Question " + i));
            getDeckByCategory(Sports).addQuestion(new Question("Sports Question " + i));
            getDeckByCategory(Rock).addQuestion(new Question("Rock Question " + i));
        }
    }

    private Deck getDeckByCategory(Category category) {
        return decks.stream()
                .filter(deck -> deck.getCategory().equals(category))
                .findAny().get();
    }
}
