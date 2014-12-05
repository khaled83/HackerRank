package com.hackerrank.warmup;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxXOR {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();
        
        // problem size: number of possible integers
        ArrayList<Integer> ones = new ArrayList<Integer>();
        ArrayList<Integer> zeros = new ArrayList<Integer>();
        int[] xor;
        
        // initialize both with all numbers
        for(int num=l; num<=r; num++) {
        	ones.add(num);
        	zeros.add(num);
        }
        
        // 1111
        // 1110
        // 1110
        // 1100
        // 1011
        // 1010
        
        long max = 0;
        for(short mostSignIndx = 31; mostSignIndx >= 0; mostSignIndx--)
        {
        	System.out.println(mostSignIndx);
        	System.out.println(ones + " <> " + zeros);
        	ArrayList<Integer> onesRemove = new ArrayList<Integer>();

        	for(int i=0; i<ones.size(); i++)
        		if ((ones.get(i) & (1 << mostSignIndx)) == 0)
        			onesRemove.add(ones.get(i));
        	
        	if(onesRemove.size() < ones.size() || max > 0)
        	{
        		ArrayList<Integer> zerosRemove = new ArrayList<Integer>();
                for(int i=0; i<zeros.size(); i++)
                	if ((zeros.get(i) & (1 << mostSignIndx)) > 0)
                		zerosRemove.add(zeros.get(i));
        		
                if(zerosRemove.size() == zeros.size())
                	ones.removeAll(onesRemove);
                
                if( (onesRemove.size() == ones.size() && onesRemove.size() < zeros.size() ) || max > 0 )
                	zeros.removeAll(zerosRemove);
            	
                System.out.println(mostSignIndx + " : #ones=" + ones.size() + " : #zeros=" + zeros.size() 
                		+ " : 1<<=" + (1 << mostSignIndx) + " : pow=" + ((int)Math.pow(2, mostSignIndx)) + " : max" + max);
                
            	if(zeros.size() > 0 && ones.size() > 0)
            	{
            		System.out.println("Adding " + (int)Math.pow(2, mostSignIndx));
            		max = max + (int)Math.pow(2, mostSignIndx);
            	}
            		
        	}
        }
        
        sc.close();
        
        System.out.println( max );
        
	}
	


}
