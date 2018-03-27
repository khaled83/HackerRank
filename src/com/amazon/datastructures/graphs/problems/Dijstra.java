package com.amazon.datastructures.graphs.problems;

import java.util.*;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class Dijstra {
	
//	public static int[] shortestPath(int[][] weight, int start) {
//	    // n X n matrix
//	    int n = weight.length;
//	    int[] cost = new int[n];
//	    // initialize cost with weight from start to v
//	    for (int w = 0; w < n; w++) {
//	        cost[w] = weight[start][w];
//	    }
//	    
//	    HashSet<Integer> visited = new HashSet<Integer>();
//	    visited.add(start);
//	    int next = start;
//	    
//	    for (int step = 0; step < n - 1; step++) {
//	        int min = Integer.MAX_VALUE;
//	        int v = next;
//	        visited.add(v);
//	        for (int w = 0; w < n; w++) {
//	            if (!visited.contains(w)) {
//	            		if (weight[start][v] < Integer.MAX_VALUE && weight[v][w] < Integer.MAX_VALUE) {
//	            			cost[w] = Math.min(cost[w], weight[start][v] + weight[v][w]);
//	            		}
//	                if (cost[w] < min) {
//	                    min = cost[w];
//	                    next = w;
//	                }
//	            }
//	        }
//	    }
//	    
//	    return cost;
//	}
	
	/**
	 
	 int[][] weight = new int[][] {
	 
	     {0, 8, 0, 9, 4},
	     {0, 0, 1, 0, 0},
	     {0, 2, 0, 3, 0},
	     {0, 0, 2, 0, 7},
	     {0, 0, 0, 1, 0, 0}
	     
	    };
	 
	 */
	
	public static int[] shortestPath(int[][] cost, int start) {
	    int n = cost.length;
	    // longest path so far
	    int[] shortest = new int[n];
	    // processed vertices
	    HashSet<Integer> visited = new HashSet<Integer>();
	    // vertices waiting to be processed
	    HashSet<Integer> waiting = new HashSet<Integer>();
	    for (int v = 0; v < n; v++) {
	        waiting.add(v);
	    }
	    for (int w = 0; w < n; w++) {
	        if (cost[start][w] > 0) {
	            shortest[w] = cost[start][w];
	        }
	        else {
		        	shortest[w] = Integer.MAX_VALUE;
	        }
	    }    
	    shortest[start] = 0;
	    
	    visited.add(start);
	    waiting.remove(start);
	    
	    while (!waiting.isEmpty()) {
	        int v = -1;
	        int minCost = Integer.MAX_VALUE;
	        for (int next : waiting) {
	            if (shortest[next] > 0 && shortest[next] < minCost) {
	                minCost = shortest[next];
	                v = next;
	            }
	        }
	        // v is processed
	        visited.add(v);
	        waiting.remove(v);
	        // update max cost for all vertices
	        for (int w : waiting) {
	            if (cost[v][w] > 0 && (cost[start][v] + cost[v][w]) < shortest[w]) {
	                shortest[w] = shortest[v] + cost[v][w];
	            }
	        }
	    }
	    return shortest;
	}

	public static int[] longestPath(int[][] cost, int start) {
		int n = cost.length;
	    // longest path so far
	    int[] longest = new int[n];
	    // processed vertices
	    HashSet<Integer> visited = new HashSet<Integer>();
	    // vertices waiting to be processed
	    HashSet<Integer> waiting = new HashSet<Integer>();
	    for (int v = 0; v < n; v++) {
	        waiting.add(v);
	    }
	    for (int w = 0; w < n; w++) {
	        if (cost[start][w] > 0) {
	            longest[w] = cost[start][w];
	        }
	        else {
		        	longest[w] = Integer.MIN_VALUE;
	        }
	    }    
	    longest[start] = 0;
	    
	    visited.add(start);
	    waiting.remove(start);
	    
	    while (!waiting.isEmpty()) {
	        int v = -1;
	        int maxCost = Integer.MIN_VALUE;
	        for (int next : waiting) {
	            if (longest[next] > 0 && longest[next] > maxCost) {
	                maxCost = longest[next];
	                v = next;
	            }
	        }
	        // v is processed
	        visited.add(v);
	        waiting.remove(v);
	        // update max cost for all vertices
	        for (int w : waiting) {
	            if (cost[v][w] > 0 && (cost[start][v] + cost[v][w]) > longest[w]) {
	                longest[w] = longest[v] + cost[v][w];
	            }
	        }
	    }
	    return longest;
	}
	
}
