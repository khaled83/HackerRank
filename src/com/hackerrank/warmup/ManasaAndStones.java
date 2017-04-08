package com.hackerrank.warmup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ManasaAndStones {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum = 1; caseNum <= numCases; caseNum++) 
        {
            int numStones = sc.nextInt();
            int dif1 = sc.nextInt();
            int dif2 = sc.nextInt();
            
            int steps = 1;
            Set<Long> lastStones = new HashSet<Long>();
            List<Long> curStones = null;
            lastStones.add(0L);
            
            while(steps++ < numStones)
            {
            	curStones = new ArrayList<Long>(lastStones);
            	lastStones.clear();
            	for(Long stone : curStones) {
            		lastStones.add(stone + dif1);
            		lastStones.add(stone + dif2);
            	}
            }
            
            curStones = new ArrayList<Long>(lastStones);
            Collections.sort(curStones);
            for(Long stone : curStones)
            	System.out.print(stone + " ");
            
            System.out.println();
        }
        
        sc.close();
	}
	
	/**
	 * Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private static void solutionWithArray() {
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum = 1; caseNum <= numCases; caseNum++) 
        {
            int numStones = sc.nextInt();
            int treeSize = (int) Math.pow(2, numStones) - 1;
            int dif1 = sc.nextInt();
            int dif2 = sc.nextInt();
            System.out.println("treeSize="+treeSize+", dif1="+dif1+", dif2="+dif2);
            
            long[] stones = new long[treeSize];
            
            stones[0] = 0;
            for(int i=0; i<treeSize/2;i++)
            {
                stones[i*2+1] = stones[i] + dif1;
                stones[i*2+2] = stones[i] + dif2;
                System.out.println("stones["+(i*2+1)+"]="+(stones[i] + dif1) + " / stones["+(i+2+2)+"]="+stones[i] + dif2);
            }
            // 0 1 2 3 4 5 6
            // 0 1 2 2 3 

            TreeSet<Long> values = new TreeSet<Long>();
            
            for(int i=treeSize/2; i<treeSize;i++) {
                values.add(stones[i]);
            }
            
            for(Long value : values) {
                System.out.print(value + " ");
            }
            
            sc.close();
            System.out.println();            
            
            
            /**
            4 10 100
            0 10 20 30
            0 10 20 120
            0 10 110 210
            0 100 200 300
            
            [0, 10, 100, 20, 110, 110, 200,...]
            [0,  1,   2,  3,   4,   5,   6,...]
            
              0
         10        100
     20   110   110    200       size=7
 30  120 120 210 ....            size=15
            */
        }
	}
	

}
