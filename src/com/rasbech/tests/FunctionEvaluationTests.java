package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.Function;

public class FunctionEvaluationTests {
	@Test
	void constant() {
		Function f1 = Function.parseFunction("6");
		Function f2 = Function.parseFunction("6.7");
		Function f3 = Function.parseFunction("0.3");
		assertEquals(6, f1.evaluate(3), "Integer constant");
		assertEquals(6.7, f2.evaluate(3), "Non integer constant");
		assertEquals(0.3, f3.evaluate(3), "Non integer less than 0 constant");
	}
	
	@Test
	void variable() {
		Function f = Function.parseFunction("x");
		assertEquals(5, f.evaluate(5), "Variable evaluated with integer");
		assertEquals(5.4, f.evaluate(5.4), "Variable evaluated with non integer");
		assertEquals(0.43, f.evaluate(0.43), "Variable evaluated with non integer");
	}
	
	@Test
	void variablePower() {
		Function f1 = Function.parseFunction("x^2");
		Function f2 = Function.parseFunction("x^0.5");
		Function f3 = Function.parseFunction("x^(1/3)");
		assertEquals(9, f1.evaluate(3), "Variable with power");
		assertEquals(3, f2.evaluate(9), "Non integer powers");
		assertEquals(27, f3.evaluate(3), "Rational powers");
	}
	
	@Test
	void constantVariable() {
		Function f1 = Function.parseFunction("2x");
		Function f2 = Function.parseFunction("2.5x");
		Function f3 = Function.parseFunction("0.5x");
		assertEquals(6, f1.evaluate(3), "Constant followed by variable");
		assertEquals(5, f2.evaluate(2), "Non integer constant followed by variable");
		assertEquals(3, f3.evaluate(6), "Non integer less than zero constant followed by variable");
	}
	
	@Test
	void constantVariableAndPower() {
		Function f1 = Function.parseFunction("3x^2");
		Function f2 = Function.parseFunction("3.2x^2");
		Function f3 = Function.parseFunction("0.5x^3");
		Function f4 = Function.parseFunction("3x^(1/3)");
		
		assertEquals(80, f2.evaluate(5), "Non integer followed by squared variable");
		assertEquals(75, f1.evaluate(5), "Integer followed by squared variable");
		assertEquals(4, f3.evaluate(2), "Non integer less than zero constant followed by cubed variable");
		assertEquals(5.129, f4.evaluate(5), 0.01, "constant followed by varible to rational power");
	}
	
	@Test
	void binomial() {
		Function f1 = Function.parseFunction("2x+7");
		Function f2 = Function.parseFunction("0.5x-5");
		assertEquals(17, f1.evaluate(5), "Simple binomial");
		assertEquals(5, f2.evaluate(20), "0.5x - 5");
	}
	
	@Test
	void quadraticPolynomials() {
		Function f1 = Function.parseFunction("x^2+5x+25");
		assertEquals(49, f1.evaluate(3), "Quadratic Trinomial");
	}
}