package com.amazon.datastructures.queues;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import javax.naming.SizeLimitExceededException;

public class QueueArrayBased extends Queue {

	private int[] items;
	private static final int DEFAULT_MAX_SIZE = 10;
	private final int maxSize;
	private int size = 0;

	private int front = 0;
	private int back = -1;

	public QueueArrayBased() {
		this(DEFAULT_MAX_SIZE);
	}

	public QueueArrayBased(int maxSize) {
		this.maxSize = maxSize;
		items = new int[maxSize];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == maxSize;
	}

	public int size() {
		return size;
	}

	public void enqueue(int value) throws SizeLimitExceededException {
		if (isFull())
			throw new SizeLimitExceededException("Queue is full");

		back = (back + 1) % maxSize;
		items[back] = value;
		++size;
	}

	public int dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("Queue is empty");

		int result = items[front];
		front = (front + 1) % maxSize;
		--size;
		return result;
	}

	public int front() {
		if (isEmpty())
			throw new EmptyQueueException("Queue is empty");

		return items[front];
	}

	@Override
	public List<Integer> asList() {
		List<Integer> list = new ArrayList<Integer>();
		for (int item : items)
			list.add(item);
		return list;
	}

//	@Override
//	public String toString() {
//		return items.toString();
//	}
	
}
