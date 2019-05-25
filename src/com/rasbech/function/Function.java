package com.rasbech.function;

public class Function {
	private OperationalTree functionTree;
	
	public Function(OperationalTree functionalTree) {
		this.functionTree = functionalTree;
	}

	public double evaluate(double variable) {
		return functionTree.evaluate(variable);
	}


	public static Function parseFunction(String function) {
		return FunctionParser.parseFunction(function);
	}
	
	@Override
	public String toString() {
		return functionTree.toString();
	}
}