package com.amazon.algorithms;

import java.util.*;
import java.lang.Math;

public class TwoPointers {
	
	/**
	arr    -1 2 1 -4
	               p

	sorted  -1 1 2
	        f    l

	sum     1

	K=3
	Time: O(n log(K)) = O(n)
	*/
	public static int sumOfThree(ArrayList<Integer> arr, int target) {
	    int n = arr.size();
	    // 3 integers are sorted according to how close they are to target
	    TreeSet<Integer> closest = new TreeSet<Integer>(new Comparator<Integer>() {
	        public int compare(Integer a, Integer b) {
	            return Integer.valueOf(Math.abs(a - target)).compareTo(Math.abs(b - target));
	        }
	    });
	    
	    int sumSoFar = 0;
	    for (int x : arr) {
	        closest.add(x);
	        sumSoFar += x;
	        if (closest.size() > 3) {
	            Integer first = closest.first();
	            Integer last = closest.last();
	            if (Math.abs(first.compareTo(target)) < Math.abs(last.compareTo(target))) {
	                // first is closer, remove last
	                closest.remove(last);
	            }
	            else {
	                // last is closer, remove first
	                closest.remove(first);
	            }
	        }
	    }
	    
	    int sum = 0;
	    // -5, 1, 4, -7, 10, -7, 0, 7, 3, 0, -2, -5, -3, -6, 4, -7, -8, 0, 4, 9, 4, 1, -8, -6, -6, 0, -9, 5, 3, -9, -5, -9, 6, 3, 8, -10, 1, -2, 2, 1, -9, 2, -3, 9, 9, -10, 0, -9, -2, 7, 0, -4, -3, 1, 6, -3
//	    System.out.println("TREE:");
//	    for (int x : closest) {
//	    	System.out.print(x + " ");
//	        sum += x;
//	    }
	    
	    return sum;
	}

}
