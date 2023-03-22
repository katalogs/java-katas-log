package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

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
    void updateQualityTest() throws FileNotFoundException {
        // execute main
        StringWriter stringWriter = new StringWriter();
        //System.setOut(stringWriter.);
        TexttestFixture.main(null);
        // add an item
        // update quality
        // verify name
        // verify quality
        // verify sellIn
    }


}
