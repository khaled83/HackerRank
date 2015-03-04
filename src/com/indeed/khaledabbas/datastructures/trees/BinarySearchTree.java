package com.indeed.khaledabbas.datastructures.trees;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

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
	
	private E getLeftMostElement(Node<E> node)
	{
		if(node.leftChild == null)
			return node.element;
		else
			return getLeftMostElement(node.leftChild);
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
	
	/***
    60
   /  \
 20    70
/  \
10  40
  /  \
 30  50
	*/
	
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
			node.rightChild = readBalanced(node.rightChild, reader, size/2);
		}
		
		return node;
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
































