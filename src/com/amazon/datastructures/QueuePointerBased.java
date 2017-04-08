package com.amazon.datastructures;

public class QueuePointerBased extends Queue {

	Node front;
	Node back;
	int size;

	class Node {

		private int item;
		private Node next;

		Node(int item) {
			this.item = item;
		}

	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(int value) {
		Node node = new Node(value);
		// empty queue
		if (isEmpty())
			front = node;
		else
			back.next = node;
		back = node;
		size++;
	}

	public int dequeue() {
		if (isEmpty())
			throw new EmptyQueueException("Queue is empty");

		int value = front.item;
		front = front.next;
		if (front == null)
			back = null;
		--size;

		return value;
	}

	public int front() {
		if (size == 0)
			throw new EmptyQueueException("Queue is empty");

		return front.item;
	}

}
