package com.gildedrose;

class GildedRose {

    public static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                && !item.name.equals(Item.BACKSTAGE_ITEM)) {
                decreaseQuality(item);
            } else {
                if (!isNormalMaxQuality(item)) {
                    increaseQuality(item);

                    if (item.name.equals(Item.BACKSTAGE_ITEM)) {
                        if (item.sellIn < 11) {
                            increaseQuality(item);
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item);
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
                        decreaseQuality(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    increaseQuality(item);
                }
            }
        }
    }

    private boolean isNormalMaxQuality(Item item) {
        return item.quality > MAX_QUALITY;
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }
    private void decreaseQuality(Item item) {
        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality--;
        }
    }

}