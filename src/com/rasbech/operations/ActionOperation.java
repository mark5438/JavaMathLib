package com.rasbech.operations;

public abstract class ActionOperation implements Operation {
	protected Operation leftOperation, rightOperation;
	
	public ActionOperation(Operation leftOperation, Operation rightOperation) {
		this.leftOperation = leftOperation;
		this.rightOperation = rightOperation;
	}
	
	public static ActionOperation getOperationForSign(char sign, Operation left, Operation right) {
		if(sign == '-')
			return new MinusOperation(left, right);
		if(sign == '+')
			return new PlusOperation(left, right);
		if(sign == '/')
			return new DivisionOperation(left, right);
		if(sign == '*')
			return new MultiplicationOperation(left, right);
		if (sign == '^')
			return new PowerOperation(left, right);
		return null;
	}
	
	public void setLeftOperation(Operation leftOperation) {
		this.leftOperation = leftOperation;
	}
	
	public void setRightOperation(Operation rightOperation) {
		this.rightOperation = rightOperation;
	}
}