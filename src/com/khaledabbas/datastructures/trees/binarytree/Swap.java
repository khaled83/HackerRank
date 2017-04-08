package com.khaledabbas.datastructures.trees.binarytree;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Swap {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        BST bst = new BST();
        LinkedList<BST.Node> queue = new LinkedList<BST.Node>();
        queue.addLast( bst.root );

        for( int i=0; i < N; i++) {
        	BST.Node cur = queue.removeFirst();
        	BST.Node left = bst.insertLeft( cur, sc.nextInt() );
        	BST.Node right  = bst.insertRight( cur, sc.nextInt() );
        	if( left != null )
        		queue.addLast( left );
        	if( right != null )
        		queue.addLast( right );
        }
        int T = sc.nextInt();
        for( int i=0; i < T; i++) {
            int K = sc.nextInt();
//            bst.inorderTraversal();System.out.println();
            bst.swap( K );
            System.out.println( new String( bst.inorderTraversal() ) );
//            bst.swap( K );
        }
        
        sc.close();
    }
    
    static class BST {
        
        public BST() {
            root = new Node( 1, null, null );
        }
        
        static class Node {
            private int element;
            private Node leftChild, rightChild;
            
            Node( int e, Node l, Node r ) {
                element = e;
                leftChild = l;
                rightChild = r;
            }
            
        }
        
        private Node root;
        
        public Node insertLeft( Node parent, int e ) {
            if( e == -1 )
                parent.leftChild = null;
            else
                parent.leftChild = new Node(e, null, null);
            return parent.leftChild;
        }
        
        public Node insertRight( Node parent, int e ) {
            if( e == -1 )
                parent.rightChild = null;
            else
                parent.rightChild = new Node(e, null, null);
            return parent.rightChild;
        }
        
        public String inorderTraversal() {
        	StringBuilder sb = inorderTraversal( root, new StringBuilder() );
        	return sb.substring(0, sb.length()-1);
        }
        
         StringBuilder inorderTraversal( Node cur, StringBuilder sb ) {
            if( cur != null ) {
            	sb =  inorderTraversal( cur.leftChild , sb);
            	sb.append( cur.element ).append( " " );
                sb =  inorderTraversal( cur.rightChild, sb );
            }
            return sb;
        }
        
        public void swap( int K ) {
            swap( root, 1, K);
        }
        
        public void swap( Node cur, int depth, int K ) {
            if( cur != null ) {
            	if( depth % K == 0 ) {
            		Node tmp = cur.leftChild;
	                cur.leftChild = cur.rightChild;
	                cur.rightChild = tmp;
            	}
                swap( cur.leftChild, depth+1, K );
                swap( cur.rightChild, depth+1, K );
            }
        }
        

    	/**
    	 * @see	alternative soltuion at Elements of Programming Interviews pg. 266
    	 * @deprecated	not tested
    	 */
    	public boolean isSymmetrical() {

//    		Node left = root.leftChild;
//    		Node right = root.rightChild;
//    		// O( N )
//    		List leftList = new ArrayList();
//    		inorderList( left, leftList );
//    		// O( N )
//    		List rightList = new ArrayList();
//    		inorderList( right, rightList );
//
//    		int n = leftList.size();
//    		if (rightList.size() != n)
//    			return false;
//
//    		// alt: reverse sort rightList, and return l1.equals(l2): cost= O( n
//    		// log(n) )
//
//    		// O( N )
//    		for (int i = 0; i < n; i++) {
//    			if (!leftList.get(i).equals(rightList.get(n - 1 - i)))
//    				return false;
//    		}

    		return true;

    	}
    	
    	public boolean isSymmetrical2() {
    	    return isSymmetrical( root.leftChild, root.rightChild );
    	}

    	private static boolean isSymmetrical( Node first, Node second ) {
    	    if( first == null )
    	        return second == null;
    	        
    	    return first == second
    	            && isSymmetrical( first.leftChild, second.rightChild )
    	            && isSymmetrical( first.rightChild, second.leftChild );
    	}
    }
}