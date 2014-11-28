package com.hackerrank.warmup;

import java.util.Scanner;

public class FindDigits {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner sc = new Scanner( System.in );
		int numCases = sc.nextInt();
		int[] results = new int[numCases];
		
		for(int caseIndx = 1; caseIndx <= numCases; caseIndx++)
		{
			// process each case
			int n = sc.nextInt();
			
			// hashtable to store whether a number is an exact divider
			boolean[] isDivHash = new boolean[10];
			isDivHash[0] = false;
			for( int div = 1; div <=9; div++)
				isDivHash[div] = n % div == 0; 
			
			int[] divCounter = new int[10];
			
			while( n > 0 )
			{
				// 1021
				int nextNum = n  % 10;
				if(isDivHash[nextNum])
					divCounter[nextNum]++;
				n = n / 10;
			}
			
			for(int i=1; i<=9; i++)
				results[caseIndx] += divCounter[i];
		}
		
		// output
		for(int caseIndx = 0; caseIndx < numCases; caseIndx++)
			System.out.println(String.valueOf(results[caseIndx]));
		
		sc.close();
    }

}
