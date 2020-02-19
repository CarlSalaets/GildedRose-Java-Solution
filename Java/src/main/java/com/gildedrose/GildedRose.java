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
		increaseQuality(item);

		item.sellIn--;

		if (item.sellIn < 0) {
			increaseQuality(item);
		}
	}

	private static void updateQualityOfBackstagePasses(Item item) {
		if (item.quality < 50) {
			item.quality++;

			if (item.sellIn < 11) {
				increaseQuality(item);
			}

			if (item.sellIn < 6) {
				increaseQuality(item);
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
		decreaseQuality(item);

		item.sellIn--;

		if (item.sellIn < 0) {
			decreaseQuality(item);
		}
	}

	private static void decreaseQuality(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}
	}

	private static void increaseQuality(Item item) {
		if (item.quality < 50) {
			item.quality++;
		}
	}
}