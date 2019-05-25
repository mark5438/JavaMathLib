package com.rasbech.operations;

public class PowerOperation extends ActionOperation {
	public PowerOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(double variable) {
		return Math.pow(leftOperation.evaluate(variable), rightOperation.evaluate(variable));
	}
}