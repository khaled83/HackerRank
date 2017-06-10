package com.amazon.datastructures;

import java.util.EmptyStackException;

import com.amazon.datastructures.stacks.Stack;

public class StackLinkedList extends Stack {

	private LinkedList items = new LinkedList();
	private int size = 0;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void push(Integer value) {
		items.addFirst(value);
		++size;
	}

	public int pop() throws EmptyStackException {
		checkEmpty();
		--size;
		return items.remove(0);
	}

	public int top() throws EmptyStackException {
		checkEmpty();
		return items.get(0);
	}

	private void checkEmpty() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
	}

	@Override
	public boolean isFull() {
		return false;
	}

}
