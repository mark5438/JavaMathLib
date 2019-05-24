package com.rasbech.operations;

public class PlusOperation extends ActionOperation {
	public PlusOperation() {
		super();
	}
	
	public PlusOperation(Operation leftOperation, Operation rightOperation) {
		super(leftOperation, rightOperation);
	}

	@Override
	public double evaluate(double variable) {
		return leftOperation.evaluate(variable) + rightOperation.evaluate(variable);
	}
}