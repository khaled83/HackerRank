package com.indeed.khaledabbas.datastructures.stack.array;

import com.indeed.khaledabbas.datastructures.stack.StackException;

public class Stack<E> {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	
	private Object[] elements;
	private int top = -1;
	
	//     t
	// 0 1 2 - -
	
	public Stack() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public Stack(int initialCapacity) {
		elements = new Object[initialCapacity];
	}
	
	public boolean empty() {
		return top == -1;
	}
	
	public int size() {
		return top+1;
	}
	
	public void push(E e) throws StackException {
		if(top == DEFAULT_INITIAL_CAPACITY - 1)
			throw new StackException("Stack is full");
		
		elements[++top] = e;
	}
	
	@SuppressWarnings("unchecked")
	public E pop() throws StackException {
		if(empty())
			throw new StackException("Stack is empty");
		
		E value = (E) elements[top];
		elements[top] = null;
		top--;
		
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public E top() throws StackException {
		if(empty())
			throw new StackException("Stack is empty");
		
		return (E) elements[top];
	}
	
}
