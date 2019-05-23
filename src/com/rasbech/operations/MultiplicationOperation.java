package com.rasbech.operations;

public class MultiplicationOperation extends ActionOperation {

	public MultiplicationOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(double variable) {
		return leftOperation.evaluate(variable) * rightOperation.evaluate(variable);
	}
}