package com.hackerrank.warmup;

import java.util.Scanner;

public class HalloweenParty {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum = 1; caseNum <= numCases; caseNum++) {
            
            long n = sc.nextLong();
            System.out.println( (n/2)*((n+1)/2) );
        }
        
        sc.close();

	}
	
	/** 
    0+1+1+2+2
    2 * ( 0+1+2+..+n )
    2 * (n(n+1)/2)
    
    1 2 3 4  5
    1 3 6 10 15
    numPieces = (n/2)*((n+1)/2) = n*(n+1) / 4
    cuts  pieces    2n  x=n/2  n+1/2   x(x+1)/2 x-1^2
    1        0      2     0    1
    2        1      4     1    1    1        0
    3        2      6     1    2    1        0
    4        4      8     2    2    3        4
    5        6      10    2    3    3        4
    6        9      12    3    3    6        
    7        12     14    3    4    6  
    8        16     16    4    4      
    */

}
