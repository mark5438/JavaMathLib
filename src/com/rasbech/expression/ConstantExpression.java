package com.rasbech.expression;

public class ConstantExpression implements Expression {
	private final double value;

	public ConstantExpression(double value) {
		this.value = value;
	}

	@Override
	public double evaluate(double var) {
		return value;
	}
}