package com.indeed.khaledabbas.datastructures.graph;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {
	
	HashMap<Vertex, TreeMap<Vertex,Integer>> adjList;
	HashMap<String, Vertex> vertices;
	
	private static final TreeMap<Vertex, Integer> EMPTY_MAP = new TreeMap<Vertex, Integer>();
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
		
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);
		
		adjList.get(w).put(v, weight);
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












