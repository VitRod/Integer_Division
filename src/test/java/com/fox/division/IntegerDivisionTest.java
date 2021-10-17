package com.fox.division;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntegerDivisionTest {

	public static final String NEW_LINE = System.getProperty("line.separator");

	IntegerDivision division;

	@BeforeEach
	public void setUp() {
		division = new IntegerDivision();
	}

	@Test
	@DisplayName("Dividing  by  zero")
	void givenDivisorIsZero_whenDivide_thenThrowArithmeticException() {

		assertThrows(ArithmeticException.class, () -> division.divide(1234567, 0));

	}
		
	@Test
	@DisplayName("Given dividend  is greater than divisor and  then result is  printed")
	void givenDividendIsGreaterThanDivisor_whenDivide_thenPrintResult() {
		String expected = "_78945|4" + NEW_LINE +
						  " 4    |-----" + NEW_LINE +
						  " -    |19736" + NEW_LINE + 
						  "_38" + NEW_LINE + 
						  " 36" + NEW_LINE + 
						  " --" + NEW_LINE + 
						  " _29"+ NEW_LINE + 
						  "  28" + NEW_LINE + 
						  "  --" + NEW_LINE + 
						  "  _14" + NEW_LINE + 
						  "   12" + NEW_LINE + 
						  "   --" + NEW_LINE + 
						  "   _25" + NEW_LINE + 
						  "    24" + NEW_LINE + 
						  "    --" + NEW_LINE + 
						  "     1" + NEW_LINE ;

		String actual = division.divide(78945, 4);

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Dividend and divisor are equal then print IllegalArgumentException")
	void givenDividendIsEqualToDivisor_whenDivide_thenIllegalArgumentException() {

		assertThrows(IllegalArgumentException.class, () -> division.divide(4, 4));

	}

	@Test
	@DisplayName("Dividend is less than the divisor then print IllegalArgumentException")
	void givenDividendIsLessThanDivisor_whenDivide_thenIllegalArgumentException() {

		assertThrows(IllegalArgumentException.class, () -> division.divide(1, 5));
	}
}

