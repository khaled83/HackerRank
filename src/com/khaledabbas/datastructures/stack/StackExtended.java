package com.khaledabbas.datastructures.stack;

import com.khaledabbas.datastructures.stack.node.Stack;

public class StackExtended<E extends Comparable> extends Stack<E> {
	
	public static void main( String[] args ) throws StackException {
		StackExtended<Integer> stack = new StackExtended<Integer>();
		stack.push(29);
		stack.push(10);
		stack.push(14);
		stack.push(37);
		stack.push(13);
		System.out.println( stack.toString() );
		System.out.println( stack.sort().toString() );
	}

	public Stack<E> sort() throws StackException {
		Stack<E> sorted = new Stack<E>();

		while (!this.empty()) {
			E top = this.pop();
			while (!sorted.empty() && top.compareTo( sorted.top() ) < 0)
				this.push(sorted.pop());

			sorted.push(top);
		}
		
		return sorted;
	}

}
