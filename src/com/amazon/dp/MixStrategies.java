package com.amazon.dp;

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
	
	public static int combiEmptyPlot(int n) {
	    int combi = 1;
	    for (int col=2; col<=n; col++) {
	        // number of possible placements for 1 tile within #col
	        int placements = col + ((col-1) * 2);
	        combi = placements * combi;
	    }
	    
	    return combi;
	}
}
