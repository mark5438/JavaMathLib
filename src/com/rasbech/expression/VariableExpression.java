package com.rasbech.expression;

public class VariableExpression implements Expression {
	private final char variable;
	
	public VariableExpression(char varible) {
		this.variable = varible;
	}
	
	@Override
	public double evaluate(double var) {
		return var;
	}
}