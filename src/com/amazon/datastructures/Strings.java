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
		return URLify(s.toCharArray(), n);
	}
	
	public static String URLify(char[] s, int n) {
	    for (int src = n-1, dst = s.length-1; src != dst; src--) {
	        if (s[src] == ' ') {
	            s[dst--] = '0';
	            s[dst--] = '2';
	            s[dst--] = '%';
	        } else {
	            s[dst--] = s[src];
	        }
	    }
	    return new String(s);
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

	private static Comparator<String> compAnagrams1 = new Comparator<String>() {
        
	    public int compare(String s1, String s2) {
	        // ASCII
	    	char[] c1 = s1.toCharArray();
	    	java.util.Arrays.sort(c1);
	    	char[] c2 = s2.toCharArray();
	    	java.util.Arrays.sort(c2);
	    	
	    	return new String(c1).compareTo(new String(c2));
	    	
//	        int[] count = new int[128];
//	        for (char c : s1.toCharArray()) {
//	            count[c]++;
//	        }
//	        
//	        for (char c : s2.toCharArray()) {
//	            count[c]--;
//	            if (count[c] < 0)
//	                return -1;
//	        }
//	        
//	        return 0;
	    }
	    
	};
	
	public static void sortAnagrams2(String[] arr) {
	    java.util.Arrays.sort(arr, compAnagrams1);
	    for (String s : arr)
			System.out.print(s + " ");
	    System.out.println();
	}
	
	public static void sortAnagrams(String[] arr) {
	    int n = arr.length;
	    HashMap<String, List<String>> groups = new HashMap<String, List<String>>(n);
	    for (String s : arr) {
	        char[] chars = s.toCharArray();
	        java.util.Arrays.sort(chars);
	        String key = new String(chars);
	        List<String> group = groups.get(key);
	        if (group == null) {
	            groups.put(key, new java.util.ArrayList<String>(n));
	        }
	        groups.get(key).add(s);
	    }
	    
	    int indx = 0;
	    for (String key : groups.keySet()) {
	        for (String s : groups.get(key)) {
	            arr[indx++] = s;
	        }
	    }
	}

	/**
	 * checks if a string is a permutation of any palyndrome
	 * works with ASCII only
	 */
	public static boolean isPermOfPalyndrome1(String s) {
	    // ASCII
	    int[] counts = new int[128];
	    
	    for (char c : s.toCharArray()) {
	        counts[c]++;
	    }
	    
	    
	    int oddsAllowed = s.length() % 2;
	    
	    for (int count : counts) {
	        if (count % 2 != 0 && oddsAllowed == 0)
	            return false;
	        else if (count %2 != 0)
	            oddsAllowed = 0;
	    }
	    
	    return true;
	}
	
	/**
	 * checks if a string is a permutation of any palyndrome
	 * works with any type of string
	 */
	public static boolean isPermOfPalyndrome2(String s) {
	    HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
	    
	    for (char c : s.toCharArray()) {
	        int count = 0;
	        if (counts.containsKey(c))
	            count = counts.get(c);
	        counts.put(c, ++count);
	    }
	    
	    int oddsAllowed = s.length() % 2;
	    
	    for (int count : counts.values()) {
	        if (count % 2 == 1 && oddsAllowed == 0)
	            return false;
	        else if (count % 2 == 1)
	            oddsAllowed = 0;
	    }
	    
	    return true;
	}
	
	public static boolean isPermOfPalyndrome3(String s) {
	    int checker = 0;
	    
	    for (char c : s.toCharArray()) {
	        int loc = c - 'a';
	        if (loc < 0 || loc > 25)
	            throw new IllegalArgumentException("This method only supports lower case alphabets");
	        // flip flag for character
	        checker = checker ^ (1 << loc);
	    }
	    
	    // checker: 0 => even 1 => odd
	    // count 1s, we can have at most one 1
	    /** 
	        checker: 100100
	                 100011 c-1
	                 ------ &
	                 100000
	                 011111 c-1
	                 ------ &
	                 000000
	    */
	    
	    int oddsAllowed = s.length() % 2;
	    if (checker > 0 && oddsAllowed == 0)
	        return false;
	    else if (checker > 0) {
	        checker = checker & (checker - 1);
	        if (checker > 0)
	            return false;
	    }
	    
	    return true;
	}
	
	public static int stringtoInt(String s) {
	    boolean isNegative = false;
	    if (s.charAt(0) == '-') {
	        isNegative = true;
	    }
	    
	    int res = 0;
	    for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
	        int digit = s.charAt(i) - '0';
	        res = 10 * res + digit;
	    }
	    
	    if (isNegative)
	        res = -res;
	    
	    return res;
	}

	public static String intToString(int num) {
	    StringBuilder res = new StringBuilder();
	    
	    boolean isNegative = false;
	    if (num < 0) {
	        isNegative = true;
	        num = -num;
	    }
	    
	    while (num > 0) {
	        char digit = (char)((num % 10) + '0');
	        res.append(digit);
	        num = num / 10;
	    }
	    
	    if (isNegative) {
	        res.append('-');
	    }
	    
	    res.reverse();
	    return new String(res);
	}

	public static boolean equalWithOneCharEdit(String s1, String s2) { 
	    HashMap<Character, Integer> count = new HashMap<Character, Integer>();
	    for (char c : s1.toCharArray()) {
	        int curCount = count.containsKey(c) ? count.get(c) : 0;
	        count.put(c, curCount+1);
	    }
	    
	    for (char c : s2.toCharArray()) {
	        int curCount = count.containsKey(c) ? count.get(c) : 0;
	        count.put(c, curCount-1);
	        // optimization: fail earlier
	        if (curCount < -1) {
	            return false;
	        }
	    }
	    
	    int more = 0, less = 0;
	    for (char c : count.keySet()) {
	        int charCount = count.get(c);
	        if (charCount > 0) {
	            less += charCount;
	        }
	        else if (charCount < 0) {
	            more += charCount;
	        }
	        if (more > 1 || less > 1) {
	            return false;
	        }
	    }
	    
	    return true;
	}

	public static boolean equalWithOneCharEditAscii(String s1, String s2) {
	    // assuming ASCII
	    int[] count = new int[128];
	    for (char c : s1.toCharArray()) {
	        count[c]++;
	    }
	    
	    for (char c : s2.toCharArray()) {
	        count[c]--;
	        // optimization: fail earlier
	        if (count[c] < -1) {
	            return false;
	        }
	    }
	    
	    int more = 0, less = 0;
	    for (int charCount : count) {
	        if (charCount > 0) {
	            less += charCount;
	        }
	        else if (charCount < 0) {
	            more += charCount;
	        }
	        if (more > 1 || less > 1) {
	            return false;
	        }
	    }
	    
	    return true;
	}

	public static String compress(String s) {
	    int n = s.length();
	    char[] res = new char[n];
	    
	    char last = s.charAt(0);
	    int count = 1;
	    int src, dst;
	    // dst >= n means compressed string is longer than original, so we terminate loop
	    for (src = 1, dst = 0; src < n && dst < (n - 1); src++) {
	        char c = s.charAt(src);
	        if (c != last) {
	            res[dst++] = last;
	            res[dst++] = (char)(count + '0');
	            last = c;
	            count = 1;
	        }
	        else {
	            count++;
	        }
	    }
	    
	    // transfer the last squence in source array, this requires room for 2 locations: char and count
	    // if dst + 2 >= n then compressed string is equal or larger than original, so we don't compress
	    if (dst < (n - 2)) {
	        res[dst++] = last;
	        res[dst++] = (char)(count + '0');
	        return new String(res, 0, dst);
	    }
	    // compressed is larger than input
	    else {
	        return s;
	    }
	}
	
	public static int convertRomanToDecimal(String s) throws IllegalArgumentException {
	    // initialize hash
		Map<Character, Integer> h = new HashMap<Character, Integer>() {
			{
				put('I', 1);
				put('V', 5);
				put('X', 10);
				put('L', 50);
				put('C', 100);
				put('D', 500);
				put('M', 1000);

			}
		};
		    
	    Map<Character, Character> e = new HashMap<Character, Character>() {
		    	{
		    		put('V', 'I');
		    		put('X', 'I');
		    		put('L', 'X');
		    		put('C', 'X');
		    		put('D', 'C');
		    		put('M', 'C');
		    		
		    	}
	    };
	    
	    int res = 0;
	    int prev = -1, cur = -1, next = -1;
	    char curChar = '0', nextChar = '0';
	    
	    int i = 0;
	    for (i = 0; i < s.length() - 1; i++) {
	        curChar = s.charAt(i);
	        nextChar = s.charAt(i + 1);
	        prev = cur;
	        cur = h.get(curChar);
	        next = h.get(nextChar);
	        
	        if (cur >= next) {
	            res += cur;
	        }
	        else {
	            // check for invalid exception
	            // only some character pair exceptions are allowed
	            if (e.get(nextChar) != curChar) {
	                throw new IllegalArgumentException("Invalid Roman number: char " + curChar + " can't prceded char " + nextChar);
	            }
	            // don't allow back to back exceptions
	            if (i < s.length() - 2 && h.get(s.charAt(i + 2)) > next) {
	                throw new IllegalArgumentException("Invalid Roman number: back to back exceptions not allowed");
	            }
	            // valid exception case
	            res += next - cur;
	            i++; // skip next char
	        }
	    }
	    
	    // handle last char
	    // else last char was part of exception case
	    if (i == s.length() - 1) {
		    	res += cur;
	    }
	    
	    return res;
	}

	public static int convertRomanToDecimal2(String s) {
	    // initialize hash
		Map<Character, Integer> h = new HashMap<Character, Integer>() {
			{
				put('I', 1);
				put('V', 5);
				put('X', 10);
				put('L', 50);
				put('C', 100);
				put('D', 500);
				put('M', 1000);

			}
		};
	    
	    int res = 0;
	    int prev = -1, cur = -1, next = -1;
	    char curChar = '0', nextChar = '0';
	    
	    int i = 0;
	    for (i = 0; i < s.length() - 1; i++) {
	        curChar = s.charAt(i);
	        nextChar = s.charAt(i + 1);
	        prev = cur;
	        cur = h.get(curChar);
	        next = h.get(nextChar);
	        
	        if (cur >= next) {
	            res += cur;
	        }
	        else {
	            // valid exception case
	            res += next - cur;
	            i++; // skip next char
	        }
	    }
	    
	    // handle last char
	    // else last char was part of exception case
	    if (i == s.length() - 1) {
		    	res += cur;
	    }
	    
	    return res;
	}

	public static String sinusoidally(String s) {
	    int n = s.length();
	    char[] arr = new char[n];
	    
	    // first line
	    int dst = 0;
	    for (int src = 1; src < n; src += 4, dst++) {
	        arr[dst] = s.charAt(src);
	    }
	    
	    // second line
	    for (int src = 0; src < n; src += 2, dst++) {
	        arr[dst] = s.charAt(src);
	    }
	    
	    // third line
	    for (int src = 3; src < n; src += 4, dst++) {
	        arr[dst] = s.charAt(src);
	    }
	    
	    return new String(arr);
	}
	
	public static String encodeRLE(String s) {
	    StringBuilder sb = new StringBuilder();
	    char cur = s.charAt(0);
	    int count = 1;
	    for (int i = 1; i < s.length(); i++, count++) {
	        if (s.charAt(i) != cur) {
	            sb.append(count).append(cur);
	            cur = s.charAt(i);
	            count = 0;
	        }
	    }
	    // append last result
	    sb.append(count).append(cur);
	    
	    return sb.toString();
	}
	
	public static String decodeRLE(String s) {
	    StringBuilder res = new StringBuilder();
	    StringBuilder numChars = new StringBuilder();
	    for (char cur : s.toCharArray()) {
	        if (isNum(cur)) {
	            numChars.append(cur);
	        }
	        else {
	            // decode characters
	            // convert number
	            int count = Integer.parseInt(numChars.toString());
	            for (int i = 0; i < count; i++) {
	                res.append(cur);
	            }
	            numChars = new StringBuilder();
	        }
	    }
	    return res.toString();
	}
	
	private static boolean isNum(char c) {
		return c >= '0' && c <= '9';
	}
	
}



















