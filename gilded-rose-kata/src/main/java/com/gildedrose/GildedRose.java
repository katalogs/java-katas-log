package com.gildedrose;

class GildedRose {

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      if (item.name.equals("Aged Brie")) {
        if (!item.isNormalMaxQuality()) {
          item.increaseQuality();
        }

        item.sellIn--;

        if (item.sellIn < 0) {
          item.increaseQuality();
        }
      } else {
        if (item.name.equals(Item.BACKSTAGE_ITEM)) {
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
        } else {
          item.decreaseQuality();

          if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
          }

          if (item.sellIn < 0) {
            item.decreaseQuality();
          }
        }
      }
    }
  }


}