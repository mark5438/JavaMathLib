package com.rasbech.operations;

import java.util.Map;

public interface Operation {
	public double evaluate(Map<Character, Double> variableValues);
}