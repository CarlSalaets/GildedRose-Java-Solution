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
		switch (item.name) {
			case AGED_BRIE:
				updateQualityOfAgedBrie(item);
				break;
			case BACKSTAGE_PASSES:
				updateQualityOfBackstagePasses(item);
				break;
			case SULFURAS:
				updateQualityOfSulfuras(item);
				break;
			default:
				updateQualityOfSimpleItem(item);
				break;
		}
	}

	private static void updateQualityOfAgedBrie(Item item) {
		if (item.quality < 50) {
			item.quality++;
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			if (item.quality < 50) {
				item.quality++;
			}
		}
	}

	private static void updateQualityOfBackstagePasses(Item item) {
		if (item.quality < 50) {
			item.quality++;

			if (item.sellIn < 11) {
				if (item.quality < 50) {
					item.quality++;
				}
			}

			if (item.sellIn < 6) {
				if (item.quality < 50) {
					item.quality++;
				}
			}
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			item.quality = 0;
		}
	}

	private static void updateQualityOfSulfuras(Item item) {
		// do nothing
	}

	private static void updateQualityOfSimpleItem(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}

		item.sellIn--;

		if (item.sellIn < 0) {
			if (item.quality > 0) {
				item.quality--;
			}
		}
	}
}