package com.amazon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EmptyStackException;

import javax.naming.LimitExceededException;
import javax.naming.SizeLimitExceededException;

import com.amazon.algorithms.BitManipulation2;
import com.amazon.algorithms.Sorting;
import com.amazon.datastructures.ArrayList;
import com.amazon.datastructures.LinkedList;
import com.amazon.datastructures.Queue;
import com.amazon.datastructures.Queue.EmptyQueueException;
import com.amazon.datastructures.QueueArrayBased;
import com.amazon.datastructures.QueueLinkedList;
import com.amazon.datastructures.QueuePointerBased;
import com.amazon.datastructures.Strings;
import com.amazon.datastructures.graphs.Graph;
import com.amazon.datastructures.stacks.Stack;
import com.amazon.datastructures.stacks.StackArray;
import com.amazon.datastructures.stacks.StackLinkedList;
import com.amazon.datastructures.stacks.StackProblems;
import com.amazon.datastructures.trees.BST;
import com.amazon.datastructures.trees.BSTArrayBased;
import com.amazon.datastructures.trees.BinaryTree;
import com.amazon.datastructures.trees.BinaryTree.Node;
import com.amazon.datastructures.trees.Tree;
import com.amazon.dp.MixStrategies;
import com.amazon.dp.Recursion;
import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class ApplicationTest {

	public static void main(String[] args) {
		
		runLinkedList();
//		runArrayList();
		runStrings();
		runStacks();
		runQueues();
		runSorts();
		runSortProblems();
		runBSTs();
		runBinaryTrees();
		
		runBits();
		
		runDynamicProgramming();
		runGraphs();
	}
	
	private static void runBits() {
		long x = Long.parseLong("01001001", 2);
		assert(BitManipulation2.swap(x, 0, 7) == Long.parseLong("11001000", 2));
		x = Long.parseLong("11001000", 2);
		assert(BitManipulation2.swap(x, 0, 7) == Long.parseLong("01001001", 2));
		System.out.println("Bits work!");
	}
	
	private static void runArrayList() {
		ArrayList list = new ArrayList(2);
		list.add(12);
		list.add(25);
		list.add(1, 3);
		list.add(18);
		
		// 12 -> 3 -> 18 -> 25
		assert(list.size() == 4);
		assert(list.get(0).equals(12));
		assert(list.get(3).equals(18));
		
		list.remove(1);
		assert(list.size() == 3);
		assert(list.get(1).equals(25));
		
		ArrayList listFromFile = ArrayList.importFromFile(
				"/Users/khaledabbas/Documents/workspace/Hacker Rank/src/com/amazon/datastructures/data.txt");
		assert(listFromFile.size() == 4);
		assert(listFromFile.get(1).equals(2));
		assert(listFromFile.get(3).equals(4));
		System.out.println("Success! ArrayList is working brilliantly :)");
	}
	
	private static void runLinkedList() {
		LinkedList list = new LinkedList();
		list.addFirst(3);
		list.addFirst(12);
		list.addLast(18);
		list.addLast(25);
		list.toString();
		
		// 12 -> 3 -> 18 -> 25
		assert (list.size() == 4);
		assert (list.get(0) == 12);
		assert (list.get(1) == 3);
		assert (list.get(2) == 18);
		assert (list.get(3) == 25);
		
		assert (list.traverseRecursive().trim().equals("12 3 18 25"));
		assert (list.traverseBackwardRecursive().trim().equals("25 18 3 12"));
		
		list.remove(1);
		// 12 -> 18 -> 25
		assert(list.size() == 3);
		assert(list.get(1) == 18);
		
		// 18 -> 25
		list.removeFirst();
		assert(list.size() == 2);
		assert(list.get(0) == 18);
		
		// 25
		list.removeLast();
		assert(list.size() == 1);
		assert(list.get(0) == 18);
		
		// find cyclic linked list
		list = new LinkedList();
		list.addFirst(3);
		list.addFirst(12);
		list.addLast(18);
		list.addLast(25);
		list.addLast(27);
		list.addLastWithCycle(30, 18);
//		assert(list.hasCycle1() == 30);
//		assert(list.hasCycle2() == 30);
		
		System.out.println("Success! LinkedList is working just nice :)");
	}

	private static void runStrings() {
		// unique string
		String s1 = "abcdefghz", s2 = "";
		assert(Strings.isUnique(s1));
		assert(Strings.isUnique2(s1));
		assert(Strings.isUnique3(s1));
		assert(Strings.isUnique4(s1));
		s1 = "abcdzhld";
		assert(!Strings.isUnique(s1));
		assert(!Strings.isUnique2(s1));
		assert(!Strings.isUnique3(s1));
		assert(!Strings.isUnique4(s1));
		
		// permutation string
		s1 = "abcd";
		s2 = "dbca";
		assert(Strings.isPermutation(s1, s2));
		assert(Strings.isPermutation2(s1, s2));
		s2 = "abcda";
		assert(!Strings.isPermutation(s1, s2));
		assert(!Strings.isPermutation2(s1, s2));
		
		s1 = "Mr John Smith    ";
		assert(Strings.URLify(s1, 13).equals("Mr%20John%20Smith"));
		
		s1 = "tactcoa";
		s2 = "Tact Coa";
		Character.getNumericValue('a');
		assert(Strings.isPalyndromePerm(s1));
		assert(!Strings.isPalyndromePerm(s2));
		assert(Strings.isPalyndromePerm2(s1));
		assert(Strings.isPalyndromePerm2(s2));
		
		// balanced braces
		assert(Strings.isBalancedBraces("{a{b}c}"));
		assert(Strings.isBalancedBraces("abc{defg{ijk}{l{mn}}op}qr"));
		assert(!Strings.isBalancedBraces("abc{def}}ghij{kl}m"));
		
		// palyndrome
		assert(Strings.isPalyndrome(""));
		assert(Strings.isPalyndrome("abccba"));
		assert(Strings.isPalyndrome("abc%cba"));
		assert(!Strings.isPalyndrome("abbc%bcba"));
		
		System.out.println("Success! Strings is working fine :P");
	}
	
	private static void runStacks() {
		Stack stack;
		stack = new StackArray(4);
		runStacks(stack);
		stack = new StackLinkedList();
		runStacks(stack);
		runStackProblems();
		
	}
	
	private static void runStacks(Stack stack) {
		assert(stack.isEmpty());
		assert(!stack.isFull());
		
		boolean thrown = false;
		try { stack.pop(); } 
		catch (EmptyStackException e) { thrown = true; }
		assert(thrown);
		
		thrown = false;
		try {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			stack.push(4);
		} catch (LimitExceededException e) {
			thrown = true;
		}
		assert(!thrown);
		if(stack instanceof StackArray)
			assert(stack.isFull());
		assert(!stack.isEmpty());
		assert(stack.pop() == 4);
		assert(stack.pop() == 3);
		assert(stack.pop() == 2);
		assert(stack.pop() == 1);
		assert(stack.isEmpty());
		
		// balanced braces
		// to use integer stack, just replace { with 6 and } with 9
		thrown = false;
		try {
			assert(Stack.isBalancedBraces("6a{b}c}"));
			assert(Stack.isBalancedBraces("abc{defg{ijk}{l{mn}}op}qr"));
			assert(!Stack.isBalancedBraces("abc{def}}ghij{kl}m"));
		} catch (Exception e) {
			thrown = true;
		}
		assert(!thrown);
		
		// palyndrome
		thrown = false;
		try {
			assert(Strings.isPalyndrome(""));
			assert(Stack.isPalyndrome("abccba"));
			assert(Stack.isPalyndrome("abc%cba"));
			assert(!Stack.isPalyndrome("abbc%bcba"));
		} catch (Exception e) {
			thrown = true;
		}
		assert(!thrown);
		
		// postfix calculator
		thrown = false;
		try {
			assert(Stack.postfix("234+*") == 14);
		} catch (SizeLimitExceededException e) { thrown = true; }
		assert(!thrown);
		
		// infix to postfix
		thrown = false;
		try {
			String postfix = Stack.infixToPostfix("a-(b+c*d)/e");
			assert(postfix.equals("abcd*+e/-"));
		} catch (Exception e) { thrown = true; e.printStackTrace(); }
		assert(!thrown);
		
		System.out.println("Success! Stacks are LIFO : )");
	}
	
	private static void runStackProblems() {
		assert(StackProblems.isWellFormed("[()[]{()()}]"));
		assert(StackProblems.isWellFormed("([]){()}"));
		assert(!StackProblems.isWellFormed("{)"));
		assert(!StackProblems.isWellFormed("()[]{()()"));
		System.out.println("Success! Stack problems solved!");
	}
	
	private static void runQueues() {
		Queue queue = new QueuePointerBased();
		runQueues(queue);
		queue = new QueueArrayBased(3);
		runQueues(queue);
		queue = new QueueLinkedList();
		runQueues(queue);
	}
	
	private static void runQueues(Queue queue) {
		assert(queue.isEmpty());
		boolean thrown = false;
		
		try {
			queue.dequeue();
		} catch (EmptyQueueException e) {
			thrown = true;
		}
		assert(thrown);
		
		thrown = false;
		try {
			queue.enqueue(1);
			assert(!queue.isEmpty());
			queue.enqueue(2);
			queue.enqueue(3);
			assert(queue.size() == 3);
		} catch (Exception e) {
			thrown = true;
		}
		assert(!thrown);
		
		thrown = false;
		if(queue instanceof QueueArrayBased) {
			try {
				queue.enqueue(4);
			} catch (SizeLimitExceededException e) {
				thrown = true;
			}
			assert(thrown);
		}
		
		assert(queue.front() == 1);
		assert(queue.dequeue() == 1);
		assert(queue.dequeue() == 2);
		assert(queue.dequeue() == 3);
		assert(queue.isEmpty());
		
		
		thrown = false;
		try {
			queue.dequeue();
		} catch (Exception e) {
			thrown = true;
		}
		assert(thrown);
		
		// evaluate expression
		assert(Queue.valueOf("123") == 123);
		
		System.out.println("Success! Queues are FIFO : )");
	}

	private static void runSorts() {
		int[] arr = {29, 10, 14, 37, 13};
		int[] sorted = {10, 13, 14, 29, 37};
		"".hashCode();
		// selection sort
		Sorting.selectionSort(arr);
		assert(Arrays.equals(arr, sorted));
		// bubble sort
		arr = new int[]{29, 10, 14, 37, 13};
		Sorting.bubbleSort(arr);
		assert(Arrays.equals(arr, sorted));
		// insertion sort
		arr = new int[]{29, 10, 14, 37, 13};
		Sorting.insertionSort(arr);
		assert(Arrays.equals(arr, sorted));
		// merge sort
		arr = new int[]{29, 10, 14, 37, 13};
		Sorting.mergeSort(arr);
		assert(Arrays.equals(arr, sorted));
		// quick sort
		arr = new int[]{29, 10, 14, 37, 13};
		Sorting.quickSort(arr);
		assert(Arrays.equals(arr, sorted));
		// radix sort
		String[] arr2 = new String[]{"ABC", "ABB", "XAB", "XYZ"};
		Sorting.radixSort(arr2);
		assert(Arrays.equals(arr2, new String[]{"ABB", "ABC", "XAB", "XYZ"}));
		
		arr = new int[] {0, 1, 2, 0, 2, 1, 1};
		Sorting.sortDutchNationalFlag(arr, 3);
//		for (int x:arr)
//			System.out.print(x + " ");
//		assert(Arrays.equals(arr, new int[]{0,0,1,2,2,1,1}));
		
		arr2 = new String[] {"Ian,Botham", "David,Gower", "Ian,Bell", "Ian,Chappell"};
		Sorting.removeDuplicateFirstNames(arr2);
		assert(Arrays.equals(arr2, new String[]{"David,Gower", "Ian,Botham", null, null}));
		
		arr = new int[] {57, 131, 493, 294, 221, 339, 418, 452, 442, 190};
		ArrayUtils.printArray(arr);
		Sorting.sortUpDownArray(arr, 4);
		ArrayUtils.printArray(arr);
		
		System.out.println("Success! Sorted out : )");
	}
	
	private static void runSortProblems() {
		// Sort Problems
		// merge B into A
		int[] A = new int[]{1, 3, 4, 0, 0, 0};
		int[] B = new int[]{2, 5, 6};
		Sorting.merge(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 3, 4, 5, 6}));
		A = new int[]{1, 5, 6, 0, 0, 0};
		B = new int[]{2, 2, 4};
		Sorting.merge(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 2, 4, 5, 6}));
		
		A = new int[]{1, 3, 4, 0, 0, 0};
		B = new int[]{2, 5, 6};
		Sorting.merge2(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 3, 4, 5, 6}));
		A = new int[]{1, 5, 6, 0, 0, 0};
		B = new int[]{2, 2, 4};
		Sorting.merge2(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 2, 4, 5, 6}));
		
		// sort anagrams
		String[] arr = {"ABC", "XYZ", "YZX", "CBA", "ADD"};
		Sorting.sortAnagrams(arr);
		assert(Arrays.equals(arr, new String[]{"ABC", "CBA", "ADD", "XYZ", "YZX"}));
		// sort anagrams
		arr = new String[]{"ABC", "XYZ", "YZX", "CBA", "ADD"};
		Sorting.sortAnagrams2(arr);
		assert(Arrays.equals(arr, new String[]{"ABC", "CBA", "ADD", "XYZ", "YZX"}));
	}

	private static void runBSTs() {
		Tree<Integer> bstArray = new BSTArrayBased(20);
		boolean thrown = false;
		try {
			bstArray.insert(60);
			bstArray.insert(20);
			bstArray.insert(70);
			bstArray.insert(10);
			bstArray.insert(40);
			bstArray.insert(30);
			bstArray.insert(50);
		} catch (SizeLimitExceededException e) {
			thrown = true;
		}
		
		assert (bstArray.inorder().equals(Arrays.asList(10, 20, 30, 40, 50, 60, 70 )));
		// @TODO @repeat floor and ceiling not working 
//		assert(bst.floor(35) == 30);
//		System.out.println(bst.floor(45));
//		assert(bst.floor(45) == 40);
		
		BST<Integer> bst = new BST<Integer>();
		bst.insert(60);
		bst.insert(20);
		bst.insert(70);
		bst.insert(10);
		bst.insert(40);
		bst.insert(30);
		bst.insert(50);
		assert(bst.inorder().equals(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		
		// test store and restore
		String path = "data.txt";
		File file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		deleteData(path);
		
		// restore original tree
		bst.storeTree(path);
		BST<Integer> bst2 = BST.restoreTree(path);
		assert(bst2.inorder().equals(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		deleteData(path);
		
		// restore balanced tree
		bst.storeTreeInorder(path);
		BST<Integer> bst3 = BST.restoreBalanced3(path, 7);
		assert(bst3.preorder().equals(Arrays.asList(40, 20, 10, 30, 60, 50, 70)));
		
		bst.delete(20);
		assert(bst.inorder().equals(Arrays.asList(10, 30, 40, 50, 60, 70)));
		bst.delete(60);
		assert(bst.inorder().equals(Arrays.asList(10, 30, 40, 50, 70)));
		System.out.println("Success! Trees are balanced : )");
		
	}
	
	private static void runBinaryTrees() {
		BinaryTree bt = new BinaryTree();
		Node root = bt.addRoot(314);
		Node n6l = root.addLeft(6);
		Node n6r = root.addRight(6);
		Node n5l = n6l.addLeft(5);
		Node n5r = n6r.addRight(5);
		Node n2l = n6l.addRight(2);
		Node n2r = n6r.addLeft(2);
		Node n3l = n2l.addRight(3);
		Node n3r = n2r.addLeft(3);
		assert(bt.isSymmetric());
		assert(bt.isSymmetric2());
		Node n1l = n2l.addLeft(1);
		assert(!bt.isSymmetric());
		assert(!bt.isSymmetric2());
		
		bt = new BinaryTree();
		root = bt.addRoot(1);
		Node B = root.addLeft(0);
		Node C = B.addLeft(0);
		Node D = C.addLeft(0);
		Node E = C.addLeft(1);
		Node F = B.addRight(1);
		Node G = F.addRight(1);
		Node H = G.addLeft(0);
		Node I = root.addRight(1);
		Node J = I.addLeft(0);
		Node O = I.addRight(0);
		Node K = J.addRight(0);
		Node L = K.addLeft(1);
		Node N = K.addRight(0);
		Node M = L.addRight(1);
		Node P = O.addRight(0);
		for (int x : bt.findSumOfPaths()) {
			System.out.print(Integer.toBinaryString(x) + " ");
		}
		System.out.println();
		
	}
	
	private static void deleteData(String path) {
		try {
			Files.delete(Paths.get(path));
		} 
		catch (NoSuchFileException e) {} 
		catch (IOException e) {}
	}
	
	private static void runDynamicProgramming() {
		
		// totals array
		int[] arr = {1, 2, 3, 4, 5, 6};
		Recursion.arrayOfSum(arr);
		assert(Arrays.equals(arr, new int[]{1, 3, 6, 10, 15, 21}));
		
		// power n
		assert(Recursion.power(2, 0) == 1);
		assert(Recursion.power(2, 3) == 8);
		
		// towers of hanoi
//		Recursion.towersOfHanoi('s', 'd', 'b', 3);
		
		// recursive bubble sort
		arr = new int[]{29, 10, 14, 37, 13};
		Recursion.bubbleSort(arr);
		assert(Arrays.equals(arr, new int[]{10, 13, 14, 29, 37}));
		
		// Fibonacci sequence
		assert(Recursion.fib(1) == 1 && Recursion.fib(2) == 1);
		assert(Recursion.fib(7) == 13);
		assert(Recursion.fib2(1) == 1 && Recursion.fib2(2) == 1);
		assert(Recursion.fib2(7) == 13);
		assert(Recursion.fib3(1) == 1 && Recursion.fib2(2) == 1);
		assert(Recursion.fib3(7) == 13);
		
		// fibonacci large number
//		System.out.println(Recursion.fib(80));
		assert(Recursion.fib2(80) == 23416728348467685L);
		assert(Recursion.fib3(80) == 23416728348467685L);
		
		// cheapest cost
		Recursion.costs = new int[][]{ 
			{ 0, 5, 15, 20 }, 
			{ -1, 0, 5, 15 }, 
			{ -1, -1, 0, 8 }, 
			{ -1, -1, -1, 0} 
		};
		assert(Recursion.cheapest(0, 3) == 18);
		assert(Recursion.cheapest(1, 3) == 13);
		assert(Recursion.cheapest2(0, 3) == 18);
		assert(Recursion.cheapest2(1, 3) == 13);
		assert(Recursion.cheapest3(0, 3) == 18);
		assert(Recursion.cheapest3(1, 3) == 13);
		assert(Recursion.cheapest4(4) == 18);
		
		Recursion.costs[1][3] = 10;
		assert(Recursion.cheapest(0, 3) == 15);
		assert(Recursion.cheapest2(0, 3) == 15);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 3) == 15);
		assert(Recursion.cheapest4(4) == 15);

		Recursion.costs[0][2] = 2;
		assert(Recursion.cheapest(0, 3) == 10);
		assert(Recursion.cheapest2(0, 3) == 10);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 3) == 10);
		assert(Recursion.cheapest4(4) == 10);
		
		Recursion.costs = new int[][]{ 
			{ 0, 10, 75, 94 }, 
			{ -1, 0, 35, 50 }, 
			{ -1, -1, 0, 80 }, 
			{ -1, -1, -1, 0 } 
		};
		assert(Recursion.cheapest(0, 2) == 45);
		assert(Recursion.cheapest2(0, 2) == 45);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 2) == 45);
		
		Recursion.costs = new int[][]{ 
			{ 0, 5, 15, 18, 30}, 
			{ -1, 0, 5, 8, 15}, 
			{ -1, -1, 0, 5, 10}, 
			{ -1, -1, -1, 0, 5},
			{ -1, -1, -1,-1, 0},
		};
		/**
	    0   1   2   3   4
	    ----5
	    -------15
	    -----------18
	    ---------------30
	        ----5
	        -------8*
	        -----------15
	            ----5
	            -------10
	                ----5
		 */
		assert(Recursion.cheapest(0, 4) == 18);
		assert(Recursion.cheapest2(0, 4) == 18);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 4) == 18);
		assert(Recursion.cheapest4(5) == 18);
		
		/**
	    0   1   2   3   5
	    ----5
	    -------15
	    -----------18
	    ---------------30
	        ----5
	        -------8
	        -----------15
	            ----5
	            -------1*
	                ----5
		 */
		
		Recursion.costs[2][4] = 1;
		assert(Recursion.cheapest(0, 4) == 11);
		assert(Recursion.cheapest2(0, 4) == 11);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 4) == 11);
		assert(Recursion.cheapest4(5) == 11);
		
		/**
	    0   1   2   3   5
	    ----2
	    -------15
	    -----------18
	    ---------------30
	        ----2
	        -------8
	        -----------15
	            ----2
	            -------1*
	                ----2
		 */
		Recursion.costs[0][1] = 2; 
		Recursion.costs[1][2] = 2; 
		Recursion.costs[2][3] = 2; 
		Recursion.costs[3][4] = 2; 
		assert(Recursion.cheapest(0, 4) == 5);
		assert(Recursion.cheapest2(0, 4) == 5);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 4) == 5);
		assert(Recursion.cheapest4(5) == 5);
		
		Recursion.costs[2][4] = 10;
		assert(Recursion.cheapest(0, 4) == 8);
		assert(Recursion.cheapest2(0, 4) == 8);
		Recursion.reviveMemo();
		assert(Recursion.cheapest3(0, 4) == 8);
		assert(Recursion.cheapest4(5) == 8);
		
		// max substring with anagram halves
		assert(Recursion.maxSubStringWithAnagramHalves("142124") == 6);
		assert(Recursion.maxSubStringWithAnagramHalves("142241") == 6);
		assert(Recursion.maxSubStringWithAnagramHalves("142243") == 4);
		
		// max substring with equal sums in first and second halves
		assert(Recursion.maxSumStrLength("142124") == 6);
		assert(Recursion.maxSumStrLength("142241") == 6);
		assert(Recursion.maxSumStrLength("142243") == 4);
		// optimized with dynamic programming
		assert(Recursion.maxSumStrLengthDP("142124") == 6);
		assert(Recursion.maxSumStrLengthDP("142241") == 6);
		assert(Recursion.maxSumStrLengthDP("142243") == 4);
		
		// combi
		assert(Recursion.combiDP(5, 4) == 5);
		assert(Recursion.combiRecursion(5, 4) == 5);
		
		// shortest path
		int[][] cost = {{1, 3, 5, 8},{4, 2, 1, 7},{4, 3, 2, 3}};
		assert(MixStrategies.shortestPath1(cost, 3, 2) == 12);
		assert(MixStrategies.shortestPath2(cost, 3, 2) == 12);
		assert(MixStrategies.shortestPath3(cost, 3, 2) == 12);
		assert(MixStrategies.shortestPath4(cost, 3, 2) == 12);
		assert(MixStrategies.shortestPath5(cost, 3, 2) == 7);
		
		// empty pot combi
		assert(MixStrategies.combiTilesDistinct(1) == 1);
		assert(MixStrategies.combiTilesDistinct(2) == 4);
		assert(MixStrategies.combiTilesDistinct(3) == 28);
		
		// 1 2 3 4 5  6
		// 1 2 3 5 8 13
		assert(MixStrategies.combiTiles1(1) == 1);
		assert(MixStrategies.combiTiles1(2) == 2);
		assert(MixStrategies.combiTiles1(6) == 13);
		
		assert(MixStrategies.combiTiles2(1) == 1);
		assert(MixStrategies.combiTiles2(2) == 2);
		assert(MixStrategies.combiTiles2(6) == 13);
		
		assert(MixStrategies.combiTiles3(1) == 1);
		assert(MixStrategies.combiTiles3(2) == 2);
		assert(MixStrategies.combiTiles3(6) == 13);
		
		assert(MixStrategies.combiTripleTiles(2) == 3);
		assert(MixStrategies.combiTripleTiles(4) == 7);
		assert(MixStrategies.combiTripleTiles(6) == 15);
		assert(MixStrategies.combiTripleTiles(8) == 31);
		
		assert(MixStrategies.combiTripleTilesDP(2) == 3);
		assert(MixStrategies.combiTripleTilesDP(4) == 7);
		assert(MixStrategies.combiTripleTilesDP(6) == 15);
		assert(MixStrategies.combiTripleTilesDP(8) == 31);
		
		System.out.println("Success! Recursion breaks nicely ; )");
	}
	
	private static void runGraphs() {
	    Graph g = new Graph(9);
	    // 0,1,2,3,4,5,6,7,8
	    // a b c d e f g h i
	    g.addEdge(0,1);
	    g.addEdge(0,5);
	    g.addEdge(0,8);
	    g.addEdge(1,2);
	    g.addEdge(1,4);
	    g.addEdge(4,6);
	    g.addEdge(5,6);
	    g.addEdge(2,3);
	    g.addEdge(2,4);
	    g.addEdge(3,6);
	    g.addEdge(3,7);
	    
//	    System.out.println(g);
//	    System.out.println("recursive dfs");
//	    g.dfs();
//	    System.out.println("iterative dfs");
//	    g.dfs2();
	}
	
	@SuppressWarnings("unused")
	
	private void reference() {
		// === READ/WRITE TO FILES
		new BST<Integer>().storeTree("");
		BST.restoreTree("");
	}
	
}























