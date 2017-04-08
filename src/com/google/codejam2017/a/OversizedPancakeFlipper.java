package com.google.codejam2017.a;

import java.util.HashSet;

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
	 */
	public static void main(String[] args) {
		flip("---+-++-".toCharArray(), 3, 0);
		System.out.println(minCount);
	}
	
	private static HashSet<String> memo = new HashSet<String>();
	private static int minCount = Integer.MAX_VALUE;
	
	public static void flip(char[] s, int k, int countSoFar) {
		
		String str = new String(s);
		//System.out.println("flip ==> " + str);
		if (memo.contains(str)) {
			if (str.equals("++++-++-") || str.equals("++++---+") )
				System.out.println("found string " + str + " => " + minCount + "/" + countSoFar);
			return;
		}
		memo.add(str);
		
		boolean solvedSoFar = true;
		for (char c : s)
			if (c == '-') {
				solvedSoFar = false;
				break;
			}
		if (solvedSoFar) {
			minCount = Math.min(minCount, countSoFar);
			System.out.println("solved => " + str + " :" + minCount + "<" + countSoFar);
			return;
		}
		
		for (int i=0; i<s.length; i++) {
//			if (i==0 || i==3 || i==5)
			if (i==5 && str == "++++-++-")
				System.out.println("flipChars => @" + i + " :" + minCount);
//			System.out.println("before => after flip");
//			System.out.println(new String(s));
			flipChars(s, k, i);
//			System.out.println(new String(s));
//			if (new String(s) == "++++-++-")
//				System.out.println("found string:" + minCount + "/" + countSoFar);
			flip(s, k, countSoFar+1);
			flipChars(s, k, i);
		}
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
