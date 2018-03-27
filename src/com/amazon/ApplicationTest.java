package com.amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.naming.LimitExceededException;
import javax.naming.SizeLimitExceededException;

import com.amazon.algorithms.Sorting;
import com.amazon.algorithms.Sorting.Pair;
import com.amazon.algorithms.TwoPointers;
import com.amazon.datastructures.ArrayList;
import com.amazon.datastructures.LinkedList;
import com.amazon.algorithms.BitManipulation;
import com.amazon.algorithms.BitManipulation2;
import com.amazon.algorithms.Combinatorics;
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
import com.amazon.datastructures.graphs.problems.Dijstra;
import com.amazon.datastructures.graphs.problems.GraphBlackenizeEnclosed;
import com.amazon.datastructures.graphs.problems.GraphFlipBlackWhite;
import com.amazon.datastructures.graphs.problems.GraphFlipEnclosedWhites2;
import com.amazon.datastructures.graphs.problems.Maze;
import com.amazon.datastructures.graphs.problems.NumberOfIslands;
import com.amazon.datastructures.graphs.problems.Team;
import com.amazon.datastructures.graphs.problems.WiredConnections;
import com.amazon.datastructures.hashtable.HashTable;
import com.amazon.datastructures.hashtable.Hashing;
import com.amazon.datastructures.heaps.Heap;
import com.amazon.datastructures.heaps.MedianStreamReader;
import com.amazon.datastructures.heaps.Point3D;
import com.amazon.datastructures.queues.Queue;
import com.amazon.datastructures.queues.QueueArrayBased;
import com.amazon.datastructures.queues.QueueCircularArray;
import com.amazon.datastructures.queues.QueueLinkedList;
import com.amazon.datastructures.queues.QueuePointerBased;
import com.amazon.datastructures.queues.QueueWithMax;
import com.amazon.datastructures.queues.QueueWithStacks;
import com.amazon.datastructures.queues.QueueWithStacks2;
import com.amazon.datastructures.queues.Queue.EmptyQueueException;
import com.amazon.datastructures.graphs.GraphDirectedWeighted;
import com.amazon.datastructures.graphs.GraphDirectedExt;
import com.amazon.datastructures.stacks.SetOfStacks;
import com.amazon.datastructures.stacks.StackWithArrays;
import com.amazon.datastructures.stacks.StackWithMax1;
import com.amazon.datastructures.stacks.StackWithMax2;
import com.amazon.datastructures.stacks.StackWithMin;
import com.amazon.datastructures.trees.BST;
import com.amazon.datastructures.trees.BST2;
import com.amazon.datastructures.trees.BSTArrayBased;
import com.amazon.datastructures.trees.BSTBalanced;
import com.amazon.datastructures.trees.BinaryTree;
import com.amazon.datastructures.trees.BinaryTree.Node;
import com.amazon.datastructures.trees.Tree;
import com.amazon.dp.MixStrategies;
import com.amazon.dp.Recursion;
import com.amazon.dp.Recursion2;
import com.khaledabbas.datastructures.arrays.ArrayUtils;
import com.khaledabbas.datastructures.trees.Heap.Star;
import com.khaledabbas.recursion.TowersOfHanoi;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ApplicationTest {
	
	public static List<Integer> res() {
		return new java.util.LinkedList<Integer>();
	}

	public static void main(String[] args) {
//		ArrayUtils.printArray(repeatedNumber(new int[] {3, 1, 2, 5, 3}));
		
		runLinkedList();
		runArrayList();
		runArrays();
		runStrings();
		runTwoPointers();
		
		runStacks();
		runQueues();
		runSorts();
		runSearching();
		runSortProblems();
		runBSTs();
		runBinaryTrees();
		
		runBits();
		runMath();
		runHeaps();
		
		runRecursion();
		runDynamicProgramming();
		runCombinatorics();
		runGraphs();
		
		runHashing();
		
		runBits();
	}
	
	public static int[] repeatedNumber(final int[] arr) {
        
        long actual = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            actual += arr[i];
        }
        
        long expected = 0;
        int x = 1;
        
        while (x <= n) {
            expected += x;
            x++;
        }
        
        long delta = actual - expected;
        
        // squares
        actual = 0;
        for (int i = 0; i < n; i++) {
            actual += (int) Math.pow(arr[i], 2);
        }
        
        expected = 0;
        x = 1;
        while (x <= n) {
            expected += (int) Math.pow(x, 2);
            x++;
        }
        
        long deltaSqr = actual - expected;
        
        int B = (int) ((delta - deltaSqr) / (-2 * delta));
        int A = (int) (B - delta);
        
        int[] res = new int[2];
        res[0] = B;
        res[1] = A;
        
        return res;
        
    }
	
	private static void runMath() {
		assert(Math.pow(3, 7) == com.amazon.algorithms.Math.pow(3, 7));
		assert(com.amazon.algorithms.Math.excelColumnIntValue("AH") == 34);
		assert(com.amazon.algorithms.Math.excelColumnIntValue("XFD") == 16384);
		assert(Arrays.equals(com.amazon.algorithms.Math.mul(
				new int[] {1,2,0},
				new int[] {5,2}
 				), new int[] {0,6,2,4,0}));
		assert(Arrays.equals(com.amazon.algorithms.Math.mul(
				new int[] {1,9,3,7,0,7,7,2,1},
				new int[] {-7,6,1,8,3,8,2,5,7,2,8,7}
				), new int[] {-1,4,7,5,7,3,9,5,2,5,8,9,6,7,6,4,1,2,9,2,7}));
		
		// Elements of Programming Interviews: Problem 4.9
		// Approach 1: calc reverse int and compare
		assert(com.amazon.algorithms.Math.isPalyndrome(25652));
		assert(com.amazon.algorithms.Math.isPalyndrome(256652));
		assert(!com.amazon.algorithms.Math.isPalyndrome(253652));
		assert(!com.amazon.algorithms.Math.isPalyndrome(24352));
		// Approach 2: compare msn and lsn
		assert(com.amazon.algorithms.Math.isPalyndrome2(25652));
		assert(com.amazon.algorithms.Math.isPalyndrome2(256652));
		assert(!com.amazon.algorithms.Math.isPalyndrome2(253652));
		assert(!com.amazon.algorithms.Math.isPalyndrome2(24352));
		
		
		// Elements of Programming Interviews 4.10: Uniform Random Numbers
		int n = 5;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int i = 0; i < 100; i++) {
			int rand = com.amazon.algorithms.Math.rand(n);
			min = Math.min(min, rand);
			max = Math.max(max, rand);
			assert(rand >= 0 && rand <= n);
		}
		assert(min >= 0 && min <= n);
		assert(max >= 0 && max <= n);
		
		// Elements of Programming Interviews 4.11: Rectangle Intersection
		assert(new com.amazon.algorithms.Math.Rectangle(1, 2, 4, 3)
				.findOverlappingRectangle(new com.amazon.algorithms.Math.Rectangle(2, 1, 3, 4))
				.equals(new com.amazon.algorithms.Math.Rectangle(2, 2, 3, 3)));

		assert(new com.amazon.algorithms.Math.Rectangle(1, 1, 3, 4)
				.findOverlappingRectangle(new com.amazon.algorithms.Math.Rectangle(2, 2, 4, 3))
				.equals(new com.amazon.algorithms.Math.Rectangle(2, 2, 3, 3)));
		
		assert(new com.amazon.algorithms.Math.Rectangle(1, 1, 4, 4)
				.findOverlappingRectangle(new com.amazon.algorithms.Math.Rectangle(2, 2, 3, 3))
				.equals(new com.amazon.algorithms.Math.Rectangle(2, 2, 3, 3)));
		
		assert(new com.amazon.algorithms.Math.Rectangle(1, 1, 3, 4)
				.findOverlappingRectangle(new com.amazon.algorithms.Math.Rectangle(3, 3, 4, 3)) == null);
		
		System.out.println("Success! Math is logic!");
	}
	
	private static void runTwoPointers() {
		java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>(Arrays.asList(-1, 2, 1, -4));
//		System.out.println("===== TwoPointers=" + TwoPointers.sumOfThree(list, 1));
		list = new java.util.ArrayList<Integer>(Arrays.asList(-5, 1, 4, -7, 10, -7, 0, 7, 3, 0, -2, -5, -3, -6, 4, -7, -8, 0, 4, 9, 4, 1, -8, -6, -6, 0, -9, 5, 3, -9, -5, -9, 6, 3, 8, -10, 1, -2, 2, 1, -9, 2, -3, 9, 9, -10, 0, -9, -2, 7, 0, -4, -3, 1, 6, -3));
//		System.out.println("===== TwoPointers=" + TwoPointers.sumOfThree(list, -1));
//		assert();
	}
	
	private static void runArrays() {
		int[] arr = new int[] {6, 3, 7, 5, 4, 2};
		assert(com.amazon.datastructures.Arrays.maximumGap(arr) == 3);
		arr = new int[] {1, 10};
		assert(com.amazon.datastructures.Arrays.maximumGap(arr) == 1);
		arr = new int[] {1};
		assert(com.amazon.datastructures.Arrays.maximumGap(arr) == 0);
		arr = new int[] {100, 100, 100, 100, 100};
		assert(com.amazon.datastructures.Arrays.maximumGap(arr) == 4);
		arr = new int[] {-1, -1, 2};
//		assert(com.amazon.datastructures.Arrays.maximumGap(arr) == 2);
		
		arr = new int[] {1, 2, 9};
		assert(Arrays.equals(com.amazon.datastructures.Arrays.plusOne(arr), new int[] {1, 3, 0}));
		arr = new int[] {9, 9, 9};
		assert(Arrays.equals(com.amazon.datastructures.Arrays.plusOne(arr), new int[] {1, 0, 0, 0}));
		arr = new int[] {3, 3, 1, 0, 2, 0, 1};
		assert(com.amazon.datastructures.Arrays.isReachable(arr));
		arr = new int[] {3, 2, 0, 0, 2, 0, 1};
		assert(!com.amazon.datastructures.Arrays.isReachable(arr));
		arr = new int[] {2,3,5,5,7,11,11,11,13};
		assert(com.amazon.datastructures.Arrays.removeDuplicates(arr) == 6);
		ArrayUtils.printArray(arr);
		
		// Elements of Programming Interviews 5.5: Delete duplicates from a sorted array
		assert(Arrays.equals(arr, new int[] {2,3,5,7,11,13,11,11,13}));
		arr = new int[] {2,3,5,5,7,11,11,11,13};
		assert(com.amazon.datastructures.Arrays.removeDuplicates2(arr) == 6);
		assert(Arrays.equals(arr, new int[] {2,3,5,7,11,13,11,11,13}));
		
		assert(com.amazon.datastructures.Arrays.maxProfit(new int[] {310, 315, 275, 295, 260, 270, 290, 230, 255, 250}) == 30);
		
		arr = new int[] {6, 5, 4, 7, 3, 1, 2, 8, 9};
		com.amazon.datastructures.Arrays.alternate(arr);
		assert(Arrays.equals(arr, new int[] {1, 6, 2, 7, 3, 8, 4, 9, 5}));
		arr = new int[] {6, 5, 4, 7, 3, 1, 2, 8, 9};
		com.amazon.datastructures.Arrays.alternate2(arr);
		assert(Arrays.equals(arr, new int[] {5, 6, 4, 7, 1, 3, 2, 9, 8}));
		
		// Elements of Programming Interviews 5.6: Enumerate All Primes to n
		System.out.println();
		assert(Arrays.equals(com.amazon.datastructures.Arrays.primesLessThan(18).toArray(), new Integer[] {2, 3, 5, 7, 11, 13, 17}));
		
		// Elements of Programming Interviews 5.12: Sample Offline Data
//		arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
//		com.amazon.datastructures.Arrays.randomSubset(arr, 3);
//		ArrayUtils.printArray(arr);
		
		// Elements of Programming Interviews 5.11: Next Higher Permutation
		arr = new int[] {0, 1, 2};
		assert(Arrays.equals(com.amazon.datastructures.Arrays.nextHigherPerm(arr), new int[] {0, 2, 1}));
		arr = new int[] {1, 0, 3, 2};
//		assert(Arrays.equals(com.amazon.datastructures.Arrays.nextHigherPerm(arr), new int[] {1, 2, 0, 3}));
//		arr = new int[] {0, 1, 2, 3};
//		assert(Arrays.equals(com.amazon.datastructures.Arrays.nextHigherPerm(arr), new int[] {0, 1, 3, 2}));
//		arr = new int[] {1, 3, 4, 5, 2};
//		assert(Arrays.equals(com.amazon.datastructures.Arrays.nextHigherPerm(arr), new int[] {2, 3, 4, 5, 1}));
//		arr = new int[] {5, 4, 3, 2, 1};
//		assert(Arrays.equals(com.amazon.datastructures.Arrays.nextHigherPerm(arr), new int[] {}));
		
		assert(com.amazon.datastructures.Arrays.hasSum(new int[] {2, 3, 5, 12}, 8));
		assert(com.amazon.datastructures.Arrays.hasSum(new int[] {2, 3, 5, 12}, 5));
		assert(com.amazon.datastructures.Arrays.hasSum(new int[] {2, 3, 3, 5}, 8));
		assert(!com.amazon.datastructures.Arrays.hasSum(new int[] {2, 3, 5, 12}, 1));
		
		/**
0 1 1 0
1 1 1 1
1 1 1 1
1 0 1 1


0 0 0 0
0 0 1 0
0 0 1 0
0 0 0 0
		*/
		int[][] matrix = new int[][] {
			{0, 7, 2, 0},
			{3, 9, 4, 4},
			{2, 5, 7, 1},
			{9, 0, 3, 4}
		};
		int[][] zeroMatrix = new int[][] {
			{0, 0, 0, 0},
			{0, 0, 4, 0},
			{0, 0, 7, 0},
			{0, 0, 0, 0}
		};
		com.amazon.datastructures.Arrays.zeroMatrix(matrix);
		assert(Arrays.deepEquals(matrix, zeroMatrix));
		matrix = new int[][] {
			{0, 7, 2, 0},
			{3, 9, 4, 4},
			{2, 5, 7, 1},
			{9, 0, 3, 4}
		};
		com.amazon.datastructures.Arrays.zeroMatrix2(matrix);
		assert(Arrays.deepEquals(matrix, zeroMatrix));
		
		// Elements of Programming Interviews 5.15: Compute a Random Subset
		for (int i = 1; i <= 10; i++) {
//			ArrayUtils.printArray(com.amazon.datastructures.Arrays.randomNChooseK2(5, 3));
		}
		
		System.out.println("Success! Arrays are in bound and neat : )");
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
		
		list = new LinkedList(new int[] {3, 5, 8, 5, 10, 2, 1});
		list.removeKthFromLast2(2);
		assert (list.traverseRecursive().trim().equals("3 5 8 5 10 1"));
		list.removeKthFromLast(3);
		assert (list.traverseRecursive().trim().equals("3 5 8 10 1"));
		list.removeKthFromLast2(5);
		assert (list.traverseRecursive().trim().equals("5 8 10 1"));
		list.removeKthFromLast2(5);
		assert (list.traverseRecursive().trim().equals("5 8 10 1"));
		
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
		
		// remove duplicates from sorted linked list
		list = new LinkedList();
		list.addLast(2); list.addLast(2); list.addLast(3); list.addLast(5); list.addLast(7); list.addLast(11); list.addLast(11);
		assert(list.toString().equals("2 2 3 5 7 11 11 "));
		list.removeDuplicatesFromSortedList();
		assert(list.toString().equals("2 3 5 7 11 "));
		// second method resets next pointer instead of copying values
		list = new LinkedList();
		list.addLast(2); list.addLast(2); list.addLast(3); list.addLast(5); list.addLast(7); list.addLast(11); list.addLast(11);
		assert(list.toString().equals("2 2 3 5 7 11 11 "));
		list.removeDuplicatesFromSortedList2();
		assert(list.toString().equals("2 3 5 7 11 "));
		
		// cyclic shift linked list
		list = new LinkedList();
		list.addLast(2); list.addLast(3); list.addLast(5); list.addLast(3); list.addLast(2);
		list.shiftRightCyclic(3);
		assert(list.toString().equals("5 3 2 2 3 "));
		list.shiftRightBy(3);
		assert(list.toString().equals("2 3 5 3 2 "));
		list.shiftRightBy(5);
		assert(list.toString().equals("2 3 5 3 2 "));
		list.shiftRightBy(7);
		assert(list.toString().equals("5 3 2 2 3 "));
		
		list = new LinkedList();
		list.addLast(2); list.addLast(3);
		list.shiftRightCyclic(3);
		assert(list.toString().equals("3 2 "));
		
		// Elements of Programming Interviews 7.10: Implement Even-Odd Merge
		list = new LinkedList(new int[] {0,1,2,3,4,5});
		list.evenOddMege();
		assert(list.toString().trim().equals("0 2 4 1 3 5"));
		list = new LinkedList(new int[] {0,1,2,3,4});
		list.evenOddMege();
		assert(list.toString().trim().equals("0 2 4 1 3"));
		
		// 7.11: Test When a Singly LinkedList is Palyndrome
		list = new LinkedList(new int[] {1, 2, 3, 2, 1});
		assert(list.isPalyndrome());
		list = new LinkedList(new int[] {1, 2, 2, 1});
		assert(list.isPalyndrome());
		list = new LinkedList(new int[] {1, 4, 3, 2, 1});
		assert(!list.isPalyndrome());
		
		// 7.12: Implement list pivoting
		list = new LinkedList(new int[] {3, 2, 2, 11, 7, 5, 11});
		list.pivot(7);
		assert(list.toString().trim().equals("3 2 2 5 7 11 11"));
		list = new LinkedList(new int[] {3, 2, 2, 4, 7, 5, 4});
		list.pivot(7);
		assert(list.toString().trim().equals("3 2 2 4 5 4 7"));
		list = new LinkedList(new int[] {3, 2, 2, 4, 7, 5, 4});
		list.pivot(1);
		assert(list.toString().trim().equals("3 2 2 4 7 5 4"));
		
		// Elements of Programming Interviews 7.13: ADD LIST-BASED INTEGERS
		assert(LinkedList.sum(new LinkedList(new int[] {3, 1, 4}), new LinkedList(new int[] {7, 0, 9}))
				.toString().trim().equals("0 2 3 1"));
		assert(LinkedList.sum2(new LinkedList(new int[] {3, 1, 4}), new LinkedList(new int[] {7, 0, 9}))
				.toString().trim().equals("0 2 3 1"));
		assert(LinkedList.sum(new LinkedList(new int[] {3, 1, 4}), new LinkedList(new int[] {7, 0, 9, 1, 3}))
				.toString().trim().equals("0 2 3 2 3"));
		assert(LinkedList.sum2(new LinkedList(new int[] {3, 1, 4}), new LinkedList(new int[] {7, 0, 9, 1, 3}))
				.toString().trim().equals("0 2 3 2 3"));
		
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
		
		// Elements of Programming Interviews 6.9 Convert from Roman Number to Decimal
		assert(Strings.convertRomanToDecimal("XXXXXIIIIIIIII") == 59);
		assert(Strings.convertRomanToDecimal("LVIIII") == 59);
		assert(Strings.convertRomanToDecimal("LIX") == 59);
		try {
			Strings.convertRomanToDecimal("IXC");
			assert(false);
		} catch (IllegalArgumentException e) { assert(true); }
		try {
			Strings.convertRomanToDecimal("CDM");
			assert(false);
		} catch (IllegalArgumentException e) { assert(true); }
		
		// Elements of Programming Interviews 6.11: Write a String Sinusoidally
		assert(Strings.sinusoidally("Hello World!").equals("e lHloWrdlo!"));
		
		// Elements of Programming Interviews 6.13: Implement RUN-LENGTH ENCODING
		assert(Strings.encodeRLE("aaaabcccaa").equals("4a1b3c2a"));
		assert(Strings.decodeRLE("4a1b3c2a").equals("aaaabcccaa"));
		assert(Strings.encodeRLE("eeeffffee").equals("3e4f2e"));
		assert(Strings.decodeRLE("3e4f2e").equals("eeeffffee"));
		
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
		
		// Problem 8.6: Compute Building with a Sunset View
		Iterable<Integer> res = StackProblems.sunsetView(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
		List<Integer> list = new java.util.ArrayList<Integer>();
		res.forEach(list::add);
		assert(Arrays.equals(new Integer[] {9}, list.toArray()));
		res = StackProblems.sunsetView(new int[] {7, 8, 7, 1, 2, 4, 6, 3, 2, 5});
		list = new java.util.ArrayList<Integer>();
		res.forEach(list::add);
		assert(Arrays.equals(new Integer[] {8,7,6,5}, list.toArray()));
		res = StackProblems.sunsetView(new int[] {9,8,7,6,5,4,3,2,1});
		list = new java.util.ArrayList<Integer>();
		res.forEach(list::add);
		assert(Arrays.equals(new Integer[] {9,8,7,6,5,4,3,2,1}, list.toArray()));
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
		queue = new QueueCircularArray(2);
		runQueues(queue);
		queue = new QueueWithStacks2();
		runQueues(queue);
		queue = new QueueWithMax();
		runQueues(queue);
		runMaxQueue();
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
			e.printStackTrace();
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

	private static void runMaxQueue() {
		QueueWithMax q = new QueueWithMax(new int[] {3, 1, 3, 2, 0});
		assert(q.max() == 3);
		q.enqueue(1);
		assert(q.max() == 3);
		q.dequeue();
		q.dequeue();
		q.enqueue(2);
		assert(q.max() == 3);
		assert(q.front() == 3);
		q.enqueue(4);
		assert(q.max() == 4);
		q.dequeue();
		q.enqueue(4);
		assert(q.max() == 4);
		assert(q.front() == 2);
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
//		ArrayUtils.printArray(arr);
		Sorting.sortUpDownArray(arr, 4);
//		ArrayUtils.printArray(arr);
		
		// groups similar objects near each other
		Sorting.Student[] arr3 = new Sorting.Student[] {
				new Sorting.Student("A", 9),new Sorting.Student("B", 0), new Sorting.Student("C", 4),
				new Sorting.Student("D", 7),new Sorting.Student("E", 1), new Sorting.Student("F", 2),
				new Sorting.Student("G", 4),new Sorting.Student("H", 7), new Sorting.Student("I", 1)
		};
		ArrayUtils.printArray(arr3);
		Sorting.group(arr3);
		ArrayUtils.printArray(arr3);
		Sorting.Student last = new Sorting.Student("x", Integer.MIN_VALUE);
		HashSet<Integer> visited = new HashSet<Integer>();
		// verify similar students are sorted together
		boolean grouped = true;
		for (int i = 0; i < arr3.length; i++) {
			if (arr3[i].getAge() != last.getAge() && !visited.contains(arr3[i].getAge())) {
				last = arr3[i];
				visited.add(last.getAge());
				while (i < arr3.length && arr3[i].getAge() == last.getAge()) {
					i++;
				}
				if (i < arr.length) {
					i--;
				}
			}
			else {
				grouped = false;
				break;
			}
		}
		assert(grouped);
		
		// median in two sorted arrays
		int[] a = new int[] {1, 4};
		int[] b = new int[] {2, 3};
		System.out.println("MEDIAN=" + Sorting.median(a, b));
		
		java.util.Queue q = new java.util.LinkedList();
//		new java.util.ArrayList<Integer>().subli
		
		// Elements of Programming Interviews: Problem 13.5: Render a Calendar
		Pair[] pairs = new Pair[] {
				new Pair(1,5), new Pair(2,7), new Pair(4,5),
				new Pair(6,10), new Pair(8,9), new Pair(9,17),
				new Pair(11,13), new Pair(12,15), new Pair(14,15)
		};
		assert(Sorting.maxConcurrent(pairs) == 3);
		
		// Elements of Programming Interviews: Problem 13.7: Compute the Union of Intervals
		Sorting.Interval[] intervals = new Sorting.Interval[] {
				new Sorting.Interval(0,3), new Sorting.Interval(2,4), new Sorting.Interval(3,4),
				new Sorting.Interval(5,7), new Sorting.Interval(7,8), new Sorting.Interval(8,11),
				new Sorting.Interval(13,15), new Sorting.Interval(16,17), new Sorting.Interval(12,14),
				new Sorting.Interval(12,16), new Sorting.Interval(9,11), new Sorting.Interval(1,1) 
		};
		assert(Sorting.union(intervals).toString().trim().equals("[[0,4], [5,11], [12,17]]"));
		
		// Elements of Programming Interviews: Problem 13.9: Team Photo Day 1
		assert(Sorting.canTakeTeamPhoto(new int[] {1, 3, 5, 7}, new int[] {2, 4, 8, 10}));
		assert(!Sorting.canTakeTeamPhoto(new int[] {1, 3, 5, 7, 7}, new int[] {2, 4, 8, 4, 10}));
		assert(!Sorting.canTakeTeamPhoto(new int[] {1, 3, 5, 7, 7}, new int[] {2, 4, 6, 7, 10}));
		
		arr = new int[] {60, 20, 70, 10, 50, 30, 40};
		assert(Sorting.klargest(arr, 1) == 70);
		assert(Sorting.klargest(arr, 3) == 50);
		assert(Sorting.klargest(arr, 7) == 10);
		
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
		
		arr = new int[] {-33, -10, 2, 108, 108, 243, 285, 285, 285, 401};
		assert(Searching.bsFirstOccurence(arr, 100) == -1);
		assert(Searching.bsFirstOccurence(arr, 108) == 3);
		assert(Searching.bsFirstOccurence(arr, 285) == 6);
		
		int[][] arr2 = new int[][] {
			{-1, 2, 4, 4, 6},
			{1, 5, 5, 9, 21},
			{3, 6, 6, 9, 22},
			{3, 6, 8, 10, 24},
			{6, 8, 9, 12, 25},
			{8, 10, 12, 13, 40}
		};
		assert(!Searching.searchSorted(arr2, 7));
		assert(Searching.searchSorted(arr2, 8));
		
		// [3,9]
		// [0,1] [2,4] [6,8] [9,10] [11,15]
		assert(Arrays.equals(Searching.merge(
				new Searching.Interval[] {
						new Searching.Interval(0, 1), new Searching.Interval(2, 4), new Searching.Interval(6, 8),
						new Searching.Interval(9, 10), new Searching.Interval(11, 15)}, 
				new Searching.Interval(3, 9)), 
				new Searching.Interval[] {
						new Searching.Interval(0, 1), new Searching.Interval(2, 10), new Searching.Interval(11, 15)}));
		
		assert(Arrays.equals(Searching.merge(
				new Searching.Interval[] {
						new Searching.Interval(0, 1), new Searching.Interval(2, 4), new Searching.Interval(6, 8),
						new Searching.Interval(9, 10), new Searching.Interval(11, 15)}, 
				new Searching.Interval(-2, -1)), 
				new Searching.Interval[] {
						new Searching.Interval(-2, -1),
						new Searching.Interval(0, 1), new Searching.Interval(2, 4), new Searching.Interval(6, 8),
						new Searching.Interval(9, 10), new Searching.Interval(11, 15)}));
		
		assert(Arrays.equals(Searching.merge(
				new Searching.Interval[] {
						new Searching.Interval(0, 1), new Searching.Interval(2, 4), new Searching.Interval(6, 8),
						new Searching.Interval(9, 10), new Searching.Interval(11, 15)}, 
				new Searching.Interval(16, 20)), 
				new Searching.Interval[] {
						new Searching.Interval(0, 1), new Searching.Interval(2, 4), new Searching.Interval(6, 8),
						new Searching.Interval(9, 10), new Searching.Interval(11, 15), new Searching.Interval(16, 20)}));
		
		// Elements of Programming Interviews 11.7: min/max in less than 2n
		assert(Arrays.equals(Searching.minMax(new int[] {3, 2, 5, 1, 2, 4}), new int[] {1, 5}));
		
		// Elements of Programming Interviews 11.
		System.out.println(new Searching.IPReader().findMissingIp((Iterator<Integer>) new java.util.ArrayList<Integer>() {{
			add(6); add(7); add(8); add(4); add(2); add(1); add(3); add(0); 
		}}.iterator()));
		
		
		System.out.println("Success! Searched and found : )");
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
		
		// restore BST from preorder, postorder, and inorder sequence of data
		// preorder
		arr = new int[] {60, 20, 10, 40, 30, 50, 70};
		BST<Integer> bs4 = BST.createFromPreorderSeq(arr);
		assert(bs4.preorder().equals(Arrays.asList(60, 20, 10, 40, 30, 50, 70)));
		// postorder
		arr = new int[] {10, 30, 50, 40, 20, 70, 60};
		bs4 = BST.createFromPostOrderSeq(arr);
		assert(bs4.preorder().equals(Arrays.asList(60, 20, 10, 40, 30, 50, 70)));
		// inorder
		arr = new int[] {10, 20, 30, 40, 50, 60, 70};
		bs4 = BST.createFromInOrderSeq(arr);
		assert(bs4.preorder().equals(Arrays.asList(40, 20, 10, 30, 60, 50, 70)));
		
		
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
		
		// print BST nodes inorder without recursion
		assert(bst.printInorderIterative().equals("10 30 40 50 70 "));
		
		/**
		 		60
		 	  /    \
		 	20      70
		  /    \
		10     40
		     /    \
		   30     50 
		 * */
		
		BST<Integer> bst4 = new BST<Integer>();
		bst4.insert(60);
		bst4.insert(20);
		bst4.insert(70);
		bst4.insert(10);
		bst4.insert(40);
		bst4.insert(30);
		bst4.insert(50);
		// Elements Of Programming Interviews - Problem 14.4: COMPUTE THE LCA IN A BST
		// sphisticate it
		bst4.insert(15);
		assert(bst4.lca(15, 50) == 20);
		// sophisticate it on the right side of root
		bst4.insert(65);
		bst4.insert(62);
		bst4.insert(67);
		bst4.insert(68);
		assert(bst4.lca(62, 68) == 65);
		
		// Elements of Programming Interviews in Java - Problem 14.3
		BST<Integer> bst5 = new BST().buildFromPreorder(new Integer[] {60, 20, 10, 40, 30, 50, 70});
		assert(bst5.inorder().equals(Arrays.asList(10, 20, 30, 40, 50, 60, 70)));
		
		// Elements of Programming Interviews Chapter 9: Binary Search Trees
		BST2 bst6 = new BST2(new int[] {60, 20, 10, 40, 30, 50, 70});
		assert(bst6.inorder().trim().equals("10, 20, 30, 40, 50, 60, 70,"));
		assert(bst6.preorder().trim().equals("60, 20, 10, 40, 30, 50, 70,"));
		assert(bst6.findSuccessor(10) == 20);
		assert(bst6.findSuccessor(20) == 30);
		assert(bst6.findSuccessor(30) == 40);
		assert(bst6.findSuccessor(40) == 50);
		assert(bst6.findSuccessor(50) == 60);
		assert(bst6.findSuccessor(60) == 70);
		
		/**
		 30
		  
		 * */
		
				/**
				    60
			/   		        \
	       20 	     	     70
		/      \      	     /  \
	   10       40   	   65    75
		\     /    \      /   \     \
		15   30     50  62     67    80
					  	         \
					  	          68
		* */
		// Elements of Programming Interviews in Java - Problem 14.3
		bst6 = new BST2(new int[] {60, 20, 10, 40, 30, 50, 70, 15, 65, 62, 67, 68, 75, 80});
		assert(bst6.isTotallyOrderedAroundMiddle(60, 67, 65));
		assert(bst6.isTotallyOrderedAroundMiddle(70, 68, 65));
		assert(!bst6.isTotallyOrderedAroundMiddle(70, 80, 65));
		assert(!bst6.isTotallyOrderedAroundMiddle(65, 67, 65));
		// iterative interleaved
		assert(bst6.isTotallyOrderedAroundMiddle2(60, 67, 65));
		assert(bst6.isTotallyOrderedAroundMiddle2(70, 68, 65));
		assert(!bst6.isTotallyOrderedAroundMiddle2(70, 80, 65));
		assert(!bst6.isTotallyOrderedAroundMiddle2(65, 67, 65));
		
		// Elements of Programming Interviews in Java - Problem 14.12: The Range Lookup Problem
		List<Integer> res = bst6.range(45, 67);
		Collections.sort(res);
		assert(res.toString().trim().equals("[50, 60, 62, 65, 67]"));
		res = bst6.range2(45, 67);
		Collections.sort(res);
		assert(res.toString().trim().equals("[50, 60, 62, 65, 67]"));
		
		// balanced insert
		BSTBalanced bstBalanced = new BSTBalanced();
		bstBalanced.insert(10);
		bstBalanced.insert(20);
		bstBalanced.insert(30);
		bstBalanced.insert(40);
		bstBalanced.insert(50);
//		System.out.println(bstBalanced.preorderParentChild());
		bstBalanced.insert(60);
		bstBalanced.insert(70);
		bstBalanced.insert(80);
		System.out.println("BALANCED BST ====");
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
		/**
		  314
	   /		  \
	 6   	 	   6
   /  \		      /  \
 5     2		    2     5
	 /  \	  /
	1    3	 3
		 */
		assert(bt.inorder2().trim().equals("5 6 1 2 3 314 3 2 6 5"));
		// 9.11
		System.out.println(bt.inorder3());
//		assert(bt.inorder3().toString().trim().equals("[5, 6, 1, 2, 3, 314, 3, 2, 6, 5]"));
		// Elements of Programming Interviews 9.8: Implement Preorder Traversal without Recursion
		// Fails this use case
//		assert(bt.preorderIterative().trim().equals("314 6 5 2 1 3 6 2 3 5"));
		
		// find if there is a path from root to node that sums to given value
		assert(bt.findPathSum(322));
		assert(bt.findPathSum2(322));
		assert(bt.findPathSum(323));
		assert(bt.findPathSum2(323));
		assert(!bt.findPathSum(326));
		assert(!bt.findPathSum2(326));
		
		// Elements of Programming Interviews 8.7: Compute Binary Tree Nodes in Order of Increasing Depth
		assert(bt.depthOrderTraversal().toString().equals("[[314], [6, 6], [5, 2, 2, 5], [1, 3, 3]]"));
		
		// find nodes per each depth
//		System.out.println("NODES PER DEPTH");
//		System.out.println(bt.nodesPerDepth());
//		System.out.println(bt.nodesPerDepth2());
		
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
//			System.out.print(Integer.toBinaryString(x) + " ");
		}
		
		/**
			 60(7)
	       /     \
	      20(5)  70(1)
	     /    \
	    10(1)  40(3)
	          /    \
	         30(1) 50(1)
	         
	         60(7)
	       /     \
	      20(5)  70(1)
	     /    \
	    10(1)  40(3)
	          /    \
	         30(1) 50(1)
		*/
		bt = new BinaryTree();
		root = bt.addRoot(60);
		Node n2 = root.addRight(70);
		Node n1 = root.addLeft(20);
		Node n3 = n1.addLeft(10);
		Node n4 = n1.addRight(40);
		Node n5 = n4.addLeft(30);
		Node n6 = n4.addRight(50);
		bt.storeNumNodesSubTrees();
		assert(bt.kthNode(1) == 10);
		assert(bt.kthNode(2) == 20);
//		System.out.println(bt.kthNode(3));
//		assert(bt.kthNode(3) == 30);
		assert(bt.kthNode(4) == 40);
//		System.out.println(bt.kthNode(5));
//		assert(bt.kthNode(5) == 50);
		assert(bt.kthNode(6) == 60);
		assert(bt.kthNode(7) == 70);
		
		// Elements of Programming Interviews Chapter 9.11: Implement inorder traversal with O(1) space
		Node n8 = n2.addRight(80);
		assert(bt.inorder3().toString().trim().equals("[10, 20, 30, 40, 50, 60, 70, 80]"));
		
		// Elements of Programming Interviews Chapter 15.8: Generate Binary Trees
		List<BinaryTree> perms = BinaryTree.perumuateTrees(3);
		// works but can be done more efficiently 
//		System.out.println("PERM TREES: " + perms.size());
//		for (BinaryTree perm : perms) {
//			System.out.println(perm.preorderParentChild());
//		}
		
		// Elements of Programming Interviews 9.12: Reconstruct Binary Tree From Traversal Data
		/**
		  314
	   /		  \
	 16   	 	   6
   /  \		      /  \
 15   12		    2     5
	 /  \	  /
   11   13	 3
		 */
		bt = new BinaryTree(new int[] {314, 16, 15, 12, 11, 13, 6, 2, 3, 5}, new int[] {15, 16, 11, 12, 13, 314, 3, 2, 6, 5});
		assert(bt.preorderIterative().toString().trim().equals("314 16 15 12 11 13 6 2 3 5"));
		assert(bt.inorder2().toString().trim().equals("15 16 11 12 13 314 3 2 6 5"));
//		System.out.println(bt.preorderParentChild());
		// Elements of Programming Interviews 9.14: Form a Linked List from The Leaves of a Binary Tree
		assert(bt.leaves1().toString().trim().equals("[15, 11, 13, 3, 5]"));
//		assert(bt.leaves2().toString().trim().equals("[15, 11, 13, 3, 5]"));
		assert(bt.leaves3().toString().trim().equals("[15, 11, 13, 3, 5]"));
		
		// Elements of Programming Interviews 9.15: Compute The External of Binary Tree
		assert(bt.exterior().toString().trim().equals("[314, 16, 15, 11, 13, 3, 5, 6]"));
		
		// Elements of Programming Interviews 9.13: Reconstruct Binary Tree From Preorder Traversal with Markers
		bt = BinaryTree.buildFromPreorderWithMarker(new Integer[] {
			7, 1, 5, null, null, 4, 0, null, null, null, 2, null, 3, null, 6, 8, null, null, null 
		});
		System.out.println("PREORDER MARKER DATA:");
		System.out.println(bt.preorderParentChildChars());
		
		// 0 1 2 3 4 5 6 7 8 9
		// A B C D E F G H I J
		
		System.out.println("Success! Binary trees are dancing on two legs : D");
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
			// Elements of Programming Interviews 10.6: Compute the K Largest Elements in a Max Heap
			assert(h.kLargest(5).toString().trim().equals("[70, 60, 50, 40, 30]"));
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
		
		// heap problems
		// closest k stars
		// solution 1
		Point3D[] arr = new Point3D[] {
				new Point3D(10,100,10), new Point3D(103,10,10), new Point3D(105,10,10),
				new Point3D(10,70,10), new Point3D(60,10,10), new Point3D(10,50,10),
				new Point3D(30,10,10), new Point3D(10,10,10), new Point3D(5,10,5)
		};
		List<Point3D> res = com.khaledabbas.datastructures.trees.Heap.closestK(arr, 3);
		assert(res.size() == 3);
		assert(res.contains(new Point3D(5,10,5)));
		assert(res.contains(new Point3D(10,10,10)));
		assert(res.contains(new Point3D(30,10,10)));
		// solution 2
		Star[] arr2 = new Star[] {
				new Star(10,100,10), new Star(103,10,10), new Star(105,10,10),
				new Star(10,70,10), new Star(60,10,10), new Star(10,50,10),
				new Star(30,10,10), new Star(10,10,10), new Star(5,10,5)
		};
		Iterable<Star> itr = com.khaledabbas.datastructures.trees.Heap.closestK2(arr2, 3);
		List<Star> kClosest = new java.util.ArrayList<Star>();
		itr.forEach(kClosest::add);
		assert(kClosest.size() == 3);
		assert(kClosest.contains(new Star(5,10,5)));
		assert(kClosest.contains(new Star(10,10,10)));
		assert(kClosest.contains(new Star(30,10,10)));
		
		// Elements of Programming Interviews 10.5: Compute the Median of Online Data
		MedianStreamReader s = new MedianStreamReader();
		assert(s.read(1).get(0) == 1);
		assert(s.read(0).get(1) == 0.5);
		assert(s.read(3).get(2) == 1);
		assert(s.read(5).get(3) == 2);
		assert(s.read(2).get(4) == 2);
		assert(s.read(0).get(5) == 1.5);
		assert(s.read(1).get(6) == 1);
		
		// Elements of Programming Interviews 13.11: Compute a salary threshold
		assert(Heap.findSalaryCap(new java.util.ArrayList<Integer>() {{
			add(90); add(30); add(100); add(40); add(20);  
		}}, 210) == 60);
		
//		System.out.println(Heap.findSalaryCap2(new java.util.ArrayList<Integer>() {{
//			add(90); add(30); add(100); add(40); add(20);  
//		}}, 210));
		assert(Heap.findSalaryCap2(new java.util.ArrayList<Integer>() {{
			add(90); add(30); add(100); add(40); add(20);  
		}}, 210) == 60);
		
		System.out.println("Success! Thanks Heaps!! : )");
		
	}
	
	private static void runRecursion() {
		// solution is not correct
		List<Integer> res = Recursion2.grayCode(3);
//		for (int x : res) {
//			System.out.print(Integer.toBinaryString(x) + ", ");
//		}
//		System.out.println();
		// 000, 001, 011, 111, 101, 010, 110, 100, 
		
		// 000, 001, 011, 111, 101, 010, 110, 100
		
//		assert(Recursion2.numOfWaysToPlaceTiles(5) == 9);
//		assert(Recursion2.numOfWaysToPlaceTiles2(5) == 9);
//		System.out.println(Recursion2.numOfWaysToPlaceTiles3(5));
//		assert(Recursion2.numOfWaysToPlaceTiles3(5) == 9);
		
		System.out.println("Recursion circles gracefully!");
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
		
		// 15.7 Generate Palindromic Decompositions
		System.out.println("Palyndromic Decomp:");
		for (List<String> decomp : Recursion.palydromicDecomp("0204451881")) {
			for (String s : decomp) {
				System.out.print("'" + s + "' ");
			}
			System.out.println();
		}
		
		
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
		
		// number of ways through a matrix from upper left corner to bottom right corner
		assert(MixStrategies.numOfWaysThroughMatrix(3, 3) == 6);
		assert(MixStrategies.numOfWaysThroughMatrix(5, 5) == 70);
		
		// generate all powersets
		arr = new int[] {0,1,2};
		int[][] expected = new int[][] {
			{ },
			{ 0 },
			{ 1 },
			{ 0, 1 },
			{ 2 },
			{ 0, 2 },
			{ 1, 2 },
			{ 0, 1, 2 }
		};
		java.util.ArrayList<int[]> out = Recursion.powerset(arr);
		assert(out.size() == expected.length);
		for (int[] set : out) {
			assert(out.contains(set));
		}
		java.util.ArrayList<java.util.ArrayList<Integer>> out2 = Recursion.powerset2(arr);
		out = new java.util.ArrayList<int[]>();
		for (java.util.ArrayList<Integer> set : out2) {
			int[] setArr = new int[set.size()];
			int indx = 0;
			for (int x : set) {
				setArr[indx++] = x;
			}
			out.add(setArr);
		}
		assert(out.size() == expected.length);
		for (int[] set : out) {
			assert(out.contains(set));
		}
		
		// ======  Recursion PART 2
		// Dynamic Programming for Coding Interviews
		// Chapter 1
		arr = new int[] {1, 2, 3, 4, 5, 6};
		Recursion2.sumPrevious(arr);
		assert(Arrays.equals(arr, new int[] {1, 3, 6, 10, 15, 21}));
		assert(Recursion2.pow(3, 5) == 243);
		
//		Recursion.towersOfHanoi(4, 'S', 'D', 'E');
		arr = new int[] {60, 50, 30, 40, 20, 70};
		Recursion2.bubbleSortIterative(arr);
		assert(Arrays.equals(arr, new int[] {20, 30, 40, 50, 60, 70}));
		
		arr = new int[] {60, 50, 30, 40, 20, 70};
		Recursion2.bubbleSortRecursive(arr);
		assert(Arrays.equals(arr, new int[] {20, 30, 40, 50, 60, 70}));
		
		assert(Recursion2.fib(9) == 34);
		assert(Recursion2.fibIterative(9) == 34);
		assert(Recursion2.fibWithMemo(9) == 34);
		
		// Chapter 2
		// minimum trip cost one direciton train
		arr2 = new int[][] {
			{0, 10, 75, 94},
			{-1,  0, 35, 50},
			{-1, -1,  0, 80},
			{-1, -1, -1, 0}
		};
		assert(Recursion2.minCost(arr2) == 60);
		assert(Recursion2.minCostMemo(arr2, 0, 3) == 60);
		assert(Recursion2.minCostDP(arr2) == 60);
		
		// Chapter 6
		// find max substring with equally summed halves
//		assert(Recursion2.maxLengthWithEqualSumHalfs(new int[] {1, 4, 2, 1, 2, 4}) == 6);
		System.out.println(Recursion2.maxLengthWithEqualSumHalfs(new int[] {9, 4, 3, 0, 7, 2, 3}));
		assert(Recursion2.maxLengthWithEqualSumHalfs(new int[] {9, 4, 3, 0, 7, 2, 3}) == 4);
		
		// Elements of Programming Interviews 15.9: Implement a Soduko Solver
		// Unfinished
		int[][] sudoku = new int[][] {
			{5,3,0,0,7,0,0,0,0},
			{6,0,0,1,9,5,0,0,0},
			{0,9,8,0,0,0,0,6,0},
			{8,0,0,0,6,0,0,0,3},
			{4,0,0,8,0,3,0,0,1},
			{7,0,0,0,2,0,0,0,6},
			{0,6,0,0,0,0,2,8,0},
			{0,0,0,4,1,9,0,0,5},
			{0,0,0,0,8,0,0,7,9}
		};
//		System.out.println(ArrayUtils.printMatrix(sudoku));
//		Recursion2.solveSudoku(sudoku);
//		System.out.println(ArrayUtils.printMatrix(sudoku));
		
		// Chapter 7: Combination and Pascal triangle
		assert(Recursion2.combination(10, 8) == 45);
		assert(Recursion2.combination2(10, 8) == 45);
		
		// Chapter 8: min cost right left
		assert(Recursion2.minCostRightLeft(new int[][] {
			{1, 3, 5, 2},
			{4, 2, 1, 7},
			{4, 3, 2, 3}}) == 12);
		
		System.out.println("Success! DB breaks nicely ; )");
	}
	
	private static void runCombinatorics() {
		int[] arr = new int[] {2,3,5};
		for (int[] a : Combinatorics.perm(arr) ) {
//			ArrayUtils.printArray(a);
		}
		/**
{ 1 2 3 5 }
{ 2 1 3 5 }
{ 3 2 1 5 }
{ 5 2 3 1 }
{ 1 3 2 5 }
{ 3 1 2 5 }
{ 2 3 1 5 }
{ 5 3 2 1 }
{ 1 5 3 2 }
{ 5 1 3 2 }
{ 3 5 1 2 }
{ 2 5 3 1 }
{ 1 2 5 3 }
{ 2 1 5 3 }
{ 5 2 1 3 }
{ 3 2 5 1 }
{ 1 5 2 3 }
{ 5 1 2 3 }
{ 2 5 1 3 }
{ 3 5 2 1 }
{ 1 3 5 2 }
{ 3 1 5 2 }
{ 5 3 1 2 }
{ 2 3 5 1 }
		 
		 
		 * */
//		System.out.println();
		arr = new int[] {1,2,3,5};
		for (int[] a : Combinatorics.perm(arr) ) {
//			ArrayUtils.printArray(a);
		}
		
		System.out.println("Success! Combinatorics play well ; )");
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
 		// first implementation 
	    java.util.ArrayList<Integer> topOrder = new java.util.ArrayList<Integer>(g2.V());
		for (int v : g2.topOrder())
			topOrder.add(v);
		for (int v = 0; v < g2.V(); v++) {
			// System.out.print(toChar(topOrder.get(v)) + "@" + v + " ->");
			for (int w : g2.adj(topOrder.get(v))) {
				// System.out.print(toChar(w) + "@" + topOrder.indexOf(w) + " ");
				assert (topOrder.indexOf(w) > v);
			}
			// System.out.println();
		}
		// just another implementation
	    topOrder = new java.util.ArrayList<Integer>(g2.V());
 		for (int v : g2.topOrder2())
 			topOrder.add(v);
 		System.out.println("topOrder=" + topOrder);
 		for (int v = 0; v < g2.V(); v++) {
 			// System.out.print(toChar(topOrder.get(v)) + "@" + v + " ->");
 			for (int w : g2.adj(topOrder.get(v))) {
 				// System.out.print(toChar(w) + "@" + topOrder.indexOf(w) + " ");
 				assert (topOrder.indexOf(w) > v);
 			}
 			// System.out.println();
 		}
	    
	    // detect deadlock
	    // 0 1 2 3 4 5 6
	    // a b c d e f g
	    g2 = new GraphDirected(7);
	    g2.addEdge(0,1);
	    g2.addEdge(0,3);
	    g2.addEdge(6,3);
	    g2.addEdge(1,2);
	    g2.addEdge(1,4);
	    g2.addEdge(3,4);
	    g2.addEdge(4,2);
	    g2.addEdge(4,5);
	    assert(!g2.hasDeadlock());
	    g2.addEdge(5,0);
	    assert(g2.hasDeadlock());
	    
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
	    
	    // Dijstra shortest path algorithm
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
	    assert(g4.shortestPath(0, 1) == 7);
	    assert(g4.shortestPath2(0, 1) == 7);
	    
//	    int[][] weight = new int[][] {
//		     {0, 8, Integer.MAX_VALUE, 9, 4},
//		     {Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE},
//		     {Integer.MAX_VALUE, 2, 0, 3, Integer.MAX_VALUE},
//		     {Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 0, 7},
//	         {4, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0}
//	    };
//	    ArrayUtils.printArray(Dijstra.shortestPath(weight, 0));
//	    assert(Arrays.equals(Dijstra.shortestPath(weight, 0), new int[] {0, 7, 5, 8, 4}));
	    
	    int[][] cost = new int[][] {
	    		{0, 8, 0, 9, 4},
	    		{0, 0, 1, 0, 0},
	    		{0, 2, 0, 3, 0},
	    		{0, 0, 2, 0, 7},
	    		{0, 0, 1, 0, 0}
	    	};
	    assert(Arrays.equals(Dijstra.shortestPath(cost, 0), new int[] {0, 7, 5, 8, 4}));
	    System.out.println("DIJSTRA ALGORITHM LONGEST PATH:");
	    ArrayUtils.printArray(Dijstra.longestPath(cost, 0));
	    
//	    assert(Arrays.equals(Dijstra.longestPath(cost, 0), new int[] {0, 13, 11, 12, 18}));
	    
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
	    
	    // search maze in black/white
	    Color B = Color.BLACK;
	    Color W = Color.WHITE;
	    Color[][] matrix = new  Color[][] {
		    	{B, B, W, W},
		    	{B, W, W, B},
		    	{B, W, B, W},
		    	{W, W, B, W}
	    };
	    Maze gMaze = new Maze(matrix);
	    assert(gMaze.findPath(3, 0, 0, 3).toString().equals("[12, 13, 9, 5, 6, 2, 3]"));
	    matrix = new  Color[][] {
	    	{B, B, B, W},
	    	{B, W, W, B},
	    	{B, W, B, W},
	    	{W, W, B, W}
	    };
	    gMaze = new Maze(matrix);
	    assert(gMaze.findPath(3, 0, 0, 3) == null);
	    
	    // flip black / white
	    matrix = new  Color[][] {
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
	    
	    GraphFlipEnclosedWhites2 g7 = new GraphFlipEnclosedWhites2(matrix, 4, 4);
	    g7.flipEnclosedWhites();
	    for (int row = 0; row < 4; row++) {
	    	for (int col = 0; col < 4; col++) {
	    		if (row == 1 && col == 0) {
	    			assert(g7.getColor(row, col) == W);
	    		} else {
	    			assert(g7.getColor(row, col) == B);
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
	    
	    g7 = new GraphFlipEnclosedWhites2(matrix, 4, 4);
	    g7.flipEnclosedWhites();
	    for (int row = 0; row < 4; row++) {
	    	for (int col = 0; col < 4; col++) {
	    		if ((row == 1 && col == 0) || (row == 1 && col == 1)) {
	    			assert(g7.getColor(row, col) == W);
	    		} else {
	    			assert(g7.getColor(row, col) == B);
	    		}
	    	}
	    }
	    
	    
	    // 19.6 making wired connections
	    WiredConnections g8 = new WiredConnections(5);
	    g8.addEdge(0, 1);
	    g8.addEdge(0, 2);
	    g8.addEdge(3, 1);
	    g8.addEdge(3, 2);
	    assert(g8.isPossbibleWireConnection());

	    g8.addEdge(4, 1);
	    g8.addEdge(4, 3);
	    assert(!g8.isPossbibleWireConnection());
	    
//	    aaaxxxaaa
//	    xxaxxxaxx
//	    aaxxaaaxx
	    // find number of connected islands
	    matrix = new  Color[][] {
	    	{B, B, B, W, W, W, B, B, B},
	    	{B, B, W, B, B, B, W, B, B},
	    	{W, W, B, B, W, W, W, B, B}
	    };
	    NumberOfIslands g9 = new NumberOfIslands(matrix, 3, 9);
	    
	    // Elements of Programming Interviews 18.8: Team Photo Day - 2
	    List<Team> teams = new java.util.ArrayList<Team>() {{
	    		add(new Team(new int[] {1, 3, 2}, 3));
	    		add(new Team(new int[] {2, 4, 3}, 3));
	    		add(new Team(new int[] {3, 4, 5}, 3));
	    		add(new Team(new int[] {1, 6, 7}, 3));
	    }};
	    /**
	    T1    1 3 2     6
		T2    2 4 3     9
		T3    3 4 5     12
		T4    1 6 7     14
	    */
	    System.out.println("****************  ");
//	    System.out.println("Num Teams = " + Team.teamPhotoMax(teams));
//	    System.out.println("****************  ");
	    
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
		
//		System.out.println(Long.toBinaryString(3L));
		long res = BitManipulation.reverse(3L);
//		System.out.println("Reverse bits: " + res + " > " + Long.toBinaryString(res));
//		System.out.println(Long.toBinaryString(4294967295L));
		res = BitManipulation.reverse(4294967295L);
//		System.out.println("Reverse bits: " + res + " > " + Long.toBinaryString(res));
		
		//						*          **       *       **
		x = Integer.parseInt("0100000000001100000001000000011", 2);
		assert(BitManipulation.nextSmallestAndLargestInt(x)[0] == Integer.parseInt("00000000000000000000000000111111", 2));
		assert(BitManipulation.nextSmallestAndLargestInt(x)[1] == Integer.parseInt("01111110000000000000000000000000", 2));
		
		assert(BitManipulation2.numBitsConverstion(
				Integer.parseInt("1100011", 2), 
				Integer.parseInt("1011010", 2)) == 4);
		assert(BitManipulation2.numBitsConverstion(29, 2) == 5);
		
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
		
		// smallest subarray covering all keywords
		assert (Hashing.smallestSubarray(
				new String[] { "My", "paramount", "object", "in", "this", "struggle", "is", "to", "save", "the",
						"Union", "and", "is", "not", "either", "to", "save", "or", "to", "destroy", "slavery.", "If",
						"I", "could", "save", "the", "Union", "without", "freeing", "any", "slave", "I", "would", "do",
						"it", "and", "if", "I", "could", "save", "it", "by", "freeing", "all", "the", "slaves", "I",
						"would", "do", "it", "and", "if", "I", "could", "save", "it", "by", "freeing", "some", "and",
						"leaving", "others", "alone", "I", "would", "also", "do", "that." },
				new String[] { "Union", "save" }) == 7);
		assert(Hashing.smallestSubarray(new String[] {"apple", "banana", "cat", "apple", "banana", "orange"}, new String[] {"banana", "apple", "orange"}) == 5);
		
		char[] arr2 = new char[] {'f', 's', 'f', 'e', 't', 'w', 'e', 'n', 'w', 'e'};
		assert(Hashing.longestDistinct(arr2) == 5);
		assert(Hashing.longestDistinct2(arr2) == 5);
		assert(Hashing.longestDistinct3(arr2) == 5);
		arr2 = new char[] {'f', 's', 'f', 'e', 't', 'w', 'e', 'n', 'w', 'e', 't', 'f', 's'};
		assert(Hashing.longestDistinct(arr2) == 6);
		assert(Hashing.longestDistinct2(arr2) == 6);
		assert(Hashing.longestDistinct3(arr2) == 6);
		
		// Elements of Programming Interviews In Java 14.8 The Most Visisted Pages Problem
		Hashing.Log log = new Hashing.Log();
		log.read('g'); log.read('a'); log.read('t'); log.read('t');
		log.read('a'); log.read('a'); log.read('a'); log.read('g'); 
		log.read('t'); log.read('c');
		assert(log.kMost(2).toString().indexOf("(t,3)") >= 0 
				&& log.kMost(2).toString().indexOf("(a,4)") >= 0);
		log.read('g'); log.read('a'); log.read('g'); 
		assert(log.kMost(2).toString().indexOf("(g,4)") >= 0 
				&& log.kMost(2).toString().indexOf("(a,5)") >= 0);
		
		// Elements of Programming Interviews 12.10: Find the length of longest contained interval
		assert(Hashing.maxContainedInterval(new int[] {3,  -2,  7,  9, 8,  1,  2,  0,  -1,  5,  8}) == 6);
		assert(Hashing.maxContainedInterval2(new int[] {3,  -2,  7,  9, 8,  1,  2,  0,  -1,  5,  8}) == 6);
		
		// Elements of Programming Interviews 12.11: Find the Student with the Top Three Scores
		assert(Hashing.maxScore(new String[] {"A", "B", "B", "B", "B", "A", "C", "D", "D", "D", "A"}, 
				new int[] {90, 80, 10, 60, 50, 70, 90, 90, 80, 80, 90}, 2) == "A");
		
		// techdevguide.withgoogle: find-longest-word-in-dictionary-that-subsequence-of-given-string
		assert(Hashing.longestWord("abppplee", 
				new String[] {"able", "ale", "apple", "bale", "kangaroo"}) == 5);
		
		// Elements of Programming Interviews 12.12: Compute all String Decompositions
		List<String> words = new java.util.ArrayList<String>() {{
			add("can"); add("apl"); add("ana");
		}};
		assert(Hashing.computeDecompositions("amanaplanacanal", words).toString().trim().equals("[4]"));
		
		// Interview Bit: 
		List<TreeSet<Integer>> res = Hashing.sum4(new int[] {1, 0, -1, 0, -2, 2}, 0);
		System.out.println("SUM 4:");
		for (TreeSet<Integer> set : res) {
			System.out.println(set);
		}
		
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