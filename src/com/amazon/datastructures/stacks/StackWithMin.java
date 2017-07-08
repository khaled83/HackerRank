package com.amazon.datastructures.stacks;

import java.util.*;

public class StackWithMin extends Stack {
    private LinkedList<Integer> items = new LinkedList<Integer>();
    
    private HashMap<Integer, Integer> minMap = new HashMap<Integer, Integer>();
    private int min = Integer.MAX_VALUE;
    
    @Override
    public void push(Integer item) {    
        items.addLast(item);
        // check min
        if (item < min) {
            minMap.put(item, min);
            min = item;
        }
    }
    
    @Override
    public int pop() {
        if (isEmpty())
            throw new EmptyStackException();
        
        int item = items.removeLast();
        if (item == min) {
            min = minMap.get(min);
            minMap.remove(item);
        }
        
        return item;
    }
    
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        
        return items.getLast();
    }
    
    public int min() {
        if (isEmpty())
            throw new EmptyStackException();
        
        return min;
    }
    
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public int size() { 
        return items.size();
    }

	
	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int top() {
		return peek();
	} 

}
