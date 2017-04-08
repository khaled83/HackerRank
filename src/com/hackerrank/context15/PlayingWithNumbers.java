package com.hackerrank.context15;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PlayingWithNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
         
        int[] arr = new int[arrSize];
        int pos = 0, neg = 0;
        int maxNeg = Integer.MIN_VALUE;
        int minPos = Integer.MAX_VALUE;
        int numPos = 0, numNeg = 0;
        
//        int sum = 0;
        
        for(int i=0; i<arrSize; i++) {
        	arr[i] = sc.nextInt();
//        	sum += Math.abs( arr[i] );
        	if(arr[i] >= 0) {
        		pos += arr[i];
        		if(arr[i] < minPos) {
        			minPos = arr[i];
        		}
        		numPos++;
        	}
        	else {
        		neg += Math.abs( arr[i] );
        		if(arr[i] > maxNeg ) {
        			maxNeg = arr[i];
        		}
        		numNeg++;
        	}
        }
        
        int numQueries = sc.nextInt();
        int queriesSoFar = 0;
        int qValue = 0;
        for(int queryIndx=0; queryIndx<numQueries;queryIndx++) {
        	qValue = sc.nextInt();
        	queriesSoFar += qValue;
        	
        	
        	boolean isMaxPosReset = false;
        	if ( (minPos+queriesSoFar) >= 0 )
        	{
        		pos += (numPos * qValue );
        	}
        	else {
        		minPos = Integer.MAX_VALUE;
        		
        		for(int i=0; i<arrSize; i++) {
        			if(arr[i] > 0)
        			{
        				if(  arr[i] < (-queriesSoFar) ) {
//                    		pos += arr[i] + queriesSoFar;
//                    		neg -= Math.abs( arr[i] + queriesSoFar - qValue );
//                    		arr[i]+= queriesSoFar;
                    	
        					
                    		numPos--;
                    		numNeg++;
                    		neg += Math.abs( arr[i] + queriesSoFar );
                    		pos -= arr[i] + queriesSoFar - qValue;
                    		arr[i]+= queriesSoFar;
                    		
                    		isMaxPosReset = true;
                    	}
                    	else {
                    		if(arr[i] < minPos ) {
                    			minPos = arr[i];
                    		}
                    	}
        			}
                	
                }
        		
        		pos += numPos * qValue;
        		
        	}
        	
        	if ( (maxNeg+queriesSoFar) <= 0 )
        	{
        		neg -= (numNeg * qValue );
        	}
        	else {
        		maxNeg = Integer.MIN_VALUE;
        		
        		for(int i=0; i<arrSize; i++) {
        			if(arr[i] < 0)
        			{
        				if(  arr[i] > (-queriesSoFar) ) {
                    		numPos++;
                    		numNeg--;
                    		pos += arr[i] + queriesSoFar;
                    		neg -= Math.abs( arr[i] + queriesSoFar - qValue );
                    		arr[i]+= queriesSoFar;
                    	}
                    	else {
                    		if(arr[i] > maxNeg ) {
                    			maxNeg = arr[i];
                    		}
                    	}
        			}
                	
                }
        		
        		neg -= numNeg * qValue;
        		if(isMaxPosReset) {
        			neg += qValue;
        		}
        		
        	}
        	
//        	for(int i=0; i<arr.length;i++) {
//        		arr[i] = arr[i] + qValue;
//        		sum += Math.abs(arr[i]);
//        	}
        	
//        	System.out.println("qValue=" + qValue + " : sum=" + sum + " : numPos="+numPos+ " : pos=" + pos + " : numNeg=" + numNeg + " : neg=" + neg + " : maxNeg=" + maxNeg);
        	
        	System.out.println(pos+neg);
        }
        
        sc.close();
	}

}
