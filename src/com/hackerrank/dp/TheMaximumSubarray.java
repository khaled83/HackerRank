package com.hackerrank.dp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheMaximumSubarray {
	
public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum = 1; caseNum <= numCases; caseNum++)
        {
            int arrLength = sc.nextInt();
            int[] arr = new int[arrLength];

            for(int i=0; i<arrLength; i++) {
                arr[i] = sc.nextInt();
            }                
            
//            int[] cache = new int[2 * arrLength];
            Map<Integer, Integer> cache = new HashMap<Integer, Integer>();  
//            for(int i=0; i<cache.length; i++) {
//                cache[i] = Integer.MIN_VALUE;
//            }
            
//            max(arr, cache, 0, arrLength-1);
//            int max = Integer.MIN_VALUE;
//            for(int x : cache) {
//            	if(x > max) {
//            		max = x;
//            	}
//            }
            
            max(arr, cache, 0, arrLength-1);
            
            System.out.print( Collections.max( cache.values() ) + " ");
            System.out.print(maxIncontg(arr));
            System.out.println();
        }
        
        sc.close();
    }

	private static int max(int[] arr, Map<Integer, Integer> cache, int startIndx, int endIndx) 
	{
	    int arrLength = endIndx - startIndx + 1;
	    if(arrLength == 1)
	    {
	        return arr[startIndx];
	    }
	    else
	    {
	        int maxPart1 = 0;
	        int key1 = startIndx*10+startIndx;
	        
	        if(cache.get(key1) != null) {
	            maxPart1 = cache.get(key1);
	        }
	        else {
	            maxPart1 = max(arr, cache, startIndx, startIndx);    
	            cache.put(key1, maxPart1);
	        }
	        
	        int maxPart2 = 0;
	        int key2 = ( (startIndx+1)*10)+endIndx;
	        
	        if(cache.get(key2) != null) {
	            maxPart2 = cache.get(key2);
	        }
	        else {
	            maxPart2 = max(arr, cache, startIndx+1, endIndx);
	            cache.put(key2, maxPart2);
	        }        
	        
	        if( ( maxPart1 + maxPart2 ) > 0)
	        {
	            return maxPart1 + maxPart2;
	        }
	        else
	        {
	            return Math.max(maxPart1, maxPart2);
	        }
	    }
	}
    
    @SuppressWarnings("unused")
	private static int max2(int[] arr, int[] cache, int startIndx, int endIndx) 
    {
        int arrLength = endIndx - startIndx + 1;
        if(arrLength == 1)
        {
            return arr[startIndx];
        }
        else
        {
            int maxPart1 = 0;
            int key1 = startIndx+startIndx;
            
            if(cache[key1] != Integer.MIN_VALUE) {
                maxPart1 = cache[key1];
            }
            else {
                maxPart1 = max2(arr, cache, startIndx, startIndx);    
                cache[startIndx+startIndx] = maxPart1;
            }
            
            int maxPart2 = 0;
            int key2 = startIndx+1+endIndx;
            
            if(cache[key2] != Integer.MIN_VALUE) {
                maxPart2 = cache[key2];
            }
            else {
                maxPart2 = max2(arr, cache, startIndx+1, endIndx);
                cache[startIndx+1+endIndx] = maxPart2;    
            }        
            
            if( ( maxPart1 + maxPart2 ) > 0)
            {
                return maxPart1 + maxPart2;
            }
            else
            {
                return Math.max(maxPart1, maxPart2);
            }
        }
    }
    
    private static int maxIncontg(int[] arr)
    {
        int pos = 0;
        int maxNeg = Integer.MIN_VALUE;
        boolean posFound = false;
        
        for(int x : arr) 
        {
            if(x >= 0) {
                pos+=x;
                posFound = true;
            }
            else if(x > maxNeg) {
                maxNeg = x;
            }
        }
        
        if(posFound) {
            return pos;
        }
        else {
            return maxNeg;
        }
    }

}
