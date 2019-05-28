package com.rasbech;

import com.rasbech.function.Function;

public class MathLibTest {
	public static void main(String[] args) {
		Function f = Function.parseFunction("(6)(x+4)");
		System.out.println(f);
		f.simplify();
		System.out.println(f);
	}
}