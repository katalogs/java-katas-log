package com.gildedrose;

public class TexttestFixture {

  public static void main(String[] args) {
    System.out.println("OMGHAI!");

    Item[] items = new Item[]{
        new Item("+5 Dexterity Vest", 10, 20), //
        new AgedBrieItem(2, 0), //
        new Item("Elixir of the Mongoose", 5, 7), //
        new LegendaryItem("Sulfuras, Hand of Ragnaros", 0, 80), //
        new LegendaryItem("Sulfuras, Hand of Ragnaros", -1, 80),
        new BackstageItem( 15, 20),
        new BackstageItem( 10, 49),
        new BackstageItem( 5, 49),
        // this conjured item does not work properly yet
        new Item("Conjured Mana Cake", 3, 6)
    };

    GildedRose app = new GildedRose(items);

    int days = 2;
    if (args.length > 0) {
      days = Integer.parseInt(args[0]) + 1;
    }

    for (int i = 0; i < days; i++) {
      System.out.println("-------- day " + i + " --------");
      System.out.println("name, sellIn, quality");
      for (Item item : items) {
        System.out.println(item);
      }
      System.out.println();
      app.updateQuality();
    }
  }

}
