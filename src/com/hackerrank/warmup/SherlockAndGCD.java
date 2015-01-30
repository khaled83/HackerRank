package com.hackerrank.warmup;

import java.util.Scanner;

public class SherlockAndGCD {

	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum=1; caseNum<=numCases; caseNum++) 
        {
            int numElements = sc.nextInt();    
            
            int result = sc.nextInt();
            for(int i=1; i<numElements; i++) {
                result = gcd(result, sc.nextInt());
            }
            
            String output = result <= 1 ? "YES" : "NO";
            System.out.println(output);
        }
    }
    
    // greatest common divider using Eclidean algorithm
    private static int gcd(int a, int b)
    {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

}
