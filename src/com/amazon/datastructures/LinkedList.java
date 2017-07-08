package com.amazon.datastructures;

import java.util.HashSet;

public class LinkedList {

	private Node head;
	private int size;
	
	public LinkedList() {}
	
	public LinkedList(int[] arr) {
		for (int x : arr)
			this.addLast(x);
	}

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
	
	public void addLastWithCycle(int item, int cycleTo) {
		addLast(item);
		Node prev = head;
		while (prev != null && prev.item != item) {
			prev = prev.next;
		}
		
		Node next = head;
		while (next != null && next.item != cycleTo) {
			next = next.next;
		}
		
		if (next == null) {
			throw new IllegalArgumentException("Item " + cycleTo + " does't exist in list");
		}
		
		prev.next = next;
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

	/**
	 * removes duplicates from linked list in O(n) time using external hash table
	 */
	public void removeDuplicates1() {

	    HashSet<Integer> visited = new HashSet<Integer>();
	    
	    Node prev = null;
	    Node cur = head;
	    while (cur != null) {
	        if (visited.contains(cur.item)) {
	            prev.next = cur.next;
	            cur.next = null;
	            cur = prev;
	            size--;
	        }
	        else
	            visited.add(cur.item);
	        prev = cur;
	        cur = cur.next;
	    }
	   
	}

	/**        =======>
	    a => m => a => z => o => n
	         c
	         p                    
	                   r                      
	         
	Hash    a m
	*/
	// O(n^2)
	/**
	 * removes duplicates from linked list in O(n^2) with any external data structure
	 */
	public void removeDuplicates2() {
	    
	    Node cur = head;
	    Node prev = null, runner = null;
	    while (cur != null) {
	        prev = cur;
	        runner = cur.next;
	        while (runner != null) {
	            if (runner.item == cur.item) {
	                prev.next = runner.next;
	                runner.next = null;
	                runner = prev;
	                size--;
	            }
	            prev = runner;
	            runner = runner.next;
	        }
	        cur = cur.next;
	    }
	    
	}
	
	// @TODO works for add, not for get and remove
	private void checkIndex(int index) {
		boolean valid = index >= 0 && index < size;
		if (!valid)
			throw new IndexOutOfBoundsException();
	}

	private static class Pair {
        int first;
        int second;
        
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    public int KthToLast(int k) {
        return KthToLast(head, k).second;
    }
    
    private Pair KthToLast(Node head, int k) {
        if (head == null)
            return new Pair(0, 0);
        else {
        	Pair p = KthToLast(head.next, k);
            int K = 1 + p.first;
            p.first = K;
            if (K == k)
                p.second = head.item;
            
            return p;
        }
    }
	
	private static class Node {
		int item;
		Node next;

		Node() {}
		
		Node(int item) {
			this.item = item;
		}
	}
	
	@Override
	public String toString() {
		Node cur = head;
		StringBuilder sb = new StringBuilder();
		// guard for cycle
		int countSoFar = 0;
		while(cur != null && countSoFar <= size) {
			sb.append(cur.item).append(" ");
			cur = cur.next;
			countSoFar++;
		}
		return sb.toString();
	}

	/**
	 			 1	   2    3    4    5
	 			 	   f	 	 
	 			 	   			 l
	 in 	h => 11 => 3 => 5 => 7 => 2 => x
	 				   [ 		 ]
	 				=> 7 => 5 => 3 => 2 
	 				
		  
	 first=2	last=4
	 
	 s		prev	head	indx	firstN	cur		target		res		ops
	 -		----	----	----	----- 	---		------		---		---
	 1		h		11		1    
	 2		11	 	3		2		3							(7,2)	11.next = 7
	 																	3.next  = 2
	 	
	 3		 		3		 				2		4					3.next.next => 5.next = 3				
	 4				5						3		4			(7,2) 	5.next.next => 7.next = 5 
	 5				7						4					(7,2)	
	 
	 * */
	private void reverseSubList(Node head, int first, int last) {
		int indx = 1;
		Node prev = null;
		while (indx < first) {
			indx++;
			prev = head;
			head = head.next;
		}
		Node firstNode = head;
		ResultSet res = reverse(head, first, last); 
		prev.next = res.last;
		firstNode.next = res.afterLast;
	}
	
	private ResultSet reverse(Node head, int cur, int target) {
		if (head == null) {
			return null;
		}
		
		ResultSet res = new ResultSet();
		if (cur != target) {
			res = reverse(head.next, cur + 1, target);
			head.next.next = head; // reverse pointer
		} else {
			res.last = head;
			res.afterLast = head.next;
		}
		
		return res;
	}
	
	public int hasCycle1() {
        return hasCycle1(head).item;
    }
	
	public int hasCycle2() {
		return hasCycle2(head).item;
	}
	
	/** Time: O(n), Space: O(1), n: number of nodes */
    private Node hasCycle2(Node head) {
    	Node slow = head;
        Node runner = head.next;
        
        while (runner.next != null && slow != runner) {
            slow = slow.next;
            runner = runner.next.next;
            System.out.println(slow.item + ":" + runner.item);
        }
        
        if (runner.next == null) {
        	return null;
        } else {
        	head = runner.next;
        	while (head.next != runner) {
        		head = head.next;
        	}
        	return head;
        }
    }
    
    /** Time: O(n), Space: O(n), n: number of nodes in list */
    private Node hasCycle1(Node head) {
        HashSet<Integer> visited = new HashSet<Integer>();
        Node prev = null;
        while (head != null) {
            if (visited.contains(head.item)) {
                return prev;
            } else {
                visited.add(head.item);
            }
            prev = head;
            head = head.next;
        }        
        return null;
    }   
	
	private static class ResultSet {
		Node last;
		Node afterLast;
	}
		
	public static int[] mergeSortedLists1(int[] sorted1, int[] sorted2) {
		LinkedList L1 = arrayToLinkedList(sorted1);
		LinkedList L2 = arrayToLinkedList(sorted2);
		Node mergedHead = sort1(L1.head, L2.head);
		int[] res = linkedListToArray(mergedHead, sorted1.length + sorted2.length);
		return res;
	}
	
	public static int[] mergeSortedLists2(int[] sorted1, int[] sorted2) {
		LinkedList L1 = arrayToLinkedList(sorted1);
		LinkedList L2 = arrayToLinkedList(sorted2);
		Node mergedHead = sort2(L1.head, L2.head);
		int[] res = linkedListToArray(mergedHead, sorted1.length + sorted2.length);
		return res;
	}
	
	public static LinkedList arrayToLinkedList(int[] arr) {
		LinkedList list = new LinkedList();
		for (int i = arr.length - 1; i >= 0; i--)
			list.addFirst(arr[i]);
		return list;
	}
	
	public static int[] linkedListToArray(LinkedList list, int n) {
		return linkedListToArray(list.head, n);
	}
	
	public static int[] linkedListToArray(Node head, int n) {
		int[] arr = new int[n];
		int indx = 0;
		while (head != null) {
			arr[indx++] = head.item;
			head = head.next;
		}
		return arr;
	}
	
	private static Node sort1(Node h1, Node h2) {
	    LinkedList list = new LinkedList();
	    Node cur = null;

	    while (h1 != null && h2 != null) {
	        // subsequent elements
	        if (cur != null) {
	            cur.next = new Node();
	            cur = cur.next;
	        }
	        // first element
	        else {
	            list.head = new Node();
	            cur = list.head;
	        }
	        
	        if (h1.item < h2.item) {
	            cur.item = h1.item;
	            h1 = h1.next;
	        }
	        else {
	            cur.item = h2.item;
	            h2 = h2.next;
	        }      
	    }
	    
	    // finish off L1
	    while (h1 != null) {
	        if (cur != null) {
	            cur.next = new Node();
	            cur = cur.next;
	        }
	        // first element
	        else {
	            list.head = new Node();
	            cur = list.head;
	        }
	        
	        cur.item = h1.item;
	        h1 = h1.next;
	    }
	    
	    // finish off L2
	    while (h2 != null) {
	        if (cur != null) {
	            cur.next = new Node();
	            cur = cur.next;
	        }
	        // first element
	        else {
	            list.head = new Node();
	            cur = list.head;
	        }
	        
	        cur.item = h2.item;
	        h2 = h2.next;
	    }
	    
	    return list.head;
	}

	public static Node sort2(Node h1, Node h2) {
	    Node head = null, prev = null, cur = null;
	    
	    while(h1 != null && h2 != null) {
	        if (h1.item < h2.item) {
	            cur = h1;
	            h1 = h1.next;
	        } else {
	            cur = h2;
	            h2 = h2.next;
	        }
	        
	        if (prev != null) {
	            prev.next = cur;
	        } else {
	            head = cur;
	        }
	        
	        prev = cur;
	    }
	    
	    // finish off the remaining array
	    cur.next = h1 != null ? h1 : h2;
	    
	    return head;
	}
	
	public void partition1(int x) {
		partition1(head, x);
	}
	
	public void partition2(int x) {
		partition2(head, x);
	}
	
	public static void partition2(Node head, int x) {
	    if (head == null)
	        return;
	    
	    Node left = head;
	    Node right = head;
	    
	    while (right != null) {
	        if (right.item < x) {
	            // do swapping
	            int tmp = left.item;
	            left.item = right.item;
	            right.item = tmp;
	            left = left.next;
	        }
	        right = right.next;
	    }
	}
	
	private static void partition1(Node head, int x) {
	    if (head == null)
	        return;
	        
	    // count elements 
	    int n = 0;
	    Node cur = head;
	    while (cur != null) {
	        n++;
	        cur = cur.next;
	    }
	    
	    for (int pass = 1; pass <= n - 1; pass++) {
	        Node prev = head;
	        cur = head.next;
	        int loc = 1;
	        while(cur != null && loc < (n - pass + 1)) {
	            if (cur.item  < x && prev.item >= x) {
	                int tmp = prev.item;
	                prev.item = cur.item;
	                cur.item = tmp;
	            }
	            prev = cur;
	            cur = cur.next;
	            loc++;
	        }
	    }
	    
	}
	
}



























