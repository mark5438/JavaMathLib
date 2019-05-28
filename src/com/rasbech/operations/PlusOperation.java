package com.rasbech.operations;

import java.util.Map;

public class PlusOperation extends ActionOperation {
	public PlusOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return leftOperation.evaluate(variableValues) + rightOperation.evaluate(variableValues);
	}

	@Override
	public String toString() {
		return leftOperation + "+" + rightOperation;
	}

	@Override
	public Operation simplifyOperation() {
		if (leftOperation instanceof ExpressionOperation && rightOperation instanceof ExpressionOperation) {
			if (((ExpressionOperation) leftOperation).isNumeric()
					&& ((ExpressionOperation) rightOperation).isNumeric()) {
				double value = Double.parseDouble(leftOperation.toString())
						+ Double.parseDouble(rightOperation.toString());
				return new ExpressionOperation(String.valueOf(value));
			}
		}
		return this;
	}

	@Override
	public void multiply(Operation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation).simplify();
		rightOperation = new MultiplicationOperation(rightOperation, operation).simplify();
	}
}