package com.gildedrose;

import com.gildedrose.item.quality.BaseItemQualityUpdater;
import com.gildedrose.item.quality.ConjuredItemQualityUpdater;
import com.gildedrose.item.quality.ItemQualityUpdater;

class GildedRose {

	private static final String CONJURED_ITEM = "Conjured ";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			getItemQualityUpdater(item.name)
					.decreaseSellInAndUpdateQuality(item);
		}
	}

	private static ItemQualityUpdater getItemQualityUpdater(String itemName) {
		if (!itemName.startsWith(CONJURED_ITEM)) {
			switch (itemName) {
				case AGED_BRIE:
					return BaseItemQualityUpdater.AGED_BRIE;
				case BACKSTAGE_PASSES:
					return BaseItemQualityUpdater.BACKSTAGE_PASSES;
				case SULFURAS:
					return BaseItemQualityUpdater.LEGENDARY;
				default:
					return BaseItemQualityUpdater.DEFAULT;
			}
		}
		return new ConjuredItemQualityUpdater(getItemQualityUpdater(
				itemName.substring(CONJURED_ITEM.length()).trim()
		));
	}
}