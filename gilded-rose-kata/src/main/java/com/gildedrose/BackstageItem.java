package com.gildedrose;

public class BackstageItem extends Item {
    private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";

    public BackstageItem(int sellIn, int quality) {
        super(BACKSTAGE_ITEM, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (!isNormalMaxQuality()) {
            increaseQuality();

            if (sellIn < 11) {
                increaseQuality();
            }

            if (sellIn < 6) {
                increaseQuality();
            }
        }

        sellIn--;

        if (sellIn < 0) {
            quality = 0;
        }
    }
}
