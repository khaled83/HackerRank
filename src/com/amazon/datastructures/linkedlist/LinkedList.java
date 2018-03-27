package com.amazon.datastructures.linkedlist;

import java.util.HashSet;

public class LinkedList {

	private Node head;
	private int size;
	
	public LinkedList() {}
	
	public LinkedList(int[] arr) {
		for (int x : arr)
			this.addLast(x);
	}

	public LinkedList(Node head) {
		this.head = head;
        Node cur = this.head;
        int count = 0;
        while (cur != null) {
        		count++;
	        	System.out.print(cur.item);
	        	cur = cur.next;
        }
		this.size = count;
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
	
    /**
    Optional improvements:
    (1) return boolean wheather delete is successful or not
    (2) return the value of the node deleted
    
    Time: O(n)
    Space: O(1) + rec stack
    */
    public void removeKthFromLast(int k) {
    	removeKthFromLast(head, k);
    }
    
    private int removeKthFromLast(Node head, int target) {
        if (head == null) {
            return 0;
        }
        
        // k value for the next node
        int k = removeKthFromLast(head.next, target);
        
        // delete node
        if (k == target) {
            head.next = head.next.next;
            // optional: return special value to trigger method to terminate earlier in previous nodes
        }
        
        return 1 + k;
    }
    
    public void removeKthFromLast2(int k) {
    	// 3 5 8 10 1
        Node cur = head;
        Node runner = head.next;
        // move runner k steps forward
        int step = 1;
        for (step = 1; step <= k && runner != null; step++) {
            runner = runner.next;
        }
        
        // special case: we are removing head element
        if (runner == null && step == k) {
            head = head.next;
            return;
        }
        // if step < (k - 1), that means we don't have kth to last elements due to short linked list
        else if (runner == null) {
        	return;
        }
        
        while (runner != null) {
            cur = cur.next;
            runner = runner.next;
        }
        
        // when runner is null, cur points at k+1 from last
        // remove kth element
        cur.next = cur.next.next;
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
	
	// alternative: set next pointer to the next distinct node
	public void removeDuplicatesFromSortedList() {
        Node dst = head;
        Node src = head.next;
        
        while (src != null) {
            if (src.item != dst.item) {
                dst.next.item = src.item;
                dst = dst.next;
            }
            src = src.next;
        }
        
        // detach the rest of the list to delete repeated nodes
        dst.next = null;
    }
	
	public void removeDuplicatesFromSortedList2() {
        Node cur = head;
        Node runner = head.next;
        
        while (runner != null) {
            if (runner.item != cur.item) {
                cur.next = runner;
                cur = cur.next;
            }
            runner = runner.next;
        }
        cur.next = null;
    }
	
	public void shiftRightCyclic(int k) {
		PairNodeWithInteger resPrev = kthNode(head, k + 1);
		Node prev = resPrev.node;
	    if (resPrev.k < k) {
	        k = k % resPrev.k;
	        prev = kthNode(k + 1);
	    }
	    Node first = prev.next;
	    Node last = kthNode(1);
	    last.next = head;
	    head = first;
	    prev.next = null;    
	}

	public Node kthNode(int k) {
		PairNodeWithInteger res = kthNode(head, k);
	    if (res.k == k) {
	        return res.node;
	    }
	    else {
	        return null;
	    }
	}

	private PairNodeWithInteger kthNode(Node cur, int k) {
	    if (cur == null) {
	        return new PairNodeWithInteger(null, 0);
	    }
	    
	    PairNodeWithInteger next = kthNode(cur.next, k);
	    if (next.k == k) {
	        return next;
	    }
	    else {
	        return new PairNodeWithInteger(cur, next.k + 1);
	    }
	}

	private static class PairNodeWithInteger {
	    public PairNodeWithInteger(Node node, int k) {
	        this.node = node;
	        this.k = k;
	    }

	    private Node node;
	    private int k;
	}
	
	public void shiftRightBy(int k) {
        Node cur = head;
        if (cur == null) {
            return;
        }
        
        Node oldLast = null;
        
        int n = 1;
        for (int i = 0; i < k - 1; i++) {
            if (cur.next == null) {
                oldLast = cur;
                cur = head;
                n = i + 1;
                break;
            } 
            else {
                cur = cur.next;
            }
        }
        

        k = k % n;
        
        for (int i = 0; i < k - 1; i++) {
            cur = cur.next;
        }
        
        if (cur.next == null) {
            return;
        }
        
        if (oldLast == null) {
            oldLast = cur;    
        }
        while (oldLast.next != null) {
            oldLast = oldLast.next;
        }
        
        oldLast.next = head;
        head = cur.next;
        cur.next = null;
    }

	public void evenOddMege() {
        if (head == null) {
            return;
        }
    
        Node even = head;
        Node odd = head.next;
        Node firstOdd = head.next;
        
        while (even != null && odd != null && odd.next != null) {
            // next even
            Node next = even.next != null ? even.next.next : null;
            even.next = next;
            even = next;
            // next odd
            next = odd.next != null ? odd.next.next : null;
            odd.next = next;
            odd = next;
        }
        
        even.next = firstOdd;
    }
	
	public boolean isPalyndrome() {
        // find mid point
        // find size
        int size = 0;
        Node head = this.head;
        while (head != null) {
            size++;
            head = head.next;
        }
        int indx = 0, midIndx = size / 2;
        Node mid = this.head;
        while (indx < (midIndx - 1)) {
            mid = mid.next;
            indx++;
        }
        System.out.println("mid="+mid.item);
        boolean isOdd = size % 2 == 1;
        Node left = mid;
        mid = isOdd ? mid.next : mid;
        Node right = mid.next;
        System.out.println("left=" + left.item + " mid="+mid.item + " right=" + right.item);
        return isPalyndrome(this.head, left, right);
    }
    
	// {1, 2, 3, 2, 1}
    private boolean isPalyndrome(Node head, Node left, Node right) {
        if (head == left) {
            boolean res = (head.item == right.item);
            right = right.next;
            return res;
        }
        boolean res = isPalyndrome (head.next, left, right);
        right = right.next;
        res = res && head.item == right.item;
        return res;
    }
	
    public void pivot(int k) {
        // last node in smaller, defaults to dummy
    		Node firstSmaller = null;
        Node lastSmaller = new Node();
        // last node in equal,, defaults to dummy
        Node firstEqual = null;
        Node lastEqual = new Node();
        lastSmaller.next = firstEqual;
        // last node in larger
        Node firstLarger = null;
        Node lastLarger = new Node();
        lastEqual.next = firstLarger;
        
        Node cur = head;
        while (cur != null) {
            if (cur.item < k) {
	            	if (firstSmaller == null) {
	        			firstSmaller = lastSmaller;
	        		}
                lastSmaller.next = cur;
                lastSmaller = cur;
            }
            else if (cur.item == k) {
	            	if (firstEqual == null) {
	        			firstEqual = lastEqual;
	        		}
                lastEqual.next = cur;
                lastEqual = cur;
            }
            else {
	            	if (firstLarger == null) {
	        			firstLarger = lastLarger;
	        		}
                lastLarger.next = cur;
                lastLarger = cur;
            }
            cur = cur.next;
        }
        
        // HEAD
        Node dummy = firstSmaller;
        if (firstSmaller != null) {
        		head = firstSmaller.next;
        		while (dummy != null) {
        			dummy = dummy.next;
        		}
        }
        // SMALLER <> EQUAL
        dummy = firstEqual;
        if (firstEqual != null) {
        		lastSmaller.next  = firstEqual.next;
        		if (head == null) {
        			head = firstEqual.next;
        		}
        } else {
        		lastSmaller.next = firstLarger != null ? firstLarger.next : null; 
        }
        
        // EQUAL <> LARGER
        dummy = firstLarger;
        if (firstLarger != null) {
        		lastEqual.next = firstLarger.next;
        		if (head == null) {
        			head = firstLarger.next;
        		}
        }
        else {
        		lastEqual.next = null;
        }
        lastLarger.next = null;
    }
 
 // Elements of Programming Interviews 7.13: ADD LIST-BASED INTEGERS
    public static LinkedList sum(LinkedList l1, LinkedList l2) {
        Node h1 = l1.head, h2 = l2.head;
        Node dummy = new Node();
        Node head = dummy;
        int sum = 0, carry = 0;
        while (h1 != null && h2 != null) {
            sum = h1.item + h2.item + carry;
            carry = sum / 10;
            head.next = new Node(sum % 10);
            h1 = h1.next;
            h2 = h2.next;
            head = head.next;
        }
        
        while (h1 != null) {
        		sum = h1.item + carry;
        		carry = sum / 10;
            head.next = new Node(sum % 10);
            head = head.next;
            h1 = h1.next;
        }
        
        while (h2 != null) {
	    		sum = h2.item + carry;
	    		carry = sum / 10;
	        head.next = new Node(sum % 10);
	        head = head.next;
	        h2 = h2.next;
	    }
        
        if (carry > 0) {
        		head.next = new Node(carry);
        		head = head.next;
        }
        
        return new LinkedList(dummy.next);
    }
    
    // 	Elements of Programming Interviews 7.13: ADD LIST-BASED INTEGERS
    // just shorter code
    public static LinkedList sum2(LinkedList l1, LinkedList l2) {
        Node h1 = l1.head, h2 = l2.head;
        Node dummy = new Node();
        Node head = dummy;
        int sum = 0, carry = 0;
        while (h1 != null || h2 != null || carry > 0) {
        		int item1 = h1 != null ? h1.item : 0;
        		int item2 = h2 != null ? h2.item : 0;
            sum = item1 + item2 + carry;
            carry = sum / 10;
            head.next = new Node(sum % 10);
            h1 = h1 != null ? h1.next : h1;
            h2 = h2 != null ? h2.next : h2;
            head = head.next;
        }
        
        return new LinkedList(dummy.next);
    }
    
}



























