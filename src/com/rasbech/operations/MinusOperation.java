package com.rasbech.operations;

import java.util.Map;

public class MinusOperation extends ActionOperation {
	public MinusOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return leftOperation.evaluate(variableValues) - rightOperation.evaluate(variableValues);
	}
	
	@Override
	public String toString() {
		return leftOperation.toString() + "-" + rightOperation.toString();
	}

	@Override
	public Operation simplify() {
		return null;		
	}
	
	@Override
	public void multiply(ExpressionOperation operation) {
		leftOperation = new MultiplicationOperation(leftOperation, operation);
		rightOperation = new MultiplicationOperation(rightOperation, operation);
	}
}