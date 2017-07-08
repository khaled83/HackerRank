package com.amazon.datastructures.queues;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import javax.naming.SizeLimitExceededException;

public class QueueWithStacks extends Queue {
	
	private Stack<Integer> s1 = new Stack<Integer>();
	private Stack<Integer> s2 = new Stack<Integer>();

	
    @Override
    public void enqueue(int item) {
        s1.push(item);
    }
    
    
    @Override
    public int dequeue() {
        checkCapacity();
        ensureGetStackReady();
        return s2.pop();
    }
    
    @Override
    public int front() {
        checkCapacity();
        ensureGetStackReady();
        return s2.peek();
    }
    
    private void checkCapacity() {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty");
    }
    
    private void ensureGetStackReady() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
    }
    
    @Override
    public int size() {
        return s1.size() + s2.size();
    }
    
    @Override
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    @Override
    public List<Integer> asList() {
    	ensureGetStackReady();
    	return s2.subList(0, s2.size());
    }
    
}
