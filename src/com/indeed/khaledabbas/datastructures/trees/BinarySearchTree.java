package com.indeed.khaledabbas.datastructures.trees;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
	
	private static class Node<E>
	{
		private E element;
		
		private Node<E> leftChild;
		private Node<E> rightChild;
		
		public Node() {}
		
		public Node(E e) {
			this.element = e;
		}
		
		public Node(E e, Node<E> leftChild, Node<E> rightChild)
		{
			this(e);
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		@Override
		public String toString() {
			return element.toString() + " <" 
					+ ( leftChild != null ? leftChild.element : "" )
					+ "," + ( rightChild != null ? rightChild.element : "" )
							+ ">";
		}
	}
	
	private Node<E> root;
	
	public BinarySearchTree()
	{
		root = null;
	}
	
	public void insert(E e)
	{
		root = insert(root, e);
	}
	
	private Node<E> insert(Node<E> node, E e)
	{
		if(node == null)
			node = new Node<E>(e, null, null);
		else if(e.compareTo(node.element) < 0)
			node.leftChild = insert(node.leftChild, e);
		else
			node.rightChild = insert(node.rightChild, e);
		
		return node;
	}
	
	public void delete(E e)
	{
		root = delete(root, e);
	}
	
	private Node<E> delete(Node<E> node, E e)
	{
		if(node != null)
		{
			if(node.element == e)
				node = deleteNode(node);
			else if(e.compareTo(node.element) < 0)
				node.leftChild = delete(node.leftChild, e);
			else
				node.rightChild = delete(node.rightChild, e);
		}
		
		return node;
	}
	
	private Node<E> deleteNode(Node<E> node)
	{
		// no children
		if(node.leftChild == null && node.rightChild == null)
		{
			node = null;
		}
		// has two children
		else if(node.leftChild != null && node.rightChild != null)
		{
			node.element = getLeftMostElement(node.rightChild);
			node.rightChild = deleteLeftMost(node.rightChild);
		}
		// has one child only
		else
		{
			Node<E> child = node.leftChild != null
							? node.leftChild
							: node.rightChild;
			
			Node<E> delNode = node;
			node = child;
			delNode = null;
		}
		
		return node;
	}
	
	private Node<E> findNodeFirstInorder3(E key) {

		Node<E> cur = root;
		Node<E> found = null;

		while (cur != null) {
			if (cur.element.equals(key)) {
				found = cur;
				cur = cur.leftChild;
			} else if (key.compareTo(cur.element) < 0)
				cur = cur.leftChild;
			else
				cur = cur.rightChild;
		}

		return found;
	}
	
	private Node<E> findNodeFirstInorder2(E key) {

		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.push(root);

		HashSet<Node<E>> visited = new HashSet<Node<E>>();

		// 60 60 10
		while (!stack.empty()) {
			Node<E> node = stack.peek();

			if (node == null) { // 1
				stack.pop();
			} else if (key.equals(node.element) && visited.contains(node)) { // 2
				return node;
			}
			else if (visited.contains(node)) // 3
				stack.pop();
			else if (key.compareTo(node.element) <= 0) { // 4
				stack.push(node.leftChild);
				visited.add(node);
			} else if (key.compareTo(node.element) > 0) { // 5
				stack.push(node.rightChild);
				visited.add(node);
			}
		}
		
		return null;
	}
	
	public void testFindNodeFirstInorder() {
		root = new Node(108);
		root.leftChild = new Node(108);
		root.rightChild = new Node(285);
		Node<E> node = root.leftChild;
		node.leftChild = new Node(-10);
		node.rightChild = new Node(108);
		node = node.leftChild;
		node.leftChild = new Node(-14);
		node.rightChild = new Node(2);
		node = root.rightChild;
		node.leftChild = new Node(243);
		node.rightChild = new Node(285);
		node = node.rightChild;
		node.rightChild = new Node(401);
		
		System.out.println( findNodeFirstInorder3( (E) new Integer( 285 ) ) );
	}
	
	private Node<E> findNodeFirstInorder(E key) {
		return findNodeFirstInorder(root, key);
	}

	private Node<E> findNodeFirstInorder(Node<E> node, E key) {

		if (node == null)
			return null;

		if ( key.equals(node.element) ) {
			Node<E> result = findNodeFirstInorder(node.leftChild, key);
			return result != null ? result : node;
		}

		if ( key.compareTo(node.element) < 0)
			return findNodeFirstInorder(node.leftChild, key);
		else
			return findNodeFirstInorder(node.rightChild, key);
	}
	
	private static class Tuple<E> {
		private boolean isKBalanced;
		private int count;
		private Node<E> node;

		Tuple(Node<E> node, int count, boolean isKBalanced) {
			this.node = node;
			this.count = count;
			this.isKBalanced = isKBalanced;
		}
	}

	public Node<E> findKBalanced(int k) {
		return findKBalanced(root, k).node;
	}

	private Tuple<E> findKBalanced(Node<E> cur, int k) {

		if (cur == null) {
			return new Tuple<E>(null, 0, true);
		} else {
			Tuple<E> left = findKBalanced(cur.leftChild, k);
			Tuple<E> right = findKBalanced(cur.rightChild, k);

			// solution found in leftChild
			if (left.node != null)
				return left;
			// solution found in rightChild
			else if (right.node != null)
				return right;

			// check current node for solution
			boolean isKBalanced = Math.abs( left.count - right.count ) <= k
					&& left.isKBalanced && right.isKBalanced;
			int count = 1 + left.count + right.count;

			// solution found in current node
			if (!isKBalanced && left.isKBalanced && right.isKBalanced) {
				return new Tuple<E>(cur, count, isKBalanced);
			} else {
				return new Tuple<E>(null, count, isKBalanced);
			}
		}

	}
	
	private E getLeftMostElement(Node<E> node)
	{
		if(node.leftChild == null)
			return node.element;
		else
			return getLeftMostElement(node.leftChild);
	}
	
	public E findInorderSuccessor(E e) {
		// special case: root
		if (root != null && root.element.equals( e ) )
			return leftMost(root.rightChild).element;

		Node<E> parent = findParent(e);

		Node<E> node = parent.leftChild;
		if (parent.rightChild != null && e.equals( parent.rightChild.element ) )
			node = parent.rightChild;

		if (node.rightChild != null)
			return leftMost(node.rightChild).element;
		else
			return parent.element;
	}

	private Node<E> findParent(E e) {
		return findParent(root, e);
	}

	private Node<E> findParent(Node<E> root, E e) {
		if (root == null)
			return null;
		else if (root.leftChild.element.equals( e ) || root.rightChild.element.equals( e ) )
			return root;
		else if (e.compareTo(root.element) < 0)
			return findParent(root.leftChild, e);
		else
			return findParent(root.rightChild, e);
	}

	private Node<E> leftMost(Node<E> node) {
		if (node == null)
			return null;
		if (node.leftChild == null)
			return node;
		else
			return leftMost(node.leftChild);
	}

	private Node<E> findNode(E e) {
		return findNode(root, e);
	}

	private Node<E> findNode(Node<E> root, E e) {
		if (root == null)
			return null;
		if (root.element == e)
			return root;
		else if (e.compareTo(root.element) < 0)
			return findNode(root.leftChild, e);
		else
			return findNode(root.rightChild, e);
	}
	
	private Node<E> deleteLeftMost(Node<E> node)
	{
		// base case
		if(node.leftChild == null)
		{
			node = deleteNode(node);
		}
		else
			node.leftChild = deleteLeftMost(node.leftChild);
		
		return node;
	}
	
	public boolean empty() { return root == null; }
	
	public int size() {
		return size(root);
	}
	
	private int size(Node<E> node)
	{
		if(node == null)
			return 0;
		else
			return 1 + size(node.leftChild) + size(node.rightChild);
	}
	
	public int maxDepth()
	{
		return maxDepth(root);
	}
	
	private int maxDepth(Node<E> node)
	{
		if(node == null)
			return 0;
		else
			return 1 + Math.max( maxDepth(node.leftChild), 
									maxDepth(node.rightChild) );
	}
	
	public E minValue()
	{
		return getLeftMostElement(root);
	}
	
	public boolean hasPathSum(int sum)
	{
		return hasPathSum(root, sum);
	}
	
	public boolean hasPathSum(Node<E> node, int sum)
	{
		if(node == null)
			return sum == 0;
		else
			return hasPathSum(node.leftChild, sum - (Integer)node.element)
								|| hasPathSum(node.rightChild, sum - (Integer)node.element);
	}
	
	/**
	 
	 2, 4, 8, 16
	 
	 1, 2, 3, 4
	 1, 3, 7, 15
	 
	 * */
	
	/***
	    60
	   /  \
	 20    70
	/  \
	10  40
	  /  \
	 30  50
	*/
	
	public boolean isBlanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node<E> node) {
		// leaf
		if (node == null)
			return true;
		else
			return isBalanced(node.leftChild) && isBalanced(node.rightChild)
					&& Math.abs(height(node.leftChild) - height(node.rightChild)) <= 1;
	}

	HashMap<Node<E>, Integer> heightsMap = new HashMap<Node<E>, Integer>();
	// reset to true after tree structural changes

	private int height(Node<E> node) {
		if (node == null)
			return 0;

		// caching
		Integer height = heightsMap.get(node);
		if (height == null) {
			height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
			heightsMap.put(node, height);
		}

		return height;
	}
	
	private static class Range {
		private int min, max;

		Range(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	public boolean isBST() {
		return isBST((Node<Integer>)root, new Range(Integer.MIN_VALUE, Integer.MAX_VALUE));
	}

	public boolean isBST(Node<Integer> node, Range range)
	{
		if(node == null)
		return true;
	
		return node.element.compareTo( range.min ) >= 0
				&& node.element.compareTo( range.max ) <= 0
				&& isBST( node.leftChild, new Range( range.min, node.element ) ) 
				&& isBST( node.rightChild, new Range( node.element, range.max ) );
	}


	
	// 
	public void printPaths()
	{
//		int maxDepth = maxDepth();
//		int maxNodes = (int) ( Math.pow(2, maxDepth) - 1 );
//		Object[] nodesArr = new Object[maxNodes];
//		toNodeArray(root, nodesArr, 0);
		printPaths(root, new StringBuilder());
	}
	
	private void toNodeArray(Node<E> node, Object[] arr, int indx)
	{
		if(node != null)
		{
			arr[indx] = node.element;
			toNodeArray(node.leftChild, arr, indx*2+1);
			toNodeArray(node.rightChild, arr, indx*2+2);
		}
	}
	
	private void printPaths(Node<E> root, StringBuilder pathSoFar)
	{
		if(root != null)
		{
			pathSoFar.append(root.element).append(" ");
			printPaths(root.leftChild, new StringBuilder(pathSoFar) );
			if(root.leftChild != root.rightChild)
				printPaths(root.rightChild, new StringBuilder(pathSoFar) );
		}
		else
		{
			System.out.println(pathSoFar);
		}
	}
	
	public static void treeSort(int[] arr)
	{
		// construct a new tree of the integers
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for(int value : arr)
			bst.insert(value);
		inorderSort(bst.root, arr, 0);
	}
	
	private static int inorderSort(Node<Integer> node, int[] arr, int arrIndx)
	{
		if(node != null)
		{
			arrIndx = inorderSort(node.leftChild, arr, arrIndx);
			arr[arrIndx++] = (Integer) node.element;
			arrIndx = inorderSort(node.rightChild, arr, arrIndx);
		}
			
		return arrIndx;
	}
	
	private static final String PATH = "C:\\Development\\prototype\\workspace\\HackerRank\\src\\com\\indeed\\khaledabbas\\datastructures\\trees\\tree.txt";
	
	private static final Charset CHARSET = Charset.forName("US-ASCII");
	
	public void storeTreeExactShape()
	{
		try( BufferedWriter writer = Files.newBufferedWriter( Paths.get(PATH), CHARSET) )
		{
			preorderWrite(root, writer);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void preorderWrite(Node<E> node, BufferedWriter writer) throws IOException
	{
		if(node != null)
		{
			writer.write(node.element + System.getProperty("line.separator"));
			preorderWrite(node.leftChild, writer);
			preorderWrite(node.rightChild, writer);
		}
	}
	
	public static BinarySearchTree restoreTreeExactShape()
	{
		BinarySearchTree bst = null;
		
		try( BufferedReader reader = Files.newBufferedReader( Paths.get(PATH), CHARSET ) )
		{
			bst = new BinarySearchTree();
			String line = null;
			while( ( line = reader.readLine() ) != null)
				bst.insert( Integer.valueOf( line ) );
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return bst;
	}
	
	public BinarySearchTree buildTreeBalanced()
	{
		// convert tree to sorted array
//		Object[] arr = new Object[ size() ];
//		treeToArray(root, arr, 0);
		BinarySearchTree balanced = null;
		
		try(BufferedWriter writer = Files.newBufferedWriter(Paths.get( PATH ), CHARSET )  )
		{
			storeTreeInorder(root, writer);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(PATH), CHARSET) )
		{
			int size = size();
			balanced = new BinarySearchTree();
			
			// 0  1  2  3  4  5  6
			// 10 20 30 40 50 60 70
			balanced.root = readBalanced(balanced.root, reader, size);
		}
		catch( IOException ex)
		{
			
		}
		
		return balanced;
	}
	
	private Node<E> readBalanced(Node<E> node, BufferedReader reader, int size) throws IOException
	{
		if(size > 0)
		{
			node = new Node();
			node.leftChild = readBalanced(node.leftChild, reader, size/2);
			node.element = (E) reader.readLine();
			node.rightChild = readBalanced(node.rightChild, reader, (size-1)/2);
		}
		
		return node;
	}
	
	public static BinarySearchTree<Integer> getBalancedTree(int[] arr) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.root = balancedTree( arr, 0, arr.length - 1 );
		return bst;
	}

	private static Node<Integer> balancedTree(int[] arr, int start, int end) {
		if( start <= end) 
		{
			int mid = (start + end) / 2;
			Node<Integer> root = new Node<Integer>( arr[mid] );
			root.leftChild = balancedTree( arr, start, mid - 1 );
			root.rightChild = balancedTree( arr, mid + 1, end );
			return root;
		}
		
		return null;
	}
	
	private void storeTreeInorder(Node<E> node, BufferedWriter writer) throws IOException
	{
		if(node != null)
		{
			storeTreeInorder(node.leftChild, writer);
			writer.write(node.element + System.getProperty("line.separator") );
			storeTreeInorder(node.rightChild, writer);
		}
	}
	
	private int treeToArray(Node<E> node, Object[] arr, int indx)
	{
		if(node != null)
		{
			indx = treeToArray(node.leftChild, arr, indx);
			arr[indx++] = node.element;
			indx = treeToArray(node.rightChild, arr, indx);
		}
		
		return indx;
	}
	
	private static void printArray(int[] arr)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for(int i : arr)
			sb.append(i).append(",");
		
		if(arr.length > 0)
			sb.setLength(sb.length() - 1);
		sb.append("}");
		
		System.out.println(new String(sb));
	}
	
	
	public void traverseInorder()
	{
		traverseInorder(root);
	}
	
	private void traverseInorder(Node<E> node)
	{
		if(node != null)
		{
			traverseInorder(node.leftChild);
			visit(node);
			traverseInorder(node.rightChild);
		}
	}
	
	public void traversePreorder()
	{
		traversePreorder(root);
	}
	
	private void traversePreorder(Node<E> node)
	{
		if(node != null)
		{
			visit(node);
			traversePreorder(node.leftChild);
			traversePreorder(node.rightChild);
		}
		
	}
	
	public void traversePostorder()
	{
		traversePostorder(root);
	}
	
	private void traversePostorder(Node<E> node)
	{
		if(node != null)
		{
			traversePostorder(node.leftChild);
			traversePostorder(node.rightChild);
			visit(node);
		}
	}
	
	private void visit(Node<E> node)
	{
		System.out.print(node.element.toString() + " ");
	}

}
