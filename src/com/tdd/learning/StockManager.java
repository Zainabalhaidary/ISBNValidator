package com.tdd.learning;

public class StockManager {
	private ExternalISBNDataService webService;
	private ExternalISBNDataService databaseService;

	public void setDatabaseService(ExternalISBNDataService databaseService) {
		this.databaseService = databaseService;
	}

	public void setWebService(ExternalISBNDataService service) {
		this.webService = service;
	}

	public String getLocatorCode(String isbn) {
		Book book = databaseService.lookup(isbn);
		if(book==null) {
			book = webService.lookup(isbn);
		}
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length()-4));
		locator.append(book.getAuthor().charAt(0));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}

}
