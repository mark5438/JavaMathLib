package com.rasbech;

import java.util.Map;
import java.util.TreeMap;

import com.rasbech.equation.Equation;

public class MathLibTest {
	public static void main(String[] args) {
		Equation e = new Equation("4=8x/2");
		System.out.println(e);
		System.out.println(e.solve('x'));
	}
	
	private static Map<Character, Double> getValueMapForX(double value){
		Map<Character, Double> variableValues = new TreeMap<Character, Double>();
		variableValues.put('x', value);
		return variableValues;
	}
}