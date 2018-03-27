package com.khaledabbas.datastructures.trees;

import java.util.*;

import com.amazon.datastructures.heaps.Point3D;

public class Heap<E extends Comparable<E>> {
	
	private int size = 0;
	
	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	
	private Object[] elements;
	
	public Heap()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public Heap(int initialCapacity)
	{
		elements = new Object[initialCapacity];
	}
	
	// 0  1  2  3  4  5
	// 30 10 20
	
	public boolean empty()
	{
		return size == 0;
	}
	
	public void insert(E e) throws HeapException
	{
		if(size == elements.length)
			throw new HeapException("Heap is full");
		
		elements[size] = e;
		
		
		// trickle up new element to proper place
		int child = size;
		int parent = (child-1)/2;
		while(parent >= 0 && ((E)elements[parent]).compareTo( (E) elements[child]) < 0 )
		{
			swap(parent, child);
			child = parent;
			parent = (child-1)/2;
		}
		
		size++;
	}
	
	public E delete() throws HeapException
	{
		if(empty())
			throw new HeapException("Heap is empty");
		
		E value = (E) elements[0];
		elements[0] = elements[size-1];
		elements[size-1] = null;
		
		// trickle down root element
		size--;
		heapRebuild(0);
		
		return value;
	}
	
	private void heapRebuild(int root)
	{
		int child = root*2+1;
		if(child < size)
		{
			int rightChild = child+1;
			if( ((E)elements[rightChild]).compareTo( (E)elements[child] ) > 0 )
				child = rightChild;
			if( ((E) elements[root] ).compareTo( (E)elements[child] ) < 0)
			{
				swap(root, child);
				heapRebuild(child);
			}
		}
	}
	
	/***
      6
     /  \
  3      5
 /  \    /
9    2 10
	*/
	public static void heapsort(Comparable[] arr)
	{
		// 6  3 5 9 2 10
		// 10 9 6 3 2 5
		//			  u
		// transform array into heap
		int n = arr.length;
		for(int i=n/2; i>=0; i--)
			heapRebuild(arr, i, n);
		printArray(arr);
		// start sorting array elements by moving heap root
		for(int unsorted=n-1; unsorted>0; unsorted--)
		{
			swap(arr, 0, unsorted);
			heapRebuild(arr, 0, unsorted);
		}
	}
	
	private static void heapRebuild(Comparable[] arr, int root, int size)
	{
		int child = root*2+1;
		if(child < size)
		{
			int rightChild = child+1;
			if( rightChild < size && arr[rightChild].compareTo( arr[child] ) > 0)
				child = rightChild;
			if(arr[child].compareTo( arr[root] ) > 0)
			{
				swap(arr, root, child);
				heapRebuild(arr, child, size);
			}
		}
	}
	
	private static void swap(Object[] arr, int indx1, int indx2)
	{
		Object tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}
	
	private void swap(int parent, int child)
	{
		Object tmp = elements[parent];
		elements[parent] = elements[child];
		elements[child] = tmp;
	}
	
	private static class HeapException extends Exception
	{
		public HeapException() {}
		
		public HeapException(String msg) { super(msg); }
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(Object o : elements)
			sb.append(o).append(" ");
		sb.append("}");
		return new String(sb);
	}
	
	public static List<Point3D> closestK(Point3D[] arr, int k) {
	    PriorityQueue<Point3D> h = new PriorityQueue<Point3D>(Collections.reverseOrder());
	    
	    for (Point3D p : arr) {
	        if (h.size() < k) {
	            h.add(p);
	        }
	        // p is smaller than max in heap
	        else if(p.compareTo(h.peek()) < 0) {
	            h.remove();
	            h.add(p);
	        }
	    }
	    
	    return new ArrayList<Point3D>(h);
	}

	public static Iterable<Star> closestK2(Star[] arr, int k) {
	    // max pq
	    PriorityQueue<Star> pQ = new PriorityQueue<Star>(Collections.reverseOrder());
	    for (Star s : arr) {
	        if (pQ.size() < k) {
	            pQ.add(s);
	        }
	        else if (s.distance() < pQ.peek().distance()) {
	            pQ.remove(); // remove old max
	            pQ.add(s); // add new close star
	        }
	    }
	    
	    return pQ;
	}
	
	public static class Star implements Comparable<Star> {

	    int x, y, z;    
	    
	    public Star(int x, int y, int z) {
	    		this.x = x;
	    		this.y = y;
	    		this.z = z;
	    }
	    
	    public int distance() {
	        return x + y + z;
	    }
	    
	    public int compareTo(Star o) {
	        return Integer.valueOf(this.distance()).compareTo(o.distance());
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	    		return ((Star) obj).distance() == this.distance();
	    }
	    
	}
	
}
