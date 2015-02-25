package com.hackerrank.algorithms.arraysandsorting;

import java.util.*;

public class ClosestNumbers {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        int[] arr = new int[arrSize];
        
        for(int i=0; i<arrSize; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
//        Arrays.
        
        ArrayList<Integer> pairs = new ArrayList<Integer>();
        int smallest = Integer.MAX_VALUE;
        
        for(int i=0; i<arrSize-1; i++)
        {
            int diff = arr[i+1] - arr[i];
            
            if(diff <= smallest) {
                // reset smallest
                if( diff < smallest) 
                {
                    pairs.clear();
                    smallest = diff;
                }
                pairs.add(arr[i]);
                pairs.add(arr[i+1]);
            }            
            
        }
    
        for(Integer value : pairs) {
            System.out.print( value + " " );
        }
        System.out.println();

	}

}
