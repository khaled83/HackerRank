package com.amazon.dp;

import java.util.*;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class Recursion2 {
	
	private static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	public static void sumPrevious(int[] arr) {
	    sum(arr, arr.length - 1);
	}

	private static int sum(int[] arr, int indx) {
	    if (indx == 0) {
	        return arr[0];
	    }
	    
	    arr[indx] = arr[indx] + sum(arr, indx - 1);
	    return arr[indx];
	}
	
	public static int pow(int x, int n) {
	    if (n == 0) {
	        return 1;
	    }
	    
	    return x * pow(x, n - 1); 
	}
	
	public static void towersOfHanoi(int n, char s, char d, char e) {
	    move(n, s, d, e);
	}

	private static void move(int n, char s, char d, char e) {
	    if (n == 1) {
	        System.out.println(s + " => " + d);
	        return;
	    }
	    
	    move(n - 1, s, e, d);
		move(1, s, d, e);
	    move(n - 1, e, d, s);
	}
	
	public static void bubbleSortIterative(int[] arr) {
	    int n = arr.length;
	    boolean sorted = false;
	    for (int last = n - 1; last >= 1 && !sorted; last--) {
	        sorted = true;
	        for (int i = 0; i < last; i++) {
	            if (arr[i] > arr[i + 1]) {
	                swap(arr, i, i + 1);
	                sorted = false;
	            }
	        }
	    }
	}
	
	public static void bubbleSortRecursive(int[] arr) {
		bubble2(arr, arr.length);
	}

	private static void bubble2(int[] arr, int n) {
	    if (n == 1) {
	        return;
	    }
	    
	    for (int i = 0; i < n - 1; i++) {
	        if (arr[i] > arr[i + 1]) {
	            swap(arr, i, i + 1);
	        }
	    }
	    
	    bubble2(arr, n - 1);
	}
	
	public static int fib(int n) {
	    if (n == 1 || n == 2) {
	        return 1;
	    }
	    
	    return fib(n - 1) + fib(n - 2);
	}
	
	public static int fibIterative(int n) {
	    int first = 1, second = 1;
	    
	    int res = 1;
	    
	    for (int i = 3; i <= n; i++) {
	        res = first + second;
	        first = second;
	        second = res;
	    }
	    
	    return res;
	}

	public static int minCost(int[][] cost) {
	    int n = cost.length;
	    return minCost(0, n - 1, cost);
	}

	private static int minCost(int src, int dst, int[][] cost) {
	    // terminating condition 0 when src == dst or src == dst - 1
		int minCost = cost[src][dst];
	    if (src < (dst - 1)) {
	        for (int tmp = src + 1; tmp < dst; tmp++) {
	            minCost = Math.min(minCost, minCost(src, tmp, cost) + minCost(tmp, dst, cost));
	        }
	    }
	    return minCost;
	} 

	private static int minCost(int[][] costs, int src, int dst, int[][] memo) {
	    if (src >= dst) {
	        return 0;
	    }
	    
	    int cost = memo[src][dst];
	    
	    if (cost == 0) {
	    		cost = costs[src][dst];
	        for (int i = src + 1; i < dst; i++) {
	            cost = Math.min(cost, minCost(costs, src, i, memo) + minCost(costs, i, dst, memo));
	        }
	    }
	    memo[src][dst] = cost;
	    return cost;
	}
	
	public static int minCostMemo(int[][] costs, int src, int dst) {
	    int n = costs.length;
	    int[][] memo = new int[n][n];
	    return minCost(costs, src, dst, memo);
	}
	
	public static int minCostDP(int[][] weights) {
	    int n = weights.length;
	    int[] costs = new int[n];
	    
	    int src = 0;
	    costs[src] = 0;
	    
	    for (int dst = 1; dst < n; dst++) {
	        int cost = weights[src][dst];
	        for (int i = src + 1; i < dst; i++) {
	            cost = Math.min(cost, costs[i] + weights[i][dst]);
	        }
	        costs[dst] = cost;
	    }
	    return costs[n - 1];
	}
	
	public static int fibWithMemo(int x) {
	    int[] memo = new int[x + 1];
	    memo[1] = 1;
	    memo[2] = 1;
	    return fib(x, memo);
	}

	private static int fib(int x, int[] memo) {    
	    if (memo[x] == 0) {
	        memo[x] = fib(x - 1) + fib(x - 2);
	    }
	    return memo[x];
	}

	public static int maxLengthWithEqualSumHalfs(int[] arr) {
	    int max = 1;
	    int n = arr.length;
	    
	    // pre calc sum so far array O(n)
	    int[] sum = new int[n];
	    sum[0] = arr[0];
	    for (int i = 1; i < n; i++) {
	        sum[i] = arr[i] + sum[i - 1];
	    }
	    
	    ArrayUtils.printArray(sum);
//	    9, 4, 3, 0, 7, 2, 3
	    
	    // calculate max lenght O(n^2)
	    for (int first = 0; first < n - 1; first++) {
	        for (int last = 1; last < n; last += Math.max(max, 1)) {
	            int length = last - first + 1;
	            boolean isOddLength = length % 2 == 1;
	            int mid = last / 2;
	            int h1 = sum[mid] - (first >  0 ? sum[first - 1] : 0); 
	            int h2 = sum[last] - sum[mid];
	            if (isOddLength) {
	                h1 -= arr[mid];
	            }
	            if (h1 == h2) {
	                max = Math.max(max, length);
	            }
	        }
	    }
	    
	    return max;
	}

	private static final int NUM_ROWS = 9;
	private static final int NUM_COLS = 9;
	private static final int NUM_GRIDS = 9;

	public static void solveSudoku(int[][] arr) {
	    int n = arr.length;
	    
	    // store values not set yet in each row, column, and grid
	    List<HashSet<Integer>> rowHashList = new ArrayList<HashSet<Integer>>();
	    List<HashSet<Integer>> colHashList = new ArrayList<HashSet<Integer>>();
	    List<HashSet<Integer>> gridHashList = new ArrayList<HashSet<Integer>>();
	    
	    for (int i = 0; i < n; i++) {
	            rowHashList.add(new HashSet<Integer>() {{
	            add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
	        }});
	            colHashList.add(new HashSet<Integer>() {{
	                add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
	            }});
	            gridHashList.add(new HashSet<Integer>() {{
	                add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
	            }});
	    }
	    
	    // default values to be stored everywhere
	    
	    // remove values already set
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLS; col++) {
	            int x = arr[row][col];
	            if (x > 0) {
	                rowHashList.get(row).remove(x);
	                colHashList.get(col).remove(x);
	                gridHashList.get(gridIndx(row, col)).remove(x);
	            }
	        }
	    }
	    
	    solveSudoku(arr, 0, 0, rowHashList, colHashList, gridHashList, new HashSet<Integer>());
	}
	
	// not working yet
	@Deprecated
	private static boolean solveSudoku(int[][] arr, int row, int col, List<HashSet<Integer>> rowHashList,
			List<HashSet<Integer>> colHashList, List<HashSet<Integer>> gridHashList, HashSet<Integer> skip) {
	    
	    if (col >= NUM_COLS) {
	        row++;
	        col = 0;
	    }
	    
	    if (row == (NUM_ROWS - 1) && col >= NUM_COLS ) {
	    		return true;
	    }
	    
	    System.out.print("(" + row + "," + col + ") => ");
	    
	    int cur = arr[row][col];
	    boolean found = false;
	    int val = 0;
	    if (cur == 0) {
	        // pick a value
//	        for (int x : rowHashList.get(row)) {
	        for (int x = 1; x <= NUM_ROWS; x++) {	
				if (rowHashList.get(col).contains(x) && colHashList.get(col).contains(x)
						&& gridHashList.get(gridIndx(row, col)).contains(x)) {
	                val = x;
	                found = true;
	                break;
	            }
	        }
	        
	        if (!found) {
		        	System.out.println("NOT FOUND");
		        	return false;
	        }
	        
	        System.out.println(val);
	        arr[row][col] = val;
	        rowHashList.get(row).remove(val);
	        colHashList.get(row).remove(val);
	        gridHashList.get(gridIndx(row, col)).remove(val);
	    }

		// System.out.println(ArrayUtils.printMatrix(arr));

		boolean solved = solveSudoku(arr, row, col + 1, rowHashList, colHashList, gridHashList, new HashSet<Integer>());
		if (!solved) {
			System.out.println(" BACKTRACK");
			// backtrack
			arr[row][col] = 0;
			rowHashList.get(row).add(val);
			colHashList.get(row).add(val);
			gridHashList.get(gridIndx(row, col)).add(val);
			skip.add(val);
			solveSudoku(arr, row, col, rowHashList, colHashList, gridHashList, skip);
		}
	    return true;    
	}

	
	// doesn't solve the problem completely due to no backtracking
	@Deprecated
	public static void solveSudoku2(int[][] arr) {
	    int n = arr.length;
	    
	    // store values not set yet in each row, column, and grid
	    List<HashSet<Integer>> rowHashList = new ArrayList<HashSet<Integer>>();
	    List<HashSet<Integer>> colHashList = new ArrayList<HashSet<Integer>>();
	    List<HashSet<Integer>> gridHashList = new ArrayList<HashSet<Integer>>();
	    
	    for (int i = 0; i < n; i++) {
	    		rowHashList.add(new HashSet<Integer>() {{
		        add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
		    }});
	    		colHashList.add(new HashSet<Integer>() {{
	    			add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
	    		}});
	    		gridHashList.add(new HashSet<Integer>() {{
	    			add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(8); add(9);
	    		}});
	    }
	    
	    // default values to be stored everywhere
	    
	    // remove values already set
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLS; col++) {
	            int x = arr[row][col];
	            if (x > 0) {
	                rowHashList.get(row).remove(x);
	                colHashList.get(col).remove(x);
	                gridHashList.get(gridIndx(row, col)).remove(x);
	            }
	        }
	    }
	    
	    // solve
	    for (int row = 0; row < NUM_ROWS; row++) {
	        for (int col = 0; col < NUM_COLS; col++) {
	            int cur = arr[row][col];
	            if (cur == 0) {
	                // pick a value
	                int val = 0;
	                for (int x : rowHashList.get(row)) {
	                    if (colHashList.get(col).contains(x) && gridHashList.get(gridIndx(row, col)).contains(x)) {
	                        val = x;
	                        break;
	                    }
	                }
	                arr[row][col] = val;
	                rowHashList.get(row).remove(val);
	                colHashList.get(row).remove(val);
	                gridHashList.get(gridIndx(row, col)).remove(val);
	            }
	        }
	    }
	    
	}

	private static int gridIndx(int row, int col) {
	    return (row / 3) * 3 + (col / 3);
	}
	
	public static int combination(int n, int k) {
	  if (n == 0 || k == 0 || n == k) {
	    return 1;
	  }
	  
	  return combination(n  - 1, k) + combination(n - 1, k - 1);
	}
	
	public static int combination2(int n, int k) {
	  List<List<Integer>> pascal = new ArrayList<List<Integer>>();
	  // initialize first 2 rows
	  List<Integer> row = new ArrayList<Integer>() {{ add(1); }};
	  pascal.add(row);
	  row = new ArrayList<Integer>() {{ 
	    add(1); add(1);
	  }};
	  pascal.add(row);
	  for (int i = 2; i <= n; i++) {
	    // starts with 1
	    row = new ArrayList<Integer>() {{ add(1); }};
	    for (int j = 1; j < i; j++) {
	      row.add(pascal.get(i - 1).get(j - 1) + pascal.get(i - 1).get(j));
	    }
	    // ends with 1
	    row.add(1);
	    pascal.add(row);
	  }
	  
	  return pascal.get(n).get(k);
	}
	
	public static int minCostRightLeft(int[][] costs) {
	    if (costs == null || costs[0] == null) {
	        return Integer.MIN_VALUE;
	    }
	    
	    int rows = costs.length;
	    int cols = costs[0].length;
	    int[][] minCost = new int[rows][cols];
	    // destination cell
	    minCost[rows - 1][cols - 1] = costs[rows - 1][cols - 1];
	    
	    for (int row = rows - 1; row >= 0; row--) {
	        for (int col = cols - 1; col >= 0; col--) {
	            // cost to the right
	            int right = (col + 1) < cols ? minCost[row][col + 1] : Integer.MAX_VALUE;
	            // cost to the bottom
	            int bottom = (row + 1) < rows ? minCost[row + 1][col] : Integer.MAX_VALUE;
	            // minimum step
	            int min = Math.min(right, bottom);
	            // special case for destination cell
	            if (min == Integer.MAX_VALUE) {
	                min = 0;
	            }
	            // min cost from current cell to destination
	            minCost[row][col] = costs[row][col] + min;
	        }
	    }
	    
	    return minCost[0][0];
	}
	
	// Elements of Programming Interviews 15.10: Compute a Gray Code
	@Deprecated
	public static List<Integer> grayCode(int n) {
	    List<Integer> res = new ArrayList<Integer>();
	    // powers of 2 from 0 to log (n)
	    TreeSet<Integer> mulSet = new TreeSet<Integer>();
	    for (int pow = 0; pow < n; pow++) {
	        mulSet.add((int) Math.pow(2, pow));
	    }
	    res.add(0);
	    grayCode(0, mulSet, res);
	    return res;
	}
	
	private static void grayCode(int x, TreeSet<Integer> mulSet, List<Integer> res) {
	    if (mulSet.isEmpty()) {
	        return;
	    }
	    
	    for (int mul : mulSet.tailSet(x, false)) {
	        int perm = x + (mul);
	        res.add(perm);
	        grayCode(perm, mulSet, res);
	    }
	}
	
	public static int numOfWaysToPlaceTiles(int n) {
	    if (n == 0) {
	        return 0;
	    }
	    
	    if (n == 1) {
	    	return 1;
	    }
	    
	    if (n == 2) {
	        return 2;
	    }
	    
	    return 1 + 2 * (numOfWaysToPlaceTiles(n - 1) - 1);
	}

	public static int numOfWaysToPlaceTiles2(int n) {
		if (n == 0) {
	        return 0;
	    }
	    
	    if (n == 1) {
	        return 1;
	    }
	    
	    int res = 2;
	    
	    for (int i = 3; i <= n; i++) {
	        res = 2 * res - 1;
	    }
	    
	    return res;
	}
	
	public static int numOfWaysToPlaceTiles3(int n) {
		if (n == 0) {
	        return 0;
	    }
	    
	    if (n == 1) {
	    	return 1;
	    }
	    
	    if (n == 2) {
	        return 2;
	    }
	    
	    return numOfWaysToPlaceTiles3(n - 1) + numOfWaysToPlaceTiles3(n - 2);
	}
}
























