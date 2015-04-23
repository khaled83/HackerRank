package com.indeed.khaledabbas.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
		**/
		
		int[] arr5 = { 60, 70, 10, 20, 30, 40, 50 };
		System.out.println("  0  1  2  3  4  5  6");
		System.out.println( print(arr5));
		System.out.println( binarySearchAfterRotation(arr5, 65) );
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
