package com.amazon.datastructures.graphs;

import java.util.HashSet;
import java.util.Stack;
import java.util.LinkedList;

public class GraphDirectedExt extends GraphUndirected {

	public GraphDirectedExt(int V) {
		super(V);
	}
	
	@Override
	public void addEdge(int v, int w) {
		checkVertex(v);
		checkVertex(w);
		adj[v].add(w);
	}
	
	/**
    state		result
    ------      ------
	0 => 3		
	1 => 3
	2 => 0,1
	3 =>  
	
	0 => 		3,
	1 => 
	2 => 0,1
	
	1 => 		3,0,
	2 => 1
	
	2 =>		3,0,1,2
	 		    2->1->3
	 		     ->0

	0 1->3 2
	 ---> <-
	       
	2->0->
	 ->1->3
	   
	0->1->3->4
	|->2
	*/
	
	public StringBuilder to;
	public void topologicalOrder() {
		
		LinkedList<HashSet<Integer>> list = new LinkedList<HashSet<Integer>>();
		for (LinkedList<Integer> l : adj) 
			list.add(new HashSet(l));
		
		while (!list.isEmpty()) {
			
		}
		
	}

}


















