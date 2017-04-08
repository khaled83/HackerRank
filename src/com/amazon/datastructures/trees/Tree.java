package com.amazon.datastructures.trees;

import java.util.NoSuchElementException;

import javax.naming.SizeLimitExceededException;

public abstract class Tree<E extends Comparable<E>> {
	
	abstract public boolean isFull();
	
	abstract public boolean isEmpty();
	
	abstract public void insert(E value) throws SizeLimitExceededException;
	
	abstract public void delete(E value) throws NoSuchElementException;
	
	abstract public E floor(E value);
	
	abstract public E ceiling(E value);
	
	abstract public Iterable<Integer> inorder();
	
}
