package com.gildedrose.item.quality;

import com.gildedrose.Item;

public class ConjuredItemQualityUpdater implements ItemQualityUpdater {

	private final ItemQualityUpdater normalItemQualityUpdater;

	public ConjuredItemQualityUpdater(ItemQualityUpdater normalItemQualityUpdater) {
		this.normalItemQualityUpdater = normalItemQualityUpdater;
	}

	@Override
	public void updateQuality(Item item) {
		normalItemQualityUpdater.updateQuality(item);
		normalItemQualityUpdater.updateQuality(item);
	}
}
