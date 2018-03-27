package com.amazon.datastructures.trees;

import com.amazon.datastructures.trees.BinaryTree.Node;
import java.util.*;

/** BST with parents */
public class BST2 {
	
	private Node root;
    
    private static class Node {
        private int item;
        private Node left, right;
        private Node parent;
        
        private Node(int item) {
        		this.item = item;
        }
    }
    
    public BST2 (int[] arr) {
        for (int x : arr) {
            add(x);
        }
    }
    
    public void add(int x) {
        root = add(null, root, x);
    }
    
    private Node add(Node parent, Node root, int x) {
        if (root == null) {
            root = new Node(x);
            root.parent = parent;
        }
        else if (x < root.item) {
            root.left = add(root, root.left, x);
        }
        else {
            root.right = add(root, root.right, x);
        }
        
        return root;
    }
    
    public int findSuccessor(int x) {
        Node n = findNode(x);
        if (n == null) {
            throw new IllegalArgumentException("Node with value " + x + " doesn't exist");
        }
        Node res = findSuccessor(n);
        if (res == null) {
            throw new IllegalArgumentException("Node " + x + " does not have a successor");
        }
        return res.item;
    }
    
    private Node findSuccessor(Node n) {
        if (n == null) {
            return null;
        }
        
        // find left most node in right child subtree
        if (n.right != null) {
            Node cur = n.right;
            // find left most child
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        // find first parent on the right i.e. larger value
        else {
            Node parent = n.parent;
            while (parent != null && parent.item < n.item) {
                parent = parent.parent;
            }
            return parent;
        }
    }
    
    private Node findNode(int x) {
        return findNode(root, x);
    }
    
    private Node findNode(Node root, int x) {
        if (root == null) {
            return null;
        }
        else if (root.item == x) {
            return root;
        }
        else if (x < root.item) {
            return findNode(root.left, x);
        }
        else {
            return findNode(root.right, x);
        }
    }

    public String inorder() {
    		StringBuilder sb = new StringBuilder();
    		inorder(root, sb);
    		return sb.toString();
    }
    
    private void inorder(Node root, StringBuilder sb) {
    		if (root == null) {
    			return;
    		}
    		
    		inorder(root.left, sb);
    		sb.append(root.item + ", ");
    		inorder(root.right, sb);
    }
    
    public String preorder() {
	    	StringBuilder sb = new StringBuilder();
	    	preorder(root, sb);
	    	return sb.toString();
    }
    
    private void preorder(Node root, StringBuilder sb) {
	    	if (root == null) {
	    		return;
	    	}
	    	
	    	sb.append(root.item + ", ");
	    	preorder(root.left, sb);
	    	preorder(root.right, sb);
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
		sb.append(root.item + " => ")
			.append(root.left != null ? root.left.item : "")
			.append(",")
			.append(root.right != null ? root.right.item : "")
			.append("\n");
		
		
		preorderParentChild(root.left, sb);
		preorderParentChild(root.right, sb);
    }
    
    public boolean isTotallyOrderedAroundMiddle(int first, int second, int middle) {
    		return isTotallyOrderedAroundMiddle(findNode(first), findNode(second), findNode(middle));
    }
    
    public boolean isTotallyOrderedAroundMiddle2(int first, int second, int middle) {
    		return isTotallyOrdered2(findNode(first), findNode(second), findNode(middle));
    }
    
    public boolean isTotallyOrderedAroundMiddle(Node first, Node second, Node middle) {
        // traverse starting from both first and second
        return isTotallyOrdered(first, middle, second) 
                || isTotallyOrdered(second, middle, first);
    }
    
    public boolean isTotallyOrdered(Node first, Node second, Node third) {
        return isTotallyOrdered(first, second)
                && isTotallyOrdered(second, third);
    }
    
    public boolean isTotallyOrdered(Node first, Node second) {
        if (first == null || second == null || first == second) {
            return false;
        }
        
        Node cur = first;
        while (cur != null) {
            if (second.item == cur.item) {
                return true;
            }
            else if (second.item < cur.item) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        return false;
    }
    
    public boolean isTotallyOrdered2(Node n1, Node n2, Node middle) {
        if (n1 == null || n2 == null || middle == null) {
            return false;
        }
        else if (n1 == n2 || n1 == middle || n2 == middle) {
            return false;
        }
        
        boolean foundMiddle = false;
        // next parent and child to look for
        Node parent = null;
        Node child = null;
        Node first = n1;
        Node second = n2;
        while (!foundMiddle && (first != null || second != null)) {
            if (first != null) {
                if (first.item == middle.item) {
                    parent = first;
                    child = n2;
                    foundMiddle = true;
                }
                else if (middle.item < first.item) {
                    first = first.left;
                }
                else {
                    first = first.right;
                }
            }
            if (parent == null && second != null) {
                if (second.item == middle.item) {
                    parent = second;
                    child = n1;
                    foundMiddle = true;
                }
                else if (middle.item < second.item) {
                    second = second.left;
                }
                else {
                    second = second.right;
                }
            }
        }
        
        if (parent == null) {
            return false;
        }
        
        Node cur = parent;
        while (cur != null && child != null) {
            if (cur.item == child.item) {
                return true;
            }
            else if (child.item < cur.item) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        
        return false;
    }

    public List<Integer> range(int start, int end) {
        List<Integer> res = new ArrayList<Integer>();
        range(root, start, end, res);
        return res;
    }
    
    private void range(Node root, int start, int end, List<Integer> res) {
        if (root == null) {
            return;
        }
        
        if (root.item >= start && root.item <= end) {
            res.add(root.item);
        }
        
        if (root.item >= start) {
            range(root.left, start, end, res);
        }
        
        if (root.item <= end) {
            range(root.right, start, end, res);
        }
    }
    
    public List<Integer> range2(int start, int end) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<Node> s =  new Stack<Node>();
        s.add(root);
        
        while (!s.isEmpty()) {
            Node cur = s.pop();
            
            if (cur.item >= start && cur.item <= end) {
                res.add(cur.item);
            }
            
            if (cur.item >= start && cur.left != null) {
                s.add(cur.left);
            }
            
            if (cur.item <= end && cur.right != null) {
                s.add(cur.right);
            }
        }
        
        return res;
    }
    
}
