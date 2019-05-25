package com.rasbech.function;

import java.util.ArrayList;
import java.util.List;

import com.rasbech.operations.DivisionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.MinusOperation;
import com.rasbech.operations.MultiplicationOperation;
import com.rasbech.operations.Operation;
import com.rasbech.operations.PlusOperation;
import com.rasbech.util.SignComparator;

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
			if (isOperation(function.charAt(i)) && bracketBalance == 0) {
				if (s.equals("") == false)
					tokens.add(s);
				tokens.add(function.charAt(i) + "");
				s = "";
			} else
				s += function.charAt(i);
		}
		tokens.add(s);
		return tokens;
	}

	private Operation getOperations() {
		return getOperations(tokens);
	}
	
	// TODO: I did not start writing this function, but i predict a comment i might
	// put here:
	// TODO: Clean up
	private Operation getOperations(List<String> tokens) {
		addBrackets(tokens);
		if (tokens.size() == 1) {
			if (isMonomial(tokens.get(0)))
				return new ExpressionOperation(tokens.get(0));
			else
				return getOperations(getTokens(tokens.get(0).substring(1, tokens.get(0).length() - 1)));
		}
		if (isAllMultiplicationAndDivision(tokens)) {
			for (int i = tokens.size() - 1; i > -1; i--) {
				if (isOperation(tokens.get(i))) {
					List<String> left = tokens.subList(0, i);
					String right = tokens.get(i + 1);
					char sign = tokens.get(i).charAt(0);
					if (sign == '/')
						return new DivisionOperation(getOperations(left), getOperations(getTokens(right)));
					if (sign == '*')
						return new MultiplicationOperation(getOperations(left), getOperations(getTokens(right)));
				}
			}
			return null;
		}
		if (isOperation(tokens.get(0)) == false)
			tokens.add(0, "+");
		List<String> expressions = new ArrayList<String>();
		for (int i = 0; i < tokens.size(); i += 2)
			expressions.add(tokens.get(i) + tokens.get(i + 1));
		expressions.sort(new SignComparator());
		tokens = getTokensFromExpressionsList(expressions);
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (isOperation(tokens.get(i))) {
				List<String> left = tokens.subList(0, i);
				String right = tokens.get(i + 1);
				char sign = tokens.get(i).charAt(0);
				if (sign == '-')
					return new MinusOperation(getOperations(left), getOperations(getTokens(right)));
				if (sign == '+')
					return new PlusOperation(getOperations(left), getOperations(getTokens(right)));
			}
		}
		return null;
	}

	private boolean isAllMultiplicationAndDivision(List<String> tokens) {
		for (String token : tokens)
			if (isOperation(token))
				if (token.equals("-") || token.equals("+"))
					return false;
		return true;
	}

	private List<String> getTokensFromExpressionsList(List<String> expressions) {
		List<String> tokens = new ArrayList<String>();
		for (String s : expressions) {
			tokens.add(s.charAt(0) + "");
			tokens.add(s.substring(1));
		}
		if (tokens.get(0).equals("+"))
			tokens.remove(0);
		if (tokens.get(0).equals("-"))
			tokens.add(0, "0");
		return tokens;
	}

	private boolean isMonomial(String function) {
		return (function.contains("+") || function.contains("-") || function.contains("*")
				|| function.contains("/")) == false;
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

	// Conditions:
	// c.length != 0
	// c.length preferred to be 1, but higher is accepted
	// PLEASE FIX THAT!!!!!!!!!!!!!!!!!
	// c != null
	private boolean isOperation(String c) {
		return isOperation(c.charAt(0));
	}
}