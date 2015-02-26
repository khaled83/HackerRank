package com.indeed.strings;

import java.util.HashMap;

public class Strings {

	public static void main(String[] args) throws Exception {
		System.out.println( firsNonRepeatingChar("total") );
		System.out.println( firsNonRepeatingChar("teeter") );
		System.out.println( firsNonRepeatingCharUnicode("total") );
		System.out.println( firsNonRepeatingCharUnicode("teeter") );
		System.out.println( removeChars("Battle of the Vowels: Hawaii vs. Grozny", "aeiou") );
		assert( removeChars("Battle of the Vowels: Hawaii vs. Grozny", "aeiou").equals("Bttl f th Vwls: Hw vs. Grzny") );
		System.out.println( removeChars2("Battle of the Vowels: Hawaii vs. Grozny", "aeiou") );
		assert( removeChars2("Battle of the Vowels: Hawaii vs. Grozny", "aeiou").equals("Bttl f th Vwls: Hw vs. Grzny") );
		
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
