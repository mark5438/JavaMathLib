package com.rasbech.function;

import java.util.Map;

import com.rasbech.operations.ActionOperation;
import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.Operation;

public class OperationalTree {
	private Operation firstOperation;

	public OperationalTree(Operation firstOperation) {
		this.firstOperation = firstOperation;
	}

	public double evaluate(Map<Character, Double> variableValues) {
		return firstOperation.evaluate(variableValues);
	}

	public ExpressionOperation[] getBottomLayerOperations() {
		if (firstOperation instanceof ExpressionOperation)
			return new ExpressionOperation[] { (ExpressionOperation)firstOperation };
		else
			return ((ActionOperation) firstOperation).getBottomOperations();
	}

	// TODO: Make tree into function string
	@Override
	public String toString() {
		return firstOperation.toString();
	}
}