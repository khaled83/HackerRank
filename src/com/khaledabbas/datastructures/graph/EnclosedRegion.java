package com.khaledabbas.datastructures.graph;

import java.util.*;

public class EnclosedRegion {
	
	public static void main( String[] args ) {
		char[][] arr = { 	{ 'B', 'B', 'B', 'B' }, 
							{ 'W', 'B', 'W', 'B' },
							{ 'B', 'W', 'W', 'B' },
							{ 'B', 'B', 'B', 'B' }
						};
		
		EnclosedRegion g = new EnclosedRegion( arr, 4, 4 );
		System.out.println( g.toString() );
		g.flipWhitesInside();
		System.out.println( g.toString() );
	}

	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	private HashMap<Vertex, ArrayList<Vertex>> adjList = new HashMap<Vertex, ArrayList<Vertex>>();

	char[][] matrix;
	int rows, cols;

	private static class Vertex {
		String name;
		char color;
		boolean reachesBoundary = false;

		Vertex(String name) {
			this.name = name;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Vertex))
				return false;
			Vertex v = (Vertex) o;
			return v.name.equals(this.name);
		}

		public int hashCode() {
			return name.hashCode();
		}
	}

	public EnclosedRegion(char[][] m, int rows, int cols) {

		matrix = m;
		this.rows = rows;
		this.cols = cols;

		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++) {
				addVertex(row, col);
				// add edges
				addEdge(row, col, row - 1, col);
				addEdge(row, col, row + 1, col);
				addEdge(row, col, row, col + 1);
				addEdge(row, col, row, col - 1);
			}

	} // end constructor

	private String name(int row, int col) {
		return row + "" + col;
	}

	private Vertex addVertex(int row, int col) {
		String name = name(row, col);
		Vertex v = vertices.get(name);
		if (v == null) {
			v = new Vertex(name);
			v.color = matrix[row][col];
			vertices.put(name, v);
			adjList.put(v, new ArrayList<Vertex>());
		}
		return v;
	}

	private boolean addEdge(int vRow, int vCol, int wRow, int wCol) {
		if (!rangeCheck(vRow, vCol) || !rangeCheck(wRow, wCol))
			return false;
		Vertex v = addVertex(vRow, vCol);
		Vertex w = addVertex(wRow, wCol);
		adjList.get(v).add(w);
		adjList.get(w).add(v);
		return true;
	}

	private boolean rangeCheck(int row, int col) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}

	// algorithm
	public void flipWhitesInside() {

		// (1) find Whites at the boundary
		int row, col;
		com.khaledabbas.datastructures.queue.adt.Queue<Vertex> queue = new com.khaledabbas.datastructures.queue.adt.Queue<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();

		// check nodes over the edges
		for (row = 0, col = 0; col < cols; col++)
			checkReachability(row, col, queue);
		for (row = rows - 1, col = 0; col < cols; col++)
			checkReachability(row, col, queue);
		for (row = 0, col = 0; row < rows; row++)
			checkReachability(row, col, queue);
		for (row = 0, col = cols - 1; row < rows; row++)
			checkReachability(row, col, queue);

		// (2) search and mark reachable whites
		while (!queue.empty()) {
			Vertex v = queue.dequeue();
			v.reachesBoundary = true;
			visited.add(v);

			for (Vertex w : adjList.get(v)) {
				if (!visited.contains(w) && w.color == 'W') {
					queue.enqueue(w);
				}
			}
		}
		
		// (3) iterate unreachable whites and flip color
		for (Vertex v : vertices.values())
			if (v.color == 'W' && !v.reachesBoundary) {
				v.color = 'B';
				setColorAtMatrix( v );
			}

	}
	
	private void setColorAtMatrix(Vertex v) {
		
		// update matrix
		int row = 0, col = 0;
		if( v.name.length() > 1 ) {
			row = v.name.charAt(0) - '0';
			col = v.name.charAt(1) - '0';
		}
		else {
			row = 0;
			col = v.name.charAt(0) - '0';
		}
		matrix[row][col] = v.color;
		
	}
	
	private void checkReachability(int row, int col, com.khaledabbas.datastructures.queue.adt.Queue<Vertex> queue) {
		Vertex v = vertices.get(name(row, col));
		if (v.color == 'W')
			queue.enqueue(v);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for( char[]arr : matrix ) {
			for( char x : arr)
				sb.append( x + " " );
			sb.append("\n");
		}
		return new String( sb );
	}

} // end class
