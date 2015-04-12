package com.indeed.khaledabbas.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {
	
	HashMap<Vertex, TreeMap<Vertex,Integer>> adjList;
	HashMap<String, Vertex> vertices;
	
	private static final TreeMap<Vertex, Integer> EMPTY_MaP = new TreeMap<Vertex, Integer>();
	private static final TreeSet<Vertex> EMPTY_SET = new TreeSet<Vertex>();
	
	private int numVertices = 0;
	private int numEdges = 0;
	
	public Graph()
	{
		adjList = new HashMap<Vertex, TreeMap<Vertex, Integer>>();
		vertices = new HashMap<String, Vertex>();
	}
	
	public boolean hasVertex(String name)
	{
		return vertices.containsKey(name);
	}
	
	public boolean hasVertex(Vertex v)
	{
		return vertices.containsKey(v.name);
	}
	
	public Vertex addVertex(String name)
	{
		Vertex v = vertices.get(name);
		if(v == null)
		{
			v = new Vertex(name);
			vertices.put(name, v);
			numVertices++;
			adjList.put(v, new TreeMap<Vertex, Integer>());
		}
		return v;
	}
	
	public boolean hasEdge(String from, String to)
	{
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);
		
		return vertices.containsKey(from)
				&& vertices.containsKey(to)
				&& adjList.get(v).containsKey( w );
	}
	
	public void addEdge(String from, String to, Integer weight)
	{
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);
		
		if(v == null)
			v = addVertex(from);
		if(w == null)
			w = addVertex(to);
		
		// adds or updates existing value
		adjList.get(v).put(w, weight);
		
		numEdges++;
	}
	
	public void addEdgeBiDirection(String from, String to, Integer weight)
	{
		addEdge(from, to, weight);
		addEdge(to, from, weight);
	}
	
	public Iterable<Vertex> adjacentTo(String name)
	{
		if(!hasVertex(name))
			return EMPTY_SET;
		
		return adjList.get( vertices.get(name) ).keySet();
	}
	
	public Iterable<Vertex> getVertices()
	{
		return vertices.values();
	}
	
	public int getNumEdges()
	{
		return numEdges;
	}
	
	public int getNumVertices()
	{
		return numVertices;
	}
	
	
	private int maxEdgesToForest = 0;
	
	/**
	 * {@link https://www.hackerrank.com/challenges/even-tree}
	 */
	int dfsForMaxEdgesToForest(Vertex v)
	{
		int value = 0;
		if( adjList.get(v).keySet().size() == 0 )
			return value;
		else
		{
			for(Vertex w : adjList.get(v).keySet())
			{
				int subtreeCount = 1 + dfsForMaxEdgesToForest(w);
				value += subtreeCount;
				if(subtreeCount % 2 == 0)
				{
					maxEdgesToForest++;
				}
					
			}
			
		}
		
		return value;
	}
	
	private Vertex getTreeRoot()
	{
		for(Vertex root : vertices.values())
		{
			boolean hasParentSoFar = false;
			for(Vertex v : vertices.values())
			{
				if( ! v.equals(root) && this.hasEdge(v.name, root.name))
				{
					hasParentSoFar = true;
					break;
				}
			}
			if(!hasParentSoFar)
				return root;
		}
		
		return null;
	}
	
	public int getMaxEdgesToForest() 
	{
		dfsForMaxEdgesToForest( getTreeRoot() );
		return maxEdgesToForest; 
	}
	
	public void bfs() {
		// being processed vertices
		Queue<Vertex> open = new Queue<Vertex>();
		// processed vertices
		ArrayList<Vertex> closed = new ArrayList<Vertex>();

		// list of all vertices
		HashMap<Vertex, Boolean> processed = new HashMap<Vertex, Boolean>();
		ArrayList<Vertex> remaining = new ArrayList<Vertex>();
		for (Vertex v : vertices.values()) {
			remaining.add(v);
		}

		Vertex v = vertices.get("A"); // vertexList.get(0);
		System.out.println("starting BFS from " + v.name);
		open.enqueue(v);
		processed.put(v, true);
		
		while (!open.isEmpty()) {
			v = open.dequeue();
			for (Vertex w : adjList.get(v).keySet()) {
				if(!processed.containsKey(w))
					open.enqueue(w);
				processed.put(w, true);
			}
			visit(v);
			closed.add(v);
			remaining.remove(v);
			// in case of disconnected graph; make sure we processed all
			// vertices in the graph
			if (open.isEmpty() && !remaining.isEmpty())
				open.enqueue(remaining.remove(0));
		}
		System.out.println();
	}
	
	public void dfs() {
		Vertex root = vertices.get("A");
		Stack<Vertex> stack = new Stack<Vertex>();
		// handles unconnected graph
		HashSet<Vertex> remaining = new HashSet<Vertex>();
		for( Vertex v : vertices.values() ) {
			remaining.add( v );
		}

		remaining.remove(root);
		stack.push(root);

		while (!stack.empty() || !remaining.isEmpty()) {
			
//			if( stack.empty() )
//				for( Vertex v : remaining ) {
//					stack.push(v);
//					remaining.remove(v);
//				}
				
			Vertex top = stack.pop();
			visit(top);
			for (Vertex child : adjList.get( top ).keySet() ) {
				if (remaining.contains(child)) {
					remaining.remove(child);
					stack.push(child);
				}
			}
		}
	}

	public boolean hasPath(String from, String to) {
		return hasPath( vertices.get(from), vertices.get(to) );
	}
	
	public boolean hasPath(Vertex v, Vertex w) {
		// bfs
		Queue<Vertex> queue = new Queue<Vertex>();
		HashSet<Vertex> closed = new HashSet<Vertex>();

		// initialize
		queue.enqueue(v);
		while ( !queue.isEmpty() ) {
			Vertex front = queue.dequeue();
			closed.add(front);
			if (front.equals(w)) // found
				return true;
			for (Vertex child : adjList.get(front).keySet() )
				if (!closed.contains(child))
					queue.enqueue(child);
		}
		
		return false;
	}
	
	private void visit(Vertex v) {
		System.out.print(v.name + " ");
	}

	static class Queue<E> {

		private LinkedList<E> list = new LinkedList<E>();

		public void enqueue(E e) {
			list.addLast(e);
		}

		public E dequeue() {
			return list.removeFirst();
		}

		public boolean isEmpty() {
			return list.isEmpty();
		}
		
		public boolean contains(E e) {
			return list.contains(e);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(Vertex v : vertices.values() )
		{
			sb.append(v.name).append(": ");
			for(Vertex w : adjList.get(v).keySet() )
			{
				sb.append(w.name).append(" ");
			}
			sb.append("\n");
		}
		
		sb.append("Meta information: " + numVertices + " vertices and " + numEdges + " edges \n");
		
		return new String(sb);
	}
	
}












