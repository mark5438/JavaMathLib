package com.rasbech.function;

import java.util.ArrayList;
import java.util.List;

public class TokenGenerator {
	private final List<String> tokens;
	private final String function;

	private TokenGenerator(String function) {
		tokens = new ArrayList<String>();
		this.function = function;
	}

	private List<String> parse() {
		int bracketBalance = 0;
		String s = "";
		for (int i = 0; i < function.length(); i++) {
			bracketBalance += getBracketCount(function.charAt(i));
			if ((isOperation(function.charAt(i))) && bracketBalance == 0) {
				if (s.equals("") == false)
					tokens.add(s);
				tokens.add(function.charAt(i) + "");
				s = "";
			} else if (function.charAt(i) == '(' && bracketBalance == 1) {
				if (s.equals("") == false)
					tokens.add(s);
				s = "(";
			} else if (function.charAt(i) == ')' && bracketBalance == 0) {
				tokens.add(s + ")");
				s = "";
			} else {
				s += function.charAt(i);
			}
		}
		if (s.equals("") == false)
			tokens.add(s);
		prepareTokens();
		return tokens;

	}

	public static List<String> getTokens(String function) {
		return new TokenGenerator(function).parse();
	}

	private int getBracketCount(char c) {
		if (c == '(')
			return 1;
		else if (c == ')')
			return -1;
		else
			return 0;
	}

	private void prepareTokens() {
		enforceLeadingZero();
		addMultiplicationBracketSigns();
		expandExpressions();
		addPowerBrackets();
		addMultiplicationBrackets();
	}

	private void enforceLeadingZero() {
		if (tokens.get(0).equals("-"))
			tokens.add(0, "0");
	}

	private void addMultiplicationBracketSigns() {
		for (int i = tokens.size() - 1; i > 0; i--) {
			if (isBracketPolynomial(tokens.get(i - 1)) && isBracketPolynomial(tokens.get(i)))
				tokens.add(i, "*");
			if (isBracketPolynomial(tokens.get(i)) && isMonomial(tokens.get(i - 1)))
				tokens.add(i, "*");
		}
	}

	private void expandExpressions() {
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (isMonomial(tokens.get(i))) {
				List<String> expression = splitExpression(tokens.get(i));
				tokens.remove(i);
				tokens.addAll(i, expression);
			}
		}
	}

	private void addPowerBrackets() {
		addBracketsForChar('^');
	}

	private void addMultiplicationBrackets() {
		addBracketsForChar('/');
		addBracketsForChar('*');
	}

	// Pre-condition: Expression must be single variable after a number
	private List<String> splitExpression(String expression) {
		List<String> tokens = new ArrayList<String>();
		if (Character.isDigit(expression.charAt(expression.length() - 1)) || expression.length() == 1) {
			tokens.add(expression);
			return tokens;
		}
		tokens.add(expression.substring(0, expression.length() - 1));
		tokens.add("*");
		tokens.add(expression.charAt(expression.length() - 1) + "");
		return tokens;
	}

	private void addBracketsForChar(char c) {
		if (tokens.size() == 3)
			return;
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (tokens.get(i).equals(c + "")) {
				String operation = "(" + tokens.get(i - 1) + tokens.get(i) + tokens.get(i + 1) + ")";
				tokens.remove(i + 1);
				tokens.remove(i);
				tokens.set(i - 1, operation);
				i--;
			}
		}
	}

	private boolean isBracketPolynomial(String polynomial) {
		return polynomial.charAt(0) == '(' && polynomial.charAt(polynomial.length() - 1) == ')';
	}

	private boolean isMonomial(String function) {
		return (function.contains("+") || function.contains("-") || function.contains("*") || function.contains("/")
				|| function.contains("^")) == false;
	}

	private boolean isOperation(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}
}