package com.rasbech.expression;

import java.util.Map;

public class ConstantExpression implements Expression {
	private final double value;

	public ConstantExpression(double value) {
		this.value = value;
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}