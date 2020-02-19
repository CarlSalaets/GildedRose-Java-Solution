package com.gildedrose.item;

import static com.gildedrose.item.ItemsFileParser.parseItemsFile;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.writeString;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class ItemsFileParserTest {

	@Test
	public void parsesEmptyFile() throws IOException {
		Path file = createEmptyFile();
		assertThat(parseItemsFile(file))
				.isNotNull()
				.isEmpty();
	}

	@Test
	public void parsesFileWithOneLineWithoutSpaces() throws IOException {
		Path file = createFileContaining(
				"item,3,26"
		);

		assertThat(parseItemsFile(file))
				.usingFieldByFieldElementComparator()
				.containsExactly(
						new Item("item", 3, 26)
				);
	}

	@Test
	public void parsesItemWithNegativeSellIn() throws IOException {
		Path file = createFileContaining(
				"overdue,-3,0"
		);

		assertThat(parseItemsFile(file))
				.usingFieldByFieldElementComparator()
				.containsExactly(
						new Item("overdue", -3, 0)
				);
	}

	@Test
	public void parsesFileWithOneLineWithSpaces() throws IOException {
		Path file = createFileContaining(
				"    item ,  3 , 26     "
		);

		assertThat(parseItemsFile(file))
				.usingFieldByFieldElementComparator()
				.containsExactly(
						new Item("item", 3, 26)
				);
	}

	@Test
	public void parsesFileWithMultipleLines() throws IOException {
		Path file = createFileContaining(
				"apple   , 11, 7",
				"  berry,22, 9",
				"cucumber,321,0"
		);

		assertThat(parseItemsFile(file))
				.usingFieldByFieldElementComparator()
				.containsExactly(
						new Item("apple", 11, 7),
						new Item("berry", 22, 9),
						new Item("cucumber", 321, 0)
				);
	}

	@Test
	public void parsingSkipsEmptyLines() throws IOException {
		Path file = createFileContaining(
				"apple, 1, 13",
				"",
				"cucumber,3,14",
				""
		);

		assertThat(parseItemsFile(file))
				.usingFieldByFieldElementComparator()
				.containsExactly(
						new Item("apple", 1, 13),
						new Item("cucumber", 3, 14)
				);
	}

	private static Path createEmptyFile() throws IOException {
		return createTempFile(ItemsFileParserTest.class.getSimpleName(), "");
	}

	private static Path createFileContaining(String content) throws IOException {
		return writeString(createEmptyFile(), content);
	}

	private static Path createFileContaining(String... content) throws IOException {
		return createFileContaining(String.join("\n", content));
	}
}