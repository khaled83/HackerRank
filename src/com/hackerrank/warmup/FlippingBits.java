package com.hackerrank.warmup;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FlippingBits {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        int bset = 0xffffffff;
        long int_mask = 0xffffffffL;
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        
        for(int caseNum=1; caseNum<=numCases; caseNum++)
        {
            long num = sc.nextLong();
            System.out.println( format.format( ( num ^ bset ) & int_mask ) );
        }

        sc.close();
	}
	
	@SuppressWarnings("unused")
	private static void print32Digits()
	{
		System.out.println("---");
        for(int i=0; i<32;i++)
        	System.out.print(i%10);
        System.out.println();
	}

}