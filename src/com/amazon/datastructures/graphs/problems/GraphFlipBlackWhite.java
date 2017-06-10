package com.amazon.datastructures.graphs.problems;

import java.util.*;

import javax.naming.SizeLimitExceededException;

public class GraphFlipBlackWhite {

    private final int V;
    private ArrayList<Integer>[] adj;
    
    private Color[] values;
    
    private final int ROWS;
    private final int COLS; 
    
    /** constructs adjacency list graph from matrix */
    public GraphFlipBlackWhite(Color[][] arr, int rows, int cols) {
        ROWS = rows;
        COLS = cols;
        V = rows * cols;
        values = new Color[V];
        
        // set vertices values
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[indx(row, col)] = arr[row][col];
            }
        }
        
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        // initialize adj lists
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	adj[indx(row, col)] = new ArrayList<Integer>();
            }
        }
        
        // set adjacency list
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
            	adj[indx(row, col)] = new ArrayList<Integer>();
                // optional optimization: adjancency set twice for some vertices
                int v = indx(row, col);
                // left vertix
                if (col > 0) {
                    int left = indx(row, col - 1);
                    addEdge(v, left);
                }
                // right
                if (col < COLS - 1) {
                    int right = indx(row, col + 1);
                    addEdge(v, right);
                }
                // top
                if (row > 0) {
                    int top = indx(row - 1, col);
                    addEdge(v, top);
                }
                // bottom
                if (row < ROWS - 1) {
                    int bottom = indx(row + 1, col);
                    addEdge(v, bottom);
                }
            }
        }
    }
    
    public Color getColor(int row, int col) {
    	return values[indx(row, col)];
    }
    
    public void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adj[v].add(w);
        adj[w].add(v);
    }
    
    private void checkVertex(int v) {
        if (v < 0 || v > V) {
            throw new IllegalArgumentException();
        }
    }
    
    private int indx(int row, int col) {
        return (row * COLS) + col;
    }
    
    /**
    0 1 2 3
0   x o o x    0 1 2 3
1   x o o x    4 5 6 7
2   x x x o    8 9 0 1
3   x o x o    2 3 4 5

s    q                visited
-    -                -------
1    0                0
2    4                0,4
3    8                0,4,8
4    2,9              0,4,8,2,9
5    9                0,4,8,2,9
6    10               0,4,8,2,9,10
7    14               0,4,8,2,9,10,14
8    -
    
    */
    /**
    Time:  O(n)
    Space: O(n)
    */
    public void flip(int row, int col) {
        int v = indx(row, col);
        checkVertex(v);
        
        com.amazon.datastructures.queues.Queue q = com.amazon.datastructures.queues.Queue.newInstance();
        HashSet<Integer> visited = new HashSet<Integer>();
        
        try { q.enqueue(v); } catch (SizeLimitExceededException e) { e.printStackTrace(); }
        visited.add(v);
        
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int w : adj[v]) {
                if (!visited.contains(w) && values[v] == values[w]) {
                    try { q.enqueue(w); } catch (SizeLimitExceededException e) { e.printStackTrace(); }
                    visited.add(w);
                }
            }
            flip(v);
        }
        
    }
    
    private void flip(int v) {
        checkVertex(v);
        values[v] = ((values[v] == Color.WHITE) ? Color.BLACK : Color.WHITE);
    }

    public static class Queue<E> {
    	
    	private LinkedList<E> list;
    	
    	public void enqueue(E e) {
    		list.addLast(e);
    	}
    	
    	public E dequeue() {
    		return list.removeFirst();
    	}
    	
    	public boolean isEmpty() {
    		return list.isEmpty();
    	}
    	
    }
    
}
