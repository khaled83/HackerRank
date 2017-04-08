package com.amazon.datastructures.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.SizeLimitExceededException;

public class BSTArrayBased extends Tree<Integer> {
	
	private static final int DEFAULT_MAX_SIZE = 100;
    private final int MAX_SIZE;
    
    private int size = 0;
    private Integer[] items;    

    public BSTArrayBased() {
        this.MAX_SIZE = DEFAULT_MAX_SIZE;
        items = new Integer[MAX_SIZE];
    }

    public BSTArrayBased(int max_size) {
        this.MAX_SIZE = max_size;
        items = new Integer[MAX_SIZE];
    }
    
    public boolean isFull() {
        return size == MAX_SIZE;
    }
    
    public boolean isEmpty() {
    	return size == 0;
    }
    
    public void insert(Integer value) throws SizeLimitExceededException {
        if(isFull())
            throw new SizeLimitExceededException();
        insert(0, value);
    }
    
    private void insert(Integer root, Integer value) {
        if(items[root] == null) {
            items[root] = value;
            ++size;
        }
        else if(value < items[root])
            insert(leftChildIndx(root), value);
        else
            insert(rightChildIndx(root), value);
    }
    
    public void delete(Integer value) {
        if(isEmpty())
            throw new TreeEmptyException("Tree is empty");
        delete(0, value);
    }
    
    private void delete(int root, int value) {
        if (items[root] == null)
            throw new NoSuchElementException("Value " + value + " not found in tree");
        else if (items[root] == value)
            items[root] = null;
        else if (value < items[root])
            delete(leftChildIndx(root), value);
        else
            delete(rightChildIndx(root), value);
    }
    
    // @TODO @repeat doesn't work for all cases
    public Integer floor(Integer value) {
        return floor(0, value);
    }
    
    private Integer floor(int rootIndx, Integer value) {
        int leftChildIndx = leftChildIndx(rootIndx);
        Integer leftChild = leftChildIndx >= 0 && leftChildIndx < MAX_SIZE ? items[leftChildIndx] : null;
        int rightChildIndx = rightChildIndx(rootIndx);
        Integer rightChild = rightChildIndx >= 0 && rightChildIndx < MAX_SIZE ? items[rightChildIndx] : null;
        Integer root = items[rootIndx];
        
        if (root > value && leftChild != null)
            return floor(leftChildIndx, value);
        else if (root < value && rightChild != null)
            return floor(rightChildIndx, value);
        else if (root <= value)
            return root;
        else
            return null;
    }
    
    // @TODO @repeat doesn't work for all cases
    public Integer ceiling(Integer value) {
        return ceiling(0, value);
    }
    
    private Integer ceiling(int rootIndx, Integer value) {
        int leftChildIndx = leftChildIndx(rootIndx);
        Integer leftChild = items[leftChildIndx];
        int rightChildIndx = rightChildIndx(rootIndx);
        Integer rightChild = items[rightChildIndx];
        Integer root = items[rootIndx];
        
        if (root < value && rightChild != null)
            return ceiling(rightChildIndx, value);
        else if (root > value && leftChild != null)
            return ceiling(leftChild, value);
        else if (root >= value)
            return root;
        else
        	return null; // root < value && rightChild == null
    }
    
    private int leftChildIndx(int parent) {
    	int leftChildIndx = 2*parent+1;
    	if (leftChildIndx >= MAX_SIZE)
    		leftChildIndx = -1;
        return leftChildIndx;
    }

    private int rightChildIndx(int parent) {
    	int rightChildIndx = 2*parent+2;
		if (rightChildIndx >= MAX_SIZE)
			rightChildIndx = -1;
        return rightChildIndx;
    }
    
    public List<Integer> inorder() {
    	List<Integer> result = new ArrayList<Integer>();
    	inorder(0, result);
    	return result;
    }
    
    private void inorder(int rootIndx, List<Integer> result) {
    	if(rootIndx < 0 || rootIndx >= MAX_SIZE || items[rootIndx] == null)
    		return;
    	
    	inorder(leftChildIndx(rootIndx), result);
    	result.add(items[rootIndx]);
    	inorder(rightChildIndx(rootIndx), result);
    }
    
    @Override
    public String toString() {
    	return Arrays.asList(items).toString();
    }
    
    public static class TreeEmptyException extends RuntimeException {
    	
    	TreeEmptyException(String msg) {
    		super(msg);
    	}
    	
    }

}
