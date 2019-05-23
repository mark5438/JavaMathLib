package com.rasbech.operations;

import com.rasbech.Expression;

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
	private final Expression expression;
	
	public ExpressionOperation(String expression) {
		this.expression = Expression.parse(expression);
	}
	
	@Override
	public double evaluate(double variable) {
		return expression.evaluate(variable);
	}
}