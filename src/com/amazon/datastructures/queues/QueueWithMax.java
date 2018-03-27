package com.amazon.datastructures.queues;

import java.util.*;

public class QueueWithMax extends Queue {
	
	private java.util.Queue<Integer> q;
	private LinkedList<Integer> max;
	
	public QueueWithMax() {
		q = new LinkedList<Integer>();
		max = new LinkedList<Integer>();
	}
	
	public QueueWithMax(int[] arr) {
		q = new LinkedList<Integer>();
		max = new LinkedList<Integer>();
		for (int x : arr) {
			enqueue(x);
		}
	}
	
	public int front() {
		checkEmpty();
		return q.peek();
	}
	
	public void enqueue(int x) {
		while (!max.isEmpty() && x > max.getLast()) {
			max.removeLast();
		}
		max.addLast(x);
		q.add(x);
	}

	public int dequeue() {
		checkEmpty();
		if (q.peek() == max()) {
			max.removeFirst();
		}
		return q.poll();
	}
	
	public int max() {
		return max.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return q.isEmpty();
	}

	@Override
	public int size() {
		return q.size();
	}

	@Override
	public List<Integer> asList() {
		return null;
	}
	
	 private void checkEmpty() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
    }
}
