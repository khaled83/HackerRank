package com.amazon.datastructures.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import javax.naming.SizeLimitExceededException;

import com.amazon.datastructures.Arrays;
import com.amazon.datastructures.Queue;
import com.amazon.datastructures.QueuePointerBased;

public class Graph {

	private int E;
	private final int V;

	private LinkedList<Integer>[] adj;

	public Graph(int V) {
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

	private void checkVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException();
	}

	public void dfs() {
		HashSet<Integer> visited = new HashSet<Integer>();
		dfs(0, visited);
	}

	private void dfs(int v, HashSet<Integer> visited) {
		System.out.println(toChar(v));
		visited.add(v);
		for (Integer w : adj[v]) {
			if (!visited.contains(w))
				dfs(w, visited);
		}
	}
	
	/**
	 0 => 1,2
	 1 => 0,3
	 2 => 0
	 3 => 1
	 
	 q	0 1 2 3
	        *
     s  0 1
          *
	 * */
	public void dfs2() {
		try {
			HashSet<Integer> visited = new HashSet<Integer>();
			Stack<Integer> s = new Stack<Integer>(); 
			s.push(0);
			visited.add(0);
			
			Stack<Character> s2 = new Stack<Character>();
			
			while(!s.isEmpty()) {
				int v = s.pop();
				System.out.println(toChar(v));
				
				ArrayList<Integer> adjArr = new ArrayList<Integer>(adj[v].size());
				for (int w : adj[v])
						adjArr.add(w);
				Collections.reverse(adjArr);
				for (int w : adjArr)
					if (!visited.contains(w)) {
						s.push(w);
						s2.push(toChar(w));
						System.out.println(s2);
						visited.add(w);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

}