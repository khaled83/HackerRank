package com.hackerrank.warmup;

import java.util.Scanner;

public class LoveLetterMystery
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner( System.in );
        int numCases = sc.nextInt();
        
        String[] inputs = new String[numCases];
        
        for( int i=0; i<numCases; i++)
        	inputs[i] = sc.next();
        
        for( int i=0; i< inputs.length; i++)
            System.out.println( solveWithAscii( inputs[i] ) );    
        
        sc.close();
    }
    
    private static int solveWithAscii(String s)
    {
        char[] orgChars = s.toCharArray();
        char[] palChars = s.toCharArray();
        int length = s.length();
        
        for(int i= 0; i<length; i++)
            palChars[i] = min(orgChars[i], orgChars[length - i - 1] );
        
        // calculate ascii values
        int asciiOrg = 0, asciiPal = 0;
        for(int i=0; i<length; i++)
        {
            asciiOrg+= (int) orgChars[i];
            asciiPal+= (int) palChars[i];
        }
        
        return Math.abs( asciiOrg - asciiPal );
    }
    
    private static char min(char a, char b)
    {
        if( a <= b) 
            return a;
        return b;
    }
    
}
