package com.gildedrose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case "Aged Brie":
                    if (!item.isNormalMaxQuality()) {
                        item.increaseQuality();
                    }

                    item.sellIn--;

                    if (item.sellIn < 0) {
                        item.increaseQuality();
                    }
                    break;

                case Item.BACKSTAGE_ITEM:
                    if (!item.isNormalMaxQuality()) {
                        item.increaseQuality();

                        if (item.sellIn < 11) {
                            item.increaseQuality();
                        }

                        if (item.sellIn < 6) {
                            item.increaseQuality();
                        }
                    }

                    item.sellIn--;

                    if (item.sellIn < 0) {
                        item.quality = 0;
                    }
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    break;
                default:
                    if (item.quality > 0) {
                        item.quality--;
                    }
                    item.sellIn--;

                    if (item.sellIn < 0) {
                        if (item.quality > 0) {
                            item.quality--;
                        }
                    }
                    break;
            }
        }
    }


}