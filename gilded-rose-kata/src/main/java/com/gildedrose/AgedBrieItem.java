package com.gildedrose;

public class AgedBrieItem extends Item {

  public AgedBrieItem(int sellIn, int quality) {
    super("Aged Brie", sellIn, quality);
  }

  @Override
  public void updateQuality() {
    if (!isNormalMaxQuality()) {
      increaseQuality();
    }

    sellIn--;

    if (sellIn < 0) {
      increaseQuality();
    }
  }
}
