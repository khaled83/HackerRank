package com.grabtaxi;

public class Max {

	public static void main(String[] args) {
		//             0  1  2  3  4  5
		int[] arr1 = { 5, 2, 6, 4, 1, 10 };
		int[] arr2 = { 5, 3, 6 };
		int[] arr3 = { 2, 4, 1 };
//		System.out.println( max(arr, 0, 2) + "" );
		System.out.println( minMax(arr1, 1, 4) + "" );
		System.out.println( minMax(arr2, 0, 2) + "" );
		System.out.println( minMax(arr3, 0, 2) + "" );
	}
	
	public static int max( int[] arr, int A, int B ) 
	{
		// 10 2 3 4 6 4 3 2 10
		//    A           B
		int indx = A+1;
		for( ; indx < B; indx++ )
		{
			if( arr[indx+1] < arr[indx] ) {
				return arr[indx];
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	public static int minMax( int[] arr, int A, int B ) {
		// 10 2 3 4 6 4 3 2 10
		//    A           B
		int indx = A+1;
		
		int comp = 1;
		if( arr[indx+1] < arr[indx] )
			comp = -1;
		
		for( ; indx < B; indx++ )
		{
			if( Integer.compare( arr[indx+1], arr[indx] ) == comp ) {
				return arr[indx];
			}
		}
		
		return Integer.MIN_VALUE;
	}
	
	
	
}
