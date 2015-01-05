package com.hackerrank.warmup;

import java.util.Scanner;

public class Solution {
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
		int numberOfCases = sc.nextInt();
		int[] inputs = new int[numberOfCases];
		for(int i=0; i<numberOfCases;i++)
			inputs[i] = sc.nextInt();
		
		for(int i : inputs)
			System.out.println("i="+i);
		
		for(int i=0; i<inputs.length;i++)
			System.out.println(solve(inputs[i]));
    }
	
	public static int solve(int n)
	{
		int factor = n % 2 == 1 ? 1 : 0; 
		return ( 2 ^ (n+1) / 2 + 2 ^ (n / 2 - factor) - 2 + factor );
	}

}
