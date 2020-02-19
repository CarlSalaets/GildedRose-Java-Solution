package com.gildedrose.item;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.gildedrose.Item;

public class ItemsFileParser {

	private static final Pattern ITEM_PATTERN = Pattern.compile("^(.*),\\s*(-?\\d+)\\s*,\\s*(\\d+)\\s*$");

	public static Item[] parseItemsFile(Path file) throws IOException {
		try (Stream<String> items = Files.lines(file)) {
			return items
					.map(ItemsFileParser::parseItem)
					.filter(Objects::nonNull)
					.toArray(Item[]::new);
		}
	}

	private static Item parseItem(String item) {
		if (item == null || item.isBlank()) {
			return null;
		}
		Matcher matcher = ITEM_PATTERN.matcher(item);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("This could not be parsed as an item: " + item);
		}

		String name = matcher.group(1).trim();
		int sellIn = parseInt(matcher.group(2));
		int quality = parseInt(matcher.group(3));

		return new Item(name, sellIn, quality);
	}
}
