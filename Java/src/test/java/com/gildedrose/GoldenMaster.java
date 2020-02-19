package com.gildedrose;

import static com.gildedrose.GildedRoseRunner.runGildedRoseForDays;
import static java.nio.file.Files.newOutputStream;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class GoldenMaster {

	@Test
	public void test30() throws IOException {
		Item[] items = new Item[]{
				new Item("+5 Dexterity Vest", 10, 20), //
				new Item("Aged Brie", 2, 0), //
				new Item("Elixir of the Mongoose", 5, 7), //
				new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
				new Item("Sulfuras, Hand of Ragnaros", -1, 80),
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
				// this conjured item does not work properly yet
				new Item("Conjured Mana Cake", 3, 6) };

		GildedRose app = new GildedRose(items);

		runAndCompare(app, 30,
				Paths.get("../texttests/ThirtyDays/stdout.gr"));
	}

	private static void runAndCompare(GildedRose app, int days, Path expected) throws IOException {
		Path resultFile = Files.createTempFile("GildedRose.", ".actual." + days);
		try (OutputStream out = newOutputStream(resultFile);
			 PrintStream printOut = new PrintStream(out)) {
			runGildedRoseForDays(app, days, printOut);
		}

		assertThat(resultFile).hasSameTextualContentAs(expected.toAbsolutePath().normalize());
	}
}
