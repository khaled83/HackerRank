package com.khaledabbas.datastructures.graph;

import java.util.*;

import com.khaledabbas.datastructures.queue.node.Queue;

public class GoogleGraphQuestion {
	
	public static void main( String[] args ) throws Exception {
		int[][] matrix = {  { 1, 1, 1, 7 },
							{ 1, 1, 6, 6 },
							{ 1, 3, 4, 1 },
							{ 2, 2, 1, 1 },
						 };
		GoogleGraphQuestion g = new GoogleGraphQuestion(matrix, 4, 4);
		System.out.println( g.continentalDivide() );
	}
	
	/**
	 * You are given a 2d rectangular array of positive integers representing
	 * the height map of a continent. The "Pacific ocean" touches the left and
	 * top edges of the array and the "Atlantic ocean" touches the right and
	 * bottom edges. - Find the "continental divide". That is, the list of grid
	 * points where water can flow either to the Pacific or the Atlantic. Water
	 * can only flow from a cell to another one with height equal or lower.
	 * 

Pacific 
	 ~ ~ ~ ~ ~ ~ 
	 ~ 1 1 1 7 ~
	 ~ 1 1 6 6 ~
	 ~ 1 3 4 1 ~
	 ~ 1 2 1 1 ~
	 ~ ~ ~ ~ ~  Atlantic

	 * Pacific ~ ~ ~ ~ ~ |__ ~ 1 2 2 3 (5) ~ ~ 3 2 3 (4)(4) ~ ~ 2 4 (5) 3 1 ~ ~
	 * (6)(7) 1 4 5 ~ __ (5) 1 1 2 4 ~ |~ ~ ~ ~ ~ Atlantic
	 * 
	 * The answer would be the list containing the coordinates of all circled
	 * cells: [(4,0), (3,1), (4,1), (2,2), (0,3), (1,3), (0,4)]
	 * 
	 * 1 /** 1: p 01 10
	 */

	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	private HashMap<Vertex, ArrayList<Vertex>> adjList = new HashMap<Vertex, ArrayList<Vertex>>();

	int[][] matrix;
	int rows, cols;

	private static class Vertex {
		String name;
		int height;
		boolean isContDivide = false;

		Vertex(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Vertex))
				return false;

			Vertex v = (Vertex) o;
			return this.name.equals(v.name);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}

	public GoogleGraphQuestion(int[][] matrix, int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.matrix = matrix;

		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++) {
				addVertex(row, col);
				// add edges
				addEdge(row, col, row, col - 1);
				addEdge(row, col, row, col + 1);
				addEdge(row, col, row - 1, col);
				addEdge(row, col, row + 1, col);
			}
	}

	private Vertex addVertex(int row, int col) {
		if (!rangeCheck(row, col))
			return null;

		String name = name(row, col);
		Vertex v = vertices.get(name);
		if (v == null) {
			v = new Vertex(name);
			vertices.put( name, v );
			adjList.put(v, new ArrayList<Vertex>(4));
		}
		v.height = matrix[row][col];
		return v;
	}

	private void addEdge(int fromRow, int fromCol, int toRow, int toCol) {
		if (!rangeCheck(fromRow, fromCol) || !rangeCheck(toRow, toCol))
			return;

		Vertex v = vertices.get(name(fromRow, fromCol));
		if (v == null)
			v = addVertex(fromRow, fromCol);

		Vertex w = vertices.get(name(toRow, toCol));
		if (w == null)
			w = addVertex(toRow, toCol);

		adjList.get(v).add(w);
	}

	private String name(int row, int col) {
		return (row + "" + col);
	}

	private boolean rangeCheck(int row, int col) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}

	/**
	 * Pacific ~ ~ ~ ~ ~ ~ ~ 1 1 1 7 ~ ~ 1 1 6 6 ~ ~ 1 3 4 1 ~ ~ 1 2 1 1 ~ ~ ~ ~
	 * ~ ~ Atlantic
	 */
	/**
	 Pacific 
	 ~ ~ ~ ~ ~ ~ 
	 ~ 1 1 1 7 ~
	 ~ 1 1 6 6 ~
	 ~ 1 3 4 1 ~
	 ~ 1 2 1 1 ~
	 ~ ~ ~ ~ ~  Atlantic */
	public List<String> continentalDivide() throws Exception {
		// find the vertex that touches 2 oceans
		int row = rows - 1;
		int col = 0;
		String name = name(row, col);
		Vertex contVertex = vertices.get(name);
		contVertex.isContDivide = true;

		// bfs starting from contVertex
		Queue<Vertex> queue = new Queue<Vertex>();
		queue.enqueue(contVertex);
		List<Vertex> visited = new ArrayList<Vertex>();

		/**
		 * 30 32 21  
		 *       f
		 */

		while (!queue.empty()) {
			Vertex v = queue.dequeue();
			visited.add(v);

			for (Vertex w : adjList.get(v)) {
				if (!visited.contains(w)) {
					if (v.isContDivide && w.height >= v.height) {
						w.isContDivide = true;
						queue.enqueue(w);
					}
				}
			}
		}

		List<String> result = new ArrayList<String>();
		for (Vertex v : visited) {
			if (v.isContDivide) {
				result.add(v.name);
			}
		}

		return result;
	}

	// following code is my solution during the interview

	//
	// HashMap<String, Vertex> vertices;
	// // ArrayList would do as well
	// HashMap<Vertex, TreeMap<Vertex>> adjList;
	//
	// Vertex[][] matrix;
	// int rows;
	// int cols;
	//
	// private static class Vertex {
	// String name;
	// int height;
	// boolean adjacentToOcean;
	//
	// public Vertex(String name, int height) {
	// this.name = name;
	// this.height = height;
	// }
	// }
	//
	// public Graph(Vertex[][] m, int rows, int cols) {
	// vertices = new HashMap<String, Vertex>();
	// adjList = new HashMap<Vertex, TreeMap<Vertex>>();
	// matrix = m;
	// this.rows = rows;
	// this.cols = cols;
	//
	// for (int row = 0; row < rows; row++) {
	// for (int col = 0; col < cols; col++) {
	// // add vertex
	// // add links
	// addEdge(row, col, row - 1, col);
	// addEdge(row, col, row, col - 1);
	// addEdge(row, col, row + 1, col);
	// addEdge(row, col, row, col + 1);
	// }
	// }
	//
	// }
	//
	// private void addEdge(int vRow, int vCol, int wRow, int wCol) {
	// if (rangeCheck(row, col)) {
	// Vertex v = vertices.get(matrix[vRow][vCol].name);
	// if (v == null) {
	// v = new Vertex(matrix[row][col].name, matrix[row][col].height);
	// vertices.add(v.name, v);
	// }
	//
	// Vertex w = vetices.get(matrix[wRow][wCol].name);
	// if (w == null) {
	// w = new Vertex(matrix[wRow][wCol].name,
	// matrix[wRow][wCol].height);
	// //
	// }
	// }
	// }
	//
	// public List<Vertex> flowingPoints() {
	// List<Vertex> result = new ArrayList<Vertex>();
	// for (Vertex v : vertices) {
	// if (v.adjacentToOcean)
	// result.add(v);
	// Queue<Vertex> queue = new Queue<Vertex>();
	// queue.add(v);
	// while (!queue.isEmpty()) {
	// Vertex next = queue.dequeue();
	// for (Vertex w : adjList.get(next)) {
	// // add all verices since source
	// if (w.adjacentToOcean) {
	// result.add(w);
	// } else if (w.height <= v.height)
	// queue.add(w);
	// }
	// }
	//
	// }
	// }
	//
	// private boolean rangeCheck(int row, int col) {
	// if (row < 0 || row > (rows - 1))
	// return false;
	// if (col < 0 || col > (cols - 1))
	// return false;
	// return true;
	// }

}
