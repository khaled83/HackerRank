package com.amazon.datastructures.trees;

import com.amazon.datastructures.trees.BinaryTree.Node;

public class BSTBalanced {

	private Node root;
    private int size;
    
    private static class Node {
        private int item;
        private Node left, right;
        // meta data
        private int size;
        
        private Node(int item) {
            this.item = item;
            this.size = 1;
        }
    }
    
    // O(log (n))
    public void insert(int item) {
        root = insert(root, item);
        balanceTree();
    }
    
    // O(log(n))
    private Node insert(Node root, int item) {
        Node n = root;
        if (root == null) {
            n = new Node(item);
        }
        else if (item < root.item) {
            root.left = insert(root.left, item);
            root.size++;
        }
        else {
            root.right = insert(root.right, item);
            root.size++;
        }
        
        return n;
    }
    
    // O(1)
    private void balanceTree() {
        // revisit: always fix from the root
        int leftSize = root.left == null ? 0 : root.left.size;
        int rightSize = root.right == null ? 0 : root.right.size;
        if (root.size == 5 ) {
        		System.out.println("left="+leftSize + " right="+ rightSize);
        }
        // tree is balanced
        if (Math.abs(leftSize - rightSize) <= 1) {
            return;
        }
        // tree requires balancing
        // rotate to the left
        if (rightSize > leftSize) {
            // root.right child is the new root
            Node newRoot = root.right;
            Node oldRoot = root;
            root = newRoot;
            Node newRootOldLeftChild = newRoot.left;
            newRoot.left = oldRoot;
            oldRoot.right = newRootOldLeftChild;
            
            // update node sizes
            oldRoot.size -= newRoot.size;
            oldRoot.size += newRootOldLeftChild == null ? 0 : newRootOldLeftChild.size;
            newRoot.size += oldRoot.size;
        }
        // rotate to the right
        else {
            // root.left child is the new root
            Node newRoot = root.left;
            Node oldRoot = root;
            root = newRoot;
            Node newRootOldRightChild = newRoot.right;
            newRoot.right = oldRoot;
            oldRoot.left = newRootOldRightChild;
        }
    }
    
    public String preorderParentChild() {
		StringBuilder sb = new StringBuilder();
		sb.append("= = = = = TREE\n");
		preorderParentChild(root, sb);
		return sb.toString();
	}
	
	private void preorderParentChild(Node root, StringBuilder sb) {
			if (root == null) {
				return;
			}
		sb.append(root.item + "[" + root.size + "]" + " => ")
			.append(root.left != null ? root.left.item : "")
			.append(",")
			.append(root.right != null ? root.right.item : "")
			.append("\n");
		
		
		preorderParentChild(root.left, sb);
		preorderParentChild(root.right, sb);
	}
    
}
