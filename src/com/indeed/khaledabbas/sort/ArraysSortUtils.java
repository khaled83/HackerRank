package com.indeed.khaledabbas.sort;

public class ArraysSortUtils {
	
	public static void quickSort(int[] arr)
	{
		quickSort(arr, 0, arr.length - 1 );
	}
	
	public static void quickSort(int[] arr, int first, int last)
	{
		if(first < last)
		{
			int pivotIndx = partition(arr, first, last);
			partition(arr, first, pivotIndx-1);
			partition(arr, pivotIndx+1, last);
		}
	}
	
	// 29 10 7 34 13
	// p
	//    s1   s2
	//            un
	private static int partition(int[] arr, int first, int last)
	{
		int pivotIndx = findPivotIndx(arr, first, last);
		int pivot = arr[pivotIndx];
		swap(arr, first, pivotIndx);
		
		int lastS1 = pivotIndx;
		
		for(int firstUnknown = pivotIndx+1; firstUnknown <= last; firstUnknown++)
		{
			if(arr[firstUnknown] < pivot)
			{
				swap(arr, firstUnknown, lastS1+1);
				lastS1++;
			}
				
		}
		
		swap(arr, pivotIndx, lastS1);
		
		return pivotIndx;
	}
	
	private static int findPivotIndx(int[] arr, int first, int last)
	{
		return 0;
	}
	
	public static void mergeSort(int[] arr)
	{
		mergeSort(arr, 0, arr.length-1);
	}
	
	public static void mergeSort(int[] arr, int first, int last)
	{
		if(first < last)
		{
			int mid = (first+last) / 2;
			mergeSort(arr, first, mid);
			mergeSort(arr, mid+1, last);
			merge(arr, first, mid, last);
		}
	}
	
	private static void merge(int[] arr, int first, int mid, int last)
	{
		// 0  1  2  3  4
		// 10 14 29 13 37
		// s     m     e
		// [      ][    ]  				
		int first1 = first;
		int last1 = mid;
		int first2 = mid+1;
		int last2 = last;
		
		int[] tmp = new int[last-first+1];
		int tmpIndx;
		
		for(tmpIndx = 0; first1 <= last1 && first2 <= last2; tmpIndx++)
		{
			if(arr[first1] <= arr[first2])
				tmp[tmpIndx] = arr[first1++];
			else
				tmp[tmpIndx] = arr[first2++];
		}
		
		// finish off first subarray
		for(; first1 <= last1; first1++, tmpIndx++)
			tmp[tmpIndx] = arr[first1];
		
		// finish off second subarray
		for(; first2 <= last2; first2++, tmpIndx++)
			tmp[tmpIndx] = arr[first2];
		
		// copy tmp to original array
		tmpIndx = 0;
		for(int indx=first; indx<=last; indx++)
			arr[indx] = tmp[tmpIndx++];
	}
	
	public static void insertionSort(int[] arr)
	{
		// 29 10 14 37 13
		//    u
		// 10 29 14 37 13
		//        u
		int n=arr.length;
		
		// total: 2 * (n-1) moves + n (n-1) / 2 compares +  n (n-1) / 2 shifts = 2n-2 + n^2 - n = n^2+n-2 -> O(n^2)
		
		// invariant: [0..unsorted-1] are already sorted
		// n-1 times
		for(int unsorted=1; unsorted<n; unsorted++)
		{
			int next = arr[unsorted];
			int insertionIndx = unsorted;
			// 1, 2, ... , n-2, n-1 -> n (n-1) / 2
			for(; insertionIndx >= 1 && arr[insertionIndx-1] > next; insertionIndx--)
			{
				arr[insertionIndx] = arr[insertionIndx-1];
			}
			// n-1
			arr[insertionIndx] = next;
		}
		
	}
	
	public static void bubbleSort(int[] arr)
	{
		// 0  1  2  3  4
		// 29 10 14 37 13	:	n=5
		// 
		
		int n = arr.length;
		boolean sorted = false;
		
		// total: 4 * n (n-1) / 2 = 2n^2-2n -> O(n^2)
		
		// n-1 times
		for(int pass=1; pass<n && !sorted; pass++) // pass: 1..3
		{
			sorted = true;
			// n-1, n-2, ..., 1 -> n (n-1) / 2
			for(int i=0; i < n-pass; i++) // i: [0..3] ... [0..1]
			{
				if(arr[i] > arr[i+1]) // 1 compare
				{
					swap(arr, i, i+1); // 3 moves
					sorted = false;
				}
			}
		}
	}
	
	public static void selectionSort(int[] arr)
	{
		// 29 10 14 13 37
		// 		    unsorted
		// invariant: unsorted+1 .. n is always sorted
		int n = arr.length;

		// outer loop: n-1 times
		for(int unsorted=n-1; unsorted>=1; unsorted--)
		{
			// unsorted times: n-1, n-2, ..., 1 times = n (n-1) / 2
			int largestIndx = indexOfLargest(arr, unsorted);
			// 3 times -> 3 * (n-1) times
			swap(arr, largestIndx, unsorted);
		}
	}
	
	private static int indexOfLargest(int[] arr, int end)
	{
		int largestSoFar = 0;
		
		for(int i=1; i<=end; i++) 
		{
			if(arr[i] > arr[largestSoFar])
				largestSoFar = i;
		}
		
		return largestSoFar;
	}
	
	private static void swap(int[] arr, int indx1, int indx2)
	{
		int tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}

}
