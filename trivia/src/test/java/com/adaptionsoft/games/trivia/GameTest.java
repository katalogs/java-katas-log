package com.adaptionsoft.games.trivia;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class GameTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        // Arrange
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        Game game = new Game();
        game.add("Cedric");
        game.roll(12);
        game.wrongAnswer();
        game.roll(2);
        game.roll(13);
        game.wasCorrectlyAnswered();
        game.roll(13);

        // Assert
        Approvals.verify(os.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        // Arrange
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        Game game = new Game();
        game.add("Cedric");
        game.add("Eloïse");
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

        // Assert
        Approvals.verify(os.toString(StandardCharsets.UTF_8));
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        // Arrange
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        Game game = new Game();
        game.add("Cedric");
        game.add("Eloïse");
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

        // Assert
        Approvals.verify(os.toString(StandardCharsets.UTF_8));
    }
}
