package com.amazon.datastructures.linkedlist;

public class LinkedListWithJump {
	
	Node head;
    
    private static class Node {
        int item;
        int order;
        
        Node next;
        Node jump;
        
        private Node(int item) {
        	this.item = item;
        }
    }
    
    public void addLast(int item, int jump) {
        if (head == null) {
            head = new Node(item);
            head.jump = head;
            return;
        }
    
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        tail.next = new Node(item);
        tail = tail.next;
        
        // find referenced jump node
        Node cur = head;
        while (cur != null) {
            if (cur.item == jump) {
                tail.jump = cur;
                break;
            }
            cur = cur.next;
        }
    }
    
    public void traverseJump() {
        traverseJump(head, 0);
    }
	/**
	h    o    list
	-    -    ---
	          h=>(a,-1,c)=>(b,-1,d)=>(c,-1,b)=>(d,-1,d)=>
	a    0    h=>(a, 0,c)=>(b,-1,d)=>(c,-1,b)=>(d,-1,d)=>
	c    1    h=>(a, 0,c)=>(b,-1,d)=>(c, 1,b)=>(d,-1,d)=>
	b    2    h=>(a, 0,c)=>(b, 2,d)=>(c, 1,b)=>(d,-1,d)=>
	d    3    h=>(a, 0,c)=>(b, 2,d)=>(c, 1,b)=>(d, 3,d)=>
	d    4    return
	*/    
    private void traverseJump(Node head, int order) {
        if (head == null || head.order >= 0) {
            return;
        }
        
        head.order = order;
        traverseJump(head.jump, order + 1);
    }

	/**
	c    o    list
	-    -    ---
	          h=>(a,-1,c)=>(b,-1,d)=>(c,-1,b)=>(d,-1,d)=>
	a    0    h=>(a, 0,c)=>(b,-1,d)=>(c,-1,b)=>(d,-1,d)=>
	c    1    h=>(a, 0,c)=>(b,-1,d)=>(c, 1,b)=>(d,-1,d)=>
	b    2    h=>(a, 0,c)=>(b, 2,d)=>(c, 1,b)=>(d,-1,d)=>
	d    3    h=>(a, 0,c)=>(b, 2,d)=>(c, 1,b)=>(d, 3,d)=>
	*/    
    public void traverseJump2() {
        Node cur = head;
        int order = 0;
        while (cur != null && cur.order < 0) {
            cur.order = order++;
            cur = cur.jump;
        }
    }

}
