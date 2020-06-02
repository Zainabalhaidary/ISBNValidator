package com.tdd.learning;

public class ValidateISBN {

	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean checkISBN(String isbn) {
		if (isbn.length() == LONG_ISBN_LENGTH) {
			return isValidLongDigitISBN(isbn);
		} else if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isValidShortDigitISBN(isbn);
		}
		throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
	}

	private boolean isValidShortDigitISBN(String isbn) {
		int total = 0;
		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			char digit = isbn.charAt(i);
			if (!Character.isDigit(digit)) {
				if (i == 9 && isbn.charAt(i) == 'X') {
					total += 10;
				} else {
					throw new NumberFormatException("Letters are not allowed");
				}
			} else {
				total += (isbn.charAt(i) - '0') * (SHORT_ISBN_LENGTH - i);
			}
		}
		return (total % SHORT_ISBN_MULTIPLIER == 0);
	}

	private boolean isValidLongDigitISBN(String isbn) {
		int total = 0;
		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			if (i % 2 == 0) {// even
				total += isbn.charAt(i) - '0';
			} else {// odd
				total += (isbn.charAt(i) - '0') * 3;
			}
		}
		return (total % LONG_ISBN_MULTIPLIER == 0);
	}

}
