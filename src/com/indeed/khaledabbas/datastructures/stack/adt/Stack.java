package com.indeed.khaledabbas.datastructures.stack.adt;

import java.util.LinkedList;

public class Stack<E> {
	
	private LinkedList<E> list = new LinkedList<E>();
	
	public boolean empty() {
		return list.isEmpty();
	}
	
	public void push(E e) {
		list.addFirst(e);
	}
	
	public E pop() {
		return list.removeFirst();
	}
	
	public E top() {
		return list.getFirst();
	}

}
