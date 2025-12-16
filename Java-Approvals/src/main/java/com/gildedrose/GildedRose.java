package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isSulfuras(item)) {
                continue; // Sulfuras ne change jamais
            }

            updateQualityBeforeSellDatePassed(item);

            // sellIn baisse pour tout sauf Sulfuras (déjà géré)
            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                updateQualityAfterSellDatePassed(item);
            }
        }
    }

    private void updateQualityBeforeSellDatePassed(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
            return;
        }

        if (isBackstage(item)) {
            increaseQuality(item); // +1 de base
            if (item.sellIn < 11) increaseQuality(item); // +1 bonus
            if (item.sellIn < 6)  increaseQuality(item); // +1 bonus
            return;
        }

        // item normal
        decreaseQuality(item);
    }

    private void updateQualityAfterSellDatePassed(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item); // Brie augmente encore (donc +1 supplémentaire)
            return;
        }

        if (isBackstage(item)) {
            item.quality = 0; // après concert, qualité tombe à 0
            return;
        }

        // item normal : se dégrade deux fois plus vite
        decreaseQuality(item);
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.name);
    }

    private boolean isBackstage(Item item) {
        return BACKSTAGE.equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS.equals(item.name);
    }
}
