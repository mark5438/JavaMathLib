package com.rasbech.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Calculus com.rasbech.util SignComparator.java 2019 <b>SignComparator</b>
 * <p>
 * Compares expressions based on front sign. "-" comes before "+". Positive
 * expressions comes before negative
 * </p>
 */
public class SignComparator implements Comparator<String> {
	private static final Map<Character, Integer> characterValues = new TreeMap<Character, Integer>();

	static {
		characterValues.put('-', 1);
		characterValues.put('+', 2);
		characterValues.put('/', 3);
		characterValues.put('*', 4);
	}

	// TODO: Check for nulls
	// TODO: Check if first character is + or -
	@Override
	public int compare(String str1, String str2) {
		char c1 = str1.charAt(0);
		char c2 = str2.charAt(0);
		return characterValues.get(c2) - characterValues.get(c1);
	}

}
