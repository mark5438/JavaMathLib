package com.rasbech.function;

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
		return TokenGenerator.getTokens(function);
	}

	private Operation getOperations() {
		return getOperations(tokens);
	}

	private Operation getOperations(List<String> tokens) {
		if (tokens.size() == 1)
			if (isMonomial(tokens.get(0)))
				return new ExpressionOperation(tokens.get(0));
			else
				return getOperations(TokenGenerator.getTokens(tokens.get(0).substring(1, tokens.get(0).length() - 1)));
		return getActionOperation(tokens);
	}

	private Operation getActionOperation(List<String> tokens) {
		for (int i = tokens.size() - 1; i > -1; i--) {
			if (isOperation(tokens.get(i))) {
				List<String> left = tokens.subList(0, i);
				String right = tokens.get(i + 1);
				char sign = tokens.get(i).charAt(0);
				return ActionOperation.getOperationForSign(sign, getOperations(left), getOperations(TokenGenerator.getTokens(right)));
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