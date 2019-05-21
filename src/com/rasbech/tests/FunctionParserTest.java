package com.rasbech.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.rasbech.Function;

class FunctionParserTest {
	@Test
	void test() {
		assertEquals(true, Function.isOperation('+'));
		assertEquals(true, Function.isOperation('-'));
		assertEquals(true, Function.isOperation('*'));
		assertEquals(true, Function.isOperation('/'));
		assertEquals(false, Function.isOperation('a'));
		assertEquals(false, Function.isOperation('5'));
		assertEquals(false, Function.isOperation('h'));
		assertEquals(false, Function.isOperation('0'));
	}
}