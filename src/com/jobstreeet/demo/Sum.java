package com.jobstreeet.demo;


import java.util.*;

public class Sum {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for( int testCase = 1; testCase <= numCases ; testCase++ ) 
		{
			int count = sc.nextInt();
			int sum = 0;
			
			for( int i = 1; i <= count; i++ ) 
			{
				sum += sc.nextInt();
			}
			
			System.out.println(sum);
		}
		
		sc.close();

	}

}
