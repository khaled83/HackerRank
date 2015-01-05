package com.hackerrank.warmup;

import java.util.Scanner;

public class AnagramToPalyndrome {

	public static void main(String[] args) {
		Scanner myScan = new Scanner(System.in);
        String inputString = myScan.nextLine();

        // Assign ans a value of YES or NO, depending on whether or not inputString satisfies the required condition
        
        int[] charCount = new int[26];
        for(char c : inputString.toCharArray() )
        {
            int indx = ( (int) c ) - 97;
            charCount[indx]++;
        }
        
        boolean isAnagram = true;
        boolean oddFound = false;
        
        for(int i=0; i<charCount.length && isAnagram; i++)
        {
            if( charCount[i] % 2 != 0 && oddFound)
                isAnagram = false;
            else if(charCount[i] % 2 != 0)
                oddFound = true;
        }
        
        isAnagram = isAnagram && ( !oddFound || ( inputString.length() % 2 == 1 ) );
        
        System.out.println(isAnagram ? "YES" : "NO" );
        myScan.close();

	}

}
