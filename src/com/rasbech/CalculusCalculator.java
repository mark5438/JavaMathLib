package com.rasbech;

import java.util.Scanner;

public class CalculusCalculator {
	private static Scanner scanner = new Scanner(System.in);
	
	public CalculusCalculator() {
		//String input = getInputString(">");
//		String input = "(8x^3-200x+9x^2)/(-2x^3-3)-3x+7*54x^2";
		String input = "x";
		Function function = Function.parseFunction(input);
		System.out.println(function.evaluate(5));
	}
	
	public static void main(String[] args) {
		new CalculusCalculator();
	}
	
	public static String getInputString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}
	
	public static int getInputInteger(String prompt) {
		try {
			return Integer.parseInt(getInputString(prompt));
		} catch (NumberFormatException e) {
			System.err.println("A number was requested, but given in wrong format");
			return -1;
		}
	}
}