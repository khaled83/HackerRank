package com.hackerrank.strings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numTestCases = sc.nextInt();
        
        for(int caseNum = 1; caseNum <= numTestCases; caseNum++)
        {
            String s = sc.nextLine();
            
            // weird behavior that input reads first line as empty
            if(s.trim().length() == 0) { caseNum--; continue; }
            
            if(s.length() % 2 == 1) {
                System.out.println(-1);
                continue;
            }
            
            int midIndx = ( s.length() / 2 );
            String s1 = s.substring( 0, midIndx);
            String s2 = s.substring( midIndx, s.length() );
            
            int[] chars1 = new int[26];
            int[] chars2 = new int[26];
            
            fillCharFrequency(s1, chars1);
            fillCharFrequency(s2, chars2);
            
            int swapsReq = 0;
            for(int i=0; i<26; i++) {
            	if( chars2[i] > chars1[i] ) {
            		swapsReq += chars2[i] - chars1[i];
            	}
            }
            
            System.out.println( swapsReq );
            
        }
        
        sc.close();
        
    }
    
    /**
    @param  arr an array with 26 elements
    */
    private static void fillCharFrequency(String s, int[] chars) {
        for(char c : s.toCharArray()) {
            // 'a' ... 'z'
            //  0  ...  25
            int indx = c - 'a';
            chars[indx]++;
        }
    }
}