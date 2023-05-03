package com.adaptionsoft.games.trivia;

import java.util.ArrayList;
import java.util.List;

import static com.adaptionsoft.games.trivia.Category.*;

public class Board {
    private List<Cell> cells = new ArrayList<>();

    public Board() {
        cells.add(new Cell(Pop, 0));
        cells.add(new Cell(Pop, 4));
        cells.add(new Cell(Pop, 8));

        cells.add(new Cell(Science, 1));
        cells.add(new Cell(Science, 5));
        cells.add(new Cell(Science, 9));

        cells.add(new Cell(Sports, 2));
        cells.add(new Cell(Sports, 6));
        cells.add(new Cell(Sports, 10));

        cells.add(new Cell(Rock, 3));
        cells.add(new Cell(Rock, 7));
        cells.add(new Cell(Rock, 11));
    }

    public Category getCategoryByPosition(int position) {
        return cells.stream().filter(cell -> cell.getPosition() == position).findFirst().get().getCategory();
    }
}
