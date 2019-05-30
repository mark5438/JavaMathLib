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

	/**
	 * <b>divide()</b>
	 * <p>
	 * Checks if constant value after division will have 2 decima places or less. If
	 * so, it will divide and return true. Otherwise no action is performed and
	 * false is returned
	 * </p>
	 * 
	 * @param value
	 *            The constant dividing by
	 * @return Boolean. Whether or not values were divided.
	 */
	public boolean divide(double value) {
		double val = this.value / value;
		if ((val * 100) % 1 == 0) {
			this.value = val;
			return true;
		}
		return false;
	}

	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return value;
	}

	@Override
	public String toString() {
		if (value % 1 == 0)
			return String.valueOf((int) value);
		return String.valueOf(value);
	}

	@Override
	public boolean isOne() {
		return value == 1;
	}
}