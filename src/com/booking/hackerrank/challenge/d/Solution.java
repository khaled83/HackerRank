package com.booking.hackerrank.challenge.d;

import java.util.Scanner;

public class Solution {
	
	/**
	     1        4
	        2        5
	  0        3
	 
	  0  1  2  3  4  5  6  7  8  9
	        s1       e1
	             s2      e2
	    
	    s1 < e2 && e1 > s2
	 
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int curCount = sc.nextInt();
		int N = sc.nextInt();
		
		DataPoint[] data = new DataPoint[N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int indx = 0; indx < N; indx++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			data[indx] = new DataPoint(start, end);
			min = Math.min(min, start);
			max = Math.max(max, end);
		}
		
		int maxCount = Integer.MIN_VALUE;
		for (int second=min; second<=max;second++) {
			int count = 0;
			for (DataPoint p : data) {
				if (second <= p.end && second >= p.start)
					count++;
			}
			maxCount = Math.max(maxCount, count);
		}
		
		System.out.println(maxCount-curCount);
		
		sc.close();
	}
	
	private static class DataPoint {
		int start;
		int end;
		
		DataPoint(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
