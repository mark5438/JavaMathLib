package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.operations.ExpressionOperation;
import com.rasbech.operations.Operation;

class OperationTreeTests {
	
	@Test
	void testConstant() {
		Operation ao1 = new ExpressionOperation("34.1");
		Operation ao2 = new ExpressionOperation("354");
		assertEquals(34.1, ao1.evaluate(7.4));
		assertEquals(354.0, ao2.evaluate(7.4));
	}

	@Test
	void testVariable() {
		Operation ao1 = new ExpressionOperation("12x");
		Operation ao2 = new ExpressionOperation("4.2x");
		assertEquals(36.0, ao1.evaluate(3));		
		assertEquals(8.4, ao2.evaluate(2));
	}

	@Test
	void testPowerVariable() {
		Operation ao1 = new ExpressionOperation("2x^3");
		Operation ao2 = new ExpressionOperation("2.3x^3");
		Operation ao3 = new ExpressionOperation("2x^3.1");
		assertEquals(128.0, ao1.evaluate(4));
		assertEquals(147.2, ao2.evaluate(4));
		assertEquals(147.033, ao3.evaluate(4), 0.01);
	}
	
	
	
	
}