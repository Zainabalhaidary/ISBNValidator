package com.tdd.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class StockManagementTests {
	
	ExternalISBNDataService testWebService;
	ExternalISBNDataService databaseService;
	StockManager stockManager;
	@BeforeEach
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class);
		databaseService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(databaseService);
	}

	@Test
	void testCanGetACorrectLocatorCode() {
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "John Steinbeck"));
		when(databaseService.lookup(anyString())).thenReturn(null);
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	void databaseIsUsedIfDataIsPresent() {
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());

	}
	
	@Test
	void webServiceIsUsedWhenDataIsNotInDatabase() {
		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		verify(databaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}

}
