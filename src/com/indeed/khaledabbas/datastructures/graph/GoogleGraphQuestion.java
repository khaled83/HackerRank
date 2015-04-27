package com.indeed.khaledabbas.datastructures.graph;

import java.util.*;

public class GoogleGraphQuestion {
	/**
	 * You are given a 2d rectangular array of positive integers representing
	 * the height map of a continent. The "Pacific ocean" touches the left and
	 * top edges of the array and the "Atlantic ocean" touches the right and
	 * bottom edges. - Find the "continental divide". That is, the list of grid
	 * points where water can flow either to the Pacific or the Atlantic. Water
	 * can only flow from a cell to another one with height equal or lower.
	 * 
	 * Example:
	 * 
	 * Pacific ~ ~ ~ ~ ~ |__ ~ 1 2 2 3 (5) ~ ~ 3 2 3 (4)(4) ~ ~ 2 4 (5) 3 1 ~ ~
	 * (6)(7) 1 4 5 ~ __ (5) 1 1 2 4 ~ |~ ~ ~ ~ ~ Atlantic
	 * 
	 * The answer would be the list containing the coordinates of all circled
	 * cells: [(4,0), (3,1), (4,1), (2,2), (0,3), (1,3), (0,4)]
	 * 
	 * 1
	 * /** 1: p 01 10
	 */

//	
//	HashMap<String, Vertex> vertices;
//	// ArrayList would do as well
//	HashMap<Vertex, TreeMap<Vertex>> adjList;
//
//	Vertex[][] matrix;
//	int rows;
//	int cols;
//
//	private static class Vertex {
//		String name;
//		int height;
//		boolean adjacentToOcean;
//
//		public Vertex(String name, int height) {
//			this.name = name;
//			this.height = height;
//		}
//	}
//
//	public Graph(Vertex[][] m, int rows, int cols) {
//		vertices = new HashMap<String, Vertex>();
//		adjList = new HashMap<Vertex, TreeMap<Vertex>>();
//		matrix = m;
//		this.rows = rows;
//		this.cols = cols;
//
//		for (int row = 0; row < rows; row++) {
//			for (int col = 0; col < cols; col++) {
//				// add vertex
//				// add links
//				addEdge(row, col, row - 1, col);
//				addEdge(row, col, row, col - 1);
//				addEdge(row, col, row + 1, col);
//				addEdge(row, col, row, col + 1);
//			}
//		}
//
//	}
//
//	private void addEdge(int vRow, int vCol, int wRow, int wCol) {
//		if (rangeCheck(row, col)) {
//			Vertex v = vertices.get(matrix[vRow][vCol].name);
//			if (v == null) {
//				v = new Vertex(matrix[row][col].name, matrix[row][col].height);
//				vertices.add(v.name, v);
//			}
//
//			Vertex w = vetices.get(matrix[wRow][wCol].name);
//			if (w == null) {
//				w = new Vertex(matrix[wRow][wCol].name,
//						matrix[wRow][wCol].height);
//				//
//			}
//		}
//	}
//
//	public List<Vertex> flowingPoints() {
//		List<Vertex> result = new ArrayList<Vertex>();
//		for (Vertex v : vertices) {
//			if (v.adjacentToOcean)
//				result.add(v);
//			Queue<Vertex> queue = new Queue<Vertex>();
//			queue.add(v);
//			while (!queue.isEmpty()) {
//				Vertex next = queue.dequeue();
//				for (Vertex w : adjList.get(next)) {
//					// add all verices since source
//					if (w.adjacentToOcean) {
//						result.add(w);
//					} else if (w.height <= v.height)
//						queue.add(w);
//				}
//			}
//
//		}
//	}
//
//	private boolean rangeCheck(int row, int col) {
//		if (row < 0 || row > (rows - 1))
//			return false;
//		if (col < 0 || col > (cols - 1))
//			return false;
//		return true;
//	}

}
