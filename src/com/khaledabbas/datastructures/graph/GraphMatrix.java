package com.khaledabbas.datastructures.graph;

import java.util.*;

public class GraphMatrix {

	public static void main(String[] args) throws Exception {
		GraphMatrix G = new GraphMatrix(5);
		G.addEdge(0, 1, 8);
		G.addEdge(0, 3, 9);
		G.addEdge(0, 4, 4);
		G.addEdge(1, 2, 1);
		G.addEdge(2, 1, 2);
		G.addEdge(2, 3, 3);
		G.addEdge(3, 4, 7);
		G.addEdge(3, 2, 2);
		G.addEdge(4, 2, 1);
		System.out.println(G.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// line 1: indices of vertices
		sb.append( "   " );
		for( int v = 0; v < numVertices; v++ )
			sb.append(v + "  ");
		
		for( int v = 0; v < numVertices; v++) {
			sb.append("\n");
			sb.append( v + "  ");
			for( int w = 0; w < numVertices ; w++ )
				sb.append( matrix[v][w] < Integer.MAX_VALUE ? matrix[v][w] : "x" )
						.append("  ");
		}
		
		sb.append("\n\nWeights:");
		try {
		for( int w = 0; w < numVertices; w++ )
			sb.append( shortestPath( w ) + " " );
		} catch( Exception ex ) {
			ex.printStackTrace();
			sb.append("weight calculation failed");
		}
		
		return new String( sb );
	}

	private int[][] matrix;

	private int numVertices;
	private int numEdges;

	public GraphMatrix(int numVertices) {
		this.numVertices = numVertices;
		this.numEdges = numVertices * numVertices;
		matrix = new int[numVertices][numVertices];

		for (int v = 0; v < numVertices; v++) {
			for (int w = 0; w < numVertices; w++) {
				if (v == w)
					matrix[v][w] = 0;
				else
					matrix[v][w] = Integer.MAX_VALUE;
			}
		}
	}

	public void addEdge(int v, int w, int weight)  throws Exception {
		rangeCheck(v, w, weight);
		isModified = true;
		matrix[v][w] = weight;
	}

	public boolean hasEdge(int v, int w)  throws Exception {
		rangeCheck(v, w, 0);
		return matrix[v][w] != Integer.MAX_VALUE;
	}

	public int weight(int v, int w)  throws Exception {
		rangeCheck(v, w, 0);
		return matrix[v][w];
	}

	// split into 2 methods for
	private void rangeCheck(int v, int w, int weight) throws Exception {
		if (v < 0 || v >= numEdges)
			throw new Exception("Vertex index " + v
					+ " is negative or exceeds maximum " + (numVertices - 1));
		if (w < 0 || w >= numEdges)
			throw new Exception("Vertex index " + w
					+ " is negative or exceeds maximum " + (numVertices - 1));
		if (weight < 0)
			throw new Exception("Weight must be positive: " + weight);
	}

	boolean isModified = true;
	int[] weight; // weights from vertex 0 to every other vertex

	/**
	 * 0 1 2 0 0 5 9 1 X 0 1 2 X 3 0
	 * 
	 * 0 5 9 :weight 0 1 :vertexSet 2 :remaining
	 * 
	 * weight[1] + matrix[1][2] < weight[2] >> 5 + 1 < 9
	 */
	private void dijkstra() {
		if (!isModified)
			return;

		System.out.println("Running Dijkstra algorithm");
		weight = new int[numVertices];
		int[] vertexSet = new int[numVertices];
		ArrayList<Integer> remaining = new ArrayList<Integer>();
		for (int v = 0; v < numVertices; v++)
			remaining.add(Integer.valueOf(v));

		// initialization
		int vertexSetIndx = 0;
		vertexSet[vertexSetIndx++] = 0;
		remaining.remove(Integer.valueOf(0));
		
		for (int w = 0; w < numVertices; w++)
			weight[w] = matrix[0][w];

		// n-1 steps; excluding vertex 0
		for (int step = 2; step <= numVertices; step++) {
			// next candidate for vertexSet
			int v = -1;
			int minWeight = Integer.MAX_VALUE;
			for (int next : remaining)
				if (weight[next] < minWeight) {
					v = next;
					minWeight = weight[next];
				}

			vertexSet[vertexSetIndx++] = v;
			remaining.remove(Integer.valueOf( v ) );

			for (int w : remaining) {
				int newWeight = matrix[v][w] == Integer.MAX_VALUE 
								? Integer.MAX_VALUE
								: weight[v] + matrix[v][w];
				if ( newWeight < weight[w])
					weight[w] = newWeight;
			}
		}

		isModified = false;
	}

	/**
	 * Returns shortest path from graph root vertex to parameter vertex @code{ w
	 * }.
	 */
	public int shortestPath(int w) {
		dijkstra();
		return weight[w];
	}
}
