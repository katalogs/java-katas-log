package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    private ByteArrayOutputStream out;

    @BeforeEach
    public void setUpOutput() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void test1() {
        var game = new Game();
        game.add("Bart");
        game.roll(12);
        game.wrongAnswer();
        game.roll(2);
        game.roll(13);
        game.wasCorrectlyAnswered();
        game.roll(13);

        verify(out.toString());
    }

    @Test
    void test2() {
        var game = new Game();
        game.add("Bart");
        game.add("Lisa");
        game.roll(1);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();

        verify(out.toString());
    }

    @Test
    void test3() {
        var game = new Game();
        game.add("Bart");
        game.add("Lisa");
        game.roll(1);
        game.wrongAnswer();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();
        game.roll(2);
        game.wasCorrectlyAnswered();

        verify(out.toString());
    }
}
