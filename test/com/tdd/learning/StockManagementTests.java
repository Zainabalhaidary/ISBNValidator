package com.tdd.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {
		ExternalISBNDataService testWebService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice and Men", "John Steinbeck");
			}
		};

		ExternalISBNDataService databaseService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return null;
			}
		};
		String isbn = "0140177396";
		StockManager stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(databaseService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		String isbn = "0140177396";
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDatabaseService(databaseService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(webService, never()).lookup(anyString());

	}
	
	@Test
	void webServiceIsUsedWhenDataIsNotInDatabase() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		String isbn = "0140177396";
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDatabaseService(databaseService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(webService).lookup("0140177396");
	}

}
