package com.hackerrank.warmup;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class ServiceLane
{
	// automated test
	public static void main(String[] args)
    {
		int numCases = 1000;
		int n = 100000;
		TreeSet<Integer> segmentsTreeBikes = new TreeSet<Integer>();
        TreeSet<Integer> segmentsTreeCars = new TreeSet<Integer>();
		
		int[] segments = new int[n];
		
		Random rand = new Random();
		
		for(int i=0; i<n; i++)
        {
            int nextSeg = (int) rand.nextInt(2) + 1;
            segments[i] = nextSeg;
            if( nextSeg == 1 )
                segmentsTreeBikes.add(i);
            else if( nextSeg == 2)
                segmentsTreeCars.add(i);
        }
		
		Pair[] cases = new Pair[numCases];
        ServiceLane sl = new ServiceLane();
        int n_minus_1 = n-1;
        @SuppressWarnings("unused")
		int n_minus_2 = n-2;
        
        for(int i=0; i<numCases; i++)
        {
        	int entry = rand.nextInt(n_minus_1); // Math.random() * n_minus_1
        	int exit = rand.nextInt(n_minus_1) + entry / 2; //(int) Math.random() * n_minus_1 + entry
        	exit = Math.min(exit, n_minus_1);
        	cases[i] = sl.new Pair(entry, exit);
        }
            
        long startTime = System.nanoTime();
        for(int i=0; i<numCases; i++)
            System.out.println( cases[i].entry + ","+ cases[i].exit + " : " + solve(segments, cases[i] ) );    
        long timeTakenIterative = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for(int i=0; i<numCases; i++)
            System.out.println( cases[i].entry + ","+ cases[i].exit + " : " + solveWithTree(segmentsTreeBikes, segmentsTreeCars, cases[i] ) );
        
        long timeTakenTree = System.nanoTime() - startTime;
        
        System.out.println("n_minus_1="+n_minus_1);
        System.out.println("Tree sizes:" + segmentsTreeBikes.size() + " : " + segmentsTreeCars.size());
        System.out.println("Time taken (iterative)=" + timeTakenIterative );
        System.out.println("Time taken (Tree)=" + ( timeTakenTree ) );
    }
	
	public static void main2(String[] args)
    {
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();
        int numCases = sc.nextInt();
        
        int[] segments = new int[n];
        TreeSet<Integer> segmentsTreeBikes = new TreeSet<Integer>();
        TreeSet<Integer> segmentsTreeCars = new TreeSet<Integer>();
        for(int i=0; i<n; i++)
        {
            int nextSeg = sc.nextInt();
            segments[i] = nextSeg;
            if( nextSeg == 1 )
                segmentsTreeBikes.add(i);
            else if( nextSeg == 2)
                segmentsTreeCars.add(i);
        }
                        
        Pair[] cases = new Pair[numCases];
        ServiceLane sl = new ServiceLane();
        for(int i=0; i<numCases; i++)
            cases[i] = sl.new Pair(sc.nextInt(), sc.nextInt());
        
        long startTime = System.nanoTime();
        for(int i=0; i<numCases; i++)
            System.out.println( solve(segments, cases[i] ) );    
        System.out.println("Time taken (iterative)=" + ( System.nanoTime() - startTime ) );
        
        startTime = System.nanoTime();
        for(int i=0; i<numCases; i++)
            System.out.println( solveWithTree(segmentsTreeBikes, segmentsTreeCars, cases[i] ) );
        System.out.println("Time taken (iterative)=" + ( System.nanoTime() - startTime ) );
        
        sc.close();
    }
    
    // iterative
    public static int solve(int[] segments, Pair pair)
    {
        int smallest = 3;
        for(int i=pair.entry; i<=pair.exit && smallest > 1; i++)
            if(segments[i] < smallest)
                smallest = segments[i];
        
        return smallest;
    }
    
    private static int solveWithTree(TreeSet<Integer> segmentsTreeBikes, TreeSet<Integer> segmentsTreeCars, Pair pair)
    {
        Integer floor = segmentsTreeBikes.floor( pair.exit );
        
        if( floor != null && floor >= pair.entry )
            return 1;
        
        floor = segmentsTreeCars.floor( pair.exit );
        if( floor != null && floor >= pair.entry )
            return 2;
        
        return 3;
    }
    
    private class Pair
    {
        private int entry = 0, exit = 0;
        
        private Pair(int entry, int exit)
        {
            this.entry = entry;
            this.exit = exit;
        }
    }
    
    
}


