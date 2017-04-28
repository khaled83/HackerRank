package com.amazon.datastructures.queues;

import java.util.List;

import javax.naming.SizeLimitExceededException;

public abstract class Queue {
	
	public abstract boolean isEmpty();
	
	public abstract int size();
	
	public abstract void enqueue(int value) throws SizeLimitExceededException ;
	
	public abstract int dequeue() throws EmptyQueueException;
	
	public abstract int front();
	
	public abstract List<Integer> asList();
	
	public Queue() {}
	
	public static Queue newInstance() {
		return new QueuePointerBased();
	}
	
	@SuppressWarnings("serial")
	public class EmptyQueueException extends RuntimeException {
		
		EmptyQueueException(String msg) {
			super(msg);
		}
		
	}
	
	public static int valueOf(String s) {
		// copy queue
		Queue q = Queue.newInstance();
		for (char c : s.toCharArray()) {
			try {
				q.enqueue(Character.getNumericValue(c));
			} catch (SizeLimitExceededException e) {
			}
		}
		int result = 0;
		while (!q.isEmpty()) {
			result = (result*10) + q.dequeue();
		}
		return result;
	}
	
}
