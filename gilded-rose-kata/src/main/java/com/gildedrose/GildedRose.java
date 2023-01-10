package com.gildedrose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                && !item.name.equals(Item.BACKSTAGE_ITEM)) {
                item.decreaseQuality();
            } else {
                if (!item.isNormalMaxQuality()) {
                    item.increaseQuality();

                    if (item.name.equals(Item.BACKSTAGE_ITEM)) {
                        if (item.sellIn < 11) {
                            item.increaseQuality();
                        }

                        if (item.sellIn < 6) {
                            item.increaseQuality();
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals(Item.BACKSTAGE_ITEM)) {
                        item.decreaseQuality();
                    } else {
                        item.quality = 0;
                    }
                } else {
                    item.increaseQuality();
                }
            }
        }
    }


}