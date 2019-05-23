package com.rasbech.operations;

public class MinusOperation extends ActionOperation {
	public MinusOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(double variable) {
		return leftOperation.evaluate(variable) - rightOperation.evaluate(variable);
	}
}