package com.amazon.datastructures.trees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<E extends Comparable<E>> {
	
	private static class Node<E> {
        E item;
        Node<E> leftChild, rightChild;    
        
        private Node() {}
        
        private Node(E item) { this.item = item; }
    }
    
    private Node<E> root;
    private int size;
    
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
    
}
