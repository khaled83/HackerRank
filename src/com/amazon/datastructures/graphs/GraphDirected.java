package com.amazon.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import com.amazon.datastructures.queues.QueueArrayBased;
import com.khaledabbas.datastructures.arrays.ArrayUtils;

/**
 * Directed unweighted graph
 * @author khaledabbas
 *
 */
public class GraphDirected {

	protected int E;
	protected final int V;

	protected HashSet<Integer>[] adj;

	public GraphDirected(int V) {
		this.V = V;
		adj = (HashSet<Integer>[]) new HashSet[V];
		for (int i = 0; i < V; i++)
			adj[i] = new HashSet<Integer>();
	}

	public void addEdge(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		E++;
		adj[v].add(w);
	}

	public void removeEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adj[v].remove(w);
    }
	
	public boolean hasEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        return adj[v].contains(w);
    }
	
	public Iterable<Integer> adj(int v) {
		checkVertex(v);
		return adj[v];
	}

	public int degree(int v) {
		checkVertex(v);
		return adj[v].size();
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	protected void checkVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException();
	}

	public Iterable<Integer> topOrder() {
        GraphDirected g = new GraphDirected(V);
        
        for (int v = 0; v < V; v++)
            for (int w : adj(v)) 
            	g.addEdge(v,w);
        
        QueueArrayBased q = new QueueArrayBased(V);
        
        /**
        0 => 
        1 => 
        2 => 
        3 => 
        4 => 
        
        q    
        w    3 2 1 0 4
        */
        try {
        	// initial state
        	for (int v = 0; v < V; v++)
        		if (g.degree(v) == 0)
        			q.enqueue(v);
        	
        	while (!q.isEmpty()) {
        		int w = q.dequeue();
        		for (int v = 0; v < V; v++)
        			if (g.hasEdge(v,w)) {
        				g.removeEdge(v,w);
        				if (g.degree(v) == 0)
        					q.enqueue(v);
        			}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        List<Integer> result = q.asList();
        Collections.reverse(result);
        return result;
    }
	
	// just another implementation repeated
	public List<Integer> topOrder2() {
        // final result: vertices in topological order
        List<Integer> res = new ArrayList<Integer>();
        
        // clone adj
        HashSet<Integer>[] adjClone = (HashSet<Integer>[]) new HashSet[V];
        for (int v = 0; v < V; v++) {
            adjClone[v] = new HashSet<Integer>();
            for (int w : adj[v]) {
                adjClone[v].add(w);
            }
        }
         
        // hash of vertices remaining to be processed  
        HashSet<Integer> h = new HashSet<Integer>();
        for (int v = 0; v < V; v++) {
            h.add(v);
        }
        System.out.println("h="+h);
        
        // repeat V times
        for (int i = 0; i < V; i++) {
            int w = -1;
            for (int candidate : h) {
            		System.out.println(adjClone[candidate].size() + " : " + adjClone[candidate].size() + " => " + adjClone[candidate]);
                if (adjClone[candidate].size() == 0) {
                    w = candidate;
                    break;
                }
            }
            res.add(w);
            h.remove(w);
            // remove all edges leading to w
            for (int v : h) {
                adjClone[v].remove(w);
            }
        }
        
        Collections.reverse(res);
        return res;
    }
	
	protected void visit(int v, StringBuilder sb) {
		sb.append(toChar(v)).append(' ');
	}
	
	private char toChar(int v) {
        return (char)('a'+v);
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int v = 0; v < V; v++) {
			sb.append((char) (toChar(v)));
			sb.append(" -> ");
			for (int w : adj[v])
				sb.append((char) (toChar(w))).append(", ");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	// ====== 2D matrix to Graph
	
	public GraphDirected(int rows, int cols) {
        this(rows*cols);
    }
    
    public GraphDirected(int [][] arr, int rows, int cols) {
        this(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (arr[row][col] > 0) {
                    if (col > 0 && arr[row][col - 1] > 0) {
						addEdge(vertexIndx(row, col), vertexIndx(row, col - 1));
                    }
					if (col < cols - 1 && arr[row][col + 1] > 0) {
						addEdge(vertexIndx(row, col), vertexIndx(row, col + 1));
                    }
                    if (row > 0 && arr[row - 1][col] > 0) {
                        addEdge(vertexIndx(row, col), vertexIndx(row - 1, col));
                    }
                    if (row < rows - 1 && arr[row + 1][col] > 0) {
                        addEdge(vertexIndx(row, col), vertexIndx(row + 1, col));
                    }                    
                }
            }
        }
    }
    
    private int vertexIndx(int row, int col) {
        return row * 4 + col;
    }
    
    public Iterable<Integer> findPath(int srcRow, int srcCol, int dstRow, int dstCol) {
    	return findPath(vertexIndx(srcRow, srcCol), vertexIndx(dstRow, dstCol));
    }
    
    public boolean hasDeadlock() {
        
        for (int start = 0; start < V; start++ ) {
            HashSet<Integer> visited = new HashSet<Integer>();   
            Stack<Integer> s = new Stack<Integer>();
            s.push(start);
            visited.add(start);
            // traverse graph starting from node v
            while (!s.isEmpty()) {
                int v = s.pop();
                for (int w : adj[v]) {
                    if (w == start) {
                        // deadlock detected!
                        return true;
                    }
                    else if (!visited.contains(w)) {
                        s.push(w);
                        visited.add(w);
                    }
                }
            }
        }
        
        return false;
        
        /**
        for (int v : vertices) {
            for (int w : adj[v]) {
                if (visited.contains(w)) {
                    // deadlock detected
                    // error: doesn't mean deadlock
                    return false;
                }
                visited.add(v);
            }
        }
        */
    }
    
    /**

        0  1  2  3
      0 1  1  0  0
      1 0  1  1  1
      2 1  1  0  0
      3 0  1  1  1
        
        0  1  2  3
      0 0  1  2  3
      1 4  5  6  7  
      2 8  9  10 11 
      3 12 13 14 15
      
      
      src=0, dst=15
      
        stack            visited                path
      - ---------        -------                ------
      1 0                0                      0
      2 1                0 1                    0      
      3 5                0 1 5                  0 1
      3 6 9              0 1 5 6 9              0 1 5                      
      4 9                0 1 5 6 9              0 1 5 6   
      4 9 7              0 1 5 6 9 7            0 1 5 6 7 
      4 9                0 1 5 6 9 7            0 1 5 
      5 13               0 1 5 6 9 13           0 1 5 6 9 
      6 14               0 1 5 6 9 13 14        0 1 5 6 9 13
      7 15               0 1 5 6 9 13 14 15     0 1 5 6 9 13 14 15
    */
    
    public Iterable<Integer> findPath(int src, int dst) {
    
        Stack<Integer> s = new Stack<Integer>();
        s.push(src);
        HashSet<Integer> visited = new HashSet<Integer>(V);
        visited.add(src);
        Stack<Integer> path = new Stack<Integer>();
        
        boolean found = false;
        while (!s.isEmpty() && !found) {
            int v = s.pop();
            path.push(v);
            boolean excluded = true;
            for (int w : adj[v]) {
                if (!visited.contains(w)) {
                    excluded = false;
                    if (w == dst) {
                        found = true;
                        path.push(w);
                    }
                    s.push(w);
                    visited.add(w);
                }
            }
            if (excluded) {
                path.pop();
            }
        }
        
        if (!found) {
            return null;
        }
        
        return path;
    }

    public int longestPath() {
    		
    		HashSet<Integer>[] adj = new HashSet[2];
    	
        int max = Integer.MIN_VALUE;
        HashSet<Integer> visited = new HashSet<Integer>();
        for (int start = 0; start < V; start++) {
            // being part of path is better than starting from that vertex
            if (visited.contains(start)) {
                continue;
            }
            Stack<Integer> s = new Stack<Integer>();
            s.add(start);
            int length = 0;
            while (!s.isEmpty()) {
                int v = s.pop();
                visited.add(v);
                length++;
                boolean foundAdjacent = false;
                for (int w : adj[v]) {
                    s.add(w);
                    foundAdjacent = true;                
                }
                // backtracking
                if (!foundAdjacent) {
                    max = Math.max(max, length);
                    length--;
                }
            }
        }
        return max;
    }

    
}
