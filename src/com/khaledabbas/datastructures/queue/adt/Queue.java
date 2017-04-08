package com.khaledabbas.datastructures.queue.adt;

import java.util.*;

public class Queue<E> {
	
	LinkedList<E> list = new LinkedList<E>();
	
	public void enqueue(E e)
	{
		list.addLast(e);
	}
	
	public E dequeue()
	{
		return list.removeFirst();
	}
	
	public boolean empty()
	{
		return list.isEmpty();
	}

}
