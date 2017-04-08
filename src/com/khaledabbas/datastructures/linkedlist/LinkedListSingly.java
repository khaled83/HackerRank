package com.khaledabbas.datastructures.linkedlist;

import java.util.Hashtable;

public class LinkedListSingly<E extends Comparable<E>> {
	
	protected static class Node<E> {
		protected E element;
		protected Node<E> next = null;
		
		protected Node(E element) {
			this.element = element;
		}
	}
	
	protected int size = 0;
	protected Node<E> head = null;
	protected Node<E> tail = null;
	

	public LinkedListSingly() {
	}

	public void add(E e) {
		addLast(e);
	}

	public void add(E e, int indx) {
		rangeCheckForAdd(indx);

		Node<E> newNode = new Node<E>(e);

		if (indx == 0)
			addFirst(e);
		else {
			Node<E> prev = get(indx-1);
			newNode.next = prev.next;
			prev.next = newNode;
			
			if(indx == size)
				tail = newNode;
			
			size++;
		}


	}

	public void addFirst(E e) {
		Node<E> newNode = new Node(e);
		newNode.next = head;
		head = newNode;
		if(size == 0)
			tail = head;
		size++;
	}

	public void addLast(E e) {
		add(e, size);
	}
	
	public void sortedAdd(E e) {
		sortedAdd(head, e);
	}
	
	@SuppressWarnings("unchecked")
	private void sortedAdd(Node<E> cur, E e) {
		if(cur == null || ((Comparable<E>) e).compareTo(cur.element) <= 0 ) {
			if(cur == null) {
				if(empty())
					addFirst(e); 
				else
					addLast(e);
			}
			else {
				int insertIndx = indexOf(cur.element);
				if(e.equals(3)) {
					System.out.println("inserting 3 >> " + insertIndx);
					System.out.println("cur:"+cur.element);
					System.out.println(((Comparable<E>) e).compareTo(cur.element));
				}
				add(e, insertIndx);
			}
		}
		else
			sortedAdd(cur.next, e);
	}
	
	public int indexOf(E e) {
		
		int indx = 0;
		Node<E> cur = head;
		do {
			if(cur.element.equals(e))
				return indx;
			cur = cur.next;
			indx++;
		} while(cur != null);
		
		
		return -1;
	}

	public E remove(int indx) {
		if (indx == 0)
			return removeFirst();
		else {
			rangeCheck(indx);
			Node<E> prev = get(indx - 1);
			Node<E> remove = prev.next;
			prev.next = remove.next;
			E value = remove.element;
			remove = null;
			
			if(indx == (size-1))
				tail = prev;
			
			size--;
			
			return value;
		}
	}

	public E removeFirst() {
		rangeCheck(0);

		Node<E> remove = head;
		head = remove.next;
		E value = remove.element;
		remove = null;
		
		size--;
		
		if(size == 0)
			tail = head;
		
		return value;
	}

	public Node<E> get(int indx) {
		rangeCheck(indx);
		Node<E> cur = head;
		for (int i = 0; i < indx; i++)
			cur = cur.next;
		return cur;
	}

	private void rangeCheckForAdd(int loc) {
		if (loc < 0 || loc > size)
			throw new ArrayIndexOutOfBoundsException(loc);
	}

	private void rangeCheck(int loc) {
		if (loc < 0 || loc >= size)
			throw new ArrayIndexOutOfBoundsException(loc);
	}
	
	public int size() {
		return size;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	@Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{");
    	Node<E> cur = head;
    	do {
    		sb.append(cur.element.toString());
    		sb.append(",");
    		cur = cur.next;
    	} while( cur != null);
    	if(!empty())
    		sb.setLength(sb.length()-1); // trim last ','
    	sb.append("}");
    	
    	return new String(sb);
    }

}
