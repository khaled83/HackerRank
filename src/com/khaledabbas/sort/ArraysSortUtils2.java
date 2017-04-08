package com.khaledabbas.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class ArraysSortUtils2 {

	public static void main(String[] args) {
		// 11 12 10 13 13 13 16
		/**
		int[] arr = { 15, 13, 11, 12, 13, 13, 16, 10 };
		quickSortSpecial(arr, 1);
		System.out.println(print(arr));
		
		int[] arr1 = { 8, 9, 10, 11, 16, 17, 18 };
		int[] arr2 = { 11, 12, 13, 14, 16, 29, 49 };
		System.out.println( print( intersect(arr1, arr2) ));
		
		String[] arr4 = { "abc", "def", "efd", "cba", "wwf", "cab", "dfe" };
		sortAnagrams( arr4 );
		System.out.println( print( arr4 ) );

		int[] arr5 = { 60, 70, 10, 20, 30, 40, 50 };
		System.out.println("  0  1  2  3  4  5  6");
		System.out.println( print(arr5));
		System.out.println( binarySearchAfterRotation(arr5, 65) );
		
		int[] arr6 = { 37, 131, 493, 294, 280, 221, 339, 418, 452, 442, 190 };
		System.out.println( print(arr6) );
		sortIncreasingDecreasingArray(arr6);
		System.out.println( print(arr6) );
		
		//              0    1  2    3    4    5    6    7    8    9
		int[] arr = { -14, -10, 2, 108, 108, 243, 285, 285, 285, 401 };
		System.out.println(binarySearchForLarger(arr, 401));
		
		System.out.println( bsIndx( new int[] { -2, 0, 2, 3, 6, 7, 9 } ) ); // { -2, -1, 0, 0, 2, 2, 3 }
		System.out.println( bsIndx2( new int[] { -2, 0, 2, 3, 6, 7, 9 } ) ); 
		
		
		int[] arr = { 3, -1, 2, 6, 4, 5, 8 };
		ArrayUtils.printArray( arr );
		sort2( arr , 2 );
		ArrayUtils.printArray( arr );
		
		// b a c b d a b d
		Item[] arr = new Item[] { new Item<String>("b"), new Item<String>("a"), new Item<String>("c"), new Item<String>("b"),
									new Item<String>("d"), new Item<String>("a"), new Item<String>("b"), new Item<String>("d") };
		System.out.println( print(arr) );
		new Item<String>().countSort(arr);
		System.out.println( print(arr) );
		**/
		
		
		Item[] arr = new Item[] { new Item<String>("g4"), new Item<String>("g2"),
				new Item<String>("t1"), new Item<String>("c3"),
				new Item<String>("a2"), new Item<String>("g5"),
				new Item<String>("a3"), new Item<String>("t4") };
		System.out.println( print(arr) );
		/**
		 {t1=1, t4=1, g2=1, a2=1, a3=1, c3=1, g5=1, g4=1}
		 {t1=0, g2=2, t4=1, a2=3, a3=4, c3=5, g5=6, g4=7}
		 */
		new Item<String>().countSort2(arr);
		System.out.println( print(arr) );
	}

	public static class Item<E> {

		E key;
		
		Item() {}
		
		Item( E key ) { this.key = key; }

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Item))
				return false;
			Item item = (Item) o;
			
			if( key instanceof String)
				return ((String) key).charAt(0) == ((String)item.key).charAt(0);
			
			return this.key.equals(item.key);
		}

		@Override
		public int hashCode() {
			if( key instanceof String ) {
				Character firstChar = ((String)key).charAt(0);
				return firstChar.hashCode();
			}
				
			return key.hashCode();
		}
		
		@Override
		public String toString() {
			return key.toString();
		}

		public void countSort2(Item<E>[] arr) {
			
			HashMap<Item<E>, Integer> countMap = new HashMap<Item<E>, Integer>();
			HashMap<Item<E>, Integer> offsetMap = new HashMap<Item<E>, Integer>();
			
			for (Item<E> e : arr) {
				Integer count = countMap.get(e);
				if (count == null)
					count = 0;
				countMap.put(e, ++count);
			}

			int offset = 0;
			for (Item<E> e : countMap.keySet()) {
				offsetMap.put(e, offset);
				offset += countMap.get(e);
			}

			for (int src = 0; src < arr.length && !countMap.isEmpty(); src++) {
				Item<E> e = arr[src];
				int dst = offsetMap.get(e);
				if (dst != src) {
					swap(arr, src, dst);
					src--; // handle swapped element
				} // dst != src

				int count = countMap.get(e);
				if ( ( count-1 ) == 0) {
					countMap.remove(e);
					offsetMap.remove(e);
				} else {
					countMap.put(e, --count);
					offsetMap.put(e, ++dst);
				}

			}

		} // end of method
		
		public void countSort(Item[] arr) {

			HashMap<E, Integer> countMap = new HashMap<E, Integer>();
			for (Item item : arr) {
				Integer count = countMap.get(item.key);
				if (count == null)
					count = 0;
				countMap.put( (E) item.key, ++count);
			}

			HashMap<E, Integer> dstMap = new HashMap<E, Integer>();
			int dst = 0;
			for (E key : countMap.keySet()) {
				dstMap.put(key, dst);
				dst += countMap.get(key);
			}
			/**
			 * 0 1 2 3 4 5 6 7 b1 c1 a1 c1 b2 d1 a2 b3 d2 src
			 */
			int sorted = 0;
			for (int src = 0; src < arr.length && sorted < arr.length; src++, sorted++) {
				dst = dstMap.get(arr[src].key);
				if ( ! arr[src].key.equals(arr[dst].key) ) {
					swap(arr, src, dst);
					src--;
				}
				dstMap.put( (E) arr[dst].key, ++dst);
			}

		}
	}
	
	public static void swap( Object[] arr, int x, int y ) {
		Object tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	/**
	 * Elements of programming interviews
	 */
	public static void sort2(int[] arr, int k) {

		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int n = arr.length;

		// initialize with the first k elements
		int src = 0;
		for ( src = 0; src < k && src < n; src++)
			heap.add(arr[src]);

		int dst = 0;
		for (src = k; src < n; src++, dst++) {
			arr[dst] = heap.remove();
			heap.add(arr[src]);
		}

		while (!heap.isEmpty())
			arr[dst++] = heap.remove();

	}

	/** 
	 * @deprecated	doesn't work correctly 
	 * time: 00:25:00
	 */
	public static void sort(int[] arr, int k) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			if (arr[i] < arr[i - 1])
				sort(arr, i - k, i);
		}
	}

	/** 
	 * @deprecated	doesn't work correctly 
	 * time: 00:25:00
	 */
	private static void sort(int[] arr, int start, int unsortedIndx) {
		if (start < 0)
			start = 0;
		int unsorted = arr[unsortedIndx];
		int insertionIndx = unsortedIndx - 1;

		while (insertionIndx > start && arr[insertionIndx] > unsorted ) {
			arr[insertionIndx + 1] = arr[insertionIndx];
			insertionIndx--;
		}

		arr[insertionIndx] = unsorted;
	}
	
	// time: 00:16:00 (rec+iter)
	public static int bsIndx2(int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		int mid = start;
		while (start <= end) {
			mid = (start + end) / 2;
			if (arr[mid] == mid)
				return mid;
			else if (arr[mid] > mid)
				end = mid - 1;
			else
				start = mid + 1;
		}

		return Integer.MIN_VALUE;
	}

	// recursive; time: O( log( n ) ), space: O( log ( n ) ) call stack
	public static int bsIndx(int[] arr) {

		return bsIndx(arr, 0, arr.length - 1);
	}

	public static int bsIndx(int[] arr, int start, int end) {

		if (start > end)
			return Integer.MIN_VALUE;

		int mid = (start + end) / 2;
		if (arr[mid] == mid)
			return mid;
		else if (arr[mid] > mid)
			return bsIndx(arr, start, mid - 1);
		else
			return bsIndx(arr, mid + 1, end);
	}
	
	public static int binarySearchForLarger(int[] arr, int key) {

		return binarySearchForLarger(arr, key, 0, arr.length - 1);

	}

	private static int binarySearchForLarger(int[] arr, int key, int start, int end) {

		// no result found
		if (start > end) {
			int indx = firstLargerAfter(arr, key, end, arr.length - 1);
			if (indx == -1)
				indx = firstLargerBefore(arr, key, end, 0);
			return indx;
		}

		int mid = (start+end) / 2;

		// key found
		if (arr[mid] == key) {
			// for loop needed for array with duplicates
			return firstLargerAfter(arr, key, mid, arr.length - 1);
		} else if (key < arr[mid]) { // search 1st half
			return binarySearchForLarger(arr, key, start, mid - 1);
		} else if (key > arr[mid]) { // serch 2nd half
			return binarySearchForLarger(arr, key, mid + 1, end);
		}

		return -1;
	}

	private static int firstLargerAfter(int[] arr, int key, int start, int end) {
		start = Math.max(start, 0);
		for (int i = start; i <= end; i++)
			if (arr[i] > key)
				return i;
		return end + 1;
	}

	private static int firstLargerBefore(int[] arr, int key, int start, int end) {
		for (int i = end; i >= start; i--)
			if (arr[i] < key)
				return i + 1;
		return 0;
	}
	
	public static void sortIncreasingDecreasingArray(int[] arr) {

		int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
		int n = arr.length;

		int i = 0;
		while (arr[i] < arr[i + 1]) {
			i++;
		}
		while (arr[i] > arr[i + 1]) {
			i++;
		}
		p2 = i;
		p3 = p2 + 1;
		p4 = n - 1;

		int sortedSoFar = 0;
		int[] sorted = new int[n];
		int indx = 0;

		while (sortedSoFar < n) {
			int minIndx = minIndx(arr, p1, p2, p3, p4);
			if (minIndx == p1)
				p1++;
			if (minIndx == p2)
				p2--;
			if (minIndx == p3)
				p3++;
			if (minIndx == p4)
				p4--;
			sorted[indx++] = arr[minIndx];
			sortedSoFar++;
		}
		
		System.arraycopy( sorted, 0, arr, 0, n );

	}

	private static int minIndx(int[] arr, int w, int x, int y, int z) {
		int min = Integer.MAX_VALUE;
		int indx = 0;
		if (w >= 0 && w <= x) {
			min = arr[w];
			indx = w;
		}
		if (x >= 0 && x >= w && arr[x] < min) {
			min = arr[x];
			indx = x;
		}
		if (y >= 0 && y <= z && arr[y] < min) {
			min = arr[y];
			indx = y;
		}
		if (z >= 0 && z >= y && arr[z] < min) {
			min = arr[z];
			indx = z;
		}
		return indx;
	}
	
	public static int binarySearchAfterRotation(int[] arr, int key) {
		int start = 0;
		int length = arr.length;
		for (int i = 1; i < length; i++)
			if (arr[i] < arr[i - 1]) {
				start = i;
				break;
			}
		int end = start - 1;

		return binarySearch(arr, start, end, length, key);
	}

	private static int binarySearch(int[] arr, int start, int end, int length, int key) {
		if (length == 0)
			return (-start) - 1;

		int mid = (start + (length / 2)) % arr.length;
		if (arr[mid] == key)
			return mid;

		else if (key < arr[mid])
			return binarySearch(arr, start, mid - 1, length / 2, key);
		else
			return binarySearch(arr, mid + 1, end, (length - 1) / 2, key);
	}
	
	public static void sortAnagrams(String[] arr) {
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				int[] charCount = new int[58];
				for (char c : o1.toCharArray())
					charCount[c - 'A'] += (int) c;
				for (char c : o2.toCharArray())
					charCount[c - 'A'] -= (int) c;
				
				int diff = 0;
				for (int count : charCount)
					diff += count;

				return diff;
			}
		});
	}

	public static Integer[] intersect(int[] arr1, int[] arr2) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int p1 = 0;
		int p2 = 0;

		while (p1 < arr1.length && p2 < arr2.length) {
			System.out.println("looping");
			int x = arr1[p1];
			int y = arr2[p2];

			if (x == y) {
				result.add(x);
				p1++;
				p2++;
			} else if (x < y) {
				p1 = Arrays.binarySearch(arr1, p1, arr1.length, y);
				if (p1 < 0) {
					p1 = (-p1) - 1;
				} // else let the next while operation handle success
			} else {
				p2 = Arrays.binarySearch(arr2, p2, arr2.length, x);
				if (p2 < 0) {
					p2 = (-p2) - 1;
				} // else let the next while operation handle success
			}
		}

		return result.toArray( new Integer[result.size()] );
	}

	public static void mergeWithInsertion(int[] a, int[] b) {
		int unsorted = a.length - b.length;
		int n = a.length;

		for (int bIndx = 0; unsorted < n; unsorted++) {
			int next = b[bIndx++];

			int insertionIndx;
			for (insertionIndx = unsorted; insertionIndx > 0
					&& a[insertionIndx - 1] > next; insertionIndx--)
				a[insertionIndx] = a[insertionIndx - 1];
			a[insertionIndx] = next;
		}

	}

	// 0 1 2 3 4
	// 0 2 3 4 5
	// 3 5
	// u
	// a
	// b
	// iA
	// iB
	// m
	public static void merge(int[] a, int b[]) {
		int unsorted = a.length - b.length;
		int indxA = unsorted - 1;
		int indxB = b.length - 1;

		for (int mergeIndx = a.length - 1; mergeIndx >= unsorted; mergeIndx--) {
			if (a[indxA] > b[indxB]) {
				a[mergeIndx] = a[indxA--];
				unsorted--;
			} else {
				a[mergeIndx] = b[indxB--];
			}
		}
	}

	public static void quickSortSpecial(int[] arr, int keyIndx) {
		int pivot = arr[keyIndx];
		int lastS1 = -1;
		int lastS2 = -1;
		int unknown = 0;

		for (; unknown < arr.length; unknown++) {
			if (arr[unknown] < pivot) {
				lastS1++;
				swap(arr, lastS1, unknown);
				lastS2++;
			} else if (arr[unknown] == pivot) {
				lastS2++;
			} else {
			}
		}
	}

	private static void swap(int[] arr, int indx1, int indx2) {
		int tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}
	
	public static String print(Object[] arr) 
	{ 
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Object x : arr)
			sb.append(x.toString()).append(" ");
		sb.append("}");
		return new String(sb);
	}

	public static String print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int x : arr)
			sb.append(x).append(" ");
		sb.append("}");
		return new String(sb);
	}

}
