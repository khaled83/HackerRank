package com.khaledabbas;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeSet;

//import com.indeed.khaledabbas.datastructures.queue.node.Queue;
import com.indeed.strings.StringUtils;
import com.khaledabbas.concurrency.Concurrency;
import com.khaledabbas.datastructures.ArrayList;
import com.khaledabbas.datastructures.graph.Graph;
import com.khaledabbas.datastructures.linkedlist.LinkedListExtended;
import com.khaledabbas.datastructures.linkedlist.LinkedListSingly;
import com.khaledabbas.datastructures.queue.QueueWithTwoStacks;
import com.khaledabbas.datastructures.queue.animalshelter.*;
import com.khaledabbas.datastructures.queue.array.Queue;
import com.khaledabbas.datastructures.stack.SetOfStacks;
import com.khaledabbas.datastructures.stack.StackException;
import com.khaledabbas.datastructures.stack.StackUtility;
import com.khaledabbas.datastructures.stack.node.Stack;
import com.khaledabbas.datastructures.trees.BinarySearchTree;
import com.khaledabbas.datastructures.trees.Heap;
import com.khaledabbas.sort.ArraysSortUtils;
import com.khaledabbas.sort.ArraysSortUtils2;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class Experiment {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(Math.pow(10, 10) > Long.MAX_VALUE);
		
		String str = "213333333333333333333333333333333333";
		long l = Long.parseLong(str);
		
//		Concurrency.main();
		
//		Graph G = new Graph();
//		G.addEdge("A", "B", 1);
//		G.addEdge("A", "C", 1);
//		G.addEdge("C", "D", 1);
//		G.addEdge("D", "E", 1);
//		G.addEdge("D", "G", 1);
//		G.addEdge("E", "G", 1);
//		G.addVertex("H");
//		// print out graph
//		System.out.println(G);
		
//		/** Graphs
//		Graph G = new Graph();
//		G.addEdge("1", "2", 1);
//		G.addEdge("1", "3", 1);
//		G.addEdge("1", "6", 1);
//		G.addEdge("2", "7", 1);
//		G.addEdge("2", "5", 1);
//		G.addEdge("3", "4", 1);
//		G.addEdge("6", "8", 1);
//		G.addEdge("8", "9", 1);
//		G.addEdge("8", "10", 1);
//		// print out graph
//		System.out.println(G);
//		System.out.println( "maxToForest = " +  G.getMaxEdgesToForest());
//		*/
		
		/** 
		Graph G = new Graph();
		G.addEdge("A", "B", 1);
		G.addEdge("A", "C", 1);
		G.addEdge("A", "D", 1);
		G.addEdge("B", "E", 1);
		G.addEdge("B", "F", 1);
		G.addEdge("E", "K", 1);
		G.addEdge("E", "L", 1);
		G.addEdge("K", "S", 1);
		G.addEdge("L", "T", 1);
		G.addEdge("F", "L", 1);
		G.addEdge("F", "M", 1);
		G.addEdge("L", "T", 1);
		G.addEdge("C", "G", 1);
		G.addEdge("C", "H", 1);
		G.addEdge("G", "N", 1);
		G.addEdge("H", "O", 1);
		G.addEdge("H", "P", 1);
		G.addEdge("P", "U", 1);
		G.addEdge("D", "I", 1);
		G.addEdge("D", "J", 1);
		G.addEdge("I", "P", 1);
		G.addEdge("I", "Q", 1);
		G.addEdge("J", "R", 1);
		System.out.println(G);
		G.bfs();
		System.out.println();
		G.dfs();
		System.out.println();
		
		
		Graph G = new Graph();
		G.addEdge("0", "1", 8);
		G.addEdge("0", "3", 9);
		G.addEdge("0", "4", 4);
		G.addEdge("1", "2", 1);
		G.addEdge("2", "1", 2);
		G.addEdge("2", "3", 3);
		G.addEdge("3", "4", 7);
		G.addEdge("3", "2", 2);
		G.addEdge("4", "2", 1);
		System.out.println( G.hasPath( "0", "2" ) );
		*/
		
		// Heaps
		/*
		Heap<Integer> heap = new Heap<Integer>();
		heap.insert(3);
		heap.insert(2);
		heap.insert(6);
		heap.insert(5);
		heap.insert(9);
		heap.insert(10);
		System.out.println(heap);
		heap.delete();
		System.out.println(heap);
		*/
		
		/**
		Integer[] arr = { 6, 3, 5, 9, 2, 10 };
		printArray(arr);
		Heap.heapsort(arr);
		printArray(arr);
		
		LinkedList<Integer> list;
		Arrays.sort(null, java.util.Collections.reverseOrder());
		
		Integer x = 0, y = 0;
		System.out.println(x - y);
		**/
		
		/**
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		System.out.println(queue.toString());
		
		System.out.println(queue.dequeue());
		System.out.println(queue.toString());
		
		queue.enqueue(4);
		System.out.println(queue.toString());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.toString());
		System.out.println(queue.dequeue());
		System.out.println(queue.toString());
		System.out.println(queue.dequeue());
		*/
		
		/*
		System.out.println("Queue with two stacks");
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		System.out.println(queue.getFront());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(4);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		*/
		
		/**
		assert( StackUtility.isBalancedBraces("{ab{c}_ }vga{adas}") );
		assert( StringUtils.isBalancedBraces("{ab{c}_ }vga{adas}") );
		*/
		
		/**
		System.out.println("Stack - - -");
		Stack<Comparable> stack = new Stack<Comparable>();
		stack.push(3);
		stack.push(1);
		stack.push(4);
		stack.push(2);
		
		System.out.println(stack);
		System.out.println(StackUtility.sort(stack).toString());
//		System.out.println(stack.top());
//		System.out.println(stack);
//		System.out.println(stack.pop());
//		System.out.println(stack);
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
//		System.out.println(stack.pop());
//		*/
		
		/*
		// c1 c2 
		// d1 d2 d3 
		// c1 d1 d2 c2 d3
		AnimalShelterQueue shelter = new AnimalShelterQueue();
		shelter.enqueue(new Cat("c1"));
		shelter.enqueue(new Dog("d1"));
		shelter.enqueue(new Dog("d2"));
		shelter.enqueue(new Cat("c2"));
		shelter.enqueue(new Dog("d3"));
		
		for(int i=0; i<3; i++)
		System.out.println(shelter.dequeueDog());
		*/
		
		/*
		System.out.println("SetOfStack - - -");
		SetOfStacks<Integer> stack = new SetOfStacks<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.toStringForTesting());
		*/
		
		
		/**
		System.out.println("Linked List - - -");
		
		LinkedListExtended<Integer> linkedList = new LinkedListExtended<Integer>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		System.out.println(linkedList.toString());
		linkedList.addFirst(1);
		linkedList.remove(3);
		System.out.println(linkedList);
		linkedList.removeFirst();
		System.out.println(linkedList);
		linkedList.add(3);
		linkedList.add(4);
		System.out.println(linkedList);
		linkedList.remove(2);
		System.out.println(linkedList);
		
		linkedList.add(2);
		linkedList.add(4);
		linkedList.add(1);
		System.out.println(linkedList);
		linkedList.printKthToLastRec(3);
		
//		linkedList.removeDuplicatesWithoutBuffer();
//		System.out.println(linkedList);
//		linkedList.removeKthToLast(2);
//		System.out.println(linkedList);
		
		linkedList.add(-3);
		linkedList.add(0);
		linkedList.add(3);
		linkedList.add(-1);
		System.out.println(linkedList.toString());
		linkedList.partition(2);
		System.out.println(linkedList.toString());
		
		linkedList = new LinkedListExtended<Integer>();
		linkedList.add(1); linkedList.add(2); linkedList.add(2); linkedList.add(1); 
		System.out.println("isPalyndrome="+linkedList.isPalyndrome());
		
		System.out.println("DONE");
		*/
		
//		linkedList.writeString();
//		linkedList.writeStringBackward();
		
		/**
		System.out.println("Sorted Linked List - - -");
		LinkedListSingly<Integer> sortedList = new LinkedListSingly<Integer>();
		sortedList.sortedAdd(2);
		sortedList.sortedAdd(4);
		System.out.println(sortedList);
		sortedList.sortedAdd(3);
		System.out.println(sortedList);
		sortedList.sortedAdd(1);
		sortedList.sortedAdd(5);
		System.out.println(sortedList);
		*/
		
		/**
		ArrayList<Integer> list = new ArrayList<>();
		assert(list.size() == 0);
		assert(list.toString().equals("{}"));
		
		list.add(1);
		list.add(2);
		list.add(3);
		assert(list.size() == 3);
		System.out.println(list);
		
		list.remove(1);
		
		Integer[] arr = { 4, 5, 6, 7, 8, 9, 10 };
		java.util.ArrayList<Integer> list2 = new java.util.ArrayList<Integer>(
					Arrays.asList(arr)
				);
		
		
		list.addAll(list2);
		System.out.println(list);
		*/
		
//		int[] arr = {29, 10, 14, 13, 37};
//		printArray(arr);
//		MyArrays.selectionSort(arr);
//		MyArrays.bubbleSort(arr);
//		MyArrays.insertionSort(arr);
//		ArraysSortUtils.mergeSort(arr);
//		ArraysSortUtils.quickSort(arr);
//		printArray(arr);
		
//		java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>(2);
//		list.get(1);
		
		// 0 1 2 3 4
		// 1 4 6 - -
//		int[] a = {0, 2, 4, 0, 0, 0};
//		int[] b = {1, 3, 6};
//		ArraysSortUtils2.merge(a, b);
//		printArray(a);
	}
	
	private static void printArray(int[] arr)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for(int i : arr)
			sb.append(i).append(",");
		
		if(arr.length > 0)
			sb.setLength(sb.length() - 1);
		sb.append("}");
		
		System.out.println(new String(sb));
	}
	
	private static void printArray(Object[] arr)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(Object o : arr)
			sb.append(o).append(" ");
		sb.append("}");
		System.out.println( new String(sb) );
	}

}






































