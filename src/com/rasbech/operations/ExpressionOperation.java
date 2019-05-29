package com.rasbech.operations;

import java.util.Map;

import com.rasbech.expression.ConstantExpression;
import com.rasbech.expression.Expression;
import com.rasbech.expression.VariableExpression;

/**
* Calculus
* com.rasbech
* ExpressionOperation.java
* 2019
* 
* Operation containing an expression. Will for evaluate return
* The evaluated expression with given value for variable
*/
public class ExpressionOperation implements Operation {
	private Expression expression;
	
	public ExpressionOperation(String expression) {
		if(expression.length() == 1 && Character.isAlphabetic(expression.charAt(0)))
			this.expression = new VariableExpression(expression.charAt(0));
		else
			try {
				this.expression = new ConstantExpression(Double.parseDouble(expression));
			} catch (NumberFormatException e) {
				System.out.println("Failed to parse expression: " + expression);
			}
	}
	
	public boolean isVariable() {
		return expression instanceof VariableExpression;
	}
	
	public boolean isNumeric() {
		return expression instanceof ConstantExpression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public void add(double value) {
		if(isNumeric())
			((ConstantExpression) expression).add(value);
	}
	
	public void multiply(double value) {
		if(isNumeric())
			((ConstantExpression) expression).multiply(value);
	}
	
	@Override
	public double evaluate(Map<Character, Double> variableValues) {
		return expression.evaluate(variableValues);
	}
	
	@Override
	public String toString() {
		return expression.toString();
	}
	
	@Override
	public int getOperationCount() {
		return 1;
	}
	
	@Override
	public Operation simplify() {
		/*
		 * Nothing to simplify
		 */
		return this;
	}
}