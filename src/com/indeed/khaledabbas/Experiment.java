package com.indeed.khaledabbas;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

import com.indeed.khaledabbas.datastructures.ArrayList;
import com.indeed.khaledabbas.datastructures.linkedlist.LinkedListExtended;
import com.indeed.khaledabbas.datastructures.linkedlist.LinkedListSingly;
import com.indeed.khaledabbas.datastructures.queue.QueueWithTwoStacks;
import com.indeed.khaledabbas.datastructures.queue.animalshelter.*;
//import com.indeed.khaledabbas.datastructures.queue.node.Queue;
import com.indeed.khaledabbas.datastructures.queue.array.Queue;
import com.indeed.khaledabbas.datastructures.stack.SetOfStacks;
import com.indeed.khaledabbas.datastructures.stack.StackException;
import com.indeed.khaledabbas.datastructures.stack.StackUtility;
import com.indeed.khaledabbas.datastructures.stack.node.Stack;
import com.indeed.khaledabbas.sort.ArraysSortUtils;
import com.indeed.khaledabbas.sort.ArraysSortUtils2;
import com.indeed.strings.StringUtils;

public class Experiment {

	public static void main(String[] args) throws Exception {
		
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
		
		int[] arr = {29, 10, 14, 13, 37};
		printArray(arr);
//		MyArrays.selectionSort(arr);
//		MyArrays.bubbleSort(arr);
//		MyArrays.insertionSort(arr);
//		ArraysSortUtils.mergeSort(arr);
		ArraysSortUtils.quickSort(arr);
		printArray(arr);
		
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

}





































