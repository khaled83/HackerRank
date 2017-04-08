package com.amazon.datastructures;

import java.util.Arrays;
import java.util.HashSet;

import javax.naming.SizeLimitExceededException;

public abstract class Stack {

	public abstract boolean isEmpty();
	public abstract boolean isFull();
	public abstract int size();
	public abstract void push(int value) throws SizeLimitExceededException;
	public abstract int pop();
	public abstract int top();
	
	// Applications of Stacks
	public static Stack newInstance() {
		return new StackLinkedList();
	}
	
	public static boolean isBalancedBraces(String s) throws SizeLimitExceededException {
		// to use back integer stack, assume 6 for { and 9 for }
		s = s.replaceAll("\\{", "6").replaceAll("}", "9");
		Stack stack = Stack.newInstance();
		for (char c : s.toCharArray()) {
			int i = Character.getNumericValue(c);
			if (i == 6)
				stack.push(i);
			else if (i == 9) {
				if (stack.isEmpty())
					return false;
				stack.pop();
			}
		}

		return stack.isEmpty();
	}
	
	public static boolean isPalyndrome(String s) throws SizeLimitExceededException {
	    int n = s.length();
	    // left part is reverse of right part
	    int leftEnd = (n/2)-1;
	    
	    Stack stack = Stack.newInstance();
	    for (int i=0; i<=leftEnd; i++) {
	        stack.push(s.charAt(i));
	    }
	    
	    int rightStart = (n/2)+(n%2);
	    for (int i=rightStart; i<n; i++) {
	        if(stack.isEmpty() || stack.pop() != s.charAt(i))
	            return false;
	    }
	    
	    return stack.isEmpty();
	}

	public static int postfix(String s) throws SizeLimitExceededException {
	    Stack operands = newInstance();
	    HashSet<Character> operators = new HashSet<Character>(Arrays.asList('+', '-', '*', '/'));
	    
	    for (char c : s.toCharArray() ) {
	        if (operators.contains(c)) {
	            if (operands.size() < 2)
	                throw new IllegalArgumentException("Postfix expression is invalid");
	            int x = operands.pop();
	            int y = operands.pop();
	            operands.push(operate(x, y, c));
	        } else {
	            int value = Character.getNumericValue(c);
	            operands.push(value);
	        }
	    }
	    
	    if (operands.size() != 1)
	        throw new IllegalArgumentException("Postfix expression is invalid");
	    
	    return operands.pop();
	}

	// TODO implement again, first try took 40m 37s
	public static String infixToPostfix(String exp) {
		java.util.Stack<Character> operators = new java.util.Stack<Character>();
		HashSet<Character> operatorSet = new HashSet<Character>(Arrays.asList('+', '-', '*', '/', '(', ')'));

		StringBuilder result = new StringBuilder();
		for (char c : exp.toCharArray()) {
			if (operatorSet.contains(c)) {
				if (c == ')') {
					while (operators.peek() != '(')
						result.append(operators.pop());
					operators.pop(); // remove '('
				} else {
					operators.push(c);
				}
			} else {
				result.append(c);
				// apply high precedence operator to last two operands
				if (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
					result.append(operators.pop());
				}
			}
		}

		while (!operators.isEmpty()) {
			result.append(operators.pop());
		}
		
		return result.toString();
	}
	
	private static int operate(int x, int y, char operator) {
		int result = 0;
	    if (operator == '+' ) {
	        result = x + y;
	    } else if (operator == '-') {
	    	result = x - y;
	    } else if (operator == '*') {
	    	result = x * y;
	    } else if (operator == '/') {
	        if(y == 0)
	        	result = Integer.MAX_VALUE;
	        result = x / y;
	    }
	    return result;
	}
	
}
