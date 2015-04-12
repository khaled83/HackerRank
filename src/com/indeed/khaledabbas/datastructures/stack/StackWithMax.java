package com.indeed.khaledabbas.datastructures.stack;

import com.indeed.khaledabbas.datastructures.stack.node.Stack;

public class StackWithMax<E extends Comparable<E>> {
	
	public static void main(String[] args) throws StackException
	{
		StackWithMax<Integer> stack = new StackWithMax<Integer>();
		stack.push(2);
		System.out.println(stack.max());
		stack.push(2);
		System.out.println(stack.max());
		stack.push(1);
		System.out.println(stack.max());
		stack.push(4);
		System.out.println(stack.max());
		stack.push(5);
		System.out.println(stack.max());
		stack.push(5);
		System.out.println(stack.max());
		stack.push(3);
		System.out.println(stack.max());
		stack.pop();
		System.out.println(stack.max());
		stack.pop();
		System.out.println(stack.max());
		stack.pop();
		System.out.println(stack.max());
		stack.pop();
		System.out.println(stack.max());
		stack.push(0);
		System.out.println(stack.max());
		stack.push(3);
		System.out.println(stack.max());
	}

	private static class Pair<E, Integer> {
		E value;
		Integer count;

		Pair(E value, Integer count) {
			this.value = value;
			this.count = count;
		}
	}

	private Stack<E> stack = new Stack<E>();

	private Stack<Pair<E, Integer>> maxStack = new Stack<Pair<E, Integer>>();

	/** implementation 2 
	 * @throws StackException */

	public void push(E e) throws StackException {
		stack.push(e);
		if (maxStack.empty() || e.compareTo(maxStack.top().value) > 0) {
			maxStack.push(new Pair(e, 1));
		} else if (e.compareTo(maxStack.top().value) == 0) {
			maxStack.top().count++;
		}
	}

	public E pop() throws StackException {
		E top = stack.pop();

		if (top.compareTo(maxStack.top().value) == 0) {
			maxStack.top().count--;
			if (maxStack.top().count == 0)
				maxStack.pop();
		}

		return top;
	}

	public E max() throws StackException {
		return maxStack.top().value;
	}

}
