package com.gildedrose;

public class ConjuredItem extends Item {

    public ConjuredItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void decreaseQuality() {
        super.decreaseQuality();
        super.decreaseQuality();
    }
}
