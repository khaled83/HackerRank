package com.google.codejam2017.a.copy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class OversizedPancakeFlipper {
	
	/**
	 	- - - + - + + - 3
	 	[   ]
	 	
	    [   ]
	    + + + + - + + -
	              [   ]
	    + + + + - - - +
	            [   ]
	    + + + + + + + +
	    
	    
	    0
	    	1.1
	    		1.1.1
	    		1.1.2
	    	1.2
	    		1.2.1
	    		1.2.2
	    	
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		String[] patterns = new String[numCases];
		int[] sizes = new int[numCases];
		
		for (int i=0; i<numCases; i++) {
			patterns[i] = sc.next();
			sizes[i] = sc.nextInt();
		}
		
		for (int i=0; i<numCases; i++) {
			memo = new HashMap<String, Integer>();
			minCount = Integer.MAX_VALUE;
			flip(patterns[i].toCharArray(), sizes[i], 0);
			if (minCount == Integer.MAX_VALUE)
				System.out.println("Case #" + (i+1) +": IMPOSSIBLE");
			else
				System.out.println("Case #" + (i+1) +": " + minCount);
		}
		sc.close();
	}
	
	private static HashMap<String, Integer> memo = new HashMap<String, Integer>();
	private static int minCount = Integer.MAX_VALUE;
	
	public static boolean flip(char[] s, int k, int countSoFar) {
		String str = new String(s);
		if (memo.containsKey(str) && memo.get(str) < countSoFar) {
			countSoFar = Math.min(memo.get(str), countSoFar);
		}
		else if (memo.containsKey(str)) {
			return false;
		}
		memo.put(str, countSoFar);
		
		boolean isSolution = true;
		for (char c : s)
			if (c == '-') {
				isSolution = false;
				break;
			}
		if (isSolution) {
			minCount = Math.min(minCount, countSoFar);
			return true;
		}
		
		for (int i=0; i<=s.length-k; i++) {
			flipChars(s, k, i);
			flip(s, k, countSoFar+1);
			flipChars(s, k, i);
		}
		
		return false;
	}
	
	private static void flipChars(char[] s, int k, int from) {
		for (int i =from; i < Math.min(from+k, s.length); i++)
			s[i] = s[i] == '+' ? '-' : '+';
	}
	
//	public static int flip(String s, int k) {
//		flip(s.toCharArray(), k, 0);
//		return flipCount;
//	}
	
//	public static boolean flip(char[] s, int k, int from) {
//		memo.add(new String(s));
//		
//		boolean solvedSoFar = true;
//		for (char c : s)
//			if (c == '-')
//				solvedSoFar = false;
//		if (solvedSoFar)
//			return true;
//		
//		for (int i=from; i<from+k; i++)
//			s[i] = s[i] == '+' ? '-' : '+';
//		flipCount++;
//		
//		if (memo.contains(new String(s)))
//			return false;
//		
//		for (int i=0; i<s.length; i++) {
//			if (s[i] == '-') {
//				for (int j = Math.max(i-k, 0); j < Math.min(i+k, s.length); j++) {
//					flip(s, k, j);
//				}
//			}
//		}
//		return false;
//	}

}
