package com.rasbech.operations;

public class DivisionOperation extends ActionOperation {
	public DivisionOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(double variable) {
		return leftOperation.evaluate(variable) / rightOperation.evaluate(variable);
	}

}