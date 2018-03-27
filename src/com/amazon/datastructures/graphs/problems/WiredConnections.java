package com.amazon.datastructures.graphs.problems;

import java.util.*;

public class WiredConnections {
	
	// each vertex is identified by 0-based index
    private PCB[] vertices;
    
    // number of vertices
    private final int V;
    
    // each entry stores the adjacent list of vertices connected by current vertex
    private LinkedList<Integer>[] adj;
    
    public static enum PCB { LEFT, RIGHT }
    
    public WiredConnections(int n) {
    	this.V = n;
    	vertices = new PCB[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
        	adj[v] = new LinkedList<Integer>();
        }
    }
    
//    public WiredConnections(int[][] arr, int rows, int cols) {
//        V = rows;
//        this.rows = rows;
//        this.cols = cols;
//        
//        vertices = new PCB[V];
//        adj = (LinkedList<Integer>[]) new LinkedList[V];
//        
//        // initialize adj list
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                // vertex id
//                int v = id(row, col);
//                adj[v] = new LinkedList<Integer>();
//                int up = row - 1;
//                int down = row + 1;
//                int left = col - 1;
//                int right = col + 1;
//                int w = id(up, col);
//                if (checkVertex(w) && arr[up][col] > 0) {
//                    addEdge(v, w);
//                }
//                w = id(down, col);
//                if (checkVertex(w) && arr[down][col] > 0) {
//                    addEdge(v, w);
//                }
//                w = id(row, left);
//                if (checkVertex(w) && arr[row][left] > 0) {
//                    addEdge(v, w);
//                }
//                w = id(row, right);
//                if (checkVertex(w) && arr[row][right] > 0) {
//                    addEdge(v, w);
//                }
//            }
//        }
//    }
    
    public boolean addEdge(int v, int w) {
        if (!checkVertex(v) || !checkVertex(w)) {
            return false;
        }
        if (adj[v] == null) {
            adj[v] = new LinkedList<Integer>();
        }
        if (adj[w] == null) {
            adj[w] = new LinkedList<Integer>();
        }
        adj[v].add(w);
        adj[w].add(v);
        return true;
    }
    
//    private int id(int row, int col) {
//    	if (row < 0 || row >= rows || col < 0 || col >= cols) {
//    		return -1;
//    	}
//        return row * cols + col;
//    }
    
    private boolean checkVertex(int v) {
        return v >= 0 && v < V;
    }
    
    /**
    
     0 1 2 3 4
   0   T T 
   1       T T
   2 T     T
   3 
   4   T   T
   
     0 1 2
   0 0 1 2
   1 3 4 5
   2 6 7 8
    
    */
        
    public boolean isPossbibleWireConnection() {
        Queue<Integer> q = new LinkedList<Integer>();
        // vertices visited and marked left or right
        HashSet<Integer> left = new HashSet<Integer>();
        HashSet<Integer> right = new HashSet<Integer>();
        
        // arbitrarily start at first vertex and consider it left
        q.add(0);
        left.add(0);
        
        while (!q.isEmpty()) {
            int v = q.poll();
            // hash for desired type of pcb
            HashSet<Integer> hDesired = left;
            // hash for the opposite type of pcb
            HashSet<Integer> hOpposite = right;
            // if vertex v is marked as left, set desired pcb to right, and vice versa
            if (left.contains(v)) {
                hDesired = right;
                hOpposite = left;
            }
            
            for (int w : adj[v]) {
                if (hOpposite.contains(w)) {
                    return false;
                } else if(!hDesired.contains(w)) {
                    hDesired.add(w);
                    q.add(w);
                }
            }
        }
        
        return true;
    }

}
