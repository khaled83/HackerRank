package com.amazon.datastructures.queues;

import java.util.ArrayList;
import java.util.List;

import com.amazon.datastructures.linkedlist.LinkedList;

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

	@Override
	public List<Integer> asList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < queue.size(); i++)
			list.add(queue.get(i));
		return list;
	}

}
