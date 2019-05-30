package com.rasbech.operations;

import java.util.Map;

public class DivisionOperation extends ActionOperation {
	public DivisionOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return leftOperation.evaluate(variableValues) / rightOperation.evaluate(variableValues);
	}

	@Override
	public String toString() {
		return leftOperation.toString() + "/" + rightOperation.toString();
	}

	@Override
	public Operation simplifyOperation() {
		if(bothNumericExpressionOperations()) {
			double value = Double.parseDouble(leftOperation.toString()) / Double.parseDouble(rightOperation.toString());
			if((value * 100) % 1 == 0)
				return new ExpressionOperation(String.valueOf(value));
		}
		return this;
	}

	@Override
	public void multiply(Operation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation).simplify();
	}

	@Override
	public boolean multiplyConstant(double constant) {
		return false;
	}

	@Override
	public boolean addConstant(double constant) {
		return false;
	}

	@Override
	public boolean divideConstant(double constant) {
		return false;
	}

	@Override
	public boolean subtractConstant(double constant) {
		return false;
	}
}