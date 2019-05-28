package com.rasbech.operations;

import java.util.Map;

public class MultiplicationOperation extends ActionOperation {
	public MultiplicationOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return leftOperation.evaluate(variableValues) * rightOperation.evaluate(variableValues);
	}

	@Override
	public String toString() {
		return leftOperation.toString() + "*" + rightOperation.toString();
	}

	@Override
	public Operation simplifyOperation() {
		if (leftOperation instanceof PowerOperation || rightOperation instanceof PowerOperation)
			return this;
		if (leftOperation.isExpressionOperation() && rightOperation.isActionOperation()) {
			((ActionOperation) rightOperation).multiply((ExpressionOperation) leftOperation);
			return rightOperation.simplify();
		}
		if (rightOperation.isExpressionOperation() && leftOperation.isActionOperation()) {
			((ActionOperation) leftOperation).multiply((ExpressionOperation) rightOperation);
			return leftOperation.simplify();
		}
		if (bothNumericExpressionOperations()) {
			double value = Double.parseDouble(leftOperation.toString()) * Double.parseDouble(rightOperation.toString());
			return new ExpressionOperation(String.valueOf(value));
		}
		return this;
	}

	@Override
	public void multiply(Operation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation).simplify();
	}
}