package com.hackerrank.warmup;

import java.util.Scanner;

public class ChocolateFeast {
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        System.out.println();
        for(int i = 0; i < t; i++){
            System.out.println(Solve(in.nextInt(), in.nextInt(), in.nextInt()));
        }
        in.close();
    }
    
	// 6 2 2
    private static long Solve(int N, int C, int M){
    	long count = 0;
    	count+= N / C;
    	
    	long wrappers = count;
    	long bonus = 0;
    	
    	while(wrappers >= M)
    	{
    		bonus = wrappers / M; 
    		count+= bonus;
    		wrappers = bonus + (wrappers % M);
    	}
    	
    	return count; 
    }

}
