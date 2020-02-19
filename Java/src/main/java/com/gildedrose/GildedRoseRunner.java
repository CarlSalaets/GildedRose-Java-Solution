package com.gildedrose;

import java.io.PrintStream;

public class GildedRoseRunner {

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
