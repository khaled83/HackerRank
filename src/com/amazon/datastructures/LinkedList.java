package com.amazon.datastructures;

public class LinkedList {

	private Node head;
	private int size;

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	/**
	 * index h 1 2 12 -> 3 -> 25
	 *
	 * 
	 * after h 1 2 3 12 -> 3 -> 8 -> 25
	 */
	public void add(int index, int item) {
		// index=size is allowed which adds a new last element
		if (index != size)
			checkIndex(index);

		Node newNode = new Node(item);

		// addFirst
		if (index == 0) {
			newNode.next = head;
			head = newNode;
		} else {
			Node prev = find(index - 1);
			newNode.next = prev.next;
			prev.next = newNode;
		}
		++size;
	}
	
	public void addFirst(int item) {
		add(0, item);
	}

	public void addLast(int item) {
		add(size, item);
	}

	public int get(int index) {
		checkIndex(index);
		return find(index).item;
	}

	/**
	 * 
	 * index h 1 2 12 -> 3 -> 25
	 *
	 * 
	 * after h 1 12 -> 25
	 */
	public int remove(int index) {
		checkIndex(index);
		Node cur = null;

		// special case: removeFirst
		if (index == 0) {
			cur = head; // to be removed
			head = cur.next;
		} else {
			Node prev = find(index - 1);
			cur = prev.next; // to be removed
			prev.next = cur.next;
		}
		--size;
		cur.next = null;
		return cur.item;
	}

	public int removeFirst() {
		return remove(0);
	}
	
	public int removeLast() {
		return remove(size-1);
	}
	
	private Node find(int index) {
		Node cur = head;
		for (int i = 0; i < index; i++)
			cur = cur.next;
		return cur;
	}

	public String traverseRecursive() {
	    return traverseRecursive(head).trim();
	} 

	private String traverseRecursive(Node head) {
		if (head != null) {
			return head.item + " " + traverseRecursive(head.next);
		}
		return "";
	}
	
	public String traverseBackwardRecursive() {
		return traverseBackwardRecursive(head).trim();
	}

	private static String traverseBackwardRecursive(Node head) {
		if (head != null) {
			return traverseBackwardRecursive(head.next) + " " + head.item;
		}
		return "";
	}
	
	// @TODO works for add, not for get and remove
	private void checkIndex(int index) {
		boolean valid = index >= 0 && index < size;
		if (!valid)
			throw new IndexOutOfBoundsException();
	}

	private static class Node {
		int item;
		Node next;

		Node(int item) {
			this.item = item;
		}
	}
	
	@Override
	public String toString() {
		Node cur = head;
		StringBuilder sb = new StringBuilder();
		while(cur != null) {
			sb.append(cur.item).append(" ");
			cur = cur.next;
		}
		sb.append("\n");
		return sb.toString();
	}

}
