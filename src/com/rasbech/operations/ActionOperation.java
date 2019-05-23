package com.rasbech.operations;

public abstract class ActionOperation implements Operation {
	protected Operation leftOperation, rightOperation;
	
	public ActionOperation() {

	}
	
	public ActionOperation(Operation leftOperation, Operation rightOperation) {
		this.leftOperation = leftOperation;
		this.rightOperation = rightOperation;
	}
	
	public void setLeftOperation(Operation leftOperation) {
		this.leftOperation = leftOperation;
	}
	
	public void setRightOperation(Operation rightOperation) {
		this.rightOperation = rightOperation;
	}
}