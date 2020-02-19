package com.gildedrose;

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
