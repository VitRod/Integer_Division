package com.fox.division;

import java.util.ArrayList;

import java.util.List;

public class IntegerDivision {

	public static final String COLUMN_SEPARATOR = "WithDivisorSeparator";
	public static final String COLUMN_RESULT = "WithResult";
	public static final String COLUMN_PLAIN = "Plain";

	public static final String DIVISION_VERTICAL_BAR = "|";
	public static final String DIVISION_VINCULUM = "-";
	public static final String SUBSTRACTION_SYMBOL = "_";
	public static final String NEW_LINE = System.getProperty("line.separator");

	public String divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new ArithmeticException("Error, we cannot  divide by zero");
		} else if (dividend < 0 || divisor < 0) {
			return (dividend + " / " + divisor + " = " + dividend / divisor);
		} else if (dividend <= divisor) {
			throw new IllegalArgumentException(" Dividend should  be  greater than divisor");
		}

		List<String> resultBuilder = new ArrayList<>();
		int currentDividend = dividend;
		int currentDivisor = divisor;
		int quotient;
		int remainder;
		int shift = 0;

		String currentPrintingMethod = COLUMN_SEPARATOR;

		printDividendAndDivisor(dividend, divisor, resultBuilder);

		while (currentDividend >= currentDivisor) {
			List currentResultDivision = intermediateDivision(currentDividend, currentDivisor, shift,
					currentPrintingMethod, resultBuilder);

			currentDividend = (int) currentResultDivision.get(0);
			quotient = (int) currentResultDivision.get(1);
			remainder = (int) currentResultDivision.get(2);
			shift = (int) currentResultDivision.get(3);

			if (currentPrintingMethod.equals(COLUMN_SEPARATOR)) {
				printColumnWithDivisorSeparator(dividend, divisor, quotient, remainder, resultBuilder);
				currentPrintingMethod = COLUMN_RESULT;

			} else if (currentPrintingMethod.equals(COLUMN_RESULT)) {
				printResult(dividend, divisor, remainder, shift, resultBuilder);
				printColumn(quotient, remainder, shift, resultBuilder);
				currentPrintingMethod = COLUMN_PLAIN;

			} else {
				printColumn(quotient, remainder, shift, resultBuilder);

			}

			shift += (countDigits(quotient) - countDigits(quotient - remainder));

		}

		printLastColumn(currentDividend, shift, resultBuilder);

		String res = String.join("", resultBuilder);

		return res;
	}

	private int countDigits(int numberForCounting) {
		if (numberForCounting != 0) {
			return (int) Math.log10(Math.abs(numberForCounting)) + 1;
		} else {
			return 0;
		}
	}

	private void printDividendAndDivisor(int dividend, int divisor, List<String> resultBuilder) {
		IntegerDivision intDiv = new IntegerDivision();

		resultBuilder.add(intDiv.SUBSTRACTION_SYMBOL.toString());

		resultBuilder.add(intDiv.toString().valueOf(dividend));

		resultBuilder.add(intDiv.DIVISION_VERTICAL_BAR.toString());

		resultBuilder.add(intDiv.toString().valueOf(divisor));

		resultBuilder.add(intDiv.NEW_LINE.toString());
	}

	private int pow10(int exponent) {
		return (int) Math.pow(10, exponent);
	}

	private int computeQuotient(int integerForTrim, int numberdigitsForTrim) {
		return integerForTrim / pow10(numberdigitsForTrim);
	}

	private List<Integer> intermediateDivision(int dividend, int divisor, int shift, String printingType,
			List<String> resultBuilder) {
		int remainder = 0;
		int digitNumber = countDigits(dividend) - countDigits(divisor);
		int quotient = computeQuotient(dividend, digitNumber);

		if (quotient >= divisor) {
			remainder = quotient / divisor * divisor;
			dividend = dividend - remainder * pow10(digitNumber);
		} else {
			quotient = computeQuotient(dividend, (digitNumber - 1));
			remainder = quotient / divisor * divisor;
			dividend = dividend - (remainder * pow10(countDigits(dividend) - (countDigits(quotient))));
		}

		List<Integer> resultsOfDivision = new ArrayList<>();
		resultsOfDivision.add(dividend);
		resultsOfDivision.add(quotient);
		resultsOfDivision.add(remainder);
		resultsOfDivision.add(shift);

		return resultsOfDivision;
	}

	private void printColumn(int quotient, int remainder, int shift, List<String> resultBuilder) {
		IntegerDivision intDiv = new IntegerDivision();

		resultBuilder.add(intDiv.NEW_LINE.toString());

		printSymbol(shift, " ", resultBuilder);

		resultBuilder.add(intDiv.SUBSTRACTION_SYMBOL.toString());
		resultBuilder.add(intDiv.toString().valueOf(quotient));
		resultBuilder.add(intDiv.NEW_LINE.toString());

		printSymbol(shift, " ", resultBuilder);

		resultBuilder.add(" ");
		resultBuilder.add(intDiv.toString().valueOf(remainder));
		resultBuilder.add(intDiv.NEW_LINE.toString());

		printSymbol(shift + 1, " ", resultBuilder);

		printSymbol(countDigits(quotient), DIVISION_VINCULUM, resultBuilder);
	}

	private void printSymbol(int numberOfTimes, String symbol, List<String> resultBuilder) {
		for (int i = 1; i <= numberOfTimes; i++) {
			resultBuilder.add(symbol);
		}
	}

	private void printColumnWithDivisorSeparator(int dividend, int divisor, int quotient, int remainder,
			List<String> resultBuilder) {
		IntegerDivision intDiv = new IntegerDivision();

		int dividendDigits = countDigits(dividend);
		int quotientDigits = countDigits(quotient);
		int resultDigits = countDigits(dividend / divisor);
		int divisorDigits = countDigits(divisor);

		resultBuilder.add(" ");

		resultBuilder.add(intDiv.toString().valueOf(remainder));

		printSymbol(dividendDigits - quotientDigits, " ", resultBuilder);

		resultBuilder.add(intDiv.DIVISION_VERTICAL_BAR.toString());

		printSymbol(Math.max(divisorDigits, resultDigits), DIVISION_VINCULUM, resultBuilder);

		resultBuilder.add(intDiv.NEW_LINE.toString());
	}

	private void printResult(int dividend, int divisor, int remainder, int shift, List<String> resultBuilder) {
		IntegerDivision intDiv = new IntegerDivision();

		int dividendDigits = countDigits(dividend);
		int divisorDigits = countDigits(divisor);
		int result = dividend / divisor;

		resultBuilder.add(" ");

		printSymbol(divisorDigits, DIVISION_VINCULUM, resultBuilder);
		printSymbol(dividendDigits - divisorDigits, " ", resultBuilder);

		resultBuilder.add(intDiv.DIVISION_VERTICAL_BAR.toString());

		resultBuilder.add((intDiv.toString().valueOf(result)));

	}

	private void printLastColumn(int remainder, int shift, List<String> resultBuilder) {
		IntegerDivision intDiv = new IntegerDivision();

		resultBuilder.add(intDiv.NEW_LINE.toString());
		printSymbol(shift, " ", resultBuilder);

		resultBuilder.add(" ");
		resultBuilder.add(intDiv.toString().valueOf(remainder));
		resultBuilder.add(intDiv.NEW_LINE.toString());
	}
}
