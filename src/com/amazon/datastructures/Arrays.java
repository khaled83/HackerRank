package com.amazon.datastructures;

import com.khaledabbas.datastructures.arrays.ArrayUtils;
import java.util.*;

public class Arrays {
	
	/**
	arr    3 5 4 2
	       i
	       j

	       0 1 2 3 4 5 
	arr    6 7 3 5 4 2 
	         i
	         j
	         
	       0 1 2 3 4 5
	       6 3 7 5 4 2
	       f     l
	           x x x x
	             
	optimizations:
	(1) ignore a[i] > 6
	(2) traverse j from i + maxGap + 1
	(3) traverse i up to n - max - 1
	Still O(n^2)
	                 
	i    j    gap    max    f    l
	-    -    ---    ---    -    -
	0    1    -1     0      -1   -1
	     2    2      2      0    2
	     3        
	     4        
	     5        
	1    4    3      3      1    3
	     5    
	2    -    -
	3
	4
	5
	*/
	public static int maximumGap(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
	    int first = -1, last = -1;
	    int n = arr.length;
	    int maxGap = 0;
	    for (int i = 0; i < n - maxGap - 1; i++) {
	        // terminate when further traversal won't lead to larger than maxGap
	    	// ignore values larger than (or equal) first, as first will have larger gap
	    	if (first < 0 || arr[i] <= arr[first]) {
	    		for (int j = i + 1 + maxGap; j < n; j++) {
	                if (arr[j] >= arr[i]) {
	                    int gap = j - i;
	                    if (gap > maxGap) {
	                        maxGap = gap;
	                        first = i;
	                        last = j;
	                    } 
	                    else if (gap == maxGap) {
	                    	maxGap = -1;
	                    }
	                }
	            }
	        }
	    }
	    
	    return maxGap;
	}

	public static int[] plusOne(int[] arr) {
	    int n = arr.length;
	    int carryover = 1;
	    for (int indx = n - 1; indx >= 0 && carryover > 0; indx--) {
	        int x = arr[indx] + carryover;
	        if (x >= 10) {
	            arr[indx] = 0;
	            carryover = 1;
	        }
	        else {
	            arr[indx] = x;
	            carryover = 0;
	        }
	    }
	    
	    // new decimal is one digit longer
	    if (carryover > 0) {
	        int[] res = new int[n + 1];
	        System.arraycopy(arr, 0, res, 1, n);
	        res[0] = 1;
	        return res;
	    }
	    else {
	        return arr;
	    }
	}

	public static boolean isReachable(int[] arr) {
	    int d = arr.length - 1;
	    for (int i = d - 1; i >= 0; i--) {
	        if (i + arr[i] >= d) {
	            d = i;
	        }
	    }
	    return d == 0;
	}
	
	// Elements of Programming Interviews 5.5: Delete duplicates from a sorted array
	public static int removeDuplicates(int[] arr) {
		int dst = 0;
	    for (int src = 0, last = -1;
	            src < arr.length; src++) {
	        if (arr[src] != last) {
	            arr[dst] = arr[src];
	            last = arr[src];
	            dst++;
	        }
	    }
	    return dst;
	}
	
	// Elements of Programming Interviews 5.5: Delete duplicates from a sorted array
	public static int removeDuplicates2(int[] arr) {
	    int dst = 1;
	    for (int src = 1; src < arr.length; src++) {
	        arr[dst] = arr[src];
	        if (arr[dst] != arr[dst - 1]) {
	            dst++;
	        }
	    }
	    
	    return dst;
	}

	public static int maxProfit(int[] arr) {
	    int min = Integer.MAX_VALUE;
	    int maxProfit = Integer.MIN_VALUE;
	    for (int x : arr) {
	        min = Math.min(x, min);
	        maxProfit = Math.max(maxProfit, x - min);
	    }
	    return maxProfit;
	}

	public static void alternate(int[] arr) {

	    int n = arr.length;
	    int[] sorted = new int[n];   
	    System.arraycopy(arr, 0, sorted, 0, n);
	    java.util.Arrays.sort(sorted);
	    
	    int mid = n / 2;
	    int low = 0;
	    int high = mid + (n % 2);
	    int dst = 0;
	    while (high < n) {
	        arr[dst++] = sorted[low++];
	        arr[dst++] = sorted[high++];
	    }
	    
	    if (n % 2 == 1) {
	        arr[dst] = sorted[mid];
	    }
	}
	
	public static void alternate2(int[] arr) {
	    for (int i = 0; i < arr.length - 1; i++) {
	        if ((i % 2 == 0 && arr[i] > arr[i + 1]) || (i % 2 != 0 && arr[i] < arr[i + 1])) {
	            swap(arr, i, i + 1);
	        }
	    }
	}

	private static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}
	
	public static List<Integer> primesLessThan(int x) {
	    int[] mem = new int[x - 1];
	    List<Integer> res = new java.util.ArrayList<Integer>();
	    mem[0] = 1;
	    
	    for (int i = 1; i < x - 1; i++) {
	        int num = i + 1;
	        if (mem[i] == 0) {
	            res.add(num);
	            // calc multipliers of num
	            // solve it for two
	            int mul = num;
	            while (mul < x) {
	                mem[mul - 1] = mul;
	                mul += num;
	            }
	        }
	    }
	    
	    return res;
	}
	
	public static void randomSubset(int[] arr, int size) {
	    int n = arr.length;
	    for (int i = 0; i < size; i++) {
	        int indx = i + (int) (Math.random() * (n - i - 1)); // 0..remaining
	        swap(arr, i, indx);
	    }
	}

	// imcomplete, fails some use cases
	public static int[] nextHigherPerm(int[] arr) {
	    int n = arr.length;
	    int high = n  - 1;
	    int low = high - 1;
	    while (low >= 0 && arr[low] >= arr[high]) {
	        low--;
	    }
	    int[] res = new int[] {};
	    if (low >= 0) {
	        res = new int[n];
	        System.arraycopy(arr, 0, res, 0, n);
	        swap(res, low, high);
	    }
	    return res;
	}

	public static boolean hasSum(int[] arr, int x) {
	    int sumSoFar = 0;
	    // edge case may not work when last exceeds length and larger than sum
	    for (int first = 0, last = 0; last < arr.length;) {
	        if (sumSoFar == x) {
	            return true;
	        }
	        // works with positive
	        else if (sumSoFar > x) {
	            sumSoFar -= arr[first];
	            first++;
	        }
	        else {
	            sumSoFar += arr[last];
	            last++;
	        }
	    }
	    return false;
	}
	
	public static void zeroMatrix(int[][] arr) {
	    if (arr == null) {
	        return;
	    }
	    
	    int numRows = arr.length;
	    int numCols = arr[0].length;
	    // register rows and cols with a zero cell
	    HashSet<Integer> rows = new HashSet<Integer>();
	    HashSet<Integer> cols = new HashSet<Integer>();
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numCols; col++) {
	            if (arr[row][col] == 0) {
	                rows.add(row);
	                cols.add(col);
	            }
	        }
	    }
	    // zerofy cells
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numCols; col++) {
	            if (rows.contains(row) || cols.contains(col)) {
	                arr[row][col] = 0;
	            }
	        }
	    }
	}
	
	public static void zeroMatrix2(int[][] arr) {
	    if (arr == null) {
	        return;
	    }
	    int numRows = arr.length;
	    int numCols = arr[0].length;
	    // use 0s where a row or col should be zerofied
	    int rows = 0;
	    int cols = 0;
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numCols; col++) {
	            if (arr[row][col] == 0) {
	                rows = rows | (1 << row);
	                cols = cols | (1 << col);
	            }
	        }
	    }
	    // zerofy cells
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numCols; col++) {
	            if ((rows & (1 << row)) > 0 || (cols & (1 << col)) > 0) {
	                arr[row][col] = 0;
	            }
	        }
	    }
	}
	
	// non optimal
	@Deprecated
	public static int[] randomNChooseK(int n, int k) {
	    int[] a = new int[n];
	    for (int i = 0; i < n; i++) {
	        a[i] = i;
	    }
	    int i = 0;
	    for (; i < k; i++) {
	        // choose a random element
	        int indx = (int) (i + Math.round(Math.random() * (n - i - 1)));
	        swap(a, i, indx);
	    }
	    int[] res = new int[k];
	    System.arraycopy(a, 0, res, 0, i);
	    return res;
	}
	
	public static int[] randomNChooseK2(int n, int k) {
	    int[] res = new int[k];
	    HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
	    for (int i = 0; i < k; i++) {
	        int indx = (int) (i + Math.round(Math.random() * (n - i - 1)));
	        int val = indx;
	        if (h.containsKey(indx)) {
	            val = h.get(indx);
	        }
	        int swap = i;
	        if (h.containsKey(i)) {
	            swap = h.get(i);
	        }
	        h.put(indx, swap);
	        res[i] = val;
	    }
	    return res;
	}

}











