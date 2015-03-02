package com.indeed.khaledabbas.datastructures.stack.node;

import com.indeed.khaledabbas.datastructures.stack.StackException;

public class Stack<E> {

	private class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E e) {
			this.element = e;
		}
	}
	
	private Node<E> top = null;
	private int size = 0;
	
	public boolean empty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}
	
	// 2 1 0
	public void push(E e) {
		Node<E> newNode = new Node<E>(e);
		newNode.next = top;
		top = newNode;
		size++;
	}
	
	public E top() throws StackException {
		if(empty())
			throw new StackException("Stack is empty");
		
		return top.element;
	}
	
	public E pop() throws StackException {
		if(empty())
			throw new StackException("Stack is empty");
		
		Node<E> oldTop = top;
		E value = oldTop.element;
		top = top.next;
		oldTop = null;
		size--;
		
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("}");
		
		Node<E> cur = top;
		do {
			sb.append(cur.element.toString());
			sb.append(",");
			cur = cur.next;
		} while (cur != null);
		
		sb.setLength(sb.length()-1);
		sb.append("{");
		return new String(sb.reverse());
	}
	
}
