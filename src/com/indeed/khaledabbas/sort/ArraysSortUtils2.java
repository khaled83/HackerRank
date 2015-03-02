package com.indeed.khaledabbas.sort;

public class ArraysSortUtils2 {

	public static void mergeWithInsertion(int[] a, int[] b)
	{
		int unsorted = a.length - b.length;
		int n = a.length;
		
		for( int bIndx = 0 ; unsorted<n; unsorted++)
		{
			int next = b[bIndx++];
			
			int insertionIndx;
			for( insertionIndx = unsorted; insertionIndx > 0 && a[insertionIndx-1] > next; insertionIndx--)
				a[insertionIndx] = a[insertionIndx-1];
			a[insertionIndx] = next;
		}
		
	}
	
	// 0 1 2 3 4
	// 0 2 3 4 5
	// 3 5
	//     u
	//   a 
	//b
	//     iA
	//   iB
	//   m
	public static void merge(int[] a, int b[])
	{
		int unsorted = a.length - b.length;
		int indxA = unsorted - 1;
		int indxB = b.length - 1;
		
		for( int mergeIndx = a.length - 1; mergeIndx >= unsorted; mergeIndx-- )
		{
			if(a[indxA] > b[indxB])
			{
				a[mergeIndx] = a[indxA--];
				unsorted--;
			}
			else
			{
				a[mergeIndx] = b[indxB--];
			}
		}
	}
	
}
