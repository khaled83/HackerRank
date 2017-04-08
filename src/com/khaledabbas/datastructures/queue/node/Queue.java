package com.khaledabbas.datastructures.queue.node;

import com.khaledabbas.datastructures.queue.QueueException;

public class Queue<E>
{

    // 0 -> 1 -> 2 -> N
    // f >> f     b >> b
    
    private static class Node<E>
    {
        private E element;
        private Node<E> next;
        
        public Node(E e)
        {
            this.element = e;
        }
    }
    
    private Node<E> front = null;
    private Node<E> back = null;
    
    public boolean empty()
    {
        return front == null;
    }
    
    public void enqueue(E e)
    {
        Node<E> newNode = new Node<E>(e);
        newNode.next = null;
        Node<E> oldBack = back;
        
        if(empty())
            front = newNode;
        else
            oldBack.next = newNode;
            
        back = newNode;
    }
    
    public E dequeue() throws QueueException
    {
        if(empty())
            throw new QueueException("Queue is empty");
        
        E value = front.element;    
        Node<E> oldFront = front;
        
        if(front == back)
            back = front.next;
        front = front.next;
        
        oldFront.next = null;
        oldFront = null;
        
        return value;
    }
    
    public E getFront() throws QueueException
    {
        if(empty())
            throw new QueueException("Queue is empty");
            
        return front.element;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	Node<E> cur = front;
    	while(cur != null) {
    		sb.append(cur.element).append(',');
    		cur = cur.next;
    	}
    	
    	if(!empty())
    		sb.setLength(sb.length()-1);
    	
    	sb.append("}");
    	
    	return new String(sb);
    }

}
