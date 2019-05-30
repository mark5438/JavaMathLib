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
		if(bothExpressionOperations())
			return leftOperation.toString() + rightOperation.toString();
		return leftOperation.toString() + "*" + rightOperation.toString();
	}

	@Override
	public Operation simplifyOperation() {
		if(leftOperation.isOne())
			return rightOperation;
		if(rightOperation.isOne())
			return leftOperation;
		if (leftOperation instanceof PowerOperation || rightOperation instanceof PowerOperation)
			return this;
		if (leftOperation.isNumeric() && rightOperation.isMultiplicationOperation()) {
			if (((ActionOperation) rightOperation).multiplyConstant(Double.parseDouble(leftOperation.toString())))
				return rightOperation;
		}
		if (rightOperation.isNumeric() && leftOperation.isMultiplicationOperation()) {
			if (((MultiplicationOperation) leftOperation)
					.multiplyConstant(Double.parseDouble(rightOperation.toString())))
				return leftOperation;
		}
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
	public boolean multiplyConstant(double constant) {
		if(attemptMultiplyConstant(constant, leftOperation) || attemptMultiplyConstant(constant, rightOperation))
			return true;
		if (rightOperation.isActionOperation()) {
			return ((ActionOperation) rightOperation).multiplyConstant(constant);
		}
		if (leftOperation.isActionOperation()) {
			return ((ActionOperation) leftOperation).multiplyConstant(constant);
		}
		return false;
	}

	@Override
	public void multiply(Operation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation).simplify();
	}

	@Override
	public boolean addConstant(double constant) {
		return false;
	}

	@Override
	public boolean divideConstant(double constant) {
		if (attemptDivideConstant(constant, leftOperation) || attemptDivideConstant(constant, rightOperation))
			return true;
		if (rightOperation.isMultiplicationOperation()) {
			return ((MultiplicationOperation) rightOperation).divideConstant(constant);
		}
		if (leftOperation.isMultiplicationOperation()) {
			return ((MultiplicationOperation) leftOperation).divideConstant(constant);
		}
		return false;
	}

	@Override
	public boolean subtractConstant(double constant) {
		return false;
	}
}