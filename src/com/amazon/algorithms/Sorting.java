package com.amazon.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Sorting {

	public static void selectionSort(int[] arr) {
		// total: n-1
		for (int unsorted = arr.length - 1; unsorted >= 1; unsorted--) {
			// 1 * (n-1)+(n-2)+...+1 = n (n-1) / 2
			int largestIndx = largestIndx(arr, unsorted + 1);
			// 3 * (n-1)
			swap(arr, unsorted, largestIndx);
		}
	}

	private static int largestIndx(int[] arr, int n) {
		int indxSoFar = 0;
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[indxSoFar])
				indxSoFar = i;
		}
		return indxSoFar;
	}

	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		boolean sorted = false;
		for (int pass = 1; pass < n && !sorted; pass++) {
			sorted = true;
			int unsorted = n - pass;
			for (int i = 0; i < unsorted; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					sorted = false;
				}
			}
		}
	}

	// @TODO implement again
	public static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int unsorted = 1; unsorted < n; unsorted++) {
			int next = arr[unsorted];
			int loc = unsorted;
			while (loc >= 1 && arr[loc - 1] > next) {
				arr[loc] = arr[loc - 1];
				--loc;
			}
			arr[loc] = next;
		}
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int first, int last) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(arr, 0, mid);
			mergeSort(arr, mid + 1, last);
			merge(arr, first, mid, last);
		}
	}

	private static void merge(int[] arr, int first, int mid, int last) {
		int[] temp = new int[arr.length];
		// first sorted half
		int first1 = first;
		int last1 = mid;
		// second sorted half
		int first2 = mid + 1;
		int last2 = last;

		int index = first1; // index in temp

		for (; first1 <= last1 && first2 <= last2; index++) {
			if (arr[first1] < arr[first2]) {
				temp[index] = arr[first1];
				first1++;
			} else {
				temp[index] = arr[first2];
				first2++;
			}
		}

		// finish off first half
		for (; first1 <= last1; first1++) {
			temp[index++] = arr[first1];
		}

		// finish off second half
		for (; first2 <= last2; first2++) {
			temp[index++] = arr[first2];
		}

		// copy temp into arr
		for (index = first; index <= last; index++) {
			arr[index] = temp[index];
		}
	}

	public static void quickSort(int[] arr) {
		int first = 0;
		int last = arr.length - 1;
		int pivot = choosePivot(arr, first, last);
		sort(arr, pivot, first, last);
	}

	public static void sort(int[] arr, int pivot, int first, int last) {
		if (first < last) {
			// sort whole array
			int newPivot = quicksort(arr, choosePivot(arr, first, last), first, last);
			// sort left partion before new pivot
			quicksort(arr, choosePivot(arr, first, newPivot - 1), first, newPivot - 1);
			// sort right partition after new pivot
			quicksort(arr, choosePivot(arr, newPivot + 1, last), newPivot + 1, last);
		}
	}

	private static int quicksort(int[] arr, int pivot, int first, int last) {
	    /**
	    arr    29 10 14 37 13
	           p
	              [
	                         ]
	              u
	           s1
	    */
		// move pivot to first location
		swap(arr, pivot, first);

		int firstUnknown = first + 1;
		int lastS1 = first; // empty S1

		for (; firstUnknown <= last; firstUnknown++) {
			if (arr[firstUnknown] < arr[pivot]) {
				swap(arr, firstUnknown, lastS1 + 1);
				lastS1++;
			}
		}

		// move pivot to its proper location
		swap(arr, first, lastS1);

		// return new pivot point
		return lastS1;
	}

	private static int choosePivot(int[] arr, int first, int last) {
		return first;
	}

	public static void radixSort(String[] arr) {
		if (arr == null || arr.length == 0)
			return;

		// ascii char groups, location matches ascii code
		LinkedList<String>[] groups = null;

		// result contains strings in the right order so far
		LinkedList<String> result = new LinkedList(Arrays.asList(arr));

		// assume string length is fixed
		int d = arr[0].length();

		for (int loc = d - 1; loc >= 0; loc--) {
			groups = new LinkedList[128];

			for (String s : result) {
				char c = s.charAt(loc);
				int group = (int) c;
				if (groups[group] == null)
					groups[group] = new LinkedList<String>();
				groups[group].add(s);
			}

			result = new LinkedList<String>();
			for (int group = 0; group < groups.length; group++) {
				if (groups[group] != null)
					result.addAll(groups[group]);
			}
		}

		int i = 0;
		for (String s : result) {
			arr[i++] = s;
		}
	}
	
	// O (m*n)
	public static void merge1(int[] A, int[] B) {
	    for (int unsorted = A.length - B.length, cur = 0; 
	            unsorted < A.length && cur < B.length;
	            unsorted++, cur++)
	    {
	        A[unsorted] = B[cur];
	        // insert element into sorted location
	        for (int i = unsorted; i >= 1; i--)
	            if (A[i] < A[i-1])
	                swap(A, i, i-1);
	    }
	}
	
	public static void merge2(int[] A, int lastA, int[] B, int lastB) {
		for (int a = lastA, b = lastB, dst = lastA + lastB + 1; b >= 0; dst--) {
			if (A[a] > B[b])
				A[dst] = A[a--];
			else
				A[dst] = B[b--];
		}
	}
	
	// O (m+n)
	public static void merge2(int[] A, int[] B) {
	    for (int indxA = A.length - B.length - 1, 
	         indxB = B.length - 1,
	         mergeIndx = A.length - 1;
	         indxB >= 0;
	         mergeIndx--) 
	     {
	         if (indxA >= 0 && A[indxA] > B[indxB])
	             A[mergeIndx] = A[indxA--];
	         else
	             A[mergeIndx] = B[indxB--];
	     }
	}

	private static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}    
	
	public static void sortAnagrams(String[] arr) {
	    Arrays.sort(arr, new AnagramComparator());
	}

	public static void sortAnagrams2(String[] arr) {
		HashMap<Integer, LinkedList<String>> groups = new HashMap<Integer, LinkedList<String>>();
		for (String s : arr) {
			int numericValue = getNumericValue(s);
			if (groups.get(numericValue) == null)
				groups.put(numericValue, new LinkedList<String>());
			groups.get(numericValue).add(s);
		}

		int index = 0;
		for (LinkedList<String> list : groups.values())
			for (String s : list)
				arr[index++] = s;
	}

	private static int getNumericValue(String s) {
		int result = 0;
		for (char c : s.toCharArray())
			result += c;
		return result;
	}
	
	private static class AnagramComparator implements Comparator<String> {

	    public int compare(String s1, String s2) {
	        return getNumericValue(s1).compareTo(getNumericValue(s2));
	    }
	    
	    private Integer getNumericValue(String s) {
	        int result = 0;
	        for (char c : s.toCharArray())
	            result += c;
	        return result;
	    }

	}
	
}
