package com.fox.division;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter dividend (> 0)");
		int dividend = scanner.nextInt();
		
		System.out.println("Enter divisor (> 0)");
		int divisor = scanner.nextInt();
	
		IntegerDivision integerDivision = new  IntegerDivision();
		String result =  integerDivision.divide(dividend, divisor);
		
		System.out.println(result);	
	}

}
