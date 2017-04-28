package com.amazon.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import com.amazon.datastructures.queues.Queue;
import com.amazon.datastructures.queues.QueueArrayBased;
import com.amazon.datastructures.queues.QueuePointerBased;

/**
 * Undirected unweighted graph
 * @author khaledabbas
 *
 */
public class GraphUndirected {

	protected int E;
	protected final int V;

	protected LinkedList<Integer>[] adj;

	public GraphUndirected(int V) {
		this.V = V;
		adj = (LinkedList<Integer>[]) new LinkedList[V];
		for (int i = 0; i < V; i++)
			adj[i] = new LinkedList<Integer>();
	}

	public void addEdge(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
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

	public StringBuilder dfs = new StringBuilder();
	public void dfs() {
		dfs = new StringBuilder();
		HashSet<Integer> visited = new HashSet<Integer>();
		dfs(0, visited);
	}

	/**
	 * recursive dfs
	 */
	private void dfs(int v, HashSet<Integer> visited) {
		dfs.append(toChar(v)).append(' ');
//		System.out.println(toChar(v));
		visited.add(v);
		for (Integer w : adj[v]) {
			if (!visited.contains(w))
				dfs(w, visited);
		}
	}
	
	/**
	 * iterative dfs
	 */
	public void dfs2() {
		dfs = new StringBuilder();
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> s = new Stack<Integer>(); 
		s.push(0);
		visited.add(0);
		
		while(!s.isEmpty()) {
			int v = s.pop();
			dfs.append(toChar(v)).append(' ');
//			System.out.println(toChar(v));
			
			ArrayList<Integer> adjArr = new ArrayList<Integer>(adj[v].size());
			for (int w : adj[v])
					adjArr.add(w);
			Collections.reverse(adjArr);
			for (int w : adjArr)
				if (!visited.contains(w)) {
					s.push(w);
					visited.add(w);
				}
		}
    }
	
	/**
	 * iterative dfs, gives exact result as recursive
	 */
	public void dfs3() {
		dfs = new StringBuilder();
		HashSet<Integer> visited = new HashSet<Integer>();
		dfs.append(toChar(0)).append(' ');
		visited.add(0);
		Stack<Integer> s = new Stack<Integer>();
		s.push(0);
		
		while (!s.empty()) {
			int v = s.peek();
			boolean visitedEach = true;
			for (int w : adj[v]) {
				if (!visited.contains(w)) {
					dfs.append(toChar(w)).append(' ');
					visited.add(w);
					s.push(w);
					visitedEach = false;
					break;
				}
			}
			if (visitedEach)
				s.pop();
		}
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

	public void bfs2() {
		bfs = new StringBuilder();
	    visit(0, bfs);
	    HashSet<Integer> visited = new HashSet<Integer>();
	    HashSet<Integer> expanded = new HashSet<Integer>();
	    visited.add(0);
	    expanded.add(0);
	    int w, W;
	    bfs2(0, visited, expanded);
	}

	public StringBuilder bfs;
	private void bfs2(int v, HashSet<Integer> visited, HashSet<Integer> expanded) {
	    for (int w : adj[v]) {
	    	if (!visited.contains(w)) {
	    		visit(w, bfs);
	    		visited.add(w);
	    	}
	    }
	    for (int w : adj[v]) {
	        if (!expanded.contains(w)) {
	        	expanded.add(w);
	            bfs2(w, visited, expanded);
	        }
	    }
	}

	/**
	 * iterative bfs
	 */
	public void bfs() {
		try {
			bfs = new StringBuilder();
			HashSet<Integer> visited = new HashSet<Integer>();
			visit(0, bfs);
			visited.add(0);
			
			Queue q = new QueuePointerBased();
			q.enqueue(0);
			
			while (!q.isEmpty()) {
				int v = q.dequeue();
				for (int w : adj[v])
					if (!visited.contains(w)) {
						visit(w, bfs);
						visited.add(w);
						q.enqueue(w);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void visit(int v, StringBuilder sb) {
		sb.append(toChar(v)).append(' ');
//	    System.out.println(toChar(v));
	}
	
}

















