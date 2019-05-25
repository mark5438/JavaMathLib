package com.rasbech.expression;

import java.util.Map;

public interface Expression {
	public double evaluate(Map<Character, Double> variableValues);
}