package com.amazon.datastructures;

import java.util.*;

public abstract class Strings {
	
	public static boolean isUnique(String s) {
		if (s.length() > 26)
			return false;

		boolean[] found = new boolean[26];
		for (char c : s.toCharArray()) {
			int loc = c - 'a';
			if (found[loc])
				return false;
			found[loc] = true;
		}
		return true;
	}

	public static boolean isUnique2(String s) {
		if (s.length() > 26)
			return false;

		int checker = 0;
		for (char c : s.toCharArray()) {
			int loc = c - 'a';
			if ((checker & (1 << loc)) > 0)
				return false;
			checker |= (1 << loc);
		}
		return true;
	}

	// Time: O(n), Space: O(n)
	public static boolean isUnique3(String s) {
		HashSet<Character> found = new HashSet<Character>();
		for (char c : s.toCharArray()) {
			if (found.contains(c))
				return false;
			found.add(c);
		}
		return true;
	}

	// Time: O(n log(n)) | Space: O(n)
	public static boolean isUnique4(String s) {
		char[] sorted = s.toCharArray();
		java.util.Arrays.sort(sorted);

		for (int i = 1; i < sorted.length; i++) {
			if (sorted[i] == sorted[i - 1])
				return false;
		}
		return true;
	}

	public static boolean isPermutation(String s1, String s2) {
	    HashMap<Character,Integer> count = new HashMap<Character,Integer>();
	    
	    for (char c : s1.toCharArray()) {
	        if(count.get(c) == null)
	            count.put(c,1);
	        else {
	            int last = count.get(c);
	            count.put(c,++last);
	        }
	    }
	    
	    for (char c : s2.toCharArray()) {
	        if(!count.containsKey(c))
	            return false;
	        else {
	            int last = count.get(c);
	            if (last == 1)
	                count.remove(c);
	            else
	                count.put(c,--last);
	        }
	    }
	    
	    return count.isEmpty();
	}
	
	public static boolean isPermutation2(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int[] count = new int[128]; // ascii

		for (char c : s1.toCharArray()) {
			count[c]++;
		}

		for (char c : s2.toCharArray()) {
			count[c]--;
			if (count[c] < 0)
				return false;
		}

		return true;
	}

	public static String URLify(String s, int n) {
		int src = n - 1;
		int dst = s.length() - 1;
		char[] result = s.toCharArray();

		for (; src >= 0; src--) {
			if (result[src] == ' ') {
				result[dst--] = '0';
				result[dst--] = '2';
				result[dst--] = '%';
			} else {
				result[dst--] = result[src];
			}
		}
		return new String(result);
	}

	/**
	 * Works for small letters only
	 */
	public static boolean isPalyndromePerm(String s) {
		int[] counts = new int[128];
		for (char c : s.toCharArray()) {
			counts[c]++;
		}

		int numOddsSoFar = 0;
		for (int charCount : counts) {
			if (charCount % 2 != 0) // odd count
				numOddsSoFar++;
			if (numOddsSoFar > 1) // maximum one odd char at the middle
				return false;
		}

		return true;
	}
	

	/**
	 * Works for combination of small and capital letters, and ignores out of range characters like spaces.
	 */
	public static boolean isPalyndromePerm2(String s) {
		int range = Character.getNumericValue('z') - Character.getNumericValue('a') + 1;
		int[] counts = new int[range];

		for (char c : s.toCharArray()) {
			if (rangeCheck(c)) {
				int code = Character.getNumericValue(c) - Character.getNumericValue('a');
				counts[code]++;
			}
		}

		int numOddsSoFar = 0;
		for (int charCount : counts) {
			if (charCount % 2 == 1)
				numOddsSoFar++;
			if (numOddsSoFar > 1)
				return false;
		}
		return true;
	}

	public static boolean isPalyndrome(String s) {
	    int n = s.length();
	    int leftEnd = (n/2) - 1;
	    int rightStart = (n/2) + (n%2);
	    
	    for(int left = 0, right = n-1; 
	            left <= leftEnd && right >= rightStart;
	            left++, right--) {
	        if (s.charAt(left) != s.charAt(right))
	            return false;
	    }
	    
	    return true;
	}
	
	private static boolean rangeCheck(char c) {
		return Character.getNumericValue(c) >= Character.getNumericValue('a')
				&& Character.getNumericValue(c) <= Character.getNumericValue('z');
	}

	public static boolean isBalancedBraces(String s) {
		int balanceSoFar = 0;
		for (char c : s.toCharArray()) {
			if (c == '{')
				balanceSoFar++;
			else if (c == '}') {
				if (balanceSoFar == 0)
					return false;
				balanceSoFar--;
			}
		}
		return balanceSoFar == 0;
	}
	
}
