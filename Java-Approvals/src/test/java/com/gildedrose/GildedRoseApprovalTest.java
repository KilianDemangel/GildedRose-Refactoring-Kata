package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    void normalItem_decreasesQualityAndSellIn() {
        Item[] items = { new Item("foo", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    void normalItem_afterSellDate_decreasesQualityTwice() {
        Item[] items = { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    void agedBrie_increasesQualityAndSellInDecreases() {
        Item[] items = { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, items[0].sellIn);
        assertEquals(1, items[0].quality);
    }

    @Test
    void agedBrie_afterSellDate_increasesQualityTwice() {
        Item[] items = { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    void backstagePass_increasesByTwo_whenSellInIs10OrLess() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality); // +2 (1 de base + 1 bonus)
    }

    @Test
    void backstagePass_dropsToZero_afterConcert() {
        Item[] items = { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    void sulfuras_neverChanges() {
        Item[] items = { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }
}
