package com.amazon.datastructures.graphs.problems;

import java.util.*;

public class GraphFlipEnclosedWhites2 {

    // each vertex has color value
    private Color[] vertices;
    
    // vertex ids are 0-based indices
    private LinkedList<Integer>[] adj;
    
    // num of vertices
    private final int V;
    private final int rows, cols;
    
    public GraphFlipEnclosedWhites2(Color[][] arr, int rows, int cols) {
        V = rows * cols;
        this.rows = rows;
        this.cols = cols;
        vertices = new Color[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        
        // set vertices values
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int v = vertexIndx(row, col);
                vertices[v] = arr[row][col];
            }
        }
        
        // set adj lists
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int v = vertexIndx(row, col);
                int left = col - 1;
                int up = row - 1;
                int right = col + 1;
                int down = row + 1;
                if (left >= 0) {
                    int w = vertexIndx(row, left);
                    addEdge(v,w);
                }
                if (up >= 0) {
                    int w = vertexIndx(up, col);
                    addEdge(v,w);
                }
                if (right < cols) {
                    int w = vertexIndx(row, right);
                    addEdge(v,w);
                }
                if (down < rows) {
                    int w = vertexIndx(down, col);
                    addEdge(v,w);
                }
            }
        }
    }
    
    private int vertexIndx(int row, int col) {
        return row * rows + col;
    }
    
    private void addEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        if (adj[v] == null) {
            adj[v] = new LinkedList<Integer>();
        }
        adj[v].add(w);
    }
    
    private void checkVertex(int v) {
        if (v < 0 || v > V) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
    }

/**
Time: O(n)
Space: O(1)
*/
    public void flipEnclosedWhites() {
        HashSet<Integer> boundary = new HashSet<Integer>();
        Queue<Integer> boundaryQ = new LinkedList<Integer>();
        
        Color W = Color.WHITE;
        Color B = Color.BLACK;
        
        // add all WHITES on boundary
        // first row
        int row = 0;
        for (int col = 0; col < cols; col++) {
            int v = vertexIndx(row, col);
            if (vertices[v] == W) {
                boundary.add(v);
                boundaryQ.add(v);
            }
        }
        // last row
        row = rows - 1;
        for (int col = 0; col < cols; col++) {
            int v = vertexIndx(row, col);
            if (vertices[v] == W) {
                boundary.add(v);
                boundaryQ.add(v);
            }
        }
        // first col
        int col = 0;
        for (row = 0; row < rows; row++) {
            int v = vertexIndx(row, col);
            if (vertices[v] == W) {
                boundary.add(v);
                boundaryQ.add(v);
            }
        }
        // last col
        col = cols - 1;
        for (row = 0; row < rows; row++) {
            int v = vertexIndx(row, col);
            if (vertices[v] == W) {
                boundary.add(v);
                boundaryQ.add(v);
            }
        }
        
        // travers boundary white graphs
        while (!boundaryQ.isEmpty()) {
            int v = boundaryQ.poll();
            for (int w : adj[v]) {
                if (!boundary.contains(w) && vertices[w] == W) {
                    boundaryQ.add(w);
                    boundary.add(w);
                }
            }
        }
        
        // traverse all vertices in the graph
        for (int v = 0; v < V; v++) {
            // find white vetex that is not reachable from boundary
            if (vertices[v] == W && !boundary.contains(v)) {
                vertices[v] = B;
            }
        }
    }
    
    /**

matrix:
    0 1 2 3
    4 5 6 7
    8 9 0 1
    2 3 4 5
    
    W B B B
    W W B B
    B B W B
    B B B B
    
adj:

b        q        v    col    w    col
-        -        -    ---    -    ---
// find all boundary whites and store them in boundary hash
0        0        0
                  1
                  2
                  3
                  12
                  13
                  14
                  15
                  0
0,4      0,4      4
                  8
                  2
                  3
                  7
                  11
                  15
// traverse starting from boundary whites, expadning to whites and store them in boundary hash
0,4      4        0           1
0,4      4        0           4
0,4               4           0
0,4,5    5        4           5
0,4,5             4           8
0,4,5             5           1,4,6,9
// traverse every white in the graph, and flip it if its not contained in boundary hash
0,4,5             0,4        
0,4,5             10* (flips to white)
    */

    public Color getColor(int row, int col) {
    	int v = vertexIndx(row, col);
    	return vertices[v];
    }
    
}
