package com.rasbech;

import com.rasbech.operations.Operation;

public class OperationalTree {
	private Operation firstOperation;

	public OperationalTree(Operation firstOperation) {
		this.firstOperation = firstOperation;
	}

	public double evaluate(double variable) {
		return firstOperation.evaluate(variable);
	}

	// TODO: Make tree into function string
	@Override
	public String toString() {
		return "OPERATION TREE - METHOD NOT IMPLEMENTED";
	}
}
