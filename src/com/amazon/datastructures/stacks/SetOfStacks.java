package com.amazon.datastructures.stacks;

import java.util.*;

public class SetOfStacks<E> extends Stack {
	
	// Array, ArrayList, LinkedList, Stack, TreeSet, etc.
    // Properties: (1) dynamic size (2) maintain insertion order (3) remove/add efficiently => LinkedList with back pointer
    LinkedList<java.util.Stack<E>> stacks = new LinkedList<java.util.Stack<E>>();
    
    private static final int DEFAULT_MAX_PER_STACK = 3;
    private final int MAX_PER_STACK;
    
    int size;
    
    public SetOfStacks() {
        this(DEFAULT_MAX_PER_STACK);
    }
    
    public SetOfStacks(int maxPerStack) {
        this.MAX_PER_STACK = maxPerStack;
    }
    
    // O(1)
    @Override
    public void push(Integer e) {
        checkCapacity();
        current().push((E) e);
        size++;
    }
    
    private java.util.Stack<E> current() {
    	if (stacks.isEmpty()) {
    		return null;
    	}
        return stacks.getLast();
    }
    
    private void checkCapacity() {
    	java.util.Stack<E> s = current();
        if (s == null || s.size() >= MAX_PER_STACK) {
            growCapacity();
        }
    }
    
    private void growCapacity() {
        stacks.add(new java.util.Stack<E>());
    }
    
    // O(1)
    @Override
    public int top() {
        checkEmpty();
        return (int) current().peek();
    }
    
    // O(1)
    @Override
    public int pop() {
        checkEmpty();
        size--;
        return (int) current().pop();
    }
    
    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        if (current().isEmpty()) {
            shrinkCapacity();
        }
    }
    
    private void shrinkCapacity() {
        if (!stacks.isEmpty()) {
            stacks.removeLast();
        }
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

	@Override
	public boolean isFull() {
		return false;
	}

}
