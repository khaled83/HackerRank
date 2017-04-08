package com.amazon.datastructures;

public class QueueLinkedList extends Queue {
	
	private LinkedList queue = new LinkedList();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	public void enqueue(int value) {
		queue.addFirst(value);
	}
	
	public int dequeue() {
		if(queue.isEmpty())
			throw new EmptyQueueException("Queue is empty");
		return queue.removeLast();
	}
	
	public int front() {
		return queue.get(queue.size()-1);
	}

}
