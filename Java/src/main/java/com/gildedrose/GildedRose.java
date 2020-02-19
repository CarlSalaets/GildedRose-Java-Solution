package com.gildedrose;

class GildedRose {

	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			updateQuality(item);
		}
	}

	private static void updateQuality(Item item) {
		getItemQualityUpdater(item)
				.decreaseSellInAndUpdateQuality(item);
	}

	private static ItemQualityUpdater getItemQualityUpdater(Item item) {
		switch (item.name) {
			case AGED_BRIE:
				return StandardItemQualityUpdater.AGED_BRIE;
			case BACKSTAGE_PASSES:
				return StandardItemQualityUpdater.BACKSTAGE_PASSES;
			case SULFURAS:
				return StandardItemQualityUpdater.LEGENDARY;
			default:
				return StandardItemQualityUpdater.DEFAULT;
		}
	}
}