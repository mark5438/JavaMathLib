package com.rasbech.operations;

import java.util.Map;

public interface Operation {
	public double evaluate(Map<Character, Double> variableValues);
	/**
	 * @return Integer. Number of sub operations
	 */
	public int getOperationCount();
	
	/**
	 * <b>simplify()</b>
	 * <p>
	 * Simplify operation. Will return simplified operation
	 * </p>
	 * @return Simplified operation
	 */
	public Operation simplify();
	
	public default boolean isActionOperation () {
		return this instanceof ActionOperation;
	}
	
	public default boolean isNumeric() {
		return isExpressionOperation() && ((ExpressionOperation)this).isNumeric();
	}
	
	public default boolean isExpressionOperation() {
		return this instanceof ExpressionOperation;
	}
	
	public default boolean isPowerOperation() {
		return this instanceof PowerOperation;
	}
	
	public default boolean isMultiplicationOperation() {
		return this instanceof MultiplicationOperation;
	}
	
	public default boolean isDivisionOperation() {
		return this instanceof DivisionOperation;
	}
	
	public default boolean isPlusOperation() {
		return this instanceof PlusOperation;
	}
	
	public default boolean isMinusOperation() {
		return this instanceof MinusOperation;
	}
}