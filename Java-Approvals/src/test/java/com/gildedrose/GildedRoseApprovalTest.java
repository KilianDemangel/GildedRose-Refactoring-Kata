package com.gildedrose;


import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;


public class GildedRoseApprovalTest {

   @Test
    void updateQualityForMultipleItems(){
        String[] names = {"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"};
        Integer[] qualities = {-5, -1, 0, 5, 49, 50, 51};
        Integer[] sellIns = {-8,-1,8,1,5,6,8,10,11,12,15 };

        //THEN 
        CombinationApprovals.verifyAllCombinations(this::doUpdateQuality,
                names, sellIns, qualities);
    }

    private String doUpdateQuality(String name, int sellIn, int quality) {
        //GIVEN
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);

        //WHEN
        app.updateQuality();

        //THEN
        return app.items[0].toString();
    }
}
