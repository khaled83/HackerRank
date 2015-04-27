package com.indeed.strings;

import java.util.*;

import com.indeed.khaledabbas.sort.ArraysSortUtils2;

public class StringUtils {

	public static void main(String[] args) throws Exception {
		/**
		System.out.println( firsNonRepeatingChar("total") );
		System.out.println( firsNonRepeatingChar("teeter") );
		System.out.println( firsNonRepeatingCharUnicode("total") );
		System.out.println( firsNonRepeatingCharUnicode("teeter") );
		System.out.println( removeChars("Battle of the Vowels: Hawaii vs. Grozny", "aeiou") );
		assert( removeChars("Battle of the Vowels: Hawaii vs. Grozny", "aeiou").equals("Bttl f th Vwls: Hw vs. Grzny") );
		System.out.println( removeChars2("Battle of the Vowels: Hawaii vs. Grozny", "aeiou") );
		assert( removeChars2("Battle of the Vowels: Hawaii vs. Grozny", "aeiou").equals("Bttl f th Vwls: Hw vs. Grzny") );
		
		assert( hasUniqueChars("abcdefg") );
		
		assert( isPermutation("acad a", "d caaa") );
		
		String s = "a b c de";
		char[] chars = Arrays.copyOf(s.toCharArray(), s.length()+6) ;
		System.out.println(chars.length);
		System.out.println(chars);
		replaceSpaces(chars, s.length());
		System.out.println(chars);
		
		assert( isPanagram("accecca") );
		
		perm("abc");
		
		List<String> combi = combi("abc");
		Collections.sort( combi );
		for(String x : combi)
			System.out.println(x);
		
		System.out.println( integerToString(Integer.MIN_VALUE + 1) );
		
		System.out.println( levenshteinDistance("sitting", "kitten") );
		
		perm("abc");
		
		
		System.out.println(encrypt("abcaad  "));
		
		System.out.println( multiply( "999", "99" ) );
		
		System.out.println(reverseWords("Bob likes Alice"));
		
		System.out.println( RPN("34+21x+") );
		System.out.println( RPN("11+2x") );
		System.out.println( RPN("63/2/") );
		
		System.out.println( canPalyndrome("edified") );
		*/
		countChars("bcdacebe");
		
	}
	
	public static void countChars(String s) {

		int[] count = new int[58];

		for (char c : s.toCharArray()) {
			count[c - 'A']++;
		}

		for (int indx = 0; indx < 58 ; indx++) {
			if( count[indx] > 0 ) {
				char c = (char) (indx + 'A');
				System.out.print("(" + c + "," + count[indx] + ") ");
			}
		}

	}
	
	public static boolean canPalyndrome(String s) {

		// assume we only have alphabets
		int[] count = new int[58];

		for (char c : s.toCharArray()) {
			count[c - 'A']++;
		}

		int oddCount = 0;

		for (int x : count) {
			if ( x % 2 != 0 && ++oddCount > 1 )
				return false;
		}

		return true;
	}
	
	public static int RPN(String exp) {

		Stack<Integer> operands = new Stack<Integer>();

		for (char c : exp.toCharArray()) {
			if (isOperator(c)) {
				int y = operands.pop();
				int x = operands.pop();
				int result = evaluate(x, y, c);
				operands.push(result);
			} else {
				operands.push(c - '0');
			}
		}
		
		return operands.pop();
	}

	private static boolean isOperator(Character c) {
		return c == 'x' || c == '/' || c == '+' || c == '-';
	}

	private static int evaluate(int x, int y, char operator) {

		if (operator == 'x')
			return x * y;
		if (operator == '/')
			return x / y;
		if (operator == '+')
			return x + y;
		if (operator == '-')
			return x - y;
		else
			return 0;
	}
	
	public static String reverseWords(String s) {
		char[] chars = s.toCharArray();
		reverseWords(chars);
		return new String(chars);
	}

	public static void reverseWords(char[] chars) {

		// reverse all characters regardless of word tokens
		int n = chars.length;
		int src = 0;
		int dst = n - 1;

		while (src < dst) {
			char tmp = chars[dst];
			chars[dst--] = chars[src];
			chars[src++] = tmp;
		}

		// reverse each word
		src = 0;
		dst = 0;
		int wordEnd = 0;
		while (dst < n) {
			while (wordEnd < (n - 1) && chars[wordEnd + 1] != ' ') {
				wordEnd++;
			}

			dst = wordEnd;
			// now dst points to end of word
			while (src < dst) {
				char tmp = chars[dst];
				chars[dst--] = chars[src];
				chars[src++] = tmp;
			}

			wordEnd += 2;
			src = wordEnd;
			dst = wordEnd;
		}
	}
	
	/**
	 * @deprecated	doesn't return correct result, but basic implementation is almost correct.
	 */
	public static String multiply(String s1, String s2) {

		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		int n = arr1.length;
		int m = arr2.length;

		boolean is1Neg = arr1[0] == '-';
		boolean is2Neg = arr2[0] == '-';
		boolean isResultNeg = ((is1Neg ? 1 : 0) ^ (is2Neg ? 1 : 0)) != 0;

		int resLength = m + n + 1 + (isResultNeg ? 1 : 0);
		char[] result = new char[resLength];

		int indx = resLength - 1;
		int order = 0;

		for (int i = n - 1; i >= (is1Neg ? 1 : 0); i--, order++) {
			indx = resLength - 1 - order;
			for (int j = m - 1; j >= (is2Neg ? 1 : 0); j--) {
				int num1 = arr1[i] - '0';
				int num2 = arr2[j] - '0';
				int mul = (num1 * num2);
				
				// process current index
				int cur = 0;
				if( result[indx] != '\u0000')
					cur = result[indx] - '0';
				int sum = cur + mul;
				
				// process previous index
				int prev = 0;
				if( result[indx-1] != '\u0000' )
					prev = result[indx-1] - '0';
				result[indx-1] = (char) ( (prev + ( mul / 10 ) ) + '0' );
				
				result[indx] = (char) ( ( sum % 10 )  + '0' );
				indx--;
			}
		}

		if (isResultNeg)
			result[indx] = '-';
		
		return new String(result, indx, resLength-indx);

	}
	
	public static String encrypt(String s) {
		char[] chars = s.toCharArray();
		int length = chars.length;
		int src = length - 1;
		int dst = length - 1;

		for (; src >= 0; src--) {
			char c = chars[src];
			if (c == 'b' || c == ' ')
				continue;
			else if (c == 'a') {
				chars[dst--] = 'd';
				chars[dst--] = 'd';
			} else
				chars[dst--] = c;
		}
		return new String(chars);
	}
	
	public static int levenshteinDistance(String s1, String s2) {
		int rows = s1.length() + 1;
		int cols = s2.length() + 1;
		int[][] matrix = new int[rows][cols];

		// initialize first row
		for (int col = 0; col < cols; col++)
			matrix[0][col] = col;
		// initialize first column
		for (int row = 0; row < rows; row++)
			matrix[row][0] = row;

		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				if (s1.charAt(row-1) == s2.charAt(col-1))
					matrix[row][col] = matrix[row - 1][col - 1];
				else {
					matrix[row][col] = min(1 + matrix[row - 1][col - 1],
							1 + matrix[row - 1][col], 1 + matrix[row][col - 1]);
				}
			}
		}

		return matrix[rows - 1][cols - 1];
	}
	
	private static int min(int x, int y, int z) {
		int min = x;
		if (y < min)
			min = y;
		if (z < min)
			min = z;
		return min;
	}
	
	public static String integerToString(int x)
	{
		// 256
		char[] chars = new char[10]; // 10 is arbitrary
		int count = 0;
		String neg = "";
		if(x < 0) {
			neg = "-";
			x = -x;
		}
	
		while(x > 0)
		{
			int cur = x % 10;
			char c = (char) ('0' + cur);
			chars = ensureCapacity(chars, count+1);
			chars[count++] = c;
			x = x / 10;
			count++;
		}
	
		// reverse chars
		for(int start=0, end=count-1;
			start < end;
			start++, end--)
		{
			swap(chars, start, end);
		}
	
		return neg + String.valueOf( chars, 0, count );
	}

	private static char[] ensureCapacity(char[] arr, int minCapacity) {
		int oldCapacity = arr.length;

		if (minCapacity > oldCapacity) {
			// 1.5 * old capacity
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			// copy into a new array
			char[] newArr = new char[newCapacity];
			System.arraycopy(arr, 0, newArr, 0, oldCapacity);
			return newArr;
		}

		return arr;
	}
	
	public static int stringToInteger(String s)
	{
		int value = 0;
		int neg = 1; // multiplied by absolute value at the end for negation
	
		int indx = 0;
		char[] chars = s.toCharArray();
		if(chars[0] == '-') {
			neg = -1;
			indx++;
		}
	
		for( ; indx < chars.length; indx++) {
			int d = chars[indx] - '0';
			value = (value * 10) + d;
		}
	
		return ( neg * value );
	}

	
	
	/**
	 * {@link http://www.growingwiththeweb.com/2013/06/algorithm-all-combinations-of-set.html}
	 * alt: {@link http://javahungry.blogspot.com/2014/03/algorithm-to-find-permutations-of-string-using-recursion-in-java.html}
	 */
    private static List<String> combi(String s)
    {
    	ArrayList<String> combi = new ArrayList<String>();
        for(int i=0; i<s.length();i++)
        {
        	int length = combi.size();
            for(int j=0; j<length; j++)
            {
                combi.add( combi.get(j) + s.charAt(i) );
            }
            combi.add(Character.toString( s.charAt(i)) );
        }
        return combi;
    }
	
	public static void perm(String s)
	{
	    perm(s.toCharArray(), 0, s.length() -1);
	}

	private static void perm(char[] chars, int start, int end)
	{
	    if(start == end)
	        System.out.println(new String(chars) );
	    for(int i=start; i<=end; i++)
	    {
	        swap(chars, i, start);
	        perm(chars, start+1, end);
	        swap(chars, i, start); // backtrack
	    }
	}
	
	private static void swap(char[] arr, int indx1, int indx2)
	{
		char tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}
	
	public static boolean isPanagram(String s)
	{
		for(int start = 0, end = s.length() - 1; start < end; start++, end-- )
			if(s.charAt(start) != s.charAt(end))
				return false;
		
		return true;
	}
	
	
	public static void replaceSpaces(char[] chars, int length)
	{
		// replace ' ' with 20%
		//a b d----
		//        d
		//    s
		
		for(int src = length-1, dst = chars.length -1; 
				src != dst; 
				src--)
		{
			if( chars[src] == ' ' )
			{
				chars[dst] = '%';
				chars[dst-1] = '0';
				chars[dst-2] = '2';
				dst = dst - 3;
			}
			else
			{
				chars[dst] = chars[src];
				dst--;
			}
		}
	}
	
	public static boolean isPermutation(String s1, String s2)
	{
		if(s1.length() != s2.length())
			return false;
		
		int[] charCount1 = new int[256];
		int[] charCount2 = new int[256];
		
		for(char c : s1.toCharArray())
			charCount1[c]++;
		
		for(char c : s2.toCharArray())
			charCount2[c]++;
		
		for(int i=0; i<256; i++)
			if(charCount1[i] != charCount2[i])
				return false;
		
		return true;
	}
	
	public static boolean hasUniqueChars(String s)
	{
		boolean hasUniqueChars = true;
		
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		
		char cur = 0;
		if(charArr[0] == cur)
			cur = 1;
		
		for(char c : charArr)
		{
			if(c == cur)
			{
				hasUniqueChars = false;
				break;
			}
			else
			{
				cur = c;
			}
		}
			
		return hasUniqueChars;
	}
	
	public static boolean isBalancedBraces(String s)
    {
        int numLeftBraces = 0;
        for(char c : s.toCharArray())
            if(c == '{')
                numLeftBraces++;
            else if(c == '}')
            {
                if(numLeftBraces == 0)
                    return false;
                
                numLeftBraces--;   
            }
            
        return numLeftBraces == 0;
    }

	
	// O(n) + O(n) = O(2n) = O(n)
    public static char firsNonRepeatingChar(String s) throws NoAnswerException
    {
        // total    ->    o
        // teeter   ->    r
        // 65 - 122
        // A ... z
        // 0 ... 57
        // sample: {3 , 2, 0, ... }
        char[] charCount = new char[58];
        
        // O(n)
        for(char c : s.toCharArray() )
            charCount[c-'A']++;

        // O(n)            
        for(char c : s.toCharArray() )
            if(charCount[c - 'A'] == 1)
                return c;

        throw new NoAnswerException("All characters are repeating");
    }
    
    public static String firsNonRepeatingCharUnicode(String s) {
        
        Object seenOnce = new Object(),
                seenMultiple = new Object();
        Object seen;
                
        HashMap<Integer, Object> charHash = new HashMap<Integer, Object>();
        int length = s.length();
        for(int i=0; i<length; )
        {
            int cp = s.codePointAt(i);
            int charCount = Character.charCount(cp);
            i += charCount;
            seen = charHash.get(cp);
            if( seen == null) {
                charHash.put(cp, seenOnce);
            }
            else if(seen == seenOnce) {
                charHash.put(cp, seenMultiple);
            }
        }
        
        for(int i=0; i<length;)
        {
            int cp = s.codePointAt(i);
            i += Character.charCount(cp);
            if(charHash.get(cp) == seenOnce)
                return new String( Character.toChars(cp) );
            
        }
        
        return null;
    }
    
    public static String removeChars( String str, String remove )
    {
        
        boolean[] isRemove = new boolean[128];
        for(char c : remove.toCharArray())
            isRemove[c] = true;
            
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray())
            if(!isRemove[c])
                sb.append(c);
                
        return new String( sb );
    }
    
    public static String removeChars2(String str, String remove)
    {
        boolean[] isRemove = new boolean[128];
        for(char c : remove.toCharArray())
            isRemove[c] = true;
            
        char[] strArr = str.toCharArray();
        
        int src, dst;
        for(src=0, dst=0;
            src < strArr.length;
            src++
           )
        {
            int charIndx = strArr[src];
            if(!isRemove[charIndx]) {
                strArr[dst] = strArr[src];
                dst++;
            }
        }
        
        return new String(strArr, 0, dst);
    }
    
    private static class NoAnswerException extends Exception
    {
    	public NoAnswerException() {}
    	
    	public NoAnswerException(String msg) {
    		super(msg);
    	}
    }

}
