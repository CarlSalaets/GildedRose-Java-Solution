package com.gildedrose;

import java.util.function.Consumer;

public enum ItemQualityUpdater implements Consumer<Item> {
	DEFAULT {
		@Override
		public void accept(Item item) {
			item.sellIn--;

			decreaseQuality(item);
			if (item.sellIn < 0) {
				decreaseQuality(item);
			}
		}
	},
	AGED_BRIE {
		@Override
		public void accept(Item item) {
			item.sellIn--;

			increaseQuality(item);
			if (item.sellIn < 0) {
				increaseQuality(item);
			}
		}
	},
	BACKSTAGE_PASSES {
		@Override
		public void accept(Item item) {
			item.sellIn--;

			if (item.sellIn < 0) {
				item.quality = 0;
			} else if (item.quality < 50) {
				item.quality++;

				if (item.sellIn < 10) {
					increaseQuality(item);
				}

				if (item.sellIn < 5) {
					increaseQuality(item);
				}
			}
		}
	},
	LEGENDARY {
		@Override
		public void accept(Item item) {
			// do nothing
		}
	};

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
