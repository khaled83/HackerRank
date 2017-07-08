package com.amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.naming.LimitExceededException;
import javax.naming.SizeLimitExceededException;

import com.amazon.algorithms.BitManipulation2;
import com.amazon.algorithms.Sorting;
import com.amazon.datastructures.ArrayList;
import com.amazon.datastructures.LinkedList;
import com.amazon.algorithms.BitManipulation;
import com.amazon.algorithms.Searching;
import com.amazon.algorithms.Searching.Listy;
import com.amazon.algorithms.Sorting;
import com.amazon.datastructures.ArrayList;
import com.amazon.datastructures.LinkedList;
import com.amazon.datastructures.Strings;
import com.amazon.datastructures.graphs.GraphUndirected;
import com.amazon.datastructures.graphs.Graph;
import com.amazon.datastructures.stacks.Stack;
import com.amazon.datastructures.stacks.StackArray;
import com.amazon.datastructures.stacks.StackLinkedList;
import com.amazon.datastructures.stacks.StackProblems;
import com.amazon.datastructures.graphs.GraphDirected;
import com.amazon.datastructures.graphs.GraphWeighted;
import com.amazon.datastructures.graphs.problems.Color;
import com.amazon.datastructures.graphs.problems.GraphBlackenizeEnclosed;
import com.amazon.datastructures.graphs.problems.GraphFlipBlackWhite;
import com.amazon.datastructures.hashtable.HashTable;
import com.amazon.datastructures.hashtable.Hashing;
import com.amazon.datastructures.heaps.Heap;
import com.amazon.datastructures.queues.Queue;
import com.amazon.datastructures.queues.QueueArrayBased;
import com.amazon.datastructures.queues.QueueLinkedList;
import com.amazon.datastructures.queues.QueuePointerBased;
import com.amazon.datastructures.queues.QueueWithStacks;
import com.amazon.datastructures.queues.Queue.EmptyQueueException;
import com.amazon.datastructures.graphs.GraphDirectedWeighted;
import com.amazon.datastructures.graphs.GraphDirectedExt;
import com.amazon.datastructures.stacks.SetOfStacks;
import com.amazon.datastructures.stacks.Stack;
import com.amazon.datastructures.stacks.StackWithArrays;
import com.amazon.datastructures.stacks.StackWithMax1;
import com.amazon.datastructures.stacks.StackWithMax2;
import com.amazon.datastructures.stacks.StackWithMin;
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
		runSearching();
		runSortProblems();
		runBSTs();
		runBinaryTrees();
		
		runBits();
		runHeaps();
		
		runDynamicProgramming();
		runGraphs();
		
		runHashing();
		
		runBits();
		
		new StringTokenizer("");
		new StringBuilder();
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
		
		// remove duplicates 1
		list.add(3, 3);
		list.addLast(18);
		list.removeDuplicates1();
		assert (list.size() == 4);
		assert (list.get(0) == 12);
		assert (list.get(1) == 3);
		assert (list.get(2) == 18);
		assert (list.get(3) == 25);
		// remove duplicates 2
		list.add(4, 3);
		list.addLast(18);
		list.removeDuplicates2();
		assert (list.size() == 4);
		assert (list.get(0) == 12);
		assert (list.get(1) == 3);
		assert (list.get(2) == 18);
		assert (list.get(3) == 25);
		
		assert(list.KthToLast(1) == 25);
		assert(list.KthToLast(2) == 18);
		assert(list.KthToLast(3) == 3);
		
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
		
		// merge sorted lists
		int[] arr1 = new int[] {2, 5, 7};
		int[] arr2 = new int[] {3, 11};
		assert(Arrays.equals(LinkedList.mergeSortedLists1(arr1, arr2), new int[] {2, 3, 5, 7, 11}));
		assert(Arrays.equals(LinkedList.mergeSortedLists2(arr1, arr2), new int[] {2, 3, 5, 7, 11}));
		
		// partition linked list to left and right around value x
		list = new LinkedList(new int[] {3, 5, 8, 5, 10, 2, 1});
		list.partition1(5);
		assert(list.toString().equals("3 2 1 5 8 5 10 "));
		list = new LinkedList(new int[] {3, 5, 8, 5, 10, 2, 1});
		list.partition2(5);
		assert(list.toString().equals("3 2 1 5 10 5 8 "));
				
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
		
		// sort anagrams
		String[] arr = new String[]{"abcd", "cinema", "cdab", "abc", "iceman"};
		Strings.sortAnagrams(arr);
		assert(Arrays.equals(arr, new String[]{"abc", "abcd", "cdab", "cinema", "iceman"}));
		
		// is permutation of palyndrome
		assert(Strings.isPermOfPalyndrome1("ABCCBA") == true);
		assert(Strings.isPermOfPalyndrome1("ABCxCBA") == true);
		assert(Strings.isPermOfPalyndrome1("ABCxyCBA") == false);
		assert(Strings.isPermOfPalyndrome2("ABCCBA") == true);
		assert(Strings.isPermOfPalyndrome2("ABCxCBA") == true);
		assert(Strings.isPermOfPalyndrome2("ABCxyCBA") == false);
		boolean thrown = false;
		try {
			Strings.isPermOfPalyndrome3("ABCCBA");
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assert(thrown);
		assert(Strings.isPermOfPalyndrome3("abccba") == true);
		assert(Strings.isPermOfPalyndrome3("abcxcba") == true);
		assert(Strings.isPermOfPalyndrome3("abcxycba") == false);
		
		// convert int to string and vice versa
		assert(Strings.stringtoInt("304") == 304);
		assert(Strings.stringtoInt("-304") == -304);
		assert(Strings.intToString(304).equals("304"));
		assert(Strings.intToString(-304).equals("-304"));
		
		// is it possible to perform at most one edit, add, replace char for s2 to equal s1?
		assert(Strings.equalWithOneCharEditAscii("pale", "ple"));
		assert(Strings.equalWithOneCharEditAscii("pale", "pales"));
		assert(Strings.equalWithOneCharEditAscii("pale", "bale"));
		assert(!Strings.equalWithOneCharEditAscii("pale", "bae"));
		assert(Strings.equalWithOneCharEdit("pale", "ple"));
		assert(Strings.equalWithOneCharEdit("pale", "pales"));
		assert(Strings.equalWithOneCharEdit("pale", "bale"));
		assert(!Strings.equalWithOneCharEdit("pale", "bae"));
		
		// compress string
		assert(Strings.compress("aabcccccaaa").equals("a2b1c5a3"));
		assert(Strings.compress("aabccaaa").equals("aabccaaa"));
		assert(Strings.compress("aabccaaa").equals("aabccaaa"));
		assert(Strings.compress("aabccaaaa").equals("a2b1c2a4"));
		assert(Strings.compress("aabccaabc").equals("aabccaabc"));
		
		System.out.println("Success! Strings are working fine :P");
	}
	
	private static void runStacks() {
		Stack stack;
		stack = new StackArray(4);
		runStacks(stack);
		stack = new StackLinkedList();
		runStacks(stack);
		runStackProblems();
		
		stack = new StackWithMin();
		runStacks(stack);
		stack = new SetOfStacks<Integer>();
		runStacks(stack);
		runStackWithMinMax();
		runStacksWithArrays();
		
		// static methods
		assert(Stack.RPN("34+2x1+") == 15);
		assert(Stack.RPN2("34+2x1+") == 15);
		assert(Stack.RPN("11+2x") == 4);
		assert(Stack.RPN2("11+2x") == 4);
		assert(Stack.RPN("88+4/4/") == 1);
		assert(Stack.RPN2("88+4/4/") == 1);
		
		System.out.println("Success! Stacks are LIFO : )");
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
	}
	
	private static void runStacksWithArrays() {
		StackWithArrays s = new StackWithArrays();
		boolean thrown = false;
		try {
			s.push(1, 11);
			s.push(1, 12);
			s.push(1, 13);
			assert(s.size(1) == 3);
			assert(s.peek(1) == 13);
			
			s.push(2, 21);
			s.push(2, 22);
			assert(s.size(2) == 2);
			assert(s.peek(2) == 22);
			
			s.push(3, 31);
			s.push(3, 32);
			assert(s.size(3) == 2);
			assert(s.peek(3) == 32);

			assert(s.pop(1) == 13);
			
			// empty 2
			assert(s.pop(2) == 22);
			assert(s.pop(2) == 21);
			assert(s.isEmpty(2));
			
			// fill 1
			s.push(1, 13);
			assert(s.peek(1) == 13);
			s.push(1, 14);
			assert(s.peek(1) == 14);
			s.push(1, 15);
			assert(s.peek(1) == 15);
			
			// fill 2
			s.push(2,21);
			assert(!s.isEmpty(2));
			s.push(2,22);
			s.push(2,23);
			assert(s.size(2) == 3);
			
			assert(s.pop(1) == 15);
			s.push(2, 24);
			assert(s.size(2) == 4);
		} catch (Exception e) {
			e.printStackTrace();
			thrown = true;
		}
		assert(thrown == false);
		
//		System.out.println(s.peek(1));
//		assert(s.peek(1) == 13);
//		assert(s.pop(1) == 13);
	}
	
	private static void runStackWithMinMax() {
		// min stack
		StackWithMin s = new StackWithMin();
		s.push(60);
		s.push(50);
		s.push(30);
		assert(s.min() == 30);
		s.push(40);
		assert(s.min() == 30);
		s.pop();
		assert(s.min() == 30);
		s.pop();
		assert(s.min() == 50);
		s.pop();
		assert(s.min() == 60);
		
		// max stack using inheritance
		StackWithMax1 s2 = new StackWithMax1();
		s2.push(30);
		s2.push(20);
		assert(s2.max() == 30);
		s2.push(40);
		assert(s2.max() == 40);
		s2.push(60);
		s2.push(60);
		assert(s2.max() == 60);
		s2.push(50);
		assert(s2.max() == 60);
		s2.peek();
		s2.peek();
		s2.peek();
		assert(s2.max() == 60);
		s2.pop();
		s2.pop();
		assert(s2.max() == 60);
		s2.pop();
		assert(s2.max() == 40);
		s2.pop();
		assert(s2.max() == 30);
		s2.pop();
		assert(s2.max() == 30);
		
		// max stack using composition
		StackWithMax2 s3 = new StackWithMax2();
		s3.push(30);
		s3.push(20);
		assert(s3.max() == 30);
		s3.push(40);
		assert(s3.max() == 40);
		s3.push(60);
		s3.push(60);
		assert(s3.max() == 60);
		s3.push(50);
		assert(s3.max() == 60);
		s3.peek();
		s3.peek();
		s3.peek();
		assert(s3.max() == 60);
		s3.pop();
		s3.pop();
		assert(s3.max() == 60);
		s3.pop();
		assert(s3.max() == 40);
		s3.pop();
		assert(s3.max() == 30);
		s3.pop();
		assert(s3.max() == 30);
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
		queue = new QueueWithStacks();
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
		// dutch flag problem
		arr = new int[]{6, 7, 5, 3, 6, 4, 5};
		Sorting.sortDutchFlag(arr, 2);
		assert(Arrays.equals(arr, new int[]{4, 3, 5, 5, 6, 6, 7}));
		
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
	
	private static void runSearching() {
		// magic indx is where index and value are the same
		int[] arr = new int[]{-40, 20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
		assert(Searching.magicIndxDist(arr, 11) == 7);
		arr = new int[]{-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		assert(Searching.magicIndx(arr, 11) == 2);
		
		// search rotated array
//		arr = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
//		System.out.println(Searching.searchRotatedArray(arr, 7));
//		assert(Searching.searchRotatedArray(arr, 7) == 9);
		
		// search in a sorted list without given size
		// case 1: normal
		Listy<Integer> list = new Listy<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		assert(Searching.sortedSearchNoSize(list, 7) == 6);
		// case 2: key before expected index 
		list = new Listy<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(7);
		assert(Searching.sortedSearchNoSize(list, 7) == 4);
		// case 3: key after expected index
		list = new Listy<Integer>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(6);
		list.add(7);
		assert(Searching.sortedSearchNoSize(list, 7) == 9);
	}
	
	private static void runSortProblems() {
		// Sort Problems
		// merge B into A
		int[] A = new int[]{1, 3, 4, 0, 0, 0};
		int[] B = new int[]{2, 5, 6};
		Sorting.merge1(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 3, 4, 5, 6}));
		A = new int[]{1, 5, 6, 0, 0, 0};
		B = new int[]{2, 2, 4};
		Sorting.merge1(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 2, 4, 5, 6}));
		
		A = new int[]{1, 3, 4, 0, 0, 0};
		B = new int[]{2, 5, 6};
		Sorting.merge2(A, B);
		assert(Arrays.equals(A, new int[]{1, 2, 3, 4, 5, 6}));
		A = new int[]{1, 5, 6, 0, 0, 0};
		B = new int[]{2, 2, 4};
		Sorting.merge2(A, B);
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
		
		System.out.println("Success! Sort problems are sorted out ;)");
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
		
		// all possible insertions of bst
		for (int[] arr : bst.combi()) {
//			System.out.print("[");
//			for (int x : arr) {
//				System.out.print(x + ",");
//			}
//			System.out.println("]");
		}
		
		// number of paths from any node to any other node that lead to designated sum
		assert(bst.numPathSums(90) == 3);
		assert(bst.numPathSums(70) == 2);
		assert(bst.numPathSums(30) == 2);
		assert(bst.numPathSums(170) == 1);
		assert(bst.numPathSums(1000) == 0);
		assert(bst.numPathSums(0) == 0);
		
		// collection of lists with one linked list for each node at each depth
		java.util.ArrayList<java.util.LinkedList<Integer>> lists = bst.rootLevelLinkedLists1();
		// second approach has a bug
//		java.util.ArrayList<java.util.LinkedList<Integer>> lists = bst.rootLevelLinkedLists2();
		for (java.util.LinkedList<Integer> list : lists) {
			StringBuilder sb = new StringBuilder();
			for (int item : list)
				sb.append(item + " => ");
			sb.append("");
			switch (list.peekFirst()) {
				case 60:
					assert(sb.toString().equals("60 => 20 => 10 => 40 => 30 => 50 => 70 => "));
					break;
				case 20:
					assert(sb.toString().equals("20 => 10 => 40 => 30 => 50 => "));
					break;
				case 40:
					assert(sb.toString().equals("40 => 30 => 50 => "));
					break;
				case 50:
					assert(sb.toString().equals("50 => "));
					break;
			}
		}
		
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
		
		// constructor to create balanced BST from a given array
		int[] arr = new int[]{30, 60, 40, 50, 10, 20, 70};
		assert(new BST(arr).preorder().equals(Arrays.asList(40, 20, 10, 30, 60, 50, 70)));
		
		// is balanced bst
		assert (!bst.isBalanced1());
		assert (!bst.isBalanced2());
		assert (!bst.isBalanced3());
		
		// check binary tree is balanced
		assert(bst.isBST());
		bst.insert(55);
		bst.replaceValue(55, 80);
		assert(!bst.isBST());
		bst.replaceValue(80, 55);
		bst.delete(55);
		
		bst.insert(80);
//		assert (bst.isBalanced1());
		assert (bst.isBalanced2());
		assert (bst.isBalanced3());
		bst.delete(80);
		
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
	
	private static void runHeaps() {
		Heap h = new Heap();
		boolean exception = false;
		try {
			h.add(60);
			h.add(50);
			h.add(30);
			assert(h.peek() == 60);
			h.add(10);
			h.add(40);
			h.add(20);
			h.add(70);
			assert(h.peek() == 70);
			assert(h.remove() == 70);
			assert(h.remove() == 60);
			assert(h.remove() == 50);
			assert(h.remove() == 40);
			assert(h.remove() == 30);
			assert(h.remove() == 20);
			assert(h.remove() == 10);
		} catch (Exception e) {
			exception = true;
		}
		assert(!exception);
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
		
		arr = new int[]{-2, -3,  4, -1, -2,  1,  5,  -3};
		assert(Arrays.equals(MixStrategies.maxSubArray1(arr), new int[]{4, -1, -2, 1, 5}));
		assert(Arrays.equals(MixStrategies.maxSubArray2(arr), new int[]{4, -1, -2, 1, 5}));
		assert(MixStrategies.maxSubArraySum(arr) == 7);
		
		assert(MixStrategies.combiClimbStairsRecursion(5) == 13);
		assert(MixStrategies.combiClimbStairsDP(5) == 13);
		
		// find robot path right+down
		/** - - -  - x -   - x - -
			- x -  - x -   - - - -
			- x -  - x -   - x - -    */
		int[][] arr2 = new int[][]{{1,1,1},{1,0,1},{1,0,1}};
		assert(MixStrategies.findPath(arr2, 3, 3).equals("(0,0) (0,1) (0,2) (1,2) (2,2) "));
		arr2 = new int[][]{{1,0,1},{1,0,1},{1,0,1}};
		assert(MixStrategies.findPath(arr2, 3, 3).equals("IMPOSSIBLE"));
		arr2 = new int[][]{{1,0,1,1},{1,1,1,1},{1,0,1,1}};
		assert(MixStrategies.findPath(arr2, 3, 4).equals("(0,0) (1,0) (1,1) (1,2) (1,3) (2,3) "));
		
		// return all primes up to number n
		assert(Arrays.equals(MixStrategies.primes(18).toArray(), new Integer[]{2, 3, 5, 7, 11, 13, 17}));
		
		System.out.println("Success! Recursion breaks nicely ; )");
	}
	
	private static void runGraphs() {
	    GraphUndirected g = new GraphUndirected(9);
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
	    // dfs
	    g.dfs();
	    assert(g.dfs.toString().equals("a b c d g e f h i "));
	    g.dfs2();
	    assert(g.dfs.toString().equals("a b c d g h e f i "));
	    g.dfs3();
	    assert(g.dfs.toString().equals("a b c d g e f h i "));
	    
	    // bfs
	    g.bfs2();
	    assert(g.bfs.toString().equals("a b f i c e d g h "));
	    g.bfs();
	    assert(g.bfs.toString().equals("a b f i c e g d h "));
	    
	    // directed graph
	    
	    // topological order
	    GraphDirected g2 = new GraphDirected(7);
	    g2.addEdge(0, 1);
	    g2.addEdge(0, 3);
	    g2.addEdge(6, 3);
	    g2.addEdge(1, 2);
	    g2.addEdge(1, 4);
	    g2.addEdge(3, 4);
	    g2.addEdge(4, 2);
	    g2.addEdge(4, 5);
	    java.util.ArrayList<Integer> topOrder = new java.util.ArrayList<Integer>(g2.V());
	    for (int v : g2.topOrder())
	    	topOrder.add(v);
	    for (int v = 0; v < g2.V(); v++) {
//	    	System.out.print(toChar(topOrder.get(v)) + "@" + v + " ->");
	    	for (int w : g2.adj(topOrder.get(v))) {
//	    		System.out.print(toChar(w) + "@" + topOrder.indexOf(w) + " ");
	    		assert(topOrder.indexOf(w) > v);
	    	}
//	    	System.out.println();
	    }
	    
	    // a b c d e f g h i
	    // 0 1 2 3 4 5 6 7 8
	    GraphWeighted g3 = new GraphWeighted(9);
	    g3.addEdge('a', 'b', 6);
	    g3.addEdge('a', 'i', 2);
	    g3.addEdge('a', 'f', 4);
	    g3.addEdge('b', 'c', 7);
	    g3.addEdge('b', 'e', 9);
	    g3.addEdge('c', 'd', 4);
	    g3.addEdge('c', 'e', 3);
	    g3.addEdge('d', 'g', 5);
	    g3.addEdge('d', 'h', 1);
	    g3.addEdge('g', 'e', 8);
	    g3.addEdge('g', 'f', 2);
	    assert(g3.E() == 11);
	    assert(g3.minSpanTree().V() == 9);
	    assert(g3.minSpanTree().E() == 8);
	    
	    GraphDirectedWeighted g4 = new GraphDirectedWeighted(5);
	    g4.addEdge(0,1,8);
	    g4.addEdge(0,3,9);
	    g4.addEdge(0,4,4);
	    g4.addEdge(1,2,1);
	    g4.addEdge(2,1,2);
	    g4.addEdge(2,3,3);
	    g4.addEdge(3,2,2);
	    g4.addEdge(3,4,7);
	    g4.addEdge(4,2,1);
	    assert(g4.shortest(0, 1) == 7);
	    
	    // dependancy projects (topological order)
	    char[] arr = new char[]{'a', 'b', 'c', 'd', 'e', 'f'};
	    HashMap<Character, Character> dep = new HashMap<Character, Character>();
	    dep.put('a', 'd');
	    dep.put('f', 'b');
	    dep.put('b', 'd');
	    dep.put('f', 'a');
	    dep.put('d', 'c');
	    arr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
	    dep = new HashMap<Character, Character>();
	    dep.put('a', 'e');
	    dep.put('b', 'a');
	    dep.put('b', 'e');
	    dep.put('c', 'a');
	    dep.put('d', 'g');
	    dep.put('f', 'a');
	    dep.put('f', 'b');
	    dep.put('f', 'c');
	    
	    /**
            0  1  2  3
		  0 1  1  0  0
		  1 0  1  1  1
		  2 1  1  0  0
		  3 0  1  1  1
	     */
	    // find path in maze
	    int[][] maze = new int[][] {{1,1,0,0},{0,1,1,1}, {1,1,0,0}, {0, 1, 1,1}};
	    assert(new GraphDirected(maze, 4, 4).findPath(0, 0, 3, 3).toString().equals("[0, 1, 5, 9, 13, 14, 15]"));
	    
	    // flip black / white
	    Color B = Color.BLACK;
	    Color W = Color.WHITE;
	    Color[][] matrix = new  Color[][] {
	    	{B, W, W, B},
	    	{B, W, W, B},
	    	{B, B, B, W},
	    	{B, W, B, W}
	    };
	    GraphFlipBlackWhite g5 = new GraphFlipBlackWhite(matrix, 4, 4);
	    g5.flip(0, 0);
	    assert(g5.getColor(0, 0) == W);
	    assert(g5.getColor(1, 0) == W);
	    assert(g5.getColor(2, 0) == W);
	    assert(g5.getColor(3, 0) == W);
	    assert(g5.getColor(2, 1) == W);
	    assert(g5.getColor(2, 2) == W);
	    assert(g5.getColor(3, 2) == W);
	    assert(g5.getColor(0, 3) == B);
	    assert(g5.getColor(1, 3) == B);
	    
	    matrix = new  Color[][] {
	    	{B, B, B, B},
	    	{W, B, W, B},
	    	{B, W, W, B},
	    	{B, B, B, B}
	    };
	    GraphBlackenizeEnclosed g6 = new GraphBlackenizeEnclosed(matrix, 4, 4);
	    g6.blackizeEnclosedRegions();
	    for (int row = 0; row < 4; row++) {
	    	for (int col = 0; col < 4; col++) {
	    		if (row == 1 && col == 0) {
	    			assert(g6.getColor(row, col) == W);
	    		} else {
	    			assert(g6.getColor(row, col) == B);
	    		}
	    	}
	    }
	    
	    matrix = new  Color[][] {
	    	{B, B, B, B},
	    	{W, W, B, B},
	    	{B, B, W, B},
	    	{B, B, B, B}
	    };
	    g6 = new GraphBlackenizeEnclosed(matrix, 4, 4);
	    g6.blackizeEnclosedRegions();
	    for (int row = 0; row < 4; row++) {
	    	for (int col = 0; col < 4; col++) {
	    		if ((row == 1 && col == 0) || (row == 1 && col == 1)) {
	    			assert(g6.getColor(row, col) == W);
	    		} else {
	    			assert(g6.getColor(row, col) == B);
	    		}
	    	}
	    }
	    
	    
	    System.out.println("Success! Graphs are curvy : )");
	    
	}
	
	private static void runBits() {
		// merge bit string into another
		int N = Integer.parseInt("1000000000", 2);
		int M = Integer.parseInt("10011", 2);
		int x = BitManipulation.merge(N, M, 2, 6);
		assert(Integer.toBinaryString(x).equals("1001001100"));
		
		// convert double to bit string
		assert(BitManipulation.doubleToBinary(0.625).equals("0.101"));
		
		// convert one 0 to 1 to produce the longest stream of 1s
		x = Integer.parseInt("11011101", 2);
		assert(BitManipulation.flipOneZeroToProduceLongestAdjacent1sStream1(x) == Integer.parseInt("11111101", 2));
		assert(BitManipulation.flipOneZeroToProduceLongestAdjacent1sStream2(x) == Integer.parseInt("11111101", 2));
		x = Integer.parseInt("11011101111", 2);
		assert(BitManipulation.flipOneZeroToProduceLongestAdjacent1sStream1(x) == Integer.parseInt("11011111111", 2));
		assert(BitManipulation.flipOneZeroToProduceLongestAdjacent1sStream2(x) == Integer.parseInt("11011111111", 2));
		
		// find the next min and max numbers that are larger than given positive integer with the same number of 1s
		x = Integer.parseInt("00110", 2);
		assert(Arrays.equals(BitManipulation.minAndMaxLarger(x), new int[]{ Integer.parseInt("01010", 2), Integer.parseInt("1100000000000000000000000000000", 2) }));
		x = Integer.parseInt("1000110000000000000000110000000", 2);
		assert(Arrays.equals(BitManipulation.minAndMaxLarger(x),
				new int[]{ Integer.parseInt("1001010000000000000000110000000", 2), 
						Integer.parseInt("1111100000000000000000000000000", 2) }));
		
		x = Integer.parseInt("1110011000000000000000110000011", 2);
//		for (int i = 30; i>=0; i--)
//			System.out.print(i % 10);
//		System.out.println();
//		System.out.println(Integer.toBinaryString(x));
//		for (int num : BitManipulation.minAndMaxLarger(x))
//			System.out.println(Integer.toBinaryString(num));
		assert(Arrays.equals(BitManipulation.minAndMaxLarger(x),
				new int[]{ Integer.parseInt("1110101000000000000000110000011", 2), 
						Integer.parseInt("1111111110000000000000000000000", 2) }));
		
		// multiply only using bit operators
		assert(BitManipulation.mul(3, 3) == 9);
		assert(BitManipulation.mul(3, 4) == 12);
		assert(BitManipulation.mul(4, 4) == 16);
		assert(BitManipulation.mul(13, 9) == 117);
		
		// divide only using plus, minus, and bit operators
		// fails
//		assert(BitManipulation.div(15, 3) == 5);
//		assert(BitManipulation.div(15, 4) == 3);
//		assert(BitManipulation.div(12, 4) == 3);
//		assert(BitManipulation.div(20, 4) == 5);
//		assert(BitManipulation.div(100, 3) == 33);
		
		long y = Long.parseLong("01001001", 2);
		assert(BitManipulation2.swap(y, 0, 7) == Long.parseLong("11001000", 2));
		y = Long.parseLong("11001000", 2);
		assert(BitManipulation2.swap(y, 0, 7) == Long.parseLong("01001001", 2));
		
		System.out.println("Success! with Bits & Bytes : D");
	}
	
	private static void runHashing() {
		HashTable<String, String> table = new HashTable<String, String>(3);
		table.put("ID01", "Khaled");
		table.put("ID02", "Radi");
		table.put("ID03", "Yusuf");
		assert(table.get("ID01").equals("Khaled"));
		table.put("ID03", "Malik");
		table.put("ID05", "Yazeed");
		assert(table.get("ID03").equals("Malik"));
		assert(table.get("ID05").equals("Yazeed"));
		assert(table.size() == 4);
		
		// find K most frequent strings in a list of strings
		String path = "/Users/khaledabbas/Documents/workspace/Hacker Rank/src/com/amazon/datastructures/hashtable/data.txt";
		java.util.ArrayList<String> list = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
			list = new java.util.ArrayList<String>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		java.util.List<String> freq = Hashing.mostFrequent(2, list);
		assert(freq.contains("select *"));
		assert(freq.contains("insert one"));
		assert(!freq.contains("update three"));
		
		freq = Hashing.mostFrequent(3, list);
		assert(freq.contains("select *"));
		assert(freq.contains("insert one"));
		assert(freq.contains("update three"));
		
		// distance between closest matching pair of words
		String[] arr = new String[] {"All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results"};
		assert(Hashing.closestMatch(arr) == 2);
		
		
		System.out.println("Success! Hashing is quick!");
	}
	
	private static char toChar(int v) {
        return (char)('a'+v);
    }
	
	@SuppressWarnings("unused")
	
	private void reference() {
		// === READ/WRITE TO FILES
		new BST<Integer>().storeTree("");
		BST.restoreTree("");
	}
	
}