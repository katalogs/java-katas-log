package com.gildedrose;

public class Item {

    public static final int MAX_QUALITY = 50;
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

    public void increaseQuality() {
        if (quality < MAX_QUALITY) {
            quality++;
        }
    }

  public void decreaseQuality() {
    if (quality > 0) {
      quality--;
    }
  }

    public boolean isNormalMaxQuality() {
        return quality > MAX_QUALITY;
    }

}
