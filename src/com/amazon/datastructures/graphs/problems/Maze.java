package com.amazon.datastructures.graphs.problems;

import java.util.*;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class Maze {

    private int V;
    // private int E;
    
    // backed by 2d matrix
    int rows, cols;
    
    // 0 based counter id, stores values
    private Color[] vertices;
    // adjacency list
    private LinkedList<Integer>[] adj;
    
    public Maze(Color[][] arr) {
        if (arr == null)
            return;
        
        rows = arr.length;
        cols = arr[0].length;
        
        V = rows * cols;
        vertices = new Color[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color c = arr[row][col];
                int id = id(row, col);
                vertices[id] = c;
                adj[id] = new LinkedList<Integer>();
                // left, up, right, down
                int left = col - 1;
                if (checkVertex(row, left)) {
                    adj[id].add(id(row, left));
                }
                int up = row - 1;
                if (checkVertex(up, col)) {
                    adj[id].add(id(up, col));
                }
                int right = col + 1;
                if (checkVertex(row, right)) {
                    adj[id].add(id(row, right));
                }
                int down = row + 1;
                if (checkVertex(down, col)) {
                    adj[id].add(id(down, col));
                }
            }
        }
    }
    
    private boolean checkVertex(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        return true;
    }
    
    private int id(int row, int col) {
        if (!checkVertex(row, col)) {
            return -1;
        }
        return row * cols + col;
    }
    
/**

B W W W
W W B B
B W B W
W W W B

0 1 2 3
4 5 6 7
8 9 0 1
2 3 4 5

0 1 2
4 5 6
7 8 9

src=12
dst=3

v    w        s        vH
-    -        -        -
              12
12   13       13       12    
13   9,14     9,14     12,13
4             9        12,13,4
9    5        5        12,13,4,9
5    4,1      4,1      12,13,4,9,5
1    2        4,2      12,13,4,9,5,1
2    3        4,3      12,13,4,9,5
3    -
*/
    
    public Iterable findPath(int srcRow, int srcCol, int dstRow, int dstCol) {
        int src = id(srcRow, srcCol);
        int dst = id(dstRow, dstCol);
        Stack<Integer> path = new Stack<Integer>();
        
        HashSet<Integer> visited = new HashSet<Integer>(V);
        Stack<Integer> s = new Stack<Integer>();
        s.add(src);
        while (!s.isEmpty()) {
            int v = s.pop();
            if (v == dst) {
            		path.add(v);
                return path;
            }
            visited.add(v);
            for (int w : adj[v]) {
            		boolean inPath = false;
                if (!visited.contains(w) && vertices[w] == Color.WHITE) {
                    s.add(w);
                    inPath = true;
                }
                if (inPath) {
                		path.add(v);
                }
            }
        }
        
        return null;
    }


}
