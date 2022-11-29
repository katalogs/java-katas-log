package com.gildedrose;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void golden() {
        PrintStream previousConsole = System.out;
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));

        // Execute
        Integer daysCount = 30;
        String[] args = new String[] {Integer.toString(daysCount)};
        TexttestFixture.main(args);
        String mainOutput = newConsole.toString();

        // Assert
        Approvals.verify(mainOutput);

        // Restore
        System.setOut(previousConsole);
    }
}
