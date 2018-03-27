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
        private Node left, right;
        private Node parent;
        // number of nodes stored at subtree rooted at current node
        private int n;
        
        private Node() {
        }
        
        private Node(int item) {
            this.item = item;
        }
        
        public Node addLeft(int x) {
	        	Node n = new Node();
	        	n.item = x;
	        	this.left = n;
	        	n.parent = this;
	        	return n;
        }
        
        public Node addRight(int x) {
	        	Node n = new Node();
	        	n.item = x;
	        	this.right = n;
	        	n.parent = this;
	        	return n;
        }
        
    }
	
	public BinaryTree() {}
	
	public BinaryTree(BinaryTree clone) {
        this.root = clone(clone.root);
    }
	
	private Node clone(Node root) {
        if (root == null) {
            return null;
        }
        Node n = new Node(root.item);
        n.left = clone(root.left);
        n.right = clone(root.right);
        return n;
    }
	
	private static BinaryTree cloneAddLeftChild(BinaryTree clone, Node n, Node left) {
		BinaryTree bt = new BinaryTree();
        bt.root = cloneAddLeftChild(clone.root, n, left);
        return bt;
    }
	
	private static Node cloneAddLeftChild(Node root, Node parent, Node left) {
        if (root == null) {
            return null;
        }
        Node n = new Node(root.item);
        n.left = cloneAddLeftChild(root.left, parent, left);
        if (root.item == parent.item) {
        		System.out.println("Adding left child " + left.item + " to " + n.item);
        		n.left = new Node(left.item);
        }
        n.right = cloneAddLeftChild(root.right, parent, left);
        return n;
    }
	
	private static BinaryTree cloneAddRightChild(BinaryTree clone, Node n, Node right) {
		BinaryTree bt = new BinaryTree();
		bt.root = cloneAddRightChild(clone.root, n, right);
		return bt;
	}
	
	private static Node cloneAddRightChild(Node root, Node parent, Node right) {
		if (root == null) {
			return null;
		}
		Node n = new Node(root.item);
		n.left = cloneAddRightChild(root.left, parent, right);
		n.right = cloneAddRightChild(root.right, parent, right);
		if (root.item == parent.item) {
			System.out.println("Adding right child " + right.item + " to " + n.item);
			n.right = new Node(right.item);
		}
		return n;
	}
	
	private void preorder(Node root, List<Node> res) {
		if (root == null) {
			return;
		}
		res.add(root);
		preorder(root.left, res);
		preorder(root.right, res);        
	}
	
    public Node addRoot(int x) {
    	Node oldRoot = root;
    	root = new Node();
    	root.item = x;
    	root.left = oldRoot;
    	return root;
    }
    
    public Node addLeft(Node parent, int x) {
    	Node n = new Node();
    	n.item = x;
    	parent.left = n;
    	return n;
    }
    
    public Node addRight(Node parent, int x) {
    	Node n = new Node();
    	n.item = x;
    	parent.right = n;
    	return n;
    }
    
    public boolean isSymmetric2() {
        return isSymmetric2(root.left, root.right);
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
                && isSymmetric2(left.left, right.right)
                && isSymmetric2(left.right, right.left);
    }
    
    public boolean isSymmetric() {
        int[] left = inorder(root.left);
        int[] right = inorder(root.right);
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
        
        inorder(root.left, res);
        res.add(root.item);
        inorder(root.right, res);
    }
    
    public String inorder2() {
        Stack<Node> s = new Stack<Node>();
        HashSet<Node> visited = new HashSet<Node>();
        s.push(root);
        
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            Node cur = s.peek();
            
            Node left = cur.left;
            // visit left subtree
            if (left != null && !visited.contains(left)) {
                s.push(left);
            }
            // visit current node
            else {
                sb.append(cur.item).append(" ");
                // remove cur from traversal
                s.pop();
                visited.add(cur);
                Node right = cur.right;
                if (right != null) {
                    s.push(right);
                }
            }
        }
        
        return sb.toString();
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
    		preorder(root.left, sb);
    		preorder(root.right, sb);
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
            if (root.left == null && root.right == null) {
                res.add(val);
            }
            // expand
            else {
                findSumOfPaths(root.left, val, res);
                findSumOfPaths(root.right, val, res);
            }
            // backtrack by elimiating LSB before going up the tree again
            val = val >>> 1;
        }
    }
    
    public ArrayList<ArrayList<Integer>> nodesPerDepth() {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        nodesPerDepth(root, 0, res);
        return res;
    }
    
/**
root    depth    res
----    -----    ---
314     0        {{314},}
6       1        {{314},{6}}
271     2        {{314},{6},{271}}
28      3        {{314},{6},{271},{28}}
x       4        ..
0       3        {{314},{6},{271},{28,0}}
561     2        {{314},{6},{271,561},{28,0}}
3       3        {{314},{6},{271,561},{28,0,3}}
17      4        {{314},{6},{271,561},{28,0,3},{4}}
6       1        {{314},{6,1},{271,561},{28,0,3},{4}}
....
*/

/**
Time: O(n)
Space: log(n) (recursion callback stack) + n => O(n)
*/
    private void nodesPerDepth(Node root, int depth, ArrayList<ArrayList<Integer>> res) {
        // past leaf
        if (root == null) {
            return;
        }
        
        // initialize result for level
        ArrayList<Integer> cur;
        if (res.size() < (depth + 1)) {
            cur = new ArrayList<Integer>();
            res.add(depth, cur);
        }
        else {
        	cur = res.get(depth);
        }
        
        // add to result
        cur.add(root.item);
        
        // continue traversing the tree preorder
        nodesPerDepth(root.left, depth + 1, res);
        nodesPerDepth(root.right, depth + 1, res);
    }

/**

q                    dQ                    n    depth    left    right    res
-                    --                    -    -----    ----    -----    ---
314                  0                     314  0        6       6        {{314}}
6,6                  1,1                   6    1        271     561      {{314},{6}}
6,271,561            1,2,2                 6    1        2       271      {{314},{6,6}}
271,561,2,271        2,2,2,2               271  2        28      0        {{314},{6,6},{271}}
561,2,271,28,0       2,2,2,3,3             561  2        -       3        {{314},{6,6},{271,561}}
2,271,28,0,3         2,2,3,3,3             2

*/

    public ArrayList<ArrayList<Integer>> nodesPerDepth2() {
        Queue<Integer> depthQ = new LinkedList<Integer>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Queue<Node> q = new LinkedList<Node>(); // TODO: implement
        q.add(root);
        depthQ.add(0);
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            int depth = depthQ.poll();
            ArrayList<Integer> cur;
            if (res.size() >= (depth + 1)) {
            	cur = res.get(depth);
            }
            else {
                cur = new ArrayList<Integer>();
                res.add(depth, cur);
            }
            cur.add(n.item);
            
            Node left = n.left;
            if (left != null) {
                q.add(left);
                depthQ.add(depth + 1);
            }
            Node right = n.right;
            if (right != null) {
                q.add(right);
                depthQ.add(depth + 1);
            }
        }
        
        return res;
    }
    
    public boolean findPathSum(int x) {
        return findPathSum(root, x, 0);
    }
    
    private boolean findPathSum(Node root, int x, int sumSoFar) {
        // assuming positive values only, stop execution when sumSoFar exceeds target, as it will only increase down the tree
        if (root == null || sumSoFar > x) {
            return false;
        }
        
        sumSoFar += root.item;
        if (sumSoFar == x) {
            return true;
        }
        
        // continue on left subtree
        return findPathSum(root.left, x, sumSoFar) || findPathSum(root.right, x, sumSoFar);
    }
    
    public boolean findPathSum2(int x) {
        Queue<Node> q = new LinkedList<Node>();
        Queue<Integer> sumQ = new LinkedList<Integer>();
        q.add(root);
        sumQ.add(0);
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int sumSoFar = sumQ.poll() + cur.item;
            if (sumSoFar == x) {
                return true;
            }
            Node left = cur.left;
            Node right = cur.right;
            if (left != null) {
                q.add(left);
                sumQ.add(sumSoFar);
            }
            
            if (right != null) {
                q.add(right);
                sumQ.add(sumSoFar);
            }
        }
        
        return false;
    }
    
    // Elements of Programming Interviews 9.8: Implement Preorder Traversal without Recursion
    public String preorderIterative() {
    		StringBuilder sb = new StringBuilder();
        Stack<Node> s = new Stack<Node>();
        Node cur = root;
        
        while (cur != null) {
            sb.append(cur.item + " ");
            if (cur.left != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                cur = cur.right;
                while (cur == null && !s.isEmpty()) {
                    cur = s.pop().right;
                }
            }
        }
        
        return sb.toString();
    }
    
    // Elements of Programming Inteviews 8.7: Compute Binary Tree Nodes in Order of Increasing Depth
    public List<List<Integer>> depthOrderTraversal() {
        Map<Integer, List<Integer>> h = new HashMap<Integer, List<Integer>>();
        depthOrderTraversal(root, 0, h);
        // convert map to array
        List<List<Integer>> res = new ArrayList<List<Integer>>(h.size());
        for (int depth : h.keySet()) {
            res.add(depth, h.get(depth));
        }
        return res;
    }
    
    private void depthOrderTraversal(Node root, int depth, Map<Integer, List<Integer>> h) {
        if (root == null) {
            return;
        }
        // add current item
        List<Integer> list = h.getOrDefault(depth, new ArrayList<Integer>());
        list.add(root.item);
        h.putIfAbsent(depth, list);
        // continue tree traversal
        depthOrderTraversal(root.left, depth + 1, h);
        depthOrderTraversal(root.right, depth + 1, h);
    }
    
    public void storeNumNodesSubTrees() {
    		storeNumNodesSubTrees(root);
    }
    
    public int storeNumNodesSubTrees(Node root) {
    		if (root == null) {
    			return 0;
    		}
    		
    		int left = storeNumNodesSubTrees(root.left);
    		int right = storeNumNodesSubTrees(root.right);
    		root.n = left + right + 1;
    		return root.n;
    }
    
    public int kthNode(int k) {
        return kthNode(root, k, 0, 0);
    }
    
    private int kthNode(Node root, int k, int leftParentN, int rightParentN) {
        if (root == null) {
            return -1;
        }
    
        // current kth value
        int cur = -1;
        // branching from parent to the right
        if (leftParentN > 0) {
            int rightN = root.right != null ? root.right.n : 0;
            if (root.item == 50) {
            		System.out.println("rightN="+rightN + " cur=" + (leftParentN - rightN) + " rightParentN=" + rightParentN);
            }
            cur = leftParentN - rightN;
            if (leftParentN > rightParentN) {
	            	cur = cur - rightParentN;
            }
        } 
        // branching from parent to the left
        else {
            int leftN = root.left != null ? root.left.n : 0;
            cur = leftN + 1;
        }
        
        if (cur == k) {
            return root.item;
        }
        else if (k < cur) {
            return kthNode(root.left, k, leftParentN, root.n);
        }
        else {
            return kthNode(root.right, k, root.n, rightParentN);
        }
    }
    
    public List<Integer> inorder3() {
        Node cur = root;
        Node prev = root;
        List<Integer> res = new ArrayList<Integer>();
        
        // end traverse when backtracking from root's right child
        while (!(cur == root && prev.equals(root.right))) {
            if (prev == cur.right || cur.right == null) {
                if (prev != cur.right && cur.left == null) {
                		res.add(cur.item);
                }
                prev = cur;
                cur = cur.parent;
            }
            else if (cur.left == null || prev == cur.left) {
            		res.add(cur.item);
                prev = cur;
                cur = cur.right;
            }
            else if(cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
        }
        
        return res;
    }
    
    private void visit(Node n) {
        System.out.println(n.item);
    }
    
    private List<Node> nodes() {
        List<Node> res = new ArrayList<Node>();
        preorder(root, res);
        return res;
    }
    
    public static List<BinaryTree> perumuateTrees(int numNodes) {
    		List<BinaryTree> res = new ArrayList<BinaryTree>();
        
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(0);
        res.add(bt);
        
        for (int i = 1; i < numNodes; i++) {
            int oldSize = res.size();
            // add new node
            Node cur = new Node(i);
            List<BinaryTree> prevRes = res;
            res = new ArrayList<BinaryTree>();
            for (BinaryTree prev : prevRes) {
                for (Node n : prev.nodes()) {
                    if (n.left == null) {
	                    	// clone new tree and add new node as left child
                    		bt = cloneAddLeftChild(prev, n, cur);
                        res.add(bt);                        
                    }
                    if (n.right == null) {
	                    	// clone new tree and add new node as left child
	                		bt = cloneAddRightChild(prev, n, cur);
	                    res.add(bt);                        
	                }
                }
            }
            // remove previous trees
//            res = res.subList(oldSize, res.size());
        }
        
        return res;
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
    
    public String preorderParentChildChars() {
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("= = = = = TREE\n");
	    	preorderParentChildChars(root, sb);
	    	return sb.toString();
    }
    
    private void preorderParentChildChars(Node root, StringBuilder sb) {
		if (root == null) {
			return;
		}
		sb.append(toChar(root.item) + " => ")
			.append(root.left != null ? toChar(root.left.item) : "")
			.append(",")
			.append(root.right != null ? toChar(root.right.item) : "")
			.append("\n");
		
		preorderParentChildChars(root.left, sb);
		preorderParentChildChars(root.right, sb);
    }
    
    private char toChar(int x) {
    		return (char) (x + 'A');
    }
    
    public BinaryTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // maps roots locations within inorder array
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            h.put(inorder[i], i);
        }
        int[] preorderIndx = new int[] {0};
        root = build(preorder, preorderIndx, inorder, 0, n - 1, h);
    }
    
    /**
       0  1  2  3  4  5    6  7  8  9
     314  6  5  2  1  3    6  2  3  5
       5  6  1  2  3  314  3  2  6  5
     * */
    
    private Node build(int[] preorder, int[] preorderIndx, int[] inorder, int firstInorder, int lastInorder, HashMap<Integer, Integer> inorderIndx) {
        if (firstInorder > lastInorder || preorderIndx[0] >= preorder.length) {
            return null;
        }
        int root = preorder[preorderIndx[0]];
        int firstLeft = firstInorder;
        int rootIndx = inorderIndx.get(root);
        int lastLeft = rootIndx - 1;
        int firstRight = rootIndx + 1;
        int lastRight = lastInorder;
        Node n = new Node(root);
        preorderIndx[0]++;
        n.left = build(preorder, preorderIndx, inorder, firstLeft, lastLeft, inorderIndx);
        n.right = build(preorder, preorderIndx, inorder, firstRight, lastRight, inorderIndx);
        return n;
    }
    
    private BinaryTree(Node root) {
        this.root = root;
    }
    
    public static BinaryTree buildFromPreorderWithMarker(Integer[] arr) {
        Node root = buildFromPreorderWithMarker(arr, new int[] {0});
        return new BinaryTree(root);
    }
    
    private static Node buildFromPreorderWithMarker(Integer[] arr, int[] indx) {
        int i = indx[0];
        if (i >= arr.length || arr[i] == null) {
            return null;
        }
        
        Node root = new Node(arr[i]);
        indx[0]++;
        root.left = buildFromPreorderWithMarker(arr, indx);
        indx[0]++;
        root.right = buildFromPreorderWithMarker(arr, indx);
        return root;
    }
    
    
    public static class LinkedListNode {
    		private int item;
    		private LinkedListNode next;
    		
    		public LinkedListNode() {}
    		
    		public LinkedListNode(int item) {
    			this.item = item;
    		}
    }
    
    private List<Integer> linkedListNodeToList(LinkedListNode head) {
		List<Integer> res = new ArrayList<Integer>();
		while (head != null) {
			res.add(head.item);
			head = head.next;
		}
		return res;
    }
    
    
    public List<Integer> leaves1() {
        List<Integer> res = new ArrayList<Integer>();
        leaves1(root, res);
        return res;
    }
    
    
    
    private void leaves1(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        else if (root.left == null && root.right == null) {
            res.add(root.item);
        }
        else {
            leaves1(root.left, res);
            leaves1(root.right, res);
        }
    }   
    
    public List<Integer> leaves2() {
    		System.out.println("LEAVES 2 =====");
		return linkedListNodeToList(leaves2Node());
    }
    
    private LinkedListNode leaves2Node() {
	    	LinkedListNode head = new LinkedListNode();
	    	LinkedListNode dummy = head;
	    	// this method works compared to the main method
//	    	leaves2Test(head, dummy);
    		leaves2(root, head);
        head = dummy.next;
        dummy.next = null;
        return head;
    }
    
    private void leaves2Test(LinkedListNode head, LinkedListNode dummy) {
    		System.out.println("START TESTING");
		head.next = new LinkedListNode(56);
		head = head.next;
		System.out.println("head="+ head.item + " dummy=" + dummy.next.item);
		head.next = new LinkedListNode(77);
		head = head.next;
		System.out.println("head="+ head.item + " dummy=" + dummy.next.item);
		System.out.println(linkedListNodeToList(dummy));
		System.out.println("END 	  TESTING");
    }
    
    private void leaves2(Node root, LinkedListNode head) {
        if (root == null) {
            return;
        }
        else if (root.left == null && root.right == null) {
            head.next = new LinkedListNode(root.item);
            head = head.next;
        }
        else {
            leaves2(root.left, head);
            leaves2(root.right, head);
        }
    }
    
    public List<Integer> leaves3() {
		return linkedListNodeToList(leaves3Node());
    }

	private LinkedListNode leaves3Node() {
	    	LinkedListNode head = new LinkedListNode();
	    	LinkedListNode dummy = head;
	    
	    Node cur = root;
	    Stack<Node> s = new Stack<Node>();
	    s.add(cur);
	    HashSet<Node> visited = new HashSet<Node>();
	    
	    while (!s.isEmpty()) {
	        cur = s.peek();
	        Node left = cur.left;
	        Node right = cur.right;
	        if (left != null && !visited.contains(left)) {
	            s.add(cur.left);
	        }
	        else if (right != null && !visited.contains(right)) {
	            s.add(cur.right);
	        }
	        else if (left == null && right == null) {
	            head.next = new LinkedListNode(cur.item);
	            head = head.next;
	            s.pop();
	            visited.add(cur);
	        }
	        else {
	            s.pop();
	            visited.add(cur);
	        }
	    }
	    
	    head = dummy.next;
	    dummy.next = null;
	    return head;
	}
    
	public List<Integer> exterior() {
	    List<Integer> res = new ArrayList<Integer>();
	    // calc left most
	    Node cur = root;
	    while (cur != null) {
	        res.add(cur.item);
	        cur = (cur.left != null) ? cur.left : cur.right;
	    }
	    // calc leaves
	    List<Integer> leaves = leaves4();
	    int size = res.size();
	    if (res.get(size - 1) == leaves.get(0)) {
	        res.remove(size - 1);
	    }
	    res.addAll(leaves);
	    // find right most leaves
	    cur = root.right;
	    List<Integer> rightMost = new ArrayList<Integer>();
	    while (cur != null) {
	        rightMost.add(cur.item);
	        cur = cur.right != null ? cur.right : cur.left;
	    }
	    size = rightMost.size();
	    if (rightMost.get(rightMost.size() - 1) == res.get(res.size() - 1)) {
	    		res.remove(res.size() - 1);
	    }
	    
	    Collections.reverse(rightMost);
	    res.addAll(rightMost);
	    return res;
	}

	public List<Integer> leaves4() {
	    List<Integer> res = new ArrayList<Integer>();
	    leaves4(root, res);
	    return res;
	}

	private void leaves4(Node root, List<Integer> res) {
	    if (root == null) {
	        return;
	    }
	    else if (root.left == null && root.right == null) {
	        res.add(root.item);
	    }
	    else {
	        leaves4(root.left, res);
	        leaves4(root.right, res);
	    }
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
}

