package com.indeed.khaledabbas.datastructures.linkedlist;

import java.util.Hashtable;

import com.indeed.khaledabbas.datastructures.linkedlist.LinkedListSingly.Node;
import com.indeed.khaledabbas.datastructures.stack.adt.Stack;

public class LinkedListExtended<E extends Comparable<E>> extends LinkedListSingly<E> {
	
	public static void main(String[] args) {
		LinkedListExtended<Integer> list = new LinkedListExtended<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println( list.toString() );
		System.out.println( "result=" + list.kthToLast(5) );
		System.out.println(list.toString());
//		list.reverseRecursive();
		list.reverse(2, 5);
		System.out.println(list.toString());
	}
	
	private static class Pair<E> {
		private Node<E> first;
		private Node<E> second;

		Pair(Node<E> f, Node<E> s) {
			this.first = f;
			this.second = s;
		}
	}
	
	// O( n )
	// Time: 00:31:44
	public void reverse(int start, int end) {
		Node<E> prev = head;
		for (int indx = 2; indx <= start - 1; indx++)
			prev = prev.next;

		Node<E> first = prev.next;
		Pair<E> result = reverse(first, first.next, start, end);
		prev.next = result.first;
		first.next = result.second;
	}

	private Pair<E> reverse(Node<E> prev, Node<E> cur, int start, int end) {

		if (start < end) {
			Pair<E> p = reverse( cur, cur.next, ++start, end );
			cur.next = prev;
			return p;
		} else {
			return new Pair<E>(prev, cur);
		}

	}
	
	public void reverseRecursive() {
		head = reverse2(null, head);
	}

	public Node<E> reverse2(Node<E> prev, Node<E> cur) {
		if (cur == null)
			return prev;
		else {
			Node<E> next = cur.next;
			cur.next = prev;
			return reverse2(cur, next);
		}
	}
	
	public void reverse() {

		Node<E> last = null;
		Node<E> cur = head;

		while (cur != null) {
			Node<E> tmp = cur.next;
			cur.next = last;
			last = cur;
			cur = tmp;
		}
		
		head = last;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedListSingly merge(LinkedListSingly list1, LinkedListSingly list2) 
	{
		LinkedListSingly mergeList = new LinkedListSingly();
		Node p1 = list1.head;
		Node p2 = list2.head;
		
		// initialization with a dummy head
		mergeList.head = new Node(null);
		Node tail = mergeList.head;

		// 29 10 14
		// 13 37

		while (p1 != null && p2 != null) {
			if ( ( (Comparable) p1.element).compareTo(p2.element) < 0) {
				Node newNode = new Node( p1.element);
				tail.next = newNode;
				tail = tail.next;
				p1 = p1.next;
			} else {
				Node newNode = new Node( p2.element);
				tail.next = newNode;
				tail = tail.next;
				p2 = p2.next;
			}
		}

		// finish off list1
		if(p1 != null)
			tail.next = p1;
		if(p2 != null)
			tail.next = p2;
		
		// remove dummy head
		mergeList.head = mergeList.head.next;

		return mergeList;

	}
	
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
	
	public E kthToLast(int k) {
		if (head == null)
			return null;
		
		int[] kSoFar = {0};
		Node<E> node = kthToLast(head, k, kSoFar); 
		return node != null ? node.element : null;
	}

	// revisit code
	public Node<E> kthToLast(Node<E> head, int k, int[] kSoFar) {
		// last element
		Node<E> node = head;
		if (head == null) {
			kSoFar[0] = -1;
			return null;
		}
		else {
			node = kthToLast(head.next, k, kSoFar);
			kSoFar[0]++;
			if ( kSoFar[0] == k)
				return head;
		}

		return node;
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
