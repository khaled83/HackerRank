package com.amazon.datastructures.graphs.problems;

import java.util.*;

import com.amazon.datastructures.queues.QueueLinkedList;

public class NumberOfIslands {
	
	private final int V;
    // vertices values
    private final Color[] vertices;
    // adjacency list
    private ArrayList<Integer>[] adj;
    
    private final int rows, cols;
    
    // creates adj list graph from 2d matrix
    public NumberOfIslands(Color[][] arr, int rows, int cols) {
        this.V = rows * cols;
        this.rows = rows;
        this.cols = cols;
        
        // set vertex values
        vertices = new Color[V];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                vertices[indx(row, col)] = arr[row][col];
            }
        }
        
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        // initialize adj lists
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>(4); // each node has max of 4 adj nodes
        }
        
        // set adj lists
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // add left, right, top, down
                // left
                int left = col - 1;
                if (left >= 0) {
                    addEdge(indx(row, col), indx(row, left));
                }
                int right = col + 1;
                if (right < cols) {
                    addEdge(indx(row, col), indx(row, right));
                }
                int up = row - 1;
                if (up >= 0) {
                    addEdge(indx(row, col), indx(up, col));
                }
                int down = row + 1;
                if (down < rows) {
                    addEdge(indx(row, col), indx(down, col));
                }                
            }
        }
    }
    
    private void addEdge(int row1, int col1, int row2, int col2) {
        int v = indx(row1, col1);
        int w = indx(row2, col2);
        addEdge(v, w);
    }
    
    public void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adj[v].add(w);
    }
    
    private void checkVertex(int v) {
        if (v < 0 || v > V) {
            throw new IllegalArgumentException();
        }
    }
    
    private int indx(int row, int col) {
        return row * cols + col;
    }
    
    public Color getColor(int row, int col) {
    	return vertices[indx(row, col)];
    }

    public int findNumIslands() {

        Queue<Integer> q = new LinkedList<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        
        int numLands = 0;
        for (int start = 0; start < V; start++) { 
            // start traversal from unvisited land node
            if (!visited.contains(start) && vertices[start] == Color.BLACK) { 
            	numLands++;
                // start traversal from each node
                q.add(start);
                visited.add(start);
                
                while (!q.isEmpty()) {
                    int v = q.poll();
                    // traverse adjacent land nodes
                    for (int w : adj[v]) {
                        if (vertices[w] == Color.BLACK && !visited.contains(w)) {
                            q.add(w);
                            visited.add(w);
                        }
                    }
                }
            }
            
        }
        
        return numLands;
    }
    
    
}
