package com.rasbech.operations;

import java.util.Map;

public interface Operation {
	public double evaluate(Map<Character, Double> variableValues);
	/**
	 * @return Integer. Number og sub operations
	 */
	public int getOperationCount();
}