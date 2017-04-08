package com.khaledabbas.datastructures.graph;

import java.util.*;

import com.khaledabbas.datastructures.queue.QueueException;

public class ColorRegionsGraph {
	
	public static void main(String[] args) throws QueueException {
		int[][] matrix = {  { 0,1,0,1,1,1,0,0,0,0 },
							{ 1,1,0,1,1,0,1,1,0,0 },
							{ 1,1,1,0,0,1,1,0,1,1 },
							{ 1,0,1,0,0,0,0,1,0,1 },
							{ 0,1,0,1,1,1,1,0,1,1 },
							{ 0,1,0,1,1,0,1,0,0,0 },
							{ 1,1,1,1,0,1,0,1,1,0 },
							{ 0,1,0,1,0,1,0,1,1,1 },
							{ 0,1,0,0,1,1,1,0,0,0 },
							{ 1,1,1,1,1,1,1,0,0,1 }
						};
		
		ColorRegionsGraph g = new ColorRegionsGraph(matrix, 10, 10);
		System.out.println(g.toString());
		g.flipColor(5, 4);
		System.out.println(g.toString());
	}

	private HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();

	/**
	 * TODO: choose Collection type iterate Lookup insert space LinkedList: O(N)
	 * O(N) O(N) N+pointers HashMap: O(N) O(1) O(1) N< SortedArray(List): O(N)
	 * O(log(N)) O(N^2) N TreeMap: O(N) O(log(N)) O(N log(N)) N+pointers
	 */
	private HashMap<Vertex, TreeSet<Vertex>> adjList = new HashMap<Vertex, TreeSet<Vertex>>();

	private static class Vertex implements Comparable<Vertex> {
		Color color;
		String id;

		Vertex(String id, Color color) {
			this.id = id;
			this.color = color;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Vertex))
				return false;
			Vertex v = (Vertex) o;
			return this.id.equals(v.id); // TODO
		}

		public int hashCode() {
			return id.hashCode();
		}

		@Override
		public int compareTo(Vertex o) {
			return this.id.compareTo(o.id);
		}
		
		@Override
		public String toString() {
			return id;
		}

	}

	private enum Color {
		BLACK, WHITE
	}

	private int[][] matrix;
	private int rows, cols;

	public ColorRegionsGraph(int[][] m, int rows, int cols) {

//		System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);
		this.matrix = m;
		this.rows = rows;
		this.cols = cols;

		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++) {
				addVertex(row, col);
				// add edges
				addEdge(row, col, row + 1, col);
				addEdge(row, col, row - 1, col);
				addEdge(row, col, row, col + 1);
				addEdge(row, col, row, col - 1);
			}

	} // end of construcor

	// gets or adds vertex
	private Vertex addVertex(int row, int col) {
		String id = id(row, col);
		Vertex v = addVertex(id, color(matrix[row][col]));
		return v;
	}

	public Vertex addVertex(String id, Color color) {
		Vertex v = vertices.get(id);
		if (v == null) {
			v = new Vertex(id, color);
			vertices.put(id, v);
			adjList.put(v, new TreeSet<Vertex>());
		}

		return v;

	}

	public void addEdge(int vRow, int vCol, int wRow, int wCol) 
	{
		if( !rangeCheck(vRow, vCol) || !rangeCheck(wRow, wCol) )
			return;
		
		Vertex v = addVertex(vRow, vCol);
		Vertex w = addVertex(wRow, wCol);
		adjList.get(v).add(w);
		adjList.get(w).add(v);
	}
	
	private boolean rangeCheck( int row, int col ) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}

	private Vertex getVertex(int row, int col) {
		return getVertex(id(row, col));
	}

	private Vertex getVertex(String id) {
		return vertices.get(id);
	}

	private Color color(int color) {
		return color == 0 ? Color.BLACK : Color.WHITE;
	}

	private String id(int row, int col) {
//		if( row == 5 && col == 4)
//			System.out.println( "id=" + row + col + "" );
		return (row + "" + col);
	}

	public static void flipColor(int[][] matrix, int row, int col)
			throws QueueException {
		ColorRegionsGraph g = new ColorRegionsGraph(matrix, matrix.length,
				matrix[0].length);
		g.flipColor(row, col);
		// copy array values into matrix
		for( int i = 0; i < g.rows; i++ ) {
			System.arraycopy(g.matrix[i], 0, matrix[i], 0, g.cols);
		}
	}

	public void flipColor(int row, int col) throws QueueException {
		Vertex v = getVertex(row, col);
		Color color = ( v.color == Color.WHITE ) ? Color.BLACK : Color.WHITE;
		// Choose queue imple,emtation
		com.khaledabbas.datastructures.queue.adt.Queue<Vertex> queue = new com.khaledabbas.datastructures.queue.adt.Queue<Vertex>();
		HashSet<Vertex> visited = new HashSet<Vertex>();

		queue.enqueue(v);
		while (!queue.empty()) {
			v = queue.dequeue();

			for (Vertex w : adjList.get(v)) {
				if (!visited.contains(w) && w.color == v.color) {
					queue.enqueue(w);
				}
			}
			
			flipColor(v, color);
			visited.add(v);
		}
	}

	private void flipColor(Vertex v, Color color) {
		
		v.color = color;
		
		// update matrix
		int row = 0, col = 0;
		if( v.id.length() > 1 ) {
			row = v.id.charAt(0) - '0';
			col = v.id.charAt(1) - '0';
		}
		else {
			row = 0;
			col = v.id.charAt(0) - '0';
		}
		matrix[row][col] = ( color == Color.BLACK ) ? 0 : 1;
			
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for( int[]arr : matrix ) {
			for( int x : arr)
				sb.append( x + " " );
			sb.append("\n");
		}
		return new String( sb );
	}

} // end of class
