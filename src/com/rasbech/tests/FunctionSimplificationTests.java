package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.function.Function;

public class FunctionSimplificationTests {
	@Test
	void simpleDivision() {
		Function f1 = Function.parseFunction("4/2");
		Function f2 = Function.parseFunction("1/3");
		Function f3 = Function.parseFunction("4x/2");
		
		f1.simplify();
		f2.simplify();
		f3.simplify();
		
		assertEquals("2", f1.toString(), "Simple division simplification");
		assertEquals("1/3", f2.toString(), "Should not divide!");
		assertEquals("x2", f3.toString(), "Divide constant in front of variable");
	}
}