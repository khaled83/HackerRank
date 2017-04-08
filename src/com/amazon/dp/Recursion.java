package com.amazon.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Recursion {

	public static void arrayOfSum(int[] arr) {
		sumArray(arr, arr.length);
	}

	private static void sumArray(int[] arr, int n) {
		if (n == 0 || n == 1)
			return;
		else {
			sumArray(arr, n - 1);
			arr[n - 1] += arr[n - 2];
		}
	}

	public static int power(int x, int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException("power must be positive, given is " + n);

		if (n == 0)
			return 1;
		else
			return x * power(x, n - 1);
	}

	public static void towersOfHanoi(char src, char dst, char buf, int n) {
		if (n <= 0)
			return;
		towersOfHanoi(src, buf, dst, n - 1);
		System.out.println("Move " + n + " from " + src + " to " + dst);
		towersOfHanoi(buf, dst, src, n - 1);
	}

	public static void bubbleSort(int[] arr) {
		bubbleSort(arr, arr.length);
	}

	private static void bubbleSort(int[] arr, int n) {
		if (n == 0)
			return;
		bubble(arr, n);
		bubbleSort(arr, n - 1);
	}

	private static void bubble(int[] arr, int n) {
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1])
				swap(arr, i, i + 1);
		}
	}

	private static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	public static long fib(int n) {
		if (n == 1 || n == 2)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

	public static long fib2(int n) {
		/***
		 * n r f s 1 1 2 1 1 1 3 2 1 2 4 3 2 3 5 5 3 5 6 8 5 8 7 13
		 */

		if (n == 1 || n == 2)
			return 1;

		long first = 1, second = 1, result = 0;

		for (int i = 3; i <= n; i++) {
			result = first + second;
			first = second;
			second = result;
		}

		return result;
	}

	private static long[] memo = new long[100];

	public static long fib3(int n) {
	    if (n <= 0 || n > 100)
	        throw new IllegalArgumentException("Value must be between 0 and 100 (given " + n + ")");
	    
	    if (n == 1 || n == 2)
	        return 1;
	    else if (memo[n] == 0)
	        memo[n] = fib3(n-2) + fib3(n-1);
	    
	    return memo[n];
	}
	
	public static int[][] costs;

	public static int cheapest(int src, int dst) {
		if (src == dst)
			return 0;
		else if (adjacent(src, dst))
			return cost(src, dst);
		else
			return Math.min(
					cost(src, dst), 
					Math.min(cheapest(src,src+1) + cheapest(src+1, dst),
							cheapest(src, dst-1) + cheapest(dst-1, dst))
					);
	}
	
	// Book Implementation 
	public static int cheapest2(int src, int dst) {
		if (src == dst || adjacent(src, dst))
			return costs[src][dst];
		
		int minCost = costs[src][dst];
		
		for (int i=src+1; i<dst; i++) {
			int tmp = cheapest(src, i) + cheapest(i, dst);
			if (tmp < minCost)
				minCost = tmp;
		}
		
		return minCost;
	}

	// can be final, but for testing purposes its reinitialized
	public static final int[][] memoCost = new int[5][5];
	
	public static int cheapest3(int src, int dst) {
		if (src == dst)
			return 0;
		
		if (adjacent(src, dst))
			return costs[src][dst];
		else if (memoCost[src][dst] == 0) {
			int minSoFar = costs[src][dst];
			for (int loc=src+1; loc<dst; loc++) {
				minSoFar = Math.min(minSoFar, 
									cheapest3(src, loc) + cheapest(loc, dst)
									);
			}
			memoCost[src][dst] = minSoFar;
		}
		return memoCost[src][dst];
	}

	public static int cheapest4(int n) {
	    int[] minCosts = new int[n];
	    minCosts[0] = 0;
	    minCosts[1] = costs[0][1];
	    for (int loc = 2; loc < n; loc++) {
	        int minCost = costs[0][loc];
	        for (int breakLoc = 1; breakLoc < loc; breakLoc++) {
	            minCost = Math.min(minCost,
	                                minCosts[breakLoc] + costs[breakLoc][loc]
	                              );
	        }
	        minCosts[loc] = minCost;
	    }
	    return minCosts[n-1];
	}
	
	public static void reviveMemo() {
		for (int i=0; i<5; i++)
			for (int j=0; j<5; j++)
				memoCost[i][j] = 0;
	}
	
	private static boolean adjacent(int src, int dst) {
		return dst - src == 1;
	}

	private static int cost(int src, int dst) {
		return costs[src][dst];
	}

	/**
	 * Finds the maximum substring with two halves having similar numbers of each character.
	 * Solution is brute force.
	 */
	public static int maxSubStringWithAnagramHalves(String s) {
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if (isMirror(s.substring(i, j + 1)))
					max = Math.max(max, j - i + 1);
			}
		}
		return max;
	}

	private static boolean isMirror(String s) {
		int mid = s.length() / 2;
		HashMap<Character, Integer> chars = new HashMap<Character, Integer>();

		// first half of string
		for (char c : s.substring(0, mid).toCharArray()) {
			Integer count = chars.get(c);
			if (count == null)
				chars.put(c, 0);
			chars.put(c, chars.get(c) + 1);
		}

		for (char c : s.substring(mid, s.length()).toCharArray()) {
			Integer count = chars.get(c);
			if (count == null || count == 0)
				return false;
			if (count == 1)
				chars.remove(c);
			else
				chars.put(c, --count);
		}

		return chars.isEmpty();
	}
	
	/**
	 * Finds the maximum substring with two halves with same sum of numbers
	 * brute force
	 */
	public static int maxSumStrLength(String s) {
	    int maxSoFar = 0;
	    for (int i = 0; i < s.length(); i++) {
	        for (int j = i + 1; j < s.length(); j+=2) {
	            if (isEqualSumsAroundMid(s.substring(i, j+1)))
	                maxSoFar = Math.max(maxSoFar, j-i+1);
	        }
	    }
	    return maxSoFar;
	}

	// brute force
	private static boolean isEqualSumsAroundMid(String s) {
	    /**
	          *
	        01234
	        abcde
	        
	           *
	        012345
	        abcdef
	    */

	    if (s.length()%2 == 1)
	        return false;
	        
	    int mid = s.length()/2;
	    int sumFirstHalf = 0;
	    
	    for (char c : s.substring(0, mid).toCharArray())
	        sumFirstHalf += Character.getNumericValue(c);
	    
	    int sumSecondHalf = 0;
	    for (char c : s.substring(mid, s.length()).toCharArray())
	        sumSecondHalf += Character.getNumericValue(c);
	    
	    return sumFirstHalf == sumSecondHalf;
	}
	
	/**
	 * Finds the maximum substring with two halves with same sum of numbers
	 * dynamic programming
	 */
	public static int maxSumStrLengthDP(String s) {
	    // calculate sums so far in both directions
	    int n = s.length();
	    int[] before = new int[n];
	    int[] after = new int[n];
	    
	    before[0] = Character.getNumericValue(s.charAt(0));
	    for (int i=1; i<n; i++)
	        before[i] = Character.getNumericValue(s.charAt(i)) + before[i-1];
	        
	    after[n-1] = Character.getNumericValue(s.charAt(n-1));
	    for (int i=n-2; i>=0; i--)
	        after[i] = Character.getNumericValue(s.charAt(i)) + after[i+1];
	    
	    int maxSoFar = 0;
	    for (int i = 0; i < s.length(); i++) {
	        for (int j = i + 1; j < s.length(); j++) {
	            if (isEqualSumsAroundMidDP(i, j, before, after))
	                maxSoFar = Math.max(maxSoFar, j-i+1);
	        }
	    }
	    return maxSoFar;
	}

	// dynamic programming
	private static boolean isEqualSumsAroundMidDP(int start, int end, int[] before, int[] after) {
	    // range has to be even
	    int n = end - start + 1;
	    if (n % 2 == 1)
	        return false;
	        
	    /**
	         *
	        3456
	        abcd
	        
	         *
	        2345
	        abcd
	    */
	        
	    int mid = (start+end) / 2;
	    int sumFirst = before[mid] - (start > 0 ? before[start-1] : 0);
	    int sumSecond = after[mid+1] - (end < (after.length-1) ? after[end+1] : 0);
	    
	    return sumFirst == sumSecond;
	}

	public static int combiRecursion(int n, int k) {
		if (n == 0 || k == 0 || n == k)
			return 1;
		else
			return combiRecursion(n-1, k) + combiRecursion(n-1, k-1);
	}
	
	public static int combiDP(int n, int k) {

		// construc pascal triangle
		int rows = n + 1;
		ArrayList<Integer>[] values = new ArrayList[n + 1];
		values[0] = new ArrayList<Integer>(1);
		values[0].add(1);

		for (int i = 1; i < rows; i++) {
			values[i] = new ArrayList<Integer>(i + 1);
			values[i].add(1);
			for (int j = 1; j < i + 1; j++) {
				int first = values[i - 1].size() > (j - 1) ? values[i - 1].get(j - 1) : 0;
				int second = values[i - 1].size() > j ? values[i - 1].get(j) : 0;
				values[i].add(first + second);
			}
		}

		return values[n].get(k);
	}
	
}

















