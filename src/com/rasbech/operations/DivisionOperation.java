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
}