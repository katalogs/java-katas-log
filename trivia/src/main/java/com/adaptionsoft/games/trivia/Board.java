package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.trivia.Category.Pop;

public class Board {
    private List<Cell> cells = new ArrayList<>();

    public Board() {
        cells.add(new Cell(Pop, 0));
    }
}
