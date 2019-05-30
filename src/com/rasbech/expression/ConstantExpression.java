package com.rasbech.expression;

import java.util.Map;

public class ConstantExpression implements Expression {
	private double value;

	public ConstantExpression(double value) {
		this.value = value;
	}

	public void add(double value) {
		this.value += value;
	}

	public void multiply(double value) {
		this.value *= value;
	}

	public void divide(double value) {
		this.value /= value;
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return value;
	}

	@Override
	public String toString() {
		if(value % 1 == 0)
			return String.valueOf((int) value);
		return String.valueOf(value);
	}

	@Override
	public boolean isOne() {
		return value == 1;
	}
}