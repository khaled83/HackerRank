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
		System.out.println( printMatrix(arr) ); **/
		
		int[] arr = { 9, 9, 9 };
		arr = add( arr, 5);
		printArray( arr );
		
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
		System.out.println(arr.length);
		sb.append("{ ");
		for(int x : arr)
			sb.append(x).append(" ");
		sb.append("}");
		System.out.println( sb );
	}
	
}
