package com.rasbech;

public class Expression {
	private final double constant;
	private final char variable;
	private final double power;
	private boolean positive;	//TODO: IMPLEMENT
	
	public Expression(double constant, char variable, double power) {
		this.constant = constant;
		this.variable = variable;
		this.power = power;
	}
	
	public Expression(double constant, char variable) {
		this(constant, variable, 1);
	}
	
	public Expression(double constant) {
		this(constant, 'x', 0);
	}
	
	public double evaluate(double var) {
		return Math.pow(var, power) * constant;
	}

	/**
	 * TODO: Clean function. Clean first while loop. Split up function.
	 * TODO: Check if expression is correct syntax
	 * TODO: Make x^2 work without beginning constant
	 * @param expression The expression to parse
	 * @return Object of type Expression, matching the given expression in parameter expression
	 */
	public static Expression parse(String expression) {
		String number = "";
		char var;
		String power = "";
		
		if(Character.isDigit(expression.charAt(0)) == false) {
			expression = "1" + expression;
		}
		
		int i = 0;
		while(i < expression.length() && (Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '.' && number.contains(",") == false))) {
			number += expression.charAt(i);
			i++;
		}
		if(i == expression.length())
			return new Expression(Double.parseDouble(number));
		var = expression.charAt(i++);
		if(i == expression.length())
			return new Expression(Double.parseDouble(number), var);
		if(expression.charAt(i) == '^')
			while(++i < expression.length())
				power += expression.charAt(i);
		return new Expression(Double.parseDouble(number), var, Double.parseDouble(power));
	}
}






