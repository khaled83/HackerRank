package com.hackerrank.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Knapsack {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numTestCases = sc.nextInt();
        
        //5 9
        //3 4 4 4 8
        for(int testCase = 1; testCase <= numTestCases; testCase++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            
            Integer[] values = new Integer[n];
            for(int i=0; i<n; i++) {
                values[i] = sc.nextInt();
            }
            
            Arrays.sort(values);
            List<Integer> valueList = new ArrayList<Integer>();
            int cur = Integer.MIN_VALUE;
            for(int value : values) {
                if(cur != value) {
                    valueList.add(value);
                    cur = value;    
                }            
            }
            
            values = new Integer[valueList.size()];
            for(int i = 0; i<valueList.size(); i++) {
            	values[i] = valueList.get(i);
            }
            int kIndx = Arrays.binarySearch(values, k);
            
            int expected = 0;
            if(kIndx >= 0) {
                expected = values[kIndx];
            }
            else {
                kIndx = Math.min( values.length - 1, Math.abs( kIndx + 2 ) );
                
                int best = 0;
                for(int i = kIndx; i >= 0 && best != k; i--) {
                    int remaining = k % values[i];
                    expected = k - remaining;
                    int remIndx = 0;
                    do {
                        remIndx = Arrays.binarySearch(values, remaining);
                        if(remIndx >= 0) {
                            expected = k;
                            best = expected;
                            break;
                        }
                        else if(remIndx != -1) {
                        	remIndx = Math.abs( remIndx+2 );
                            expected = k + values[remIndx];
                            remaining -= values[remIndx];
                        }
                    } while( remIndx != -1 && remaining != 0 );

                    if(expected > best) {
                        best = expected;
                    }
                }
            }
            
            System.out.println(expected);
        }
        
        sc.close();
        
    }
}















