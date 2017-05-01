package com.amazon.datastructures.trees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
	
	private static class Node<E> {
        E item;
        Node<E> leftChild, rightChild;    
        
        private Node() {}
        
        private Node(E item) { this.item = item; }
    }
    
    private Node<E> root;
    private int size;
    
    public BST() {}
    
    /**
     * creates balanced binary search tree from the given array
     */
    public BST(int[] arr) {
        // check sorted then sort
    	if (!isSorted(arr))
    		Arrays.sort(arr);
        root = balanced(arr, 0, arr.length-1);
    }
    
    private boolean isSorted(int[] arr) {
    	boolean sorted = true;
    	for (int i = 1; i < arr.length; i++)
    		if (arr[i] < arr[i-1]) {
    			sorted = false;
    			break;
    		}
    	return sorted;
    }
    
    private Node balanced(int[] arr, int startIndx, int lastIndx) {
        Node root = null;
        if (lastIndx >= startIndx) {
            root = new Node();
            int mid = (startIndx + lastIndx)/2;
            root.leftChild = balanced(arr, startIndx, mid-1);
            root.item = arr[mid];
            root.rightChild = balanced(arr, mid+1, lastIndx);
        }
        
        return root;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void insert(E item) {
    	root = insert(root, item);
    }
    
    private Node<E> insert(Node<E> root, E item) {
        if (root == null)
            root = new Node<E>(item);
        else if (item.compareTo(root.item) < 0)
            root.leftChild = insert(root.leftChild, item);
        else
            root.rightChild = insert(root.rightChild, item);
        return root;
    }
    
    public void delete(E item) throws NoSuchElementException {
        root = delete(root, item);
    }
    
    private Node<E> delete(Node<E> root, E item) throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException();
        else if (item.equals(root.item))
            root = deleteNode(root);
        else if (item.compareTo(root.item) < 0)
            root.leftChild = delete(root.leftChild, item);
        else
            root.rightChild = delete(root.rightChild, item);
        return root;
    }
    
    private Node<E> deleteNode(Node<E> delNode) {
        // no children
        if (delNode.leftChild == null && delNode.rightChild == null)
            delNode = null;
        // two children
        else if (delNode.leftChild != null && delNode.rightChild != null) {
            // find inorder successor
            E inorderNext = first(delNode.rightChild);
            delete(inorderNext);
            delNode.item = inorderNext;
        }
        // left child only
        else if (delNode.leftChild != null)
            delNode = delNode.leftChild;
        // right child only
        else if (delNode.rightChild != null)
            delNode = delNode.rightChild;
        
        return delNode;
    }
    
    public E first() {
        return first(root);
    }
    
    private E first(Node<E> root) {
        while (root.leftChild != null)
            root = root.leftChild;
        return root.item;
    }
    
    public boolean contains(E item) {
        return contains(root, item);
    }
    
    private boolean contains(Node<E> root, E item) {
        if (root == null)
            return false;
        else if (item.equals(root.item))
            return true;
        else if (item.compareTo(root.item) < 0)
            return contains(root.leftChild, item);
        else 
            return contains(root.rightChild, item);
    }

    public Iterable<E> inorder() {
    	List<E> result = new ArrayList<E>(); 
    	inorder(root, result);
    	return result;
    }
    
    private void inorder(Node<E> root, List<E> result) {
    	if(root != null) {
    		inorder(root.leftChild, result);
    		result.add(root.item);
    		inorder(root.rightChild, result);
    	}
    }
    
    public Iterable<E> preorder() {
    	List<E> result = new ArrayList<E>();
    	preorder(root, result);
    	return result;
    }
    
    private void preorder(Node<E> root, List<E> result) {
    	if (root != null) {
    		result.add(root.item);
    		preorder(root.leftChild, result);
    		preorder(root.rightChild, result);
    	}
    }
    
    public void preorderAdvanced() {
    	preorderAdvanced(root);
    }
    
    private void preorderAdvanced(Node<E> root) {
    	if(root != null) {
			System.out.println(
					root.item + ": <"
					+ (root.leftChild != null ? root.leftChild.item : "") + " " 
					+ (root.rightChild != null ? root.rightChild.item  : "" ) + ">");
			preorderAdvanced(root.leftChild);
			preorderAdvanced(root.rightChild);
    	}
    }

    public void storeTree(String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.forName("US-ASCII"))) {
            for (E e : this.preorder())
                writer.write(e.toString() + "\n", 0, e.toString().length()+1);
        }
        catch (IOException e) {}
    }
    
    public void storeTreeInorder(String path) {
    	try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), Charset.forName("US-ASCII"))) {
    		StringBuilder sb = new StringBuilder();
    		for (E e : this.inorder()) 
    			sb.append(e.toString()).append("\n");
    		sb.deleteCharAt(sb.length()-1);
			writer.write(sb.toString(), 0, sb.length());
    	} catch (IOException e) {e.printStackTrace(); }
    }
    
    public static BST<Integer> restoreTree(String path) {
        BST<Integer> bst = new BST<Integer>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("US-ASCII"))) {
        	String line = "";
            while ((line = reader.readLine()) != null) {
            	bst.insert(Integer.valueOf(line));
            }
        }
        catch (IOException e) {}
        return bst;
    }

    public static BST<Integer> restoreBalanced3(String path, int n) {
        BST<Integer> bst = new BST<Integer>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("US-ASCII"))) {
            bst.root = restoreBalanced3(bst.root, n, reader);
        } catch(IOException e) {}
        return bst;
    }
    
    private static Node<Integer> restoreBalanced3(Node<Integer> root, int n, BufferedReader reader) throws NumberFormatException, IOException {
        if (!reader.ready())
        	return null;
    	root = new Node<Integer>();
        if (n==1) {
        	String s = reader.readLine();
        	int x = Integer.valueOf(s);
        	root.item = x;
        }
        else {
            root.leftChild = restoreBalanced3(root.leftChild, n/2, reader);
            Integer value; 
            try {
            	value = Integer.valueOf(reader.readLine());
            } catch (Exception e) {
            	value = null;
            }
            root.item = value; 
            root.rightChild = restoreBalanced3(root.rightChild, n/2, reader);
        }
        
        return root;
    }
    
    public static BST<Integer> restoreBalanced2(String path, int n) {
        BST<Integer> bst = new BST<Integer>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("US-ASCII"))) {
        	List<Integer> items = new ArrayList<Integer>();
        	String s = "";
            while ((s = reader.readLine()) != null)
            	items.add(Integer.valueOf(s));
            balancedInsert(bst, items, 0, n);
        } catch(IOException e) {}
        return bst;
    }
    
    private static void balancedInsert(BST<Integer> bst, List<Integer> items, int first, int n) {
        if (n == 1)
            bst.insert(items.get(first));
        else {
            int mid = first + (n/2);
            balancedInsert(bst, items, mid, 1);
            balancedInsert(bst, items, first, n/2);
            balancedInsert(bst, items, mid+1, n/2);
        }
    }
    
    @SuppressWarnings("unchecked")
	public static BST<Integer> restoreBalanced(String path, int n) {
        List<Integer> items = new ArrayList<Integer>();
        BST<Integer> bst = new BST<Integer>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String s = "";
            while ((s = reader.readLine()) != null)
                items.add(Integer.valueOf(s));
            bst.root = insert(bst.root, items, 0, n);
        } 
        catch (IOException e) {}
        return bst;
    }
    
    private static Node<Integer> insert(Node<Integer> root, List<Integer> items, int first, int n) {
    
        if(root == null)
            root = new Node<Integer>();
            
        if (n==1)
            root.item = items.get(first);
        else if (n > 1) {
            int mid = first + (n/2);
            /**
             n		mid		first		R1			R2
             7		3		0			0,1,2		4,5,6
             3		1		0			0			2
             3		1		4			4			
             * */
            root.leftChild = insert(root.leftChild, items, first, n/2);
            root = insert(root, items, mid, 1);
            root.rightChild = insert(root.rightChild, items, mid+1, n/2);
        }
        
        return root;
    }
 
    /** ====== APPROACH 1: Recursion =======
	    60
	 /      \
	20:3    70:1
	/  \      
	10  40      
	 /  \
	30  50
	
	60 =>F ; (3-1) > 1
     *h(20)=3
     *h(10)=1
     *h(40)=2  
     *h(30)=1
     *h(50)=1        
     *h(70)=1
     *b(20)=T
	     h(10)=1
	     h(40)=2
	         h(30)=1
	         h(50)=1
     *b(10)=T
     *b(40)=T
	         h(30)=1
	         h(50)=1
     *b(30)=T
     *b(50)=T
     *B(70)=T
     */
    // reinitialize or update hash when new elements are added to BST
    private HashMap<E, Boolean> balanceMemo = new HashMap<E, Boolean>();
    
    public boolean isBalanced1() {
        return isBalanced1(root);
    }    
    
    private boolean isBalanced1(Node<E> root) {
    	if (root == null)
    		return true;

    	if (balanceMemo.containsKey(root.item))
    		return balanceMemo.get(root.item);

        boolean isBalanced = Math.abs(height(root.leftChild) - height(root.rightChild)) <= 1
                                && isBalanced1(root.leftChild)
                                && isBalanced1(root.rightChild);
                                
        balanceMemo.put(root.item, isBalanced);
        return balanceMemo.get(root.item);
    }
    
    private HashMap<E, Integer> heightMemo = new HashMap<E, Integer>();
    public int height() {
        return height(root);
    }
    
    private int height(Node<E> root) {
        if (root == null)
            return 0;
            
        if (heightMemo.containsKey(root.item))
        	return heightMemo.get(root.item);
        
    	int height = 1 + Math.max(height(root.leftChild), height(root.rightChild));
    	
    	heightMemo.put(root.item, height);
    	return heightMemo.get(root.item);
    }
 
    /** ====== APPROACH 2: Iterative/DP =======
            60
         /      \
        20:3    70:1
       /  \      
      10  40      
         /  \
        30  50
        
        cur  visiting           visited
        ---  --------           -------
        60   60                 x     
        60   60 20              x 
        60   60 20 10           x  
        60   60 20 40           x 10  
        60   60 20 40 30        x 10  
        60   60 20 40 30        x 10
        60   60 20 40 50        x 10 30
        60   60 20 40 50        x 10 30 50
        60   60 20 40           x 10 30 50
        60   60 20              x 10 30 50 40
        60   60                 x 10 30 50 40 20
        60   60 70              x 10 30 50 40 20
        60   60                 x 10 30 50 40 20 70   => 3 - 1 > 1 => return false
        
        heights
        -------
        10 => 1
        30 => 1
        50 => 1
        40 => 2
        20 => 3
        70 => 1
        
    */
    public boolean isBalanced2() {
        HashMap<Node<E>, Integer> heights = new HashMap<Node<E>, Integer>();
        heights.put(null, 0);
        
        // calculate heights
        Stack<Node<E>> visiting = new Stack<Node<E>>();
        visiting.push(root);
        HashSet<Node<E>> visited = new HashSet<Node<E>>();
        visited.add(null);
        
        // post order traversal prcesses all subtrees before processing root
        while (!visiting.isEmpty()) {
            Node<E> cur = visiting.peek();
            Node<E> leftChild = cur.leftChild;
            Node<E> rightChild = cur.rightChild;
            
            // 1. node is a leaf, both left and right are null
            // 2. both left and right are visisted
            if (visited.contains(leftChild) && visited.contains(rightChild)) {
                // processed subtrees underneath this node, check balance and terminate if not balanced
        		if (Math.abs((heights.get(leftChild) - heights.get(rightChild))) > 1)
        			return false;
        		int height = 1 + Math.max(heights.get(leftChild), heights.get(rightChild));
        		heights.put(cur, height);
        		visiting.pop();
        		visited.add(cur);
            		
            }
            else {
                if (leftChild != null && !visited.contains(leftChild)) {
                    visiting.push(leftChild);
                }
                else if (rightChild != null && !visited.contains(rightChild)) {
                    visiting.push(rightChild);
                }
            }
        }
        
        return true;
    }
 
    // ====== APPROACH 1: Using ArrayLists with duplicate node references
    /**
     * returns a list of linked lists, one linked list for each node at each depth with all the children nodes inside list
     * implementation uses ArrayList with duplicate references to nodes in each node's list
     */
    public ArrayList<LinkedList<E>> listsOfRootNodesItems1() {
    	ArrayList<LinkedList<Node<E>>> nodeLists = listsOfRootNodes1();
    	ArrayList<LinkedList<E>> result = new ArrayList<LinkedList<E>>();
    	for (LinkedList<Node<E>> nodeList : nodeLists) {
    		LinkedList<E> itemList = new LinkedList<E>();
    		for (Node<E> node : nodeList)
    			itemList.add(node.item);
    		result.add(itemList);
    	}
    	return result;
    }
    
    public ArrayList<LinkedList<Node<E>>> listsOfRootNodes1() {
        ArrayList<LinkedList<Node<E>>> result = new ArrayList<LinkedList<Node<E>>>(size);
        listsOfRootNodes1(root, result);
        Collections.reverse(result);
        return result;
    }
    
    private LinkedList<Node<E>> listsOfRootNodes1(Node<E> root, ArrayList<LinkedList<Node<E>>> result) {
        if (root == null)
            return null;
        
        LinkedList<Node<E>> cur = new LinkedList<Node<E>>();
        LinkedList<Node<E>> left = listsOfRootNodes1(root.leftChild, result);
        cur.add(root);
        if (left != null)
        	cur.addAll(left);
        LinkedList<Node<E>> right = listsOfRootNodes1(root.rightChild, result);
        if (right != null)
        	cur.addAll(right);
        result.add(cur);
        
        return cur;
    }
    
    // ====== APPROACH 2: Using LinkedLists without duplicate node references
    private static class LinkedListInternal<E> {
        
    	ListNode<E> head, back;
        
        private static class ListNode<E> {
        	ListNode<E> next;
            E item;
            
            ListNode(E item) { this.item = item; }
        }
        
    }
    
    public ArrayList<LinkedList<E>> listsOfRootNodesItems2() {
    	ArrayList<LinkedListInternal<Node<E>>> nodeLists = listsOfRootNodes2();
    	ArrayList<LinkedList<E>> result = new ArrayList<LinkedList<E>>();
    	for (LinkedListInternal<Node<E>> nodeList : nodeLists) {
    		LinkedList<E> itemList = new LinkedList<E>();
    		LinkedListInternal.ListNode<Node<E>> cur = nodeList.head;
    		while (cur != null) {
    			itemList.add(cur.item.item);
    			cur = cur.next;
    		}
//    		System.out.println(itemList);
    		result.add(itemList);
    	}
    	return result;
    }
    
    private ArrayList<LinkedListInternal<Node<E>>> listsOfRootNodes2() {
        ArrayList<LinkedListInternal<Node<E>>> result = new ArrayList<LinkedListInternal<Node<E>>>(size);
        listsOfRootNodes2(root, result);
        return result;
    }
    
    private LinkedListInternal<Node<E>> listsOfRootNodes2(Node<E> root, ArrayList<LinkedListInternal<Node<E>>> result) {
        if (root == null)
            return null;
        
        // assuming we can access LinkedList internals, either customisable implementation or to be refactored to using APIs
        LinkedListInternal<Node<E>> curList = new LinkedListInternal<Node<E>>();
        curList.head = new LinkedListInternal.ListNode<Node<E>>(root);
        curList.back = curList.head;
        LinkedListInternal<Node<E>> left = listsOfRootNodes2(root.leftChild, result);
        if (left != null) {
        	curList.head.next = left.head;
        	curList.back = left.back;
        }
        LinkedListInternal<Node<E>> right = listsOfRootNodes2(root.rightChild, result);
        if (right != null) {
        	curList.back = right.back; // progress back to the end
        }
        
        result.add(curList);
        
        StringBuilder sb = new StringBuilder();
        LinkedListInternal.ListNode<Node<E>> cur = curList.head;
        while (cur != null) {
        	sb.append(cur.item.item + " => ");
        	cur = cur.next;
        }
        System.out.println(sb);
		
        return curList;
    }
    
}











































