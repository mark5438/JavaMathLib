package com.rasbech.operations;

import java.util.Map;

import com.rasbech.expression.ConstantExpression;

public class PowerOperation extends ActionOperation {
	public PowerOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return Math.pow(leftOperation.evaluate(variableValues), rightOperation.evaluate(variableValues));
	}

	@Override
	public String toString() {
		return leftOperation.toString() + "^" + rightOperation.toString();
	}

	@Override
	public Operation simplifyOperation() {
		if (leftOperation instanceof ExpressionOperation && rightOperation instanceof ExpressionOperation) {
			if (((ExpressionOperation) leftOperation).isNumeric()
					&& ((ExpressionOperation) rightOperation).isNumeric()) {
				double value = Math.pow(Double.parseDouble(leftOperation.toString()),
						Double.parseDouble(rightOperation.toString()));
				return new ExpressionOperation(String.valueOf(value));
			}
		}
		return this;
	}

	@Override
	public void multiply(Operation operation) {

	}
}