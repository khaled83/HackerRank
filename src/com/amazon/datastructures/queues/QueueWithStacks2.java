package com.amazon.datastructures.queues;

import java.util.*;

public class QueueWithStacks2 extends Queue {
	
	private Stack<Integer> s1 = new Stack<Integer>();
    private Stack<Integer> s2 = new Stack<Integer>();
    
    public void enqueue(int x) {
        s1.add(x);
    }
    
    public int front() throws EmptyQueueException {
        checkEmpty();
        ensurePopCapacity();
        return s2.peek();
    }
    
    public int dequeue() {
        checkEmpty();
        ensurePopCapacity();
        return s2.pop();
    }
    
    private void ensurePopCapacity() {
        // elements available for dequeue
        if (!s2.isEmpty()) {
            return;
        }
        // move elements from push stack to pop stack
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
    }
    
    private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
    }
            
    public int size() {
        return s1.size() + s2.size();
    }
            
    public boolean isEmpty() {
        return size() == 0;
    }
    
	@Override
	@Deprecated
	public List<Integer> asList() {
		throw new UnsupportedOperationException("This method is not supported for this implementation of queue");
	}
    
}
