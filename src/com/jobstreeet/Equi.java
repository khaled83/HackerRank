package com.jobstreeet;

public class Equi {

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println( solution( new int[] { -1, 3, -4, 5, 1, -6, 2, 1 }, 8 ) );
	}
	
	// Time: 9 minutes
	public static int solution( int A[], int N ) 
	{
		// -1 3 -4 5 1 -6 2 1
		//    *      *      *
		int left = 0;
		int right = 0;
		int total = 0;
		
		if( A == null || N == 0 )
			return -1;
		
		for( int i = 0; i < N; i++)
			total += A[i];
		
		for( int i = 0; i < N; i++) {
			right = total - A[i] - left;
			if( left == right )
				return i;
			left += A[i];
		}
		
		return -1;
	}

}
