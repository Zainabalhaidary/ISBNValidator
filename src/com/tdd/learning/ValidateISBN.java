package com.tdd.learning;

public class ValidateISBN {

	public boolean checkISBN(String isbn) {
		int total = 0;
		if (isbn.length() == 13) {
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {// even
					total += isbn.charAt(i) - '0';
				} else {// odd
					total += (isbn.charAt(i) - '0') * 3;
				}
			}
		} else {
			if (isbn.length() != 10) {
				throw new NumberFormatException("ISBN numbers must be 10 digits long");
			}
			for (int i = 0; i < 10; i++) {
				char digit = isbn.charAt(i);
				if (!Character.isDigit(digit)) {
					if (i == 9 && isbn.charAt(i) == 'X') {
						total += 10;
					} else {
						throw new NumberFormatException("Letters are not allowed");
					}
				} else {
					total += (isbn.charAt(i) - '0') * (10 - i);
				}
			}
		}
		if (total % 11 == 0) {
			return true;
		}
		return false;
	}

}
