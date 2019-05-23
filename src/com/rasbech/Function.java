package com.rasbech;

import java.util.ArrayList;
import java.util.List;

import com.rasbech.operations.ActionOperation;
import com.rasbech.operations.DivisionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.MinusOperation;
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
		String[] tokens = getTokens(function);
		Operation operation = getOperations(tokens);
		return new Function(new OperationalTree(operation));
	}

	private static String[] getTokens(String function) {
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
		return tokens.toArray(new String[tokens.size()]);
	}

	// TODO: I did not start writing this function, but i predict a comment i might
	// put here:
	// TODO: Clean up
	private static Operation getOperations(String[] tokens) {
		// for (String s : tokens)
		// System.out.println(s);
		// System.out.println("\n");

		// TODO: Check if function is single expression
		// TODO: If so use ExpressionOperation instead
		if (tokens.length == 1)
			return new ExpressionOperation(tokens[0]);
		ActionOperation operation = null;
		String left = null, right = null;
		String function = String.join("", tokens);

		if (hasDivisionOrMultiplication(function)) {
			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i].length() == 1) {
					if (isDivision(tokens[i].charAt(0))) {
						operation = new DivisionOperation();
						left = tokens[i - 1];
						right = tokens[i + 1];
						break;
					}
				}
			}
			if (isPolynomial(left)) {
				operation.setLeftOperation(getOperations(getTokens(prepareFunction(left))));
			}
			if (isPolynomial(right)) {
				operation.setRightOperation(getOperations(getTokens(prepareFunction(right))));
			}
		} else if (isPolynomial(function)) {
			operation = getNonMultiplyPolynomialOperation(tokens);
		}

		return operation;
	}

	// TODO: Clean up. Split function
	private static ActionOperation getNonMultiplyPolynomialOperation(String[] tokensArray) {
		List<String> tokens = getList(tokensArray);
		if (isOperation(tokens.get(0)) == false)
			tokens.add(0, "+");
		List<String> expressions = new ArrayList<String>();
		for (int i = 0; i < tokens.size(); i += 2)
			expressions.add(tokens.get(i) + tokens.get(i + 1));
		expressions.sort(new SignComparator());
		String[] sortedTokens = expressionsListToTokenArray(expressions);
		for (int i = sortedTokens.length - 2; i > -1; i -= 2) {
			char sign = sortedTokens[i].charAt(0);
			String[] remainder = getElementsAtIndexes(sortedTokens, 0, i);
			Operation right = new ExpressionOperation(sortedTokens[i + 1]);
			Operation left = null;
			if(remainder.length == 1)
				left = new ExpressionOperation(sortedTokens[i - 1]);
			else
				left = getNonMultiplyPolynomialOperation(remainder);
			
			if(sign == '-')
				return new MinusOperation(left, right);
			if(sign == '+')
				return new PlusOperation(left, right);
		}
		return null;
	}

	// TODO: Go to stack overflow. This can be done easier
	private static String[] getElementsAtIndexes(String[] arr, int begin, int end) {
		String[] out = new String[end - begin];
		for (int i = begin, j = 0; i < end; i++, j++) {
			out[j] = arr[i];
		}
		return out;
	}

	private static String[] expressionsListToTokenArray(List<String> expressions) {
		List<String> tokens = new ArrayList<String>();
		for (String s : expressions) {
			char sign = s.charAt(0);
			String expression = s.substring(1);
			tokens.add(sign + "");
			tokens.add(expression);
		}
		if (tokens.get(0).equals("+"))
			tokens.remove(0);
		if (tokens.get(0).equals("-"))
			tokens.add(0, "0");

		return tokens.toArray(new String[tokens.size()]);
	}

	// TODO: Find out if there is an easier way to do this
	private static List<String> getList(String[] arr) {
		List<String> list = new ArrayList<String>();
		for (String s : arr)
			list.add(s);
		return list;
	}

	// Return parsed function without enclosing brackets
	private static String prepareFunction(String function) {
		if (isBracketPolynomial(function))
			return function.substring(1, function.length() - 1);
		else
			return function;
	}

	private static boolean hasDivisionOrMultiplication(String function) {
		return function.contains("*") || function.contains("/");
	}

	// TODO: Check if null
	private static boolean isBracketPolynomial(String s) {
		return s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')';
	}

	/**
	 * <b>isPolynomial(String s)</b>
	 * <p>
	 * Check whether a function string is a polynomial or single expression. A
	 * single expression will return false, even though technically it should be a
	 * polynomial.
	 * </p>
	 * 
	 * @param s
	 *            String represents the function checking
	 * @return boolean. True if function is polynomial. False if function consist of
	 *         single expression
	 */
	private static boolean isPolynomial(String s) {
		return s != null && (s.contains("+") || s.contains("-") || s.contains("/") || s.contains("*"));
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

	private static boolean isDivision(char c) {
		return c == '/';
	}

	@Override
	public String toString() {
		return functionTree.toString();
	}
}
