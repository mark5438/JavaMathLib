package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.Function;

public class FunctionEvaluationTests {
	@Test
	void constant() {
		Function f1 = Function.parseFunction("6");
		Function f2 = Function.parseFunction("6.7");
		assertEquals(6, f1.evaluate(3));
		assertEquals(6.7, f2.evaluate(3));
	}
	
	@Test
	void variable() {
		Function f = Function.parseFunction("x");
		assertEquals(5, f.evaluate(5));
		assertEquals(5.4, f.evaluate(5.4));
	}
}