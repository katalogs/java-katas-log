package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
    void updateQualityTest() throws UnsupportedEncodingException {
        // execute main
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        String output = os.toString("UTF8");

        TexttestFixture.main(new String[0]);

        System.out.println(output);

        // add an item
        // update quality
        // verify name
        // verify quality
        // verify sellIn
    }


}
