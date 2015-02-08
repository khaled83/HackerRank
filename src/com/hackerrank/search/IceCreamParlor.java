package com.hackerrank.search;

import java.util.*;

public class IceCreamParlor {
	
	public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int numCases = sc.nextInt();
        
        int maxFlavors = 10000;
        
        for(int caseNum = 1; caseNum <= numCases; caseNum++) {
            int dollars = sc.nextInt();
            int numFlavors = sc.nextInt();
            
//            BitSet vector = new BitSet(maxFlavors+1);
            int[] count = new int[maxFlavors+1];
            int[] flavors = new int[numFlavors];
                
            for(int indx=0; indx<numFlavors; indx++)
            {
                int flavor = sc.nextInt();
                flavors[indx] = flavor;
                count[flavor]++;
//                vector.set( flavor );
            }
            
            for(int indx = 0; indx < flavors.length; indx++) {
                int flavor = flavors[indx];
                int remaining = dollars - flavor;
                //vector.get(remaining)
                
                if( remaining > 0 
                		&& ( count[remaining] - (flavor == remaining ? 1 : 0) > 0 ) ) 
                {
                	int remainingIndx = getArrIndx(flavors, remaining, indx+1);
                    System.out.println((indx+1) + " " + (remainingIndx+1) 
                    		); 
//                    		+ " dollars="+dollars + " : " + flavors[indx] + " + " + flavors[getArrIndx(flavors, remaining, indx+1)]);
                    break;
                }
            }
        }
        
        sc.close();
    }
    
    private static int getArrIndx(int[] arr, int value, int startIndx) {
        for(int indx=startIndx; indx<arr.length; indx++) {
            if(arr[indx] == value) {
                return indx;
            }
        }
        
        return -1;
    }

}
