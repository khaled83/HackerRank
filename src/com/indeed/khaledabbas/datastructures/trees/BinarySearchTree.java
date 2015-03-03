package com.indeed.khaledabbas.datastructures.trees;

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
































