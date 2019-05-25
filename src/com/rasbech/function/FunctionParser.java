package com.rasbech.function;

import java.util.ArrayList;
import java.util.List;

import com.rasbech.operations.ActionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.Operation;

public class FunctionParser {
	private List<String> tokens;
	private String function;

	private FunctionParser(String function) {
		this.function = function;
		this.tokens = getTokens();
	}

	private Function parse() {
		Operation operation = getOperations();
		return new Function(new OperationalTree(operation));
	}

	public static Function parseFunction(String function) {
		FunctionParser fp = new FunctionParser(function);
		return fp.parse();
	}

	private List<String> getTokens() {
		return getTokens(function);
	}

	private List<String> getTokens(String function) {
		List<String> tokens = new ArrayList<String>();
		int bracketBalance = 0;
		String s = "";
		for (int i = 0; i < function.length(); i++) {
			bracketBalance += getBracketCount(function.charAt(i));
			if ((isOperation(function.charAt(i))) && bracketBalance == 0) {
				if (s.equals("") == false)
					tokens.add(s);
				tokens.add(function.charAt(i) + "");
				s = "";
			} else if (function.charAt(i) == ')' && bracketBalance == 0) {
				tokens.add(s + function.charAt(i));
				s = "";
			} else {
				s += function.charAt(i);
			}
		}
		if (s.equals("") == false)
			tokens.add(s);
		return tokens;
	}

	private Operation getOperations() {
		return getOperations(tokens);
	}

	private Operation getOperations(List<String> tokens) {
		System.out.println(tokens);
		prepareTokens(tokens);
		System.out.println(tokens);
		if (tokens.size() == 1)
			if (isMonomial(tokens.get(0)))
				return new ExpressionOperation(tokens.get(0));
			else
				return getOperations(getTokens(tokens.get(0).substring(1, tokens.get(0).length() - 1)));
		return getActionOperation(tokens);
	}

	private Operation getActionOperation(List<String> tokens) {
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (isOperation(tokens.get(i))) {
				List<String> left = tokens.subList(0, i);
				String right = tokens.get(i + 1);
				char sign = tokens.get(i).charAt(0);
				return ActionOperation.getOperationForSign(sign, getOperations(left), getOperations(getTokens(right)));
			}
		}
		return null;
	}

	private boolean isMonomial(String function) {
		return (function.contains("+") || function.contains("-") || function.contains("*")
				|| function.contains("/")) == false;
	}

	private void prepareTokens(List<String> tokens) {
		enforceLeadingZero(tokens);
		addMultiplicationSigns(tokens);
		addBrackets(tokens);
	}

	private void enforceLeadingZero(List<String> tokens) {
		if(tokens.get(0).equals("-"))
			tokens.add(0, "0");
	}

	private void addMultiplicationSigns(List<String> tokens) {
		for (int i = tokens.size() - 1; i > 0; i--)
			if (isBracketPolynomial(tokens.get(i - 1)) && isBracketPolynomial(tokens.get(i)))
				tokens.add(i, "*");
	}

	private void addBrackets(List<String> tokens) {
		if (tokens.size() == 3)
			return;
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (tokens.get(i).equals("/") || tokens.get(i).equals("*")) {
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

	private int getBracketCount(char c) {
		if (c == '(')
			return 1;
		else if (c == ')')
			return -1;
		else
			return 0;
	}

	private boolean isOperation(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private boolean isOperation(String c) {
		return isOperation(c.charAt(0));
	}
}