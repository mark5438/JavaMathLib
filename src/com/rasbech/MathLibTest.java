package com.rasbech;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.rasbech.function.Function;

public class MathLibTest {
	private static Scanner scanner = new Scanner(System.in);
	
	public MathLibTest() {
//		String input = getInputString(">");
		String input = "xy^2";
		Function function = Function.parseFunction(input);
		System.out.println("Function parsed: ");
		System.out.println(function);
		
		Map<Character, Double> variableValues = new TreeMap<Character, Double>();
		char[] variables = function.getVariables();
		
		for(char c : variables) {
			variableValues.put(c, getInputDouble(c + " = "));
		}
		
		System.out.println(function.evaluate(variableValues));
	}
	
	public static void main(String[] args) {
		new MathLibTest();
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
	
	public static double getInputDouble(String prompt) {
		try {
			return Double.parseDouble(getInputString(prompt));
		} catch (NumberFormatException e) {
			System.err.println("A number was requested, but given in wrong format");
			return -1;
		}
	}
}