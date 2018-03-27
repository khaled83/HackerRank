package com.amazon.datastructures.queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazon.datastructures.queues.Queue.EmptyQueueException;
import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class QueueCircularArray extends Queue {
	
	private int[] items;
    private int size;
    private int front, back;
    
    /** max allowed array capacity in JVM */
    private int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    
    public QueueCircularArray(int initialCapacity) {
        items = new int[initialCapacity];
        front = 0;
        back  = -1;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void enqueue(int item) {
        ensureCapacity(size + 1);
        back = (back + 1) % capacity();
        items[back] = item;
        size++;
    }
    
    /**
        f  b
      [       ]
      
        bf
      [aaaaaa---] 
      
        b f
      [aa-aaa---] 
    */
    
    private void ensureCapacity(int minCapacity) {
        int capacity = capacity();
        if (capacity >= minCapacity) {
            return;
        }
        int oldCapacity = capacity;
        capacity = capacity + (capacity >> 1);
        capacity = Math.max(capacity, minCapacity);
        items = Arrays.copyOf(items, capacity);
        if (back < front) {
            System.arraycopy(items, 0, items, oldCapacity, back + 1);
        }
        back = oldCapacity + back + 1;
    }
    
    public int peek() {
        return items[front];
    }
    
    public int dequeue() throws EmptyQueueException {
    		if (isEmpty()) {
    			throw new EmptyQueueException("Queue is empty");
    		}
        int item = items[front];
        front = (front + 1) % capacity();
        --size;
        return item;
    }
    
    private int capacity() {
        return items.length;
    }
    
	@Override
	public int front() {
		return peek();
	}

	@Override
	public List<Integer> asList() {
		List<Integer> list = new ArrayList<Integer>();
		int indx = front;
		int count = 0;
		while (count < size()) {
			list.add(items[indx]);
			indx = (indx + 1) % capacity();
			count++;
		}
		return list;
	}
    
	public void print() {
		System.out.println("F="+front + " B="+ back + " size=" + 
				size);
		ArrayUtils.printArray(items);
	}
	
}
