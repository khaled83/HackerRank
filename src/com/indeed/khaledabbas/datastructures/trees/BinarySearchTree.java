package com.indeed.khaledabbas.datastructures.trees;

public class BinarySearchTree<E extends Comparable<E>> {
	
	private static class Node<E>
	{
		private E element;
		
		private Node<E> leftChild;
		private Node<E> rightChild;
		private Node<E> parent;
		
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
	
	private Node<E> root = null;
	
	private int size;
	
	public int size() { return size; }
	
	public boolean empty() { return size == 0; }
	
	public void add(E e)
	{
		Node<E> newNode = findNode(e);
		newNode = new Node<E>(e, null, null);
		if(newNode.parent == null)
			newNode.parent = root;
		System.out.println("Root after adding " + e + " : " + root);
	}
	
	public E remove(E e)
	{
		return null;
	}
	
	private Node<E> findNode(E e)
	{
		return findNode(root, e);
	}
	
	private Node<E> findNode(Node<E> node, E e)
	{
		if(node == null)
			return null;
		else if(node.element == e)
			return node;
		else if(e.compareTo( node.element ) < 0)
			return findNode(node.leftChild, e);
		else
			return findNode(node.rightChild, e);
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
	
	private void visit(Node<E> node)
	{
		System.out.println(node.element.toString());
	}
	
	

}
































