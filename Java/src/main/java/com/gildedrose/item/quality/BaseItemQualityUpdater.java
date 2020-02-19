package com.gildedrose.item.quality;

import com.gildedrose.Item;

public enum BaseItemQualityUpdater implements ItemQualityUpdater {
	DEFAULT {
		@Override
		public void updateQuality(Item item) {
			decreaseQualityByOne(item);
			if (item.sellIn < 0) {
				decreaseQualityByOne(item);
			}
		}
	},
	AGED_BRIE {
		@Override
		public void updateQuality(Item item) {
			increaseQualityByOne(item);
			if (item.sellIn < 0) {
				increaseQualityByOne(item);
			}
		}
	},
	BACKSTAGE_PASSES {
		@Override
		public void updateQuality(Item item) {
			if (item.sellIn < 0) {
				item.quality = 0;
			} else if (item.quality < 50) {
				item.quality++;

				if (item.sellIn < 10) {
					increaseQualityByOne(item);
				}

				if (item.sellIn < 5) {
					increaseQualityByOne(item);
				}
			}
		}
	},
	LEGENDARY {
		@Override
		public void decreaseSellIn(Item item) {
			// sellIn does not decrease for legendary items
		}

		@Override
		public void updateQuality(Item item) {
			// quality does not decrease for legendary items
		}
	};

	private static void decreaseQualityByOne(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}
	}

	private static void increaseQualityByOne(Item item) {
		if (item.quality < 50) {
			item.quality++;
		}
	}
}
