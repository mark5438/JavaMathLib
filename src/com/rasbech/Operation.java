package com.rasbech;

public class Operation {
	public static enum Operations {
		MULTIPLY, DIVIDE, PLUS, MINUS, NUMBER
	}
	private final Operation leftNode, rightNode;
	private final Operations operation;
	
	public Operation(Operation leftNode, Operation rightNode, Operations operation) {
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.operation = operation;
	}	
}