package com.rasbech.equation;

import com.rasbech.function.Function;

public class Equation {
	private Function left, right;

	// TODO: Make syntax check on parameter equation
	public Equation(String equation) {
		String[] pieces = equation.split("=");
		left = Function.parseFunction(pieces[0]);
		right = Function.parseFunction(pieces[1]);
	}

	/**
	 * <b>com.rasbech.equation.Equation.solve</b>
	 * <p>
	 * Takes in a parameter c, defining the variable solving for.
	 * </p>
	 * <p>
	 * Function will try to isolate the given variable, then return the function
	 * that equals that variable.
	 * </p>
	 * 
	 * @param c
	 *            Variable to solve for
	 * @return Function equals to variable
	 */
	public Function solve(char c) {
		char[] leftVariables = left.getVariables();
		char[] rightVariables = right.getVariables();

		/**
		 * I want the variable on the left side. If the right side has the variable but
		 * left don't, they will be swapped around
		 */
		if (arrayHasElement(rightVariables, c) && arrayHasElement(leftVariables, c) == false) {
			Function temp = left;
			left = right;
			right = temp;
		}

		return null;
	}

	private boolean arrayHasElement(char[] arr, char element) {
		for (char c : arr)
			if (c == element)
				return true;
		return false;
	}

	@Override
	public String toString() {
		return left + "=" + right;
	}
}