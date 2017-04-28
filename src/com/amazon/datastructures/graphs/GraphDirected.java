package com.amazon.datastructures.graphs;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.amazon.datastructures.queues.QueueArrayBased;

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
	
}
