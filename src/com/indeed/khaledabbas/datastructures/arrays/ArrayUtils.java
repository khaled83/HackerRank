package com.indeed.khaledabbas.datastructures.arrays;

import java.util.*;

public class ArrayUtils {

	public static void main(String[] args) {
		// didn't work for 4x4
		/**
		int[][] arr = { {0, 1, 2},
						{3, 4, 5},
						{6, 7, 8} 
					  };
		System.out.println( printMatrix(arr) );
		rotate(arr, 3, 3);
		System.out.println( printMatrix(arr) ); 
		
		int[] arr = { 9, 9, 9 };
		arr = add( arr, 5);
		printArray( arr );
		
		//            0 1 2 3 4 5 6
//		int[] arr = { 3,4,1,0,2,0,1 };
		int[] arr = { 3,2,0,0,2,0,1 };
		// 4,6
		System.out.println( winnable(arr) );
		
		System.out.println( isCyclic( new int[] { 2, 2, -1 } ) );
		**/
		System.out.println("remove duplicates:");
		System.out.println("Imran Ibrahim".charAt(5) == ' ');
		
		String[] arr = { "Issa Johns", "Bill Gates", "Imran Ibrahim" , "Steve Jobs", "Imran Issa" };
		removeDuplicates( arr );
		printArray( arr);
		printArray( removeDuplicates2( 
				new String[] { "Issa Johns", "Bill Gates", "Imran Ibrahim" , "Steve Jobs", "Imran Issa" } ) );
		
	}
	
	/***
	 * Elements of programming interviews
	 * @return
	 */
	// approach 2: O( N K ); N:num of words, K:num of characters
	public static String[] removeDuplicates2(String[] arr) {

		HashSet<String> names = new HashSet<String>();

		for (String fullName : arr) {
			String firstName = firstName(fullName, -1);
			names.add(firstName);
		}

		return names.toArray(new String[]{});
	}

	// N ( log( N ) ) K + K
	public static void removeDuplicates(String[] arr) {

		int n = arr.length;

		Arrays.sort(arr);
		int lastIndx = firstNameIndx(arr[0]);
		String last = firstName(arr[0], lastIndx);
		arr[0] = last;
		int dst = 1;
		
		for (int src = 1; src < n; src++) {
			String firstName = firstName(arr[src], lastIndx);
			if (!firstName.equals( last ) ) {
				System.out.println("arr["+dst+"]=" + firstName);
				arr[dst++] = firstName;
				last = firstName;
			}
		}
		
		// nullify remaining elements
		while( dst < n )
			arr[dst++]  = null;
		
	}

	private static int firstNameIndx( String fullName ) { 
		return fullName.indexOf( " " ); 
	}

	private static String firstName(String fullName, int indx) {

		if (indx > 0 && fullName.charAt(indx) == ' ')
			return fullName.substring(0, indx);

		return fullName.substring(0, firstNameIndx(fullName));
	}
	
	// time: O( N ), space: O(N)
	public static boolean isCyclic(int[] arr) {

		int n = arr.length;
		boolean[] flags = new boolean[n];
		int count = 0;
		int next = 0;

		while (count < n) {
			next = (next + arr[next]) % n;
			if (flags[next])
				return false;
			flags[next] = true;
			count++;
		}

		return true;

	}
	
	// time: 00:33:33
	// O( n log( n ) )
	public static boolean winnable(int[] arr) {

		int lastIndx = arr.length - 1;
		TreeSet<Integer> flags = new TreeSet<Integer>();
		// last elements is part of solution
		flags.add(lastIndx);

		for (int i = lastIndx - 1; i >= 0; i--) {
			int reach = i + arr[i];
			Integer indx = flags.floor(reach);

			if ( indx != null )
				flags.add(i);
		}
		
		System.out.println(flags);
		return flags.first() == 0;

	}
	
	public static int[] add(int[] D, int add) {
		int length = D.length;
		for (int indx = length - 1; indx >= 0 && add > 0; indx--) {
			int x = D[indx];
			x = x + add;
			D[indx] = x % 10;
			add = x / 10;
			System.out.println("add = " + add);
		}

		// need to allocate a new array location at start
		if (add > 0) {
			D = Arrays.copyOf(D, length + 1);
			System.arraycopy(D, 0, D, 1, length);
		}
		
		D[0] = add;
		
		return D;
	}
	
	public static void rotate(int[][] matrix, int rows, int cols) {
		// rotated cells so far
		int cellsSoFar = 0;
		// number of cells except center for odd matrix
		int cells = rows * cols;
		cells = cells - (cells % 2);

		for (int row = 0; row < rows / 2 && cellsSoFar < cells; row++) {
			for (int col = 0; col < cols - row - 1; col++) {
				int nextRow = row;
				int nextCol = col;
				int curRow;
				int curCol;
				ArrayList<Integer> tmpList = new ArrayList<Integer>();
				tmpList.add(matrix[row][col]);

				for (int side = 1; side <= 4; side++) {
					curRow = nextRow;
					curCol = nextCol;
					nextRow = curCol;
					nextCol = cols - curRow - 1;
					int tmp = matrix[nextRow][nextCol];
					matrix[nextRow][nextCol] = tmpList.get( tmpList.size() - 1 );
					tmpList.add(tmp);
				}
				cellsSoFar += 4;
			}
		}
	}
	
	public static String printMatrix(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		for( int row = 0; row < matrix.length; row++) {
			for( int col = 0; col < matrix[0].length; col++) {
				sb.append(matrix[row][col] + "  ");
			}
			sb.append("\n");
		}
		return new String(sb);
	}
	
	public static void printArray( int[] arr ) {
		StringBuilder sb = new StringBuilder();
//		System.out.println(arr.length);
		sb.append("{ ");
		for(int x : arr)
			sb.append(x).append(" ");
		sb.append("}");
		System.out.println( sb );
	}
	
	public static void printArray( Object[] arr ) {
		StringBuilder sb = new StringBuilder();
//		System.out.println(arr.length);
		sb.append("{ ");
		for(Object x : arr)
			sb.append(x).append(",");
		sb.append("}");
		System.out.println( sb );
	}
	
	public static Object[] union(Object[] arr1, Object[] arr2) {
		Object[] union = new Object[arr1.length + arr2.length];
		int dst = 0;
		for (int src = 0; src < arr1.length; src++, dst++)
			union[dst] = arr1[src];

		for (int src = 0; src < arr2.length; src++, dst++)
			union[dst] = arr2[src];

		return union;
	}
	
}
