package com.rasbech;

import java.util.Map;
import java.util.TreeMap;

import com.rasbech.function.Function;

public class MathLibTest {
	public static void main(String[] args) {
		Function f = Function.parseFunction("3x*5");
		f.simplify();
		System.out.println(f);
	}
	
	private static Map<Character, Double> getValueMapForX(double value){
		Map<Character, Double> variableValues = new TreeMap<Character, Double>();
		variableValues.put('x', value);
		return variableValues;
	}
}