package com.amazon.dp;

import java.util.ArrayList;

public class MixStrategies {

	/**
	 * finds shortest path from cell 0,0 to cell x,y iteratively
	 */
	public static int shortestPath1(int[][] cost, int x, int y) {
		int rows = cost.length;
		int cols = cost[0].length;
		if (cost == null || rows == 0 || cols == 0)
			return 0;

		int minCost = cost[0][0];

		for (int row = 0, col = 0; row < rows && col < cols;) {
			int nextRow = Math.min(row + 1, rows - 1);
			int nextCol = Math.min(col + 1, cols - 1);
			if (row == y && col == x) {
				return minCost;
			}
			if (nextRow != row && nextCol != col && cost[nextRow][col] < cost[row][nextCol]) {
				row = nextRow;
				minCost += cost[nextRow][col];
			} else if (nextRow != row && nextCol != col) {
				col = nextCol;
				minCost += cost[row][nextCol];
			} else if (nextRow != row) {
				row = nextRow;
				minCost += cost[nextRow][col];
			} else {
				col = nextCol;
				minCost += cost[row][nextCol];
			}
		}
		
		return Integer.MAX_VALUE;
	}

	/**
	 * finds shortest path from cell 0,0 to cell x,y with recursion
	 */
	public static int shortestPath2(int[][] cost, int x, int y) {
	    if (cost == null || cost.length == 0 || cost[0].length == 0)
	        throw new IllegalArgumentException();
	        
	    return cost2(cost, cost.length, cost[0].length, 0, 0, y, x);
	}

	private static int cost2(int[][] cost, int rows, int cols, int row1, int col1, int row2, int col2) {
	    // out of array bounds, unreachable, excluded with min
	    if (row2 < 0 || row2 >= rows || col2 < 0 || col2 >= cols)
	        return Integer.MAX_VALUE;
	    // src and dst on the same cell
	    if (row1 == row2 && col1 == col2)
	        return cost[row1][col1];
	    // expand recursion
	    else
	        return cost[row2][col2]
	                + Math.min(
	                    cost2(cost, rows, cols, row1, col1, row2-1, col2), 
	                    cost2(cost, rows, cols, row1, col1, row2, col2-1));
	}
	
	private static int[][] memo;
	/**
	 * finds shortest path from cell 0,0 to cell x,y with recursion and memoization
	 */
	public static int shortestPath3(int[][] cost, int x, int y) {
	    int rows = cost.length;
	    int cols = cost[0].length;
	    if (cost == null || rows == 0 || cols == 0)
	        throw new IllegalArgumentException();
	        
	    memo = new int[rows][cols];
	    return cost3(cost, rows, cols, 0, 0, y, x);
	}
	
	private static int cost3(int[][] cost, int rows, int cols, int row1, int col1, int row2, int col2) {
		// out of array bounds, unreachable, excluded with min
	    if (row2 < 0 || row2 >= rows || col2 < 0 || col2 >= cols)
	        return Integer.MAX_VALUE;
	    // src and dst on the same cell
	    if (row1 == row2 && col1 == col2)
	        return cost[row1][col1];
	    // expand recursion
	    else if (memo[row2][col2] == 0) {
	        memo[row2][col2] = 
	            cost[row2][col2]
	            + Math.min(
	                cost3(cost, rows, cols, row1, col1, row2-1, col2), 
	                cost3(cost, rows, cols, row1, col1, row2, col2-1));
	    }
	    return memo[row2][col2];
	}

	/**
	 * finds shortest path from cell 0,0 to cell x,y with dynamic programming
	 */
	public static int shortestPath4(int[][] cost, int x, int y) {
	    int rows = cost.length;
	    int cols = cost[0].length;
	    
	    int[][] memo = new int[rows][cols];
	    
	    for (int row = 0; row < rows; row++) {
	    	for (int col = 0; col < cols; col++) {
	    		int up = row > 0 ? memo[row-1][col] : Integer.MAX_VALUE;
	    		int left = col > 0 ? memo[row][col-1] : Integer.MAX_VALUE;
	    		int minCost = Math.min(up, left);
	    		if (minCost == Integer.MAX_VALUE)
	    			minCost = 0;
	    		memo[row][col] = cost[row][col] + minCost;
	    	}
	    }
	    
	    return memo[y][x];
	}
	
	/**
	 * finds shortest path from cell 0,0 to cell x,y with dynamic programming and including diagonal moves
	 */
	public static int shortestPath5(int[][] cost, int x, int y) {
	    int rows = cost.length;
	    int cols = cost[0].length;
	    
	    int[][] memo = new int[rows][cols];
	    
	    for (int row = 0; row < rows; row++) {
	    	for (int col = 0; col < cols; col++) {
	    		int diag = row > 0 && col > 0 ? memo[row-1][col-1] : Integer.MAX_VALUE; 
	    		int up = row > 0 ? memo[row-1][col] : Integer.MAX_VALUE;
	    		int left = col > 0 ? memo[row][col-1] : Integer.MAX_VALUE;
	    		int minCost = Math.min(up, left);
	    		minCost = Math.min(minCost, diag);
	    		if (minCost == Integer.MAX_VALUE)
	    			minCost = 0;
	    		memo[row][col] = cost[row][col] + minCost;
	    	}
	    }
	    
	    return memo[y][x];
	}
	
	public static int combiTilesDistinct(int n) {
	    int combi = 1;
	    for (int col=2; col<=n; col++) {
	        // number of possible placements for 1 tile within #col
	        int placements = col + ((col-1) * 2);
	        combi = placements * combi;
	    }
	    
	    return combi;
	}

	public static int combiTiles1(int n) {
		int[] memo = new int[n];
		return combiTiles1(n, memo);
	}

	private static int combiTiles1(int n, int[] memo) {
		if (memo[n-1] != 0)
			return memo[n-1];

		if (n == 0)
			memo[n-1] = 0;
		else if (n == 1)
			memo[n-1] = 1;
		else if (n == 2)
			memo[n-1] = 2;
		else
			memo[n-1] = combiTiles1(n - 1, memo) + combiTiles1(n - 2, memo);

		return memo[n-1];
	}

	public static int combiTiles2(int n) {
		
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (n == 2)
			return 2;
		
		int[] result = new int[n];
		result[0] = 1;
		result[1] = 2;
		/**
		 * 1 2 3 5 f s r 5
		 */
		//
		//
		for (int i = 2; i < n; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result[n - 1];
	}

	public static int combiTiles3(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (n == 2)
			return 2;

		int first = 1;
		int second = 2;
		int result = 0;

		for (int i = 3; i <= n; i++) {
			result = first + second;
			first = second;
			second = result;
		}

		return result;
	}
	
	public static int combiTripleTiles(int n) {
	    if (n % 2 == 1)
	        throw new IllegalArgumentException("n="+n);
	    if (n == 0)
	        return 0;
	    else if (n == 2)
	        return 3;
	    else
	        return 2 * combiTripleTiles(n-2) + 1;
	}

	public static int combiTripleTilesDP(int n) {
	    if (n % 2 == 1)
	        throw new IllegalArgumentException();
	    if (n == 0)
	        return 0;
	    else if (n == 2)
	        return 3;
	        
	    int result = 3;
	    /**
	    2 4 6 8
	   [0 1 2 3]
	        i    
	    r=15
	    3 7 15 31
	    
	    */
	    for (int i=1; i < n/2; i++) {
	        result = 2 * result + 1;
	    }
	    
	    return result;
	    
	    /***
	    int[] result = new int[n/2];
	    result[0] = 3;
	    for (int i=1; i < n/2; i++) {
	        result[i] = 2 * result[n-1] + 1;
	    }
	    **/
	}

	/**
	N     combi                        combi vals          
	-     -----                        ---------
	3     1                        
	5     1
	10    2 (5,5) (10)                (5,5) (10)
	13    3 (5,5,3) (5,3,5)           (5,5,3) (10,3)
	        (3,5,5) (10,3) (3,10)
	23                                (5,5,3,5,5) (5,5,3,10) (10,3,5,5) (10,3,10)

	3,5,10


	3,6,9,12,15,18,21,24
	3,8,13,18,23,28
	3,13,23

	5,8,11,14,17,20,23
	5,10,15,20,25
	5,15,25

	10,13,16,19,22,25
	10,15,20,25
	10,20,30


	arr       0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
	---      [                                                             ]
	         *      1   1 1   2 1 2  1  1  3  2  3  1  1  2  1  4  1  1  3
	         
	3               1     1     1       1        1        1        1
	+5                        1            1              1              1
	+10                                    1                             1

	5                   1         1              1              1        
	+3                        1      1        1        1        1        1
	+10                                       1                                           

	10                            1                             1
	+3                                     1        1        1         1
	+5                                           1              1   

	*/

	public static int combi(int N, ArrayList<Integer> steps) {
	    
	    
	    int[] arr = new int[N+1];
	    
	    for (int step : steps) {
	        for (int i = step; i <= N; i = i + step) {
	            arr[i]++;
	        }        
	    }
	    
	    for (int i = 0; i <= N; i++) {
	        if (arr[i] != 0) {
	            for (int step : steps) {
	                for (int j = i; j <= N; j = j + step)
	                    arr[j]++;
	            }
	        }
	    }
	    
	    return arr[N];
	    
	}

	
}
