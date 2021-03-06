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

import com.amazon.Utils;

public class BST<E extends Comparable<E>> {
	
	private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left, right;    
        
        private Node() {}
        
        private Node(E item) { this.item = item; }
        
        private Node<E> addChild(E val) {
        		Node<E> n = null;
            if (val.compareTo(item) < 0 && left == null) {
                n = left = new Node<E>(val);
            } else if (val.compareTo(item) < 0) {
                n = left.addChild(val);
            }
            else if (right == null) {
                n = right = new Node<E>(val);
            } 
            else {
                n = right.addChild(val);
            }
            
            return n;
        }
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
    
    private BST(Node<E> root) {
    		this.root = root;
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
            root.left = balanced(arr, startIndx, mid-1);
            root.item = arr[mid];
            root.right = balanced(arr, mid+1, lastIndx);
        }
        
        return root;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void insert(E item) {
    	root = insert(root, item);
    	size++;
    }
    
    private Node<E> insert(Node<E> root, E item) {
        if (root == null)
            root = new Node<E>(item);
        else if (item.compareTo(root.item) < 0)
            root.left = insert(root.left, item);
        else
            root.right = insert(root.right, item);
        return root;
    }
    
    public void delete(E item) throws NoSuchElementException {
        root = delete(root, item);
        size--;
    }
    
    private Node<E> delete(Node<E> root, E item) throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException();
        else if (item.equals(root.item))
            root = deleteNode(root);
        else if (item.compareTo(root.item) < 0)
            root.left = delete(root.left, item);
        else
            root.right = delete(root.right, item);
        return root;
    }
    
    private Node<E> deleteNode(Node<E> delNode) {
        // no children
        if (delNode.left == null && delNode.right == null)
            delNode = null;
        // two children
        else if (delNode.left != null && delNode.right != null) {
            // find inorder successor
            E inorderNext = first(delNode.right);
            delete(inorderNext);
            delNode.item = inorderNext;
        }
        // left child only
        else if (delNode.left != null)
            delNode = delNode.left;
        // right child only
        else if (delNode.right != null)
            delNode = delNode.right;
        
        return delNode;
    }
    
    public E first() {
        return first(root);
    }
    
    private E first(Node<E> root) {
        while (root.left != null)
            root = root.left;
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
            return contains(root.left, item);
        else 
            return contains(root.right, item);
    }

    public Iterable<E> inorder() {
    	List<E> result = new ArrayList<E>(); 
    	inorder(root, result);
    	return result;
    }
    
    private void inorder(Node<E> root, List<E> result) {
    	if(root != null) {
    		inorder(root.left, result);
    		result.add(root.item);
    		inorder(root.right, result);
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
    		preorder(root.left, result);
    		preorder(root.right, result);
    	}
    }
    
    public Iterable<E> postorder() {
    	List<E> result = new ArrayList<E>();
    	postorder(root, result);
    	return result;
    }
    
    private void postorder(Node<E> root, List<E> result) {
    	if (root != null) {
    		postorder(root.left, result);
    		postorder(root.right, result);
    		result.add(root.item);
    	}
    }
    
    public void preorderAdvanced() {
    	preorderAdvanced(root);
    }
    
    private void preorderAdvanced(Node<E> root) {
    	if(root != null) {
			System.out.println(
					root.item + ": <"
					+ (root.left != null ? root.left.item : "") + " " 
					+ (root.right != null ? root.right.item  : "" ) + ">");
			preorderAdvanced(root.left);
			preorderAdvanced(root.right);
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
            root.left = restoreBalanced3(root.left, n/2, reader);
            Integer value; 
            try {
            	value = Integer.valueOf(reader.readLine());
            } catch (Exception e) {
            	value = null;
            }
            root.item = value; 
            root.right = restoreBalanced3(root.right, n/2, reader);
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
            root.left = insert(root.left, items, first, n/2);
            root = insert(root, items, mid, 1);
            root.right = insert(root.right, items, mid+1, n/2);
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

        boolean isBalanced = Math.abs(height(root.left) - height(root.right)) <= 1
                                && isBalanced1(root.left)
                                && isBalanced1(root.right);
                                
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
        
    	int height = 1 + Math.max(height(root.left), height(root.right));
    	
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
            Node<E> leftChild = cur.left;
            Node<E> rightChild = cur.right;
            
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
 
    // ====== APPROACH 2: Iterative/DP =======
    public boolean isBalanced3() {
        return isBalanced3(root).isBalanced;
    }
    
    /**
    n: number of nodes
    Time: O(n)
    Mem: O(2n+1) = O(n) due to recursion
    */
    private Pair isBalanced3(Node<E> root) {
        if (root == null) {
            return new Pair(true, 0);
        }
        
        Pair left = isBalanced3(root.left);
        // optimization: stop calculation and fail earlier
        if (!left.isBalanced) {
            return new Pair(false, left.height + 1); // height value is inaccurate but it doesn't matter
        }
        Pair right = isBalanced3(root.right);
        
        int height = 1 + Math.max(left.height, right.height);
        boolean isBalanced = right.isBalanced && Math.abs(left.height - right.height) <= 1;
        Pair res = new Pair(isBalanced, height);
        return res;
    }
    
    private static class Pair {
        boolean isBalanced;
        int height;
        
        Pair(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    
    // ====== APPROACH 1: Using ArrayLists with duplicate node references
    /**
     * returns a list of linked lists, one linked list for each node at each depth with all the children nodes inside list
     * implementation uses ArrayList with duplicate references to nodes in each node's list
     */
    public ArrayList<LinkedList<E>> rootLevelLinkedLists1() {
    	ArrayList<LinkedList<Node<E>>> nodeLists = currentLevelLinkedLists1();
    	ArrayList<LinkedList<E>> result = new ArrayList<LinkedList<E>>();
    	for (LinkedList<Node<E>> nodeList : nodeLists) {
    		LinkedList<E> itemList = new LinkedList<E>();
    		for (Node<E> node : nodeList)
    			itemList.add(node.item);
    		result.add(itemList);
    	}
    	return result;
    }
    
    public ArrayList<LinkedList<Node<E>>> currentLevelLinkedLists1() {
        ArrayList<LinkedList<Node<E>>> result = new ArrayList<LinkedList<Node<E>>>(size);
        currentLevelLinkedLists1(root, result);
        Collections.reverse(result);
        return result;
    }
    
    private LinkedList<Node<E>> currentLevelLinkedLists1(Node<E> root, ArrayList<LinkedList<Node<E>>> result) {
        if (root == null)
            return null;
        
        LinkedList<Node<E>> cur = new LinkedList<Node<E>>();
        cur.add(root);
        LinkedList<Node<E>> left = currentLevelLinkedLists1(root.left, result);
        if (left != null)
        	cur.addAll(left);
        LinkedList<Node<E>> right = currentLevelLinkedLists1(root.right, result);
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
    
    /**
     * @Deprecated	there is bug with leaf nodes pointing to other nodes due to line <code>curList.back = right.back;</code>
     */
    public ArrayList<LinkedList<E>> rootLevelLinkedLists2() {
    	ArrayList<LinkedListInternal<Node<E>>> nodeLists = currentLevelLinkedLists2();
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
    
    private ArrayList<LinkedListInternal<Node<E>>> currentLevelLinkedLists2() {
        ArrayList<LinkedListInternal<Node<E>>> result = new ArrayList<LinkedListInternal<Node<E>>>(size);
        currentLevelLinkedLists2(root, result);
        return result;
    }
    
    private LinkedListInternal<Node<E>> currentLevelLinkedLists2(Node<E> root, ArrayList<LinkedListInternal<Node<E>>> result) {
        if (root == null)
            return null;
        
        // assuming we can access LinkedList internals, either customisable implementation or to be refactored to using APIs
        LinkedListInternal<Node<E>> curList = new LinkedListInternal<Node<E>>();
        curList.head = new LinkedListInternal.ListNode<Node<E>>(root);
        curList.back = curList.head;
        LinkedListInternal<Node<E>> left = currentLevelLinkedLists2(root.left, result);
        if (left != null) {
        	curList.head.next = left.head;
        	curList.back = left.back;
        }
        LinkedListInternal<Node<E>> right = currentLevelLinkedLists2(root.right, result);
        if (right != null) {
        	curList.back.next = right.head;
        	curList.back = right.back; // progress back to the end
        }
        
        result.add(curList);
//        
//        StringBuilder sb = new StringBuilder();
//        LinkedListInternal.ListNode<Node<E>> cur = curList.head;
//        while (cur != null) {
//        	sb.append(cur.item.item + " => ");
//        	cur = cur.next;
//        }
//        System.out.println(sb);
		
        return curList;
    }
    
    
    /**
     * number of paths from any node to any other node downwards that leads to the specified sum 
     */
    public int numPathSums(int x) {
        Counter counter = new Counter();
        try {
        	numPathSums((Node<Integer>) root, x, counter);
        } catch (ClassCastException e) {
        	System.err.println("This method only works for integer trees");
        	return 0;
        }
        // All combis => O(n^2) memory
        /** int count = 0;
        for (int sum : sums) {
            if (sum == x) {
                count++;
            }
        }**/
        return counter.count;
    }
    
    private static class Counter {
        int count = 0;
    }
    
    private List<Integer> numPathSums(Node<Integer> root, int x, Counter counter) {
    
        List<Integer> res = new ArrayList<Integer>();
    
        if (root == null)
            return res;
        
        List<Integer> left = numPathSums(root.left, x, counter);
        List<Integer>  right = numPathSums(root.right, x, counter);
        
        // path from the node to itself
        res.add(root.item);
        // All combis => O(n^2) memory
        // res.addAll(left);
        // res.addAll(right);
        
        // sum and add items from left subtee 
        for (int sum : left) {
            res.add(root.item + sum);
        }
        
        // sum and add items from the right subtree
        for (int sum : right) {
            res.add(root.item + sum);
        }
        
        // count matches
        for (int sum : res) {
            if (sum == x) {
                counter.count++;
            }    
        }
        
        return res;
    }
 
    public boolean isBST() {
        MinMax minMax = isBalanced(root);
        return minMax.min < Integer.MAX_VALUE && minMax.max > Integer.MIN_VALUE;
    }
    
    // Time: O(n) Mem: O(1), n: number of nodes
    // Time: 2 * n * (4 compares) = 8n operations => O(n)
    private MinMax isBalanced(Node<E> root) { // may be rename method to minMax
        if (root == null) {
            // values that will always validate
            return new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE); // newSoftMinMax()
        }
        
        MinMax res;
        MinMax left = isBalanced(root.left);
        // fail early optimization: O(n) with best case O(1), avg case O(n/2)
        if ((int) root.item < left.max) {
            // bubble error up the tree with values that will always fail
            res = new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE); // newHardMinMax()
            return res;
        }
        
        MinMax right = isBalanced(root.right);
        
        if ((int) root.item < right.min) {
            // reset min/max when set to infinity
            int min = left.min < Integer.MAX_VALUE ? left.min : (int) root.item;
            int max = right.max > Integer.MIN_VALUE ? right.max : (int) root.item;
            res = new MinMax(min, max);
        } 
        else {
            // bubble error up the tree with values that will always fail
            res = new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE); // newHardMinMax()
        }
        
        return res;

        /**        
        return root.item > root.leftChild 
               && root.item < root.rightChild
               && isBalanced(root.leftChild)
               && isBalanced(root.rightChild);
       */
    }
    
    public void replaceValue(E oldValue, E newValue) {
    	replaceValue(root, oldValue, newValue);
    }
    
    private void replaceValue(Node<E> root, E oldValue, E newValue) {
    	if (root == null)
    		return;
    	
    	if (root.item == oldValue) {
    		root.item = newValue;
    		return;
    	}
    	
    	replaceValue(root.left, oldValue, newValue);
    	replaceValue(root.right, oldValue, newValue);
    }
    
    private static class MinMax {
        int min, max;
        
        MinMax() {}
        
        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        // optional: convenient methods
        static MinMax newSoftMinMax() {
            return new MinMax(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        
        static MinMax newHardMinMax() {
            return new MinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
    
    public ArrayList<int[]> combi() {
        ArrayList<Integer> parents = new ArrayList<Integer>(size);
        ArrayList<Integer> leaves = new ArrayList<Integer>(size);
        // find parents and leaves nodes using preorder traversal
        preorderFindParentsAndLeaves((Node<Integer>) root, parents, leaves);
        // find all possible ordering of leaves values
        Object[] leavesArrObj = leaves.toArray();
        Integer[] leavesArr = new Integer[leavesArrObj.length];
        int indx = 0;
        for (Object o : leavesArrObj) {
        	leavesArr[indx++] = (Integer) o;
        }
        ArrayList<int[]> combi = combi(leavesArr) ;
        ArrayList<int[]> res = new ArrayList<int[]>();
        // combine parents fixed arrangement with every possible leaves arrangement
        for (int[] arr : combi) {
            int[] comb = new int[size];
            // copy fixed part
            for (int i = 0; i < parents.size(); i++) {
                comb[i] = parents.get(i);
            }
            // copy dynamic part
            for (int i = parents.size(), src = 0; i < size && src < arr.length; i++, src++) {
                comb[i] = arr[src];
            }
            res.add(comb);
        }
        return res;
    }
    
    private void preorderFindParentsAndLeaves(Node<Integer> root, ArrayList<Integer> parents, ArrayList<Integer> leaves) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            leaves.add(root.item);
        }
        else {
            parents.add(root.item);
        }
        
        preorderFindParentsAndLeaves(root.left, parents, leaves);
        preorderFindParentsAndLeaves(root.right, parents, leaves);
    }
    
    /**
          0  1  2
    in    10 30 50
    
    s    first    last    cur    loc    arr            n    res
    -    -----    ----    ---    ---    ---            -    ---
    1    0        2       10     0..2   10 30 50       3    [30,50] => [10,30,50] [30,10,50] [50,30,10]     
                                                            [50,30] => [10,50,30] [50,10,30] [30,50,10]
    2    1        2       30     0..1   30 50          2    [30,50] [50,30]
    3    2        2       50     2      50             1    [50]
    
    
    Final result: [60 20 40] (as prefix) + { [10,30,50] [30,10,50] [50,30,10], [10,50,30] [50,10,30] [30,50,10] } (as postfix)
    
    */
    private ArrayList<int[]> combi(Integer[] arr) {
        return combi(arr, 0, arr.length - 1);
    }
    
    private ArrayList<int[]> combi(Integer[] arr, int first, int last) {
    
        ArrayList<int[]> res = new ArrayList<int[]>();
        int n = last - first + 1;
        // base case with one element array
        if (first == last) {
            int[] a = new int[n];
            a[0] = arr[first];
            res.add(a);
            return res;
        }
        
        // find all possible combinations of subarray n-1
        ArrayList<int[]> combi = combi(arr, first + 1, last);
        int cur = arr[first];
        for (int[] subarray : combi) {
            int[] a = new int[n];
            // copy cur to first location
            a[0] = cur;
            // copy all subarray elements into rest of the array
            System.arraycopy(subarray, 0, a, 1, n - 1);
            // place cur element into all possible locations within the array
            for (int loc = 0; loc < n; loc++) {
                swap(a, 0, loc);
                res.add(Arrays.copyOf(a, n));
                swap(a, 0, loc); // backtrack
            }
        }
        
        return res;
        
    }
    
    /** Elements Of Programming Interviews - Problem 14.4: COMPUTE THE LCA IN A BST */
    public int lca(int first, int second) {
    		Node firstNode = findNode(first);
    		Node secondNode = findNode(second);
    		Node lca = lca(firstNode, secondNode);
    		if (lca != null) {
    			return (int) lca.item;
    		}
    		else {
    			return Integer.MIN_VALUE;
    		}
    }
    
    private Node findNode(int key) {
	    	if (root == null)
	    		return null;
	    	
    		Node cur = root;
    		
    		while (cur != null && (int) cur.item != key) {
			if (key < (int) cur.item) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
    		}
    		return cur;
    }
    
    /** Problem 14.4 LCA */
    private Node lca(Node first, Node second) {
    		if (first == null || second == null)
    			return null;
    		
        Node lca = root;
        
        Utils<E> utils = new Utils<E>();
        
        while (lca != null) {
            if (utils.forallGreaterThan((E) lca.item, (E) first.item, (E) second.item)) {
                lca = lca.right;
            }    
            else if (utils.forallSmallerThan((E) lca.item, (E) first.item, (E) second.item)) {
                lca = lca.left;
            }
            else {
                break;
            }
        }
        
        return lca;
    }
    
//    private Node lca2(Node first, Node second) {
//        if (first == null || second == null) {
//            return null;
//        }
//    
//        Node left = first.item < second.item ? first : second;
//        Node right = first.item < second.item ? second : first;
//        
//        Node cur = left;
//        Node parent = left.parent;
//        while (parent != null && parent.item < right.item) {
//            cur = parent;
//            parent = parent.parent;
//        }
//        
//        return cur;
//    }
    
    private static void swap(int[] arr, int x, int y) {
    	int tmp = arr[x];
    	arr[x] = arr[y];
    	arr[y] = tmp;
    }
    
    private static void printArray(int[] arr) {
    	System.out.print("[");
    	for (int x : arr) {
    		System.out.print(x + ",");
    	}
    	System.out.println("]");
    }
    
    public static BST<Integer> createFromPreorderSeq(int[] arr) {
        BST<Integer> bst = new BST<Integer>();
        for (int x : arr) {
            bst.insert(x);
        }
        return bst;
    }

    public static BST<Integer> createFromPostOrderSeq(int[] arr) {
        BST<Integer> bst = new BST<Integer>();
        for (int indx = arr.length - 1; indx >= 0; indx--) {
            bst.insert(arr[indx]);
        }
        return bst;
    }
    
    // 10, 20, 30, 40, 50, 60, 70
    public static BST<Integer> createFromInOrderSeq(int[] arr) {
        BST<Integer> bst = new BST<Integer>();
        insert(bst, arr, 0, arr.length - 1);
        return bst;
    }
    
/**

          40(1)
       /         \
      20(2)       60(5)
     /  \        /    \
  (3)10  30(4)  50(6) 70(7)
    
indx:   0  1  2  3  4  5  6
arr:    10 20 30 40 50 60 70

first    last    mid    arr[mid]
-----    ----    ---    --------
0        6       3      40
0        2       1      20
0        0       0      10
2        2       2      30
4        6       5      60
4        4       4      50
6        6       6      70
*/
    
    private static void insert(BST<Integer> bst, int[] arr, int first, int last) {
        if (last < first) {
            return;
        }
        
        int mid = (first + last) / 2;
        bst.insert(arr[mid]);
        insert(bst, arr, first, mid - 1);
        insert(bst, arr, mid + 1, last);
    }
    
    public String printInorderIterative() {
    		return printInorderIterative(root);
    }
    
    public String printInorderIterative(Node cur) {
        Stack<Node> s = new Stack<Node>();
        StringBuilder res = new StringBuilder();
        
        while (cur != null || !s.isEmpty()) {
            if (cur != null) {
                s.push(cur);        
                cur = cur.left;
            }
            else {
                cur = s.pop();
                visit(cur, res);
                cur = cur.right;
            }
        }
        
        return res.toString();
    }

    private void visit(Node node, StringBuilder sb) {
        sb.append(node.item).append(" ");
    }
    
    // 	Elements of Programming Interviews in Java - Problem 14.3
    public BST buildFromPreorder(E[] arr) {
        Stack<Node<E>> s = new Stack<Node<E>>();
        Node<E> root = new Node<E>(arr[0]);
        BST<E> bst = new BST<E>(root);
        s.push(root);
        
        for (int i = 1; i < arr.length; i++) {
        		E val = arr[i];
            root = s.peek();
            if (val.compareTo(root.item) < 0) {
                Node<E> left = root.addChild(val); // TODO method
                s.push(left);
            } else {
                do {
                    root = s.pop();
                } while(!s.isEmpty() && val.compareTo(s.peek().item) > 0);
                Node<E> right = root.addChild(val);
                s.push(right);
            }
        }
        
        return bst;
    }
    
}











































