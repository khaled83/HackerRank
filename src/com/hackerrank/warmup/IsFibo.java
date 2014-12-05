package com.hackerrank.warmup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class IsFibo {

	public static void main(String[] args) throws IOException {
		
		// initialize datastructures for fibo search
		long max = (long) Math.pow(10, 10);
		ArrayList<Long> fiboList = new ArrayList<Long>();
		
		fiboList.add(0L);
		fiboList.add(1L);
		
		Long fibo = 1L;
		int lastIndx;
		
		while( fibo <= max  )
		{
			lastIndx = fiboList.size() - 1;
			fibo = fiboList.get(lastIndx) + fiboList.get(lastIndx - 1);
			fiboList.add(fibo);
		}
		
		// sorted array with all possible fibos up to max sorted for optimal binary search
		Long[] fiboArr = new Long[fiboList.size()]; 
		fiboList.toArray(fiboArr);
		
		// read input
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		for(int caseNum = 0; caseNum < numCases; caseNum++)
			System.out.println( Arrays.binarySearch(fiboArr, sc.nextLong()) > 0
								? "IsFibo"
								: "IsNotFibo" );
		
		sc.close();
	}

}