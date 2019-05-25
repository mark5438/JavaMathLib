package com.rasbech.function;

import java.util.List;

import com.rasbech.operations.ActionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.Operation;

/**
 * Calculus com.rasbech.function FunctionParser.java 2019 Has public static
 * method parseFunction, that takes in a String and returns a function object
 */
public class FunctionParser {
	/**
	 * <b>List\<String\> tokens</b>
	 * <p>
	 * To keep track of all the tokens, defining the full function
	 * </p>
	 */
	private List<String> tokens;

	/**
	 * <b>String function</b>
	 * <p>
	 * The function as it was first parsed to parseFunction
	 * </p>
	 */
	private String function;

	/**
	 * <b>FunctionParser Constructor</b>
	 * <p>
	 * Constructs an object FunctionParser. Initializes String function to given
	 * function from parameter, and initializes List tokens with function call
	 * getTokens()
	 * </p>
	 * 
	 * @param function
	 *            The function to parse
	 */
	private FunctionParser(String function) {
		this.function = function;
		this.tokens = getTokens();
	}

	/**
	 * <b>getTokens()</b>
	 * <p>
	 * Get tokens for original function
	 * </p>
	 * 
	 * @return A List of strings containing all the tokens
	 */
	private List<String> getTokens() {
		return TokenGenerator.getTokens(function);
	}

	/**
	 * <b>parse()</b>
	 * <p>
	 * Using token array to create Function object
	 * </p>
	 * 
	 * @return An object of class Function
	 */
	private Function parse() {
		Operation operation = getOperations(this.tokens);
		return new Function(new OperationalTree(operation));
	}

	/**
	 * <b>parseFunction()</b>
	 * <p>
	 * Takes in a parameter function as a string, parses it and returns a function
	 * object.
	 * </p>
	 * 
	 * @param function
	 *            The function to parse
	 * @return The function object
	 */
	public static Function parseFunction(String function) {
		FunctionParser fp = new FunctionParser(function);
		return fp.parse();
	}

	/**
	 * <b>getOperations()</b>
	 * <p>
	 * Takes in a list of strings as tokens, and returns an operation forking down
	 * to more operations.
	 * </p>
	 * <p>
	 * Has recursive calls
	 * </p>
	 * 
	 * @param tokens
	 *            A list of strings containing the tokens to pass
	 * @return The top operation
	 */
	private Operation getOperations(List<String> tokens) {
		if (tokens.size() == 1)
			if (isMonomial(tokens.get(0)))
				return new ExpressionOperation(tokens.get(0));
			else
				return getOperations(TokenGenerator.getTokens(tokens.get(0).substring(1, tokens.get(0).length() - 1)));
		return getActionOperation(tokens);
	}

	/**
	 * <b>getActionOperation()</b>
	 * @param tokens The tokens to make into an action operation
	 * @return An action operation
	 */
	private Operation getActionOperation(List<String> tokens) {
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (isOperation(tokens.get(i))) {
				List<String> left = tokens.subList(0, i);
				String right = tokens.get(i + 1);
				char sign = tokens.get(i).charAt(0);
				return ActionOperation.getOperationForSign(sign, getOperations(left),
						getOperations(TokenGenerator.getTokens(right)));
			}
		}
		return null;
	}

	private boolean isMonomial(String function) {
		return (function.contains("+") || function.contains("-") || function.contains("*") || function.contains("/")
				|| function.contains("^")) == false;
	}

	private boolean isOperation(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}

	private boolean isOperation(String c) {
		return isOperation(c.charAt(0));
	}
}