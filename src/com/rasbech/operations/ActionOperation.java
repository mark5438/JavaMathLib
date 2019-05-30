package com.rasbech.operations;

import java.util.ArrayList;
import java.util.List;

public abstract class ActionOperation implements Operation {
	protected Operation leftOperation, rightOperation;
	
	public abstract void multiply(Operation operation);
	public abstract Operation simplifyOperation();
	public abstract boolean multiplyConstant(double constant);
	public abstract boolean addConstant(double constant);
	public abstract boolean divideConstant(double constant);
	public abstract boolean subtractConstant(double constant);
	
	public ActionOperation(Operation leftOperation, Operation rightOperation) {
		this.leftOperation = leftOperation;
		this.rightOperation = rightOperation;
	}
	
	@Override
	public Operation simplify() {
		leftOperation = leftOperation.simplify();
		rightOperation = rightOperation.simplify();
		return simplifyOperation();
	}

	public static ActionOperation getOperationForSign(char sign, Operation left, Operation right) {
		if (sign == '-')
			return new MinusOperation(left, right);
		if (sign == '+')
			return new PlusOperation(left, right);
		if (sign == '/')
			return new DivisionOperation(left, right);
		if (sign == '*')
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

	public ExpressionOperation[] getBottomOperations() {
		List<ExpressionOperation> operations = getBottomOperationsAsList();
		return operations.toArray(new ExpressionOperation[operations.size()]);
	}

	private List<ExpressionOperation> getBottomOperationsAsList() {
		List<ExpressionOperation> operations = new ArrayList<ExpressionOperation>();
		if (this.leftOperation instanceof ExpressionOperation)
			operations.add((ExpressionOperation)leftOperation);
		else
			operations.addAll(((ActionOperation) leftOperation).getBottomOperationsAsList());

		if (this.rightOperation instanceof ExpressionOperation)
			operations.add((ExpressionOperation)rightOperation);
		else
			operations.addAll(((ActionOperation) rightOperation).getBottomOperationsAsList());
		return operations;
	}
	
	@Override
	public int getOperationCount() {
		return leftOperation.getOperationCount() + rightOperation.getOperationCount();
	}

	protected boolean bothNumericExpressionOperations() {
		return leftOperation.isNumeric() && rightOperation.isNumeric();
	}
	
	protected static boolean attemptDivideConstant(double constant, Operation operation) {
		if(operation.isNumeric()) {
			((ExpressionOperation) operation).divide(constant);
			return true;
		}
		return false;
	}
	
	protected static boolean attemptMultiplyConstant(double constant, Operation operation) {
		if(operation.isNumeric()) {
			((ExpressionOperation) operation).multiply(constant);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOne() {
		return false;
	}
}