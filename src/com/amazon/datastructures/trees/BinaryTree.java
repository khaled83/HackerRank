package com.amazon.datastructures.trees;

import java.util.*;

/**

			314
		/		 \
	 6				6
	  \			  /
	   2		2
	    \	  /
	     3	 3
 
 */

public class BinaryTree {
	
	private Node root;

    public static class Node {
        private int item;
        private Node leftChild, rightChild;
        
        public Node addLeft(int x) {
        	Node n = new Node();
        	n.item = x;
        	this.leftChild = n;
        	return n;
        }
        
        public Node addRight(int x) {
        	Node n = new Node();
        	n.item = x;
        	this.rightChild = n;
        	return n;
        }
        
    }
    
    public Node addRoot(int x) {
    	Node oldRoot = root;
    	root = new Node();
    	root.item = x;
    	root.leftChild = oldRoot;
    	return root;
    }
    
    public Node addLeft(Node parent, int x) {
    	Node n = new Node();
    	n.item = x;
    	parent.leftChild = n;
    	return n;
    }
    
    public Node addRight(Node parent, int x) {
    	Node n = new Node();
    	n.item = x;
    	parent.rightChild = n;
    	return n;
    }
    
    public boolean isSymmetric2() {
        return isSymmetric2(root.leftChild, root.rightChild);
    }

/**
    
            314
          /     \
        6         6
       / \       /  \
      5   2     2    5
           \   /
           3   3
    
left    right
----    -----
6        6
5        5
x        x
x        x
2        2
x        x
3        3
*/
    
    private boolean isSymmetric2(Node left, Node right) {
    	if (left == null || right == null) {
    		return left == right;
    	}
        return  left.item == right.item
                && isSymmetric2(left.leftChild, right.rightChild)
                && isSymmetric2(left.rightChild, right.leftChild);
    }
    
    public boolean isSymmetric() {
        int[] left = inorder(root.leftChild);
        int[] right = inorder(root.rightChild);
        // match arrays in reverse
        if (left.length != right.length) {
            return false;
        }
        
        for (int first = 0, second = right.length - 1; first < left.length; first++, second--) {
            if (left[first] != right[second]) {
                return false;
            }
        }
        
        return true;
    }
    
    private int[] inorder(Node root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        int[] arr = new int[res.size()];
        int indx = 0;
        for (int x : res) {
        	arr[indx++] = x;
        }
        return arr;
    }
    
    private void inorder(Node root, ArrayList<Integer> res) {
        if (root == null) {
            return;
        }
        
        inorder(root.leftChild, res);
        res.add(root.item);
        inorder(root.rightChild, res);
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	preorder(root, sb);
    	
    	return sb.toString();
    }
    
    private void preorder(Node root, StringBuilder sb) {
    	if (root != null) {
    		sb.append(root.item).append(" ");
    		preorder(root.leftChild, sb);
    		preorder(root.rightChild, sb);
    	}
    }
 
    public List<Integer> findSumOfPaths() {
        List<Integer> res = new ArrayList<Integer>();
        findSumOfPaths(root, 0, res);
        return res;
    }
    
    private void findSumOfPaths(Node root, int val, List<Integer> res) {
        if (root != null) {
            val = (val << 1) | root.item;
            // leaf node => add new path sum
            if (root.leftChild == null && root.rightChild == null) {
                res.add(val);
            }
            // expand
            else {
                findSumOfPaths(root.leftChild, val, res);
                findSumOfPaths(root.rightChild, val, res);
            }
            // backtrack by elimiating LSB before going up the tree again
            val = val >>> 1;
        }
    }
    
}

