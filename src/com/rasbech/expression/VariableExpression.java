package com.rasbech.expression;

import java.util.Map;

public class VariableExpression implements Expression {
	private final char variable;
	
	public VariableExpression(char varible) {
		this.variable = varible;
	}
	
	public char getVariable() {
		return variable;
	}
	
	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return variableValues.get(variable);
	}
	
	@Override
	public String toString() {
		return variable + "";
	}
}