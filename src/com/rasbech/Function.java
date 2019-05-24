package com.rasbech;

import java.util.ArrayList;
import java.util.List;

import com.rasbech.operations.DivisionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.MinusOperation;
import com.rasbech.operations.MultiplicationOperation;
import com.rasbech.operations.Operation;
import com.rasbech.operations.PlusOperation;
import com.rasbech.util.SignComparator;

public class Function {
	private OperationalTree functionTree;

	// TODO: Check if function has correct syntax
	// TODO: Make () work properly:
	// TODO: (5x+8)/x^2
	// (200x+9x^2)/(2x^3+3)
	private Function(OperationalTree functionalTree) {
		this.functionTree = functionalTree;
	}

	public double evaluate(double variable) {
		return functionTree.evaluate(variable);
	}

	// TODO: Clean function. Split into sub functions
	public static Function parseFunction(String function) {
		List<String> tokens = getTokens(function);
		Operation operation = getOperations(tokens);
		return new Function(new OperationalTree(operation));
	}

	private static List<String> getTokens(String function) {
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

	// TODO: I did not start writing this function, but i predict a comment i might
	// put here:
	// TODO: Clean up
	private static Operation getOperations(List<String> tokens) {
		addBrackets(tokens);
		if (tokens.size() == 1) {
			if (isMonomial(tokens.get(0)))
				return new ExpressionOperation(tokens.get(0));
			else
				return getOperations(getTokens(tokens.get(0).substring(1, tokens.get(0).length() - 1)));
		}
		if(isAllMultiplicationAndDivision(tokens)) {
			for(int i = tokens.size() - 1; i > -1; i--){
				if(isOperation(tokens.get(i))) {
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

	private static boolean isAllMultiplicationAndDivision(List<String> tokens) {
		for (String token : tokens)
			if (isOperation(token))
				if (token.equals("-") || token.equals("+"))
					return false;
		return true;
	}

	private static List<String> getTokensFromExpressionsList(List<String> expressions) {
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

	private static boolean isMonomial(String function) {
		return (function.contains("+") || function.contains("-") || function.contains("*")
				|| function.contains("/")) == false;
	}

	private static void addBrackets(List<String> tokens) {
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

	private static int getBracketCount(char c) {
		if (c == '(')
			return 1;
		else if (c == ')')
			return -1;
		else
			return 0;
	}

	private static boolean isOperation(char c) {
		return (c + "").matches("[+-/*]");
	}

	// Conditions:
	// c.length != 0
	// c.length preferred to be 1, but higher is accepted
	// PLEASE FIX THAT!!!!!!!!!!!!!!!!!
	// c != null
	private static boolean isOperation(String c) {
		return isOperation(c.charAt(0));
	}

	@Override
	public String toString() {
		return functionTree.toString();
	}
}
