package com.khaledabbas.datastructures.graph.repeat;

import java.util.*;

public class Graph2 {

	private static class Vertex {
		String name;

		public Vertex(String name) {
			this.name = name;
		}
	}

	HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	HashMap<Vertex, TreeMap<Vertex, Integer>> adjList = new HashMap<Vertex, TreeMap<Vertex, Integer>>();

	private int numVertices = 0; // @getter
	private int numEdges = 0; // @getter

	public Vertex addVertex(String name) {
		Vertex vertex = vertices.get(name);
		if (vertex == null) {
			vertex = new Vertex(name);
			vertices.put(name, vertex);
			adjList.put(vertex, new TreeMap<Vertex, Integer>());
			numVertices++;
		}
		return vertex;
	}

	public boolean hasVertex(String name) {
		return vertices.containsKey(name);
	}

	public void addEdge(String from, String to, int weight) {
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);

		if (v == null)
			v = addVertex(from);
		if (w == null)
			w = addVertex(to);

		// undirectional -> add 2 edges
		adjList.get(v).put(w, weight);
		adjList.get(w).put(v, weight);
		numEdges++;
	}

	public boolean hasEdge(String from, String to) {
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);

		if (v == null || w == null)
			return false;

		return hasEdge(v, w);
	}

	private boolean hasEdge(Vertex from, Vertex to) {
		Vertex v = vertices.get(from);
		Vertex w = vertices.get(to);
		return adjList.get(v).containsKey(w);
	}

	public Iterable<Vertex> adjacentTo(String from) {
		Vertex v = vertices.get(from);
		return adjList.get(v).keySet();
	}
}
