package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.function.Expression;

public class ExpressionTests {
	@Test
	void constant() {
		Expression e1 = new Expression(6);
		Expression e2 = new Expression(4.5);
		assertEquals(6, e1.evaluate(5));
		assertEquals(4.5, e2.evaluate(5));
	}

	@Test
	void constantAndVariable() {
		Expression e1 = new Expression(3, 'x');
		Expression e2 = new Expression(2.5, 'x');
		assertEquals(15, e1.evaluate(5));
		assertEquals(5, e2.evaluate(2));
	}

	@Test
	void variable() {
		Expression e = new Expression(1, 'x');
		assertEquals(5, e.evaluate(5));
	}

	@Test
	void varibleAndPower() {
		Expression e1 = new Expression(1, 'x', 2);
		Expression e2 = new Expression(1, 'x', 0.5);
		assertEquals(25, e1.evaluate(5));
		assertEquals(3, e2.evaluate(9));
	}

	@Test
	void constantVariableAndPower() {
		Expression e1 = new Expression(3, 'x', 2);
		assertEquals(75, e1.evaluate(5));
	}
}