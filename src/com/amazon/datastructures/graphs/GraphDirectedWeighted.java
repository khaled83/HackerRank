package com.amazon.datastructures.graphs;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Weighted directed graph
 * @author khaledabbas
 *
 */
public class GraphDirectedWeighted {

	protected int E;
	protected final int V;

	protected HashMap<Integer, Integer>[] adj;

	public GraphDirectedWeighted(int V) {
		this.V = V;
		adj = (HashMap<Integer, Integer>[]) new HashMap[V];
		for (int i = 0; i < V; i++)
			adj[i] = new HashMap<Integer, Integer>();
	}

	public void addEdge(int v, int w, int weight) {
		checkVertex(v);
		checkVertex(w);
		E++;
		adj[v].put(w, weight);
	}

	public void addEdge(char v, char w, int weight) {
		addEdge(v-'a', w-'a', weight);
	}
	
	public void removeEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        adj[v].remove(w);
    }
	
	public boolean hasEdge(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        return adj[v].containsKey(w);
    }
	
	public Iterable<Integer> adj(int v) {
		checkVertex(v);
		return adj[v].keySet();
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
			throw new IllegalArgumentException(v+"");
	}

	protected void visit(int v, StringBuilder sb) {
		sb.append(toChar(v)).append(' ');
	}
	
	private char toChar(int v) {
        return (char)('a'+v);
    }
	
	public GraphWeighted minSpanTree() {
        GraphWeighted g = new GraphWeighted(this.V);

        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        
        HashSet<Integer> unvisited = new HashSet<Integer>();
        for (int v = 1; v < V; v++)
        	unvisited.add(v);

        while (!unvisited.isEmpty()) {
            // examine unvisited edges from v and choose min
        	int minE = Integer.MAX_VALUE;
        	int minV = -1;
        	int minW = -1;
            for (int v : visited) {
            	for (int w : adj[v].keySet()) {
            		// check if edge v-w is unvisited
            		if (!visited.contains(w)) {
            			int weight = adj[v].get(w);
            			if (weight < minE) {
            				minV = v;
            				minE = weight;
            				minW = w;
            			}                
            		}
            	}
            }
            if (minW >= 0) {
    			visited.add(minW);
    			unvisited.remove(minW);
            	g.addEdge(minV, minW, minE);
            }
        }
        
        return g;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int v = 0; v < V; v++) {
			sb.append((char) (toChar(v)));
			sb.append(" -> ");
			for (int w : adj[v].keySet())
				sb.append((char) (toChar(w))).append("("+adj[v].get(w)+")").append(", ");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int shortest(int v, int dst) {
	    
        int[] weight = new int[V];
        for (int w = 0; w < V; w++) {
            if (adj[v].containsKey(w))
                weight[w] = adj[v].get(w);
            else
                weight[w] = Integer.MAX_VALUE;
        }
        weight[v] = 0;
            
        HashSet<Integer> visited = new HashSet<Integer>(V);
        visited.add(v);
        
        HashSet<Integer> unvisited = new HashSet<Integer>(V);
        for (int w = 0; w < V; w++)
            unvisited.add(w);
        unvisited.remove(v);
        
        /**

        0 => 1(5), 3(15)
        1 => 2(5)
        2 => 3(2)
        3
        
        visited    0  1  2
        
        v=0
        
        visited    0 1 2
        unvisited  3
        
                   0  1  2  3
        weight     0  5  X  15
                   0  5  10 12
                   
                   
                   
        */
        
        int counter = 0;
        while (visited.size() < V && counter < 5) {
        	counter++;
            int minE = Integer.MAX_VALUE;
            int minV = -1;
            for (int w : unvisited) {
                if (weight[w] < minE) {
                    minE = weight[w];
                    minV = w;   
                }
            }
            
            visited.add(minV);
            unvisited.remove(minV);
            
            for (int w : unvisited) {
                if (adj[minV].containsKey(w)) {
                    int newWeight = weight[minV] + adj[minV].get(w);
                    if (newWeight < weight[w])
                        weight[w] = newWeight;
                }
            }
            
        }
        
        return weight[dst];
    }
	
}








