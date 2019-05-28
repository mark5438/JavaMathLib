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
}