package com.rasbech.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rasbech.expression.VariableExpression;
import com.rasbech.operations.ExpressionOperation;

public class Function {
	private OperationalTree functionTree;

	public Function(OperationalTree functionalTree) {
		this.functionTree = functionalTree;
	}
	
	public double evaluate(Map<Character, Double> variableValues) {
		return functionTree.evaluate(variableValues);
	}

	public static Function parseFunction(String function) {
		return FunctionParser.parseFunction(function);
	}

	public char[] getVariables() {
		ExpressionOperation[] bottomOperations = functionTree.getBottomLayerOperations();
		List<Character> variables = new ArrayList<Character>();
		for (ExpressionOperation eo : bottomOperations)
			if(eo.isVariable() && variables.contains(((VariableExpression)eo.getExpression()).getVariable()) == false)
				variables.add(((VariableExpression)eo.getExpression()).getVariable());
		return characterListToCharArray(variables);
	}

	private char[] characterListToCharArray(List<Character> characters) {
		char[] out = new char[characters.size()];
		for (int i = 0; i < characters.size(); i++)
			out[i] = characters.get(i);
		return out;
	}

	public int getOperationCount() {
		return functionTree.getOperationCount();
	}
	
	public void simplify() {
		functionTree.simplify();
	}
	
	@Override
	public String toString() {
		return functionTree.toString();
	}
}