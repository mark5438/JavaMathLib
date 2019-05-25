package com.rasbech.operations;

import java.util.Map;

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
}