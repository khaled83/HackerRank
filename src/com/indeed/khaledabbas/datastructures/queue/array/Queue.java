package com.indeed.khaledabbas.datastructures.queue.array;

import com.indeed.khaledabbas.datastructures.queue.QueueException;

public class Queue<E>
{
    //   5
    //   0 1 2 - -
    //   f>  b>
    //   - - - - -
    // b f
    
    private Object[] elements;
    private int size = 0;
    
    private int front = 0;
    private int back = -1;
    
    private static final int DEFAULT_MAX_CAPACITY = 4;
    int maxCapacity;

    public Queue()
    {
        this(DEFAULT_MAX_CAPACITY);
    }
    
    public Queue(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        elements = new Object[maxCapacity];
    }
    
    public void enqueue(E e) throws QueueException
    {
        if(size == maxCapacity)
            throw new QueueException("Queue is full");
            
        back = (back+1) % maxCapacity;
        elements[back] = e;
        size++;
    }

    public E dequeue() throws QueueException
    {
        if(empty())
            throw new QueueException("Queue is empty");
            
        E value = (E) elements[front];
        elements[front] = null;
        front = (front+1) % maxCapacity;
        size--;
        
        return value;
    }
    
    public boolean empty()
    {
        return size == 0;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	for(Object o : elements)
    		sb.append(o).append(',');
    	
    	if(!empty())
    		sb.setLength(sb.length()-1);
    	
    	sb.append("}");
    	
    	return new String(sb);
    }

}
