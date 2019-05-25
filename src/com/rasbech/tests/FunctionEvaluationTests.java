package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.rasbech.function.Function;

public class FunctionEvaluationTests {
	@Test
	void constant() {
		Function f1 = Function.parseFunction("6");
		Function f2 = Function.parseFunction("6.7");
		Function f3 = Function.parseFunction("0.3");
		assertEquals(6, f1.evaluate(getValueMapForX(3)), "Integer constant");
		assertEquals(6.7, f2.evaluate(getValueMapForX(3)), "Non integer constant");
		assertEquals(0.3, f3.evaluate(getValueMapForX(3)), "Non integer less than 0 constant");
	}

	@Test
	void variable() {
		Function f = Function.parseFunction("x");
		assertEquals(5, f.evaluate(getValueMapForX(5)), "Variable evaluated with integer");
		assertEquals(5.4, f.evaluate(getValueMapForX(5.4)), "Variable evaluated with non integer");
		assertEquals(0.43, f.evaluate(getValueMapForX(0.43)), "Variable evaluated with non integer");
	}

	@Test
	void variablePower() {
		Function f1 = Function.parseFunction("x^2");
		Function f2 = Function.parseFunction("x^0.5");
		Function f3 = Function.parseFunction("x^(1/3)");
		Function f4 = Function.parseFunction("x^10");
		assertEquals(9, f1.evaluate(getValueMapForX(3)), "Variable with power");
		assertEquals(3, f2.evaluate(getValueMapForX(9)), "Non integer powers");
		assertEquals(3, f3.evaluate(getValueMapForX(27)), "Rational powers");
		assertEquals(1024, f4.evaluate(getValueMapForX(2)), "Multi digit power");
	}

	@Test
	void constantVariable() {
		Function f1 = Function.parseFunction("2x");
		Function f2 = Function.parseFunction("2.5x");
		Function f3 = Function.parseFunction("0.5x");
		assertEquals(6, f1.evaluate(getValueMapForX(3)), "Constant followed by variable");
		assertEquals(5, f2.evaluate(getValueMapForX(2)), "Non integer constant followed by variable");
		assertEquals(3, f3.evaluate(getValueMapForX(6)), "Non integer less than zero constant followed by variable");
	}

	@Test
	void constantVariableAndPower() {
		Function f1 = Function.parseFunction("3x^2");
		Function f2 = Function.parseFunction("3.2x^2");
		Function f3 = Function.parseFunction("0.5x^3");
		Function f4 = Function.parseFunction("3x^(1/3)");

		assertEquals(80, f2.evaluate(getValueMapForX(5)), "Non integer followed by squared variable");
		assertEquals(75, f1.evaluate(getValueMapForX(5)), "Integer followed by squared variable");
		assertEquals(4, f3.evaluate(getValueMapForX(2)), "Non integer less than zero constant followed by cubed variable");
		assertEquals(5.129, f4.evaluate(getValueMapForX(5)), 0.01, "constant followed by varible to rational power");
	}

	@Test
	void binomial() {
		Function f1 = Function.parseFunction("2x+7");
		Function f2 = Function.parseFunction("0.5x-5");
		assertEquals(17, f1.evaluate(getValueMapForX(5)), "Simple binomial");
		assertEquals(5, f2.evaluate(getValueMapForX(20)), "0.5x - 5");
	}

	@Test
	void allNegativeBinomial() {
		Function f1 = Function.parseFunction("-2x-6");
		assertEquals(-10, f1.evaluate(getValueMapForX(2)));
	}

	@Test
	void Trinomial() {
		Function f1 = Function.parseFunction("5x+5x+5");
		assertEquals(55, f1.evaluate(getValueMapForX(5)));
	}

	@Test
	void quadraticPolynomials() {
		Function f1 = Function.parseFunction("x^2+5x+25");
		assertEquals(49, f1.evaluate(getValueMapForX(3)), "Quadratic Trinomial");
	}

	@Test
	void squaredFunction() {
		Function f1 = Function.parseFunction("(5x+2)^2");
		Function f2 = Function.parseFunction("(5x^2+2x-6)^2");
		Function f3 = Function.parseFunction("(5x^2+2x-6)^2*(3x+7)");
		assertEquals(729, f1.evaluate(getValueMapForX(5)), "Squared binomial");
		assertEquals(2025, f2.evaluate(getValueMapForX(3)), "Squared trinomial");
		assertEquals(4212, f3.evaluate(getValueMapForX(2)), "Squared trinomial multiplied with binomial");
	}

	@Test
	void higherPowerFunctions() {
		Function f1 = Function.parseFunction("(5x+2)^11");
		assertEquals(Math.pow(12, 11), f1.evaluate(getValueMapForX(2)));
	}

	@Test
	void bracketMultiplication() {
		Function f1 = Function.parseFunction("(6x+2)(x-4)");
		assertEquals(-28, f1.evaluate(getValueMapForX(2)), "Bracket Multiplication");
	}

	@Test
	void rationalFunction() {
		Function f1 = Function.parseFunction("2x/(5x+7)+(4x+6)/x");
		assertEquals(69.0 / 11.0, f1.evaluate(getValueMapForX(3)), "Adding rational functions");
	}

	@Test
	void mulitplyToRationalExpression() {
		Function f1 = Function.parseFunction("5*8/4");
		assertEquals(10, f1.evaluate(getValueMapForX(0)));
	}
	
	private Map<Character, Double> getValueMapForX(double value){
		Map<Character, Double> variableValues = new TreeMap<Character, Double>();
		variableValues.put('x', value);
		return variableValues;
	}
}