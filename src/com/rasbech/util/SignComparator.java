package com.rasbech.util;

import java.util.Comparator;

/**
 * Calculus com.rasbech.util SignComparator.java 2019 <b>SignComparator</b>
 * <p>
 * Compares expressions based on front sign. "-" comes before "+". Positive
 * expressions comes before negative
 * </p>
 */
public class SignComparator implements Comparator<String> {

	// TODO: Check for nulls
	// TODO: Check if first character is + or -
	@Override
	public int compare(String str1, String str2) {
		char c1 = str1.charAt(0);
		char c2 = str2.charAt(0);

		if (c1 == '-' && c2 == '+')
			return 1;
		if (c1 == '+' && c2 == '-')
			return -1;
		return 0;
	}

}
