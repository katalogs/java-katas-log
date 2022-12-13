package com.gildedrose;

public class Item {

    public static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
