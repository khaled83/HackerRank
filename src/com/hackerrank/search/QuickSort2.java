package com.hackerrank.search;

import java.util.*;

public class QuickSort2 {

	static void quickSort(int[] ar, int start, int end) {
		// 0 1 2 3 4 5
		if (end > start) {
			int pivotIndx = partition(ar, start, end);
			quickSort(ar, 0, pivotIndx - 1);
			quickSort(ar, pivotIndx + 1, end);
			printArray(ar);
		}

	}

	// returns pivot index
	static int partition(int[] ar, int start, int end) {
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();

		int p = ar[start];
		for (int i = start + 1; i <= end; i++) {
			if (ar[i] < p)
				left.add(ar[i]);
			else
				right.add(ar[i]);
		}

		int i;
		for (i = start; i < left.size(); i++)
			ar[i] = left.get(i);

		int pIndx = i;
		ar[pIndx] = p;

		for (int arIndx = pIndx + 1, listIndx = 0; listIndx < right.size(); arIndx++, listIndx++)
			ar[arIndx] = right.get(listIndx);

		return pIndx;
	}

	static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		quickSort(ar, 0, ar.length - 1);
		in.close();
	}

}
