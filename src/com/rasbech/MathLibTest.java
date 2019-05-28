package com.rasbech;

import com.rasbech.function.Function;

public class MathLibTest {
	public static void main(String[] args) {
		Function f = Function.parseFunction("2(x+4)");
		f.simplify();
		System.out.println(f);
	}
}