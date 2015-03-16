package com.indeed.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

public class StringUtils {

	public static void main(String[] args) throws Exception {
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
		
		assert( isPanagram("acceaca") );
		
		perm("abc");
		
		List<String> combi = combi("abc");
		Collections.sort( combi );
		for(String x : combi)
			System.out.println(x);
		
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
