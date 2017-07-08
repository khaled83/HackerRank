package com.amazon.datastructures.stacks;

import java.util.EmptyStackException;

import javax.naming.SizeLimitExceededException;

public class StackArray extends Stack {

	private static final int DEFAULT_MAX = 10;
	private final int MAX_SIZE;

	private int[] items;
	int top = -1;

	public StackArray() {
        MAX_SIZE = DEFAULT_MAX;
        items = new int[DEFAULT_MAX];
    }

	public StackArray(int maxSize) {
        MAX_SIZE = maxSize;
        items = new int[MAX_SIZE];
    }

	public int size() {
        return top + 1;
    }

	public boolean isEmpty() {
		return top < 0;
	}

	public boolean isFull() {
		return top == (MAX_SIZE - 1);
	}

	public void push(int value) throws SizeLimitExceededException {
		if (isFull())
			throw new SizeLimitExceededException("Stack is full");
		items[++top] = value;
	}

	public int pop() {
		if (isEmpty())
			throw new EmptyStackException();
		return items[top--];
	}

	public int top() {
		if (isEmpty())
			throw new EmptyStackException();
		return items[top];
	}

}
