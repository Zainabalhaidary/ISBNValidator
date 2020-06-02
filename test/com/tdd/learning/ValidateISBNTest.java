package com.tdd.learning;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Al-HaidaryZ
 * 
 * ISBN-10: 0747528306
   ISBN-10: 1408855674
   ISBN-10: 097522980X with X
   ISBN-13: 9780747528302
 *
 */
class ValidateISBNTest {

	@Test
	void checkAValidShortDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0747528306");
		assertTrue(result, "first value 10 digits ISBN");
		
		result = validator.checkISBN("1408855674");
		assertTrue(result, "Second value 10 digits ISBN");
	}
	
	@Test
	void checkAValidLongDigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780747528302");
		assertTrue(result, "third value 13 digits ISBN");
	}

	
	@Test
	void isbnTenDigitNumberEndingWithXIsValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("097522980X");
		assertTrue(result, "Ten digits");
		
		result = validator.checkISBN("097522980X");
		assertTrue(result, "Thirteen digits");
	}
	
	@Test
	void checkATenDigitInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0747528307");
		assertFalse(result,"10 digit invalid ISBN");
	}
	
	@Test
	void checkAThirteenDigitInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780747528303");
		assertFalse(result, "13 digit invalid ISBN");
	}

	@Test
	void nineDigitsISBNAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, ()->{validator.checkISBN("074752830");});
		
	}
	
	@Test
	void lettersInISBNAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, ()->{validator.checkISBN("helloworld");});
		
	}

}
