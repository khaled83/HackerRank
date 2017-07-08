package com.amazon.datastructures.stacks;

import java.util.Stack;

public class StackWithMax2 {
	
	private final Stack<Integer> stack;
    private final Stack<Integer> maxStack = new Stack<Integer>();
    
    public StackWithMax2() {
        stack = new Stack<Integer>();
        maxStack.push(Integer.MIN_VALUE);
    }
    
    public void push(int item) {
        stack.push(item);
        if (item >= max()) {
            maxStack.push(item);
        }
    }
    
    public int pop() {
        int top = stack.pop();
        if (top == max()) {
            maxStack.pop();
        }
        return top;
    }
    
    public int peek() {
        return stack.peek();
    }
    
    // implement all the methods
    
    public int max() {
        return maxStack.peek();
    }

}
