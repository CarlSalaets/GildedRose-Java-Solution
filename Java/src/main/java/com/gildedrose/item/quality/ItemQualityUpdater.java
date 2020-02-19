package com.gildedrose.item.quality;

import com.gildedrose.Item;

public interface ItemQualityUpdater {

	default void decreaseSellInAndUpdateQuality(Item item) {
		decreaseSellIn(item);
		updateQuality(item);
	}

	default void decreaseSellIn(Item item) {
		item.sellIn--;
	}

	void updateQuality(Item item);
}
