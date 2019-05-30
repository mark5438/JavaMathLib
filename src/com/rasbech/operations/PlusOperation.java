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
		if (bothNumericExpressionOperations()) {
			double value = Double.parseDouble(leftOperation.toString()) + Double.parseDouble(rightOperation.toString());
			return new ExpressionOperation(String.valueOf(value));
		}
		if (leftOperation.isPlusOperation() && rightOperation.isNumeric()) {
			// TODO:
			// If leftOperation.isMinusOperation() == true, same should happen
			// Add rightOperation value to leftOperation function
			if (((PlusOperation) leftOperation).addConstant(Double.parseDouble(rightOperation.toString())))
				return leftOperation;
		}
		if (rightOperation.isPlusOperation() && leftOperation.isNumeric()) {
			// TODO:
			// If rightOperation.isMinusOperation() == true, same should happen
			// Add leftOperation value to rightOperation function
			if (((PlusOperation) rightOperation).addConstant(Double.parseDouble(leftOperation.toString())))
				return rightOperation;
		}
		return this;
	}

	public boolean addConstant(double constant) {
		if (leftOperation.isNumeric()) {
			((ExpressionOperation) leftOperation).add(constant);
			return true;
		}
		else if (rightOperation.isNumeric()) {
			((ExpressionOperation) rightOperation).add(constant);
			return true;
		} else if(rightOperation.isPlusOperation()) {	//Or minus
			return ((PlusOperation)rightOperation).addConstant(constant);
		} else if(leftOperation.isPlusOperation()) {	//Or minus
			return ((PlusOperation)leftOperation).addConstant(constant);			
		}
		return false;
	}

	@Override
	public void multiply(Operation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation).simplify();
		rightOperation = new MultiplicationOperation(rightOperation, operation).simplify();
	}

	@Override
	public boolean divideConstant(double constant) {
		return false;
	}

	@Override
	public boolean subtractConstant(double constant) {
		return false;
	}

	@Override
	public boolean multiplyConstant(double constant) {
		return false;
	}
}