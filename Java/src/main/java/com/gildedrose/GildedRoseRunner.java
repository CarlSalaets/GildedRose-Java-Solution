package com.gildedrose;

import static com.gildedrose.ItemsFileParser.parseItemsFile;
import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Arrays;

public class GildedRoseRunner {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("expecting 2 arguments, but received " + Arrays.toString(args));
		}

		Item[] items = parseItemsFile(Paths.get(args[0]).toAbsolutePath());
		int days = parseInt(args[1]);

		GildedRose app = new GildedRose(items);
		runGildedRoseForDays(app, days);
	}

	public static void runGildedRoseForDays(GildedRose app, int days) {
		runGildedRoseForDays(app, days, System.out);
	}

	public static void runGildedRoseForDays(GildedRose app, int days, PrintStream out) {
		out.println("OMGHAI!");

		for (int i = 0; i <= days; i++) {
			out.println("-------- day " + i + " --------");
			out.println("name, sellIn, quality");
			for (Item item : app.items) {
				out.println(item);
			}
			out.println();
			app.updateQuality();
		}
	}
}
