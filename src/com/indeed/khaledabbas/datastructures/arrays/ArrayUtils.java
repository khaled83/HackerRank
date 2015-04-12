package com.indeed.khaledabbas.datastructures.arrays;

import java.util.*;

public class ArrayUtils {

	public static void main(String[] args) {
		// didn't work for 4x4
		int[][] arr = { {0, 1, 2},
						{3, 4, 5},
						{6, 7, 8} 
					  };
		System.out.println( printMatrix(arr) );
		rotate(arr, 3, 3);
		System.out.println( printMatrix(arr) );
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
	
	
}
