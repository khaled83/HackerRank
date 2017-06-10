package com.amazon.dp;

import java.util.*;

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

	public static int[] maxSubArray1(int[] arr) {
	    
	    int n = arr.length;
	    int[][] res = new int[n][n];
	    
	    for (int i=0; i<n; i++)
	        for (int j=0; j<n; j++) {
	            res[i][j] = Integer.MIN_VALUE;
	        }
	    
	    int max = Integer.MIN_VALUE;
	    int maxStart = -1;
	    int maxEnd = -1;
	    for (int i=0; i<n; i++)
	        for (int j=0; j<n; j++) {
	            if (res[i][j] == Integer.MIN_VALUE) {
	                // calc
	                int start = Math.min(i,j);
	                int end = Math.max(i,j);
	                res[i][j] = 0;
	                for (int indx=start; indx<=end; indx++) {
	                    res[i][j] += arr[indx];
	                }
	                if (res[i][j] > max) {
	                    max = res[i][j];
	                    maxStart = start;
	                    maxEnd = end;
	                }
	                res[j][i] = res[i][j];
	            }
	        }
	    
	    int[] resArr = new int[maxEnd-maxStart+1];
	    for (int src=maxStart, dst=0; src<=maxEnd; src++, dst++)
	    	resArr[dst] = arr[src];
	    
	    return resArr;

	}
	
	public static int[] maxSubArray2(int[] arr) {

	    int n = arr.length;
	    int[] sum = new int[n];
	    sum[0] = arr[0];
	    
	    for (int i=1; i<n; i++)
	        sum[i] = arr[i] + sum[i-1];
	        
	    
	    int first = 0, second = n-1;
	    int max = Integer.MIN_VALUE;
	    int maxFirst = -1, maxSecond = n;
	    
	    for (int left=1, right=n-1; left <= right; left++, right--) {
	        // detect elevation point from left
	        if (sum[left] > sum[left-1]) {
	            first = left;
	        }
	        
	        if (sum[right] > sum[right-1]) {
	            second = right;
	        }
	        
	        if ((sum[second] - sum[first]) > max) {
	            max = sum[second] - sum[first];
	            maxFirst = first;
	            maxSecond = second;
	        }
	    }
	    
	    int[] resArr = new int[maxSecond-maxFirst+1];
	    for (int src=maxFirst, dst=0; src<=maxSecond; src++, dst++)
	    	resArr[dst] = arr[src];
	    
	    return resArr;
	    
	    
	}

	public static int maxSubArraySum(int[] arr) {
	    int max = 0;
	    int sumSoFar = 0;
	    
	    for (int i=0; i<arr.length; i++) {
	        sumSoFar += arr[i];
	        sumSoFar = Math.max(0, sumSoFar);
	        max = Math.max(max, sumSoFar);
	    }
	    
	    return max;
	}
	
	/**
	 * finds number of ways to climb stairs using 1,2, or 3 steps with dynamic programming
	 */
	public static int combiClimbStairsDP(int n) {
	    int[] res = new int[n];
	        
	    for (int step=1; step<=3; step++) {
	        for (int i=step-1; i<n; i++) {
	        	res[i] = 0;
	            for (int j=1; j<=step; j++)
	                if ((i-j) >= 0) 
	                    res[i] += res[i-j];
	                else
	                    res[i] += 1;
	        }
	    }
	    
	    return res[n-1];
	}


	/**
	 * finds number of ways to climb stairs using 1,2, or 3 steps with recursion and memoization
	 */
	public static int combiClimbStairsRecursion(int n) {
	    int[] memo = new int[n+1];
	    return combiClimbStairsRecursion(n, memo);
	}

	/**
	 * finds number of ways to climb stairs using 1,2, or 3 steps with recursion 
	 */
	private static int combiClimbStairsRecursion(int n, int[] memo) {
	    
		if (n < 0)
	        return 0;
		
	    if (memo[n] != 0)
	        return memo[n];
	    
	    if (n == 0 || n == 1)
	        memo[n] = 1;
	    else
	        memo[n] = combiClimbStairsRecursion(n-3, memo) + combiClimbStairsRecursion(n-2, memo) + combiClimbStairsRecursion(n-1, memo);
	        
	    return memo[n];
	}
	
	public static String findPath(int[][] arr, int rows, int cols) {
	    int[][] flags = new int[rows][cols];
	    
	    flags[rows-1][cols-1] = 1;
	    
	    for (int row = rows-1; row >= 0; row--) {
	        for (int col = cols-1; col >= 0; col--) {
	        	// skip out of limit cells
	        	if (arr[row][col] == 0)
	        		continue;
	            int right = col+1;
	            if (right < cols && flags[row][right] > 0)
	            	flags[row][col] = 1;
	            int down = row+1;
	            if (down < rows && flags[down][col] >0)
	            	flags[row][col] = 1;
	        }
	    }
	    
	    int row = 0, col = 0;
	    StringBuilder path = new StringBuilder();
	    path.append("("+row+","+col+") ");
	    
	    boolean impossible = false;
	    
	    while ((row != rows-1 || col != cols-1) && !impossible) {
	        int right = col+1;
	        int down = row+1;
	        if (right < cols && flags[row][right] > 0) {
	            col = right;
	            path.append("("+row+","+col+") ");
	        }
	        else if (down < rows && flags[down][col] > 0) {
	            row = down;
	            path.append("("+row+","+col+") ");
	        }
	        else {
	            impossible = true;
	        }
	    }
	    
	    if (impossible)
	        return "IMPOSSIBLE";
	    else
	        return new String(path);
	}
	
	public static List<Integer> primes(int n) {

	    List<Integer> res = new ArrayList<Integer>(n);
	    // zeros by default
	    int[] arr = new int[n]; // 0..n-1
	    
	    for (int base = 2; base < n; base++) {
	        // number is prime since its not reached from a smaller base
	        if (arr[base] == 0) {
	            res.add(base);
	            // if number is not prime, ignore it, as its multipliers are covered by lower bases
	            for (int sum = base; sum < n; sum += base) {
	                arr[sum] = base; // sometimes overrides, can add IF statement to keep lowest base
	            }  
	        }
	    }
	    
	    return res;
	}
}
