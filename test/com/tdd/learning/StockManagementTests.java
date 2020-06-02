package com.tdd.learning;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {
		ExternalISBNDataService testService = new ExternalISBNDataService() {
			
			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice and Men", "John Steinbeck");
			}
		};
		String isbn = "0140177396";
		StockManager stockManager = new StockManager();
		stockManager.setService(testService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
