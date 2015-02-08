package com.hackerrank.context15;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MaximiseSum {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        
        for(int caseNum=1; caseNum<=numCases; caseNum++)
        {
        	int arrSize = sc.nextInt();
        	double mod = sc.nextDouble();
        	
        	double[] arr = new double[arrSize];
        	for(int i=0; i<arr.length;i++) {
        		arr[i] = sc.nextDouble();
        	}
        	
        	double maxMod = 0;
        	double curMod = 0;
        	for(int i=0; i<arr.length;i++) {
        		curMod = arr[i] % mod;
        		if(maxMod == (mod - 1)) {
        			break;
        		}
        		for(int window = 1; window<arr.length-1-i; window++) {
        			curMod = (curMod + (arr[i+window] % mod));
        			if(curMod > mod) {
        				 curMod = curMod % mod;
        			}
        			if(curMod > maxMod) {
        				maxMod = curMod;
        				if( maxMod == ( mod - 1) ) {
        					break;
        				}
        			}
        		}
        	}
        	
        	System.out.println( format.format(maxMod) );
        }
        
        sc.close();

	}

}
