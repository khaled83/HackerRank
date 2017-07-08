package com.amazon.datastructures.graphs.problems;

import java.util.*;

import com.amazon.datastructures.queues.QueueLinkedList;

public class GraphBlackenizeEnclosed {
	
	private final int V;
    // vertices values
    private final Color[] values;
    // adjacency list
    private ArrayList<Integer>[] adj;
    
    private final int rows, cols;
    
    // creates adj list graph from 2d matrix
    public GraphBlackenizeEnclosed(Color[][] arr, int rows, int cols) {
        this.V = rows * cols;
        this.rows = rows;
        this.cols = cols;
        
        // set vertex values
        values = new Color[V];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[indx(row, col)] = arr[row][col];
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
    	return values[indx(row, col)];
    }
    
/**
    0 1 2 3
0   B B B B
1   W B W B
2   B W W B
4   B B B B

    0 1 2 3
0   0 1 2 3
1   4 5 6 7
2   8 9 0 1
3   2 3 4 5

s    bound
-    -----
1    [0,1,2,3]
2    [0,1,2,3,12,13,14,15]
3    [0,1,2,3,12,13,14,15,4,8]
4    [0,1,2,3,12,13,14,15,4,8,7,1]

s        b      v    w      q        visited
-        -      -    -      -        -------
5..8     0..3
9..12    4..7
13       4      4    -      -        4
14       8
15       7
16       11     4    -      -        4

s        v        cur      Flip?
-        -        -        -----
1..16    0..15    4        No (visited.contains(4))
                  6,9,10   Yes
                  0,1,2,   No (values[v] = B)
                  3,5,7,   
                  8,11,12,
                  13,14,15
*/
    // Possible to implement static method that acccepts 2d array and manipulates it blackizeEnclosedRegions(int[][]..)
    // Time O(3 * n) => O(n), Space: O(n)
    public void blackizeEnclosedRegions() {
        // 1. traverse boundary 2. expand inward from whites 3. mark visited whites 4. flip non visisted whites
        // find white boundary nodes
        ArrayList<Integer> boundary = new ArrayList<Integer>();    
        // first row
        for (int row = 0, col = 0; col < cols; col++) {
            boundary.add(indx(row, col));
        }
        // last row
        for (int row = rows - 1, col = 0; col < cols; col++) {
            boundary.add(indx(row, col));
        }
        // first col
        for (int col = 0, row = 1; row < rows - 1; row++) {
            boundary.add(indx(row, col));
        }
        // last col
        for (int col = cols - 1, row = 1; row < rows - 1; row++) {
            boundary.add(indx(row, col));
        }
        
        // 2. expand
        HashSet<Integer> visited = new HashSet<Integer>();
        QueueLinkedList q = new QueueLinkedList(); // dfs, @TODO: implement Queue
        Color W = Color.WHITE;
        Color B = Color.BLACK;
        // 1. traverse boundary
        for (Integer b : boundary) {
            if (values[b] == W && !visited.contains(b)) {
                // 2. expand
                visited.add(b);
                q.enqueue(b);
                
                while (!q.isEmpty()) {
                    int v = q.dequeue();
                    for (int w : adj[v]) {
                        if (values[w] == W && !visited.contains(w)) {
                            // 3. mark visisted whites
                            visited.add(w);
                            q.enqueue(w);
                        }
                    }
                }
            }
        }
        
        // 4. flip non visited whites
        // all whites reachable from boundary are in visited
        for (int v = 0; v < V; v++) {
            if (!visited.contains(v) && values[v] == W) {
                values[v] = B;
            }
        }
    }

}
