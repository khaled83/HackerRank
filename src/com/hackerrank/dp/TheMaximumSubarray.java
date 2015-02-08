package com.hackerrank.dp;

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
            
            int[] cache = new int[arrLength * arrLength];
            for(int i=0; i<cache.length; i++) {
                cache[i] = Integer.MIN_VALUE;
            }
            
            System.out.print(max(arr, cache, 0, arrLength-1));
            System.out.print(maxIncontg(arr));
            System.out.println();
        }
        
        sc.close();
    }
    
    private static int max(int[] arr, int[] cache, int startIndx, int endIndx) 
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
                maxPart1 = max(arr, cache, startIndx, startIndx);    
                cache[startIndx+startIndx] = maxPart1;
            }
            
            int maxPart2 = 0;
            int key2 = startIndx+1+endIndx;
            
            if(cache[key2] != Integer.MIN_VALUE) {
                maxPart2 = cache[key2];
            }
            else {
                maxPart2 = max(arr, cache, startIndx+1, endIndx);
                cache[startIndx+1+endIndx] = maxPart2;    
            }        
            
            if(maxPart1 > 0 && maxPart2 > 0)
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
