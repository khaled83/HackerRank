package com.indeed.khaledabbas.datastructures.linkedlist;

import java.util.Hashtable;

import com.indeed.khaledabbas.datastructures.stack.adt.Stack;

public class LinkedListExtended<E extends Comparable<E>> extends LinkedListSingly<E> {
	
	public void writeString()
	{
		System.out.print("{");
		writeString(head);
		System.out.println("}");
	}
	
	private void writeString(Node<E> cur) {
		if(cur != null) {
			System.out.print(cur.element + ",");
			writeString(cur.next);
		}
	}
	
	public void writeStringBackward()
	{
		System.out.print("{");
		writeStringBackward(head);
		System.out.println("}");
	}
	
	private void writeStringBackward(Node<E> cur) {
		if(cur != null) {
			writeStringBackward(cur.next);
			System.out.print(cur.element + ",");
		}
	}
	
	// 1 -> 2 -> 2 -> 1
	//           s          
	//                  f
	// 122
	public boolean isPalyndrome()
	{
		Node<E> slow = head;
		Node<E> fast = head;
		
		Stack<E> stack = new Stack<E>();
		
		while(fast != null && fast.next != null)
		{
			stack.push(slow.element);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(fast != null)
			slow = slow.next;
		
		while(slow != null)
		{
			if(! slow.element.equals(stack.pop()) )
				return false;
			slow = slow.next;
		}
			
		return true;
	}
	
	
	
	
	
	public void partition(E e)
	{
	    Node<E> prev = head;
	    Node<E> cur = head.next;
	    
	    while(cur != null)
	    {
	        if(cur.element.compareTo(e) < 0)
	        {
	            prev.next = cur.next;
	            Node<E> oldHead = head;
	            head = cur;
	            cur.next = oldHead;
	        }
	        else
	            prev = prev.next;
	        
	        cur = prev.next;
	    }
	}
	
	public void printKthToLastRec(int k)
	{
		printKthToLastRec(head, k);
	}
	
	private int printKthToLastRec(Node<E> head, int k)
	{
		if(head == null)
			return -1;
			
		int i = printKthToLastRec(head.next, k) + 1;
		
		if(i == k)
			System.out.println((k+1) + "th to last element: " + head.element);
		
		return i;
	}
	
	public void removeKthToLast(int k) throws Exception
	{
	    Node<E> prev = head;
	    Node<E> last = head;
	    
	    // move last K steps forward
	    for(int skip = 1; skip<=k+1; skip++) {
	    	if(last == null)
	    		throw new Exception("Out of linked list range: " + k);
	    	last = last.next;
	    }
	    
	    if(last == null)
    	{
    		// remove head
    		Node<E> del = head;
    		head = head.next;
    		del.next =null;
    		del = null;
    		return;
    	}
	        
	    while(last != null && last.next != null)
	    {
	        last = last.next;
	        prev = prev.next;
	    }
	    
	    Node<E> del = prev.next;
	    prev.next = del.next;
	    del.next = null;
	    del = null;
	        
	}
	
	
	public void removeDuplicatesWithoutBuffer()
	{
		Node<E> base = head;
		while(base != null)
		{
			Node<E> prev = base;
			Node<E> cur = base.next;
			while(cur != null)
			{
				if(cur.element == base.element)
				{
					prev.next = cur.next;
					Node<E> old = cur;
					old.next = null;
					old = null;
				}
				else
				{
					prev = prev.next;
				}
				
				cur = prev.next;
			}
			
			base = base.next;
		}
	}
	
	public void removeDuplicates()
	{
	    Node<E> prev = head;
	    Node<E> cur = head.next;
	    Hashtable<E, Boolean> table = new Hashtable<E, Boolean>();
	    table.put(head.element, true);
	    
	    while(cur != null)
	    {
	        if(table.containsKey(cur.element))
	        {
	            prev.next = cur.next;
	            Node<E> dup = cur;
	            dup.next = null;
	            dup = null;
	        }
	        else
	        {
	            table.put(cur.element, true);
	            prev = prev.next;
	        }
	        cur = prev.next;
	    }
	}

}
