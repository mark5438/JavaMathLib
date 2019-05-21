package com.rasbech;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private final String function;

	public Function(String function) {
		this.function = function;
	}

	public Function getDerivative() {
		String[] tokens = getTokens();
		if (correctSyntax() == false)
			return null;
		for(String token : tokens) {
			System.out.println(token);
		}

		return null;
	}

	private boolean correctSyntax() {
		return matchingBrackets();
	}

	private boolean matchingBrackets() {
		return countCharacter('(') == countCharacter(')');
	}

	private int countCharacter(char c) {
		int sum = 0;
		for (int i = 0; i < function.length(); i++)
			if (function.charAt(i) == c)
				sum++;
		return sum;
	}

	private String[] getTokens() {
		char[] characters = new char[function.length()];
		List<String> tokens = new ArrayList<String>();
		String s = "";
		for (int i = 0; i < function.length(); i++) {
			if(isOperation(function.charAt(i))) {
				tokens.add(s);
				tokens.add(function.charAt(i) + "");
				s = "";
				continue;
			}
			s += function.charAt(i);
		}
		tokens.add(s);
		return tokens.toArray(new String[tokens.size()]);
	}

	public static boolean isOperation(char c) {
		return (c + "").matches("[+-/*]");
	}

	@Override
	public String toString() {
		return function;
	}
}