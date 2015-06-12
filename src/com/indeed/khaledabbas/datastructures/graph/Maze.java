package com.indeed.khaledabbas.datastructures.graph;

import java.util.*;

import com.indeed.khaledabbas.datastructures.queue.node.Queue;

public class Maze {
	
	public static void main( String[] args ) throws Exception {
		
		int[][] matrix = { 	{ 0,1,1,1,1,1,0,0,1,1 }, 
							{ 1,1,0,1,1,1,1,1,1,1 },
							{ 0,1,0,1,1,0,0,1,0,0 },
							{ 1,1,1,0,0,0,1,1,0,1 },
							{ 1,0,0,1,1,1,1,1,1,1 },
							{ 1,0,0,1,1,0,1,0,0,1 },
							{ 1,1,1,1,0,1,1,1,1,1 },
							{ 0,1,0,1,0,1,0,1,1,1 },
							{ 0,1,0,0,1,1,1,0,0,0 },
							{ 1,1,1,1,1,1,1,0,0,1 }
						 };
		
		Maze maze = new Maze( matrix, 10, 10 );
		maze.path(9, 0, 0, 9);
		
	}

	HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();

	// ArrayList, TreeSet
	HashMap<Vertex, TreeSet<Vertex>> adjList = new HashMap<Vertex, TreeSet<Vertex>>();

	int[][] matrix;
	int rows, cols;

	private static class Vertex implements Comparable<Vertex> {
		String id;

		public Vertex(String id) {
			this.id = id;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.id.compareTo(o.id);
		}
		
		@Override
		public boolean equals(Object obj) {
			if( ! ( obj instanceof Vertex ) )
				return false;
			
			Vertex v = (Vertex) obj;
			return this.id.equals( v.id );
		}
	
		@Override
		public int hashCode() {
			return id.hashCode();
		}
	
		@Override
		public String toString() {
			return id;
		}
	}

	public Maze(int[][] matrix, int rows, int cols) {
		this.matrix = matrix;
		this.rows = rows;
		this.cols = cols;
		
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++) {
				if (matrix[row][col] == 1)
					addVertex(row, col);
			}
	}
	
	
	/**
	 * s p1					:queue 	
	 * s p1    				:visited
	 * s p1    				:path
	 *         
	 */
	public void path(int startRow, int startCol, int endRow, int endCol) throws Exception {
		Vertex src = getVertex(startRow, startCol);
		Vertex dst = getVertex(endRow, endCol);

//		Queue<Vertex> queue = new Queue<Vertex>();
		Stack<Vertex> stack = new Stack<Vertex>();
		List<String> path = new ArrayList<String>();
		HashSet<Vertex> visited = new HashSet<Vertex>();

//		queue.enqueue(src);
		stack.push(src);

//		while (!queue.empty()) {
		while (!stack.empty()) {
//			Vertex v = queue.getFront();
			Vertex v = stack.peek();
			visited.add(v);
			path.add(v.id);

			if (v.equals( dst ) )
				break;

			Vertex next = null;
			for (Vertex w : adjList.get(v)) {
				if (!visited.contains(w)) {
					next = w;
					break;
				}
			}
			
			if(next != null) {
//				queue.enqueue( next );
				stack.push(next);
			}
			else {
//				queue.dequeue();
				stack.pop();
				path.remove(v);
			}
			
		}
		
		System.out.println(path);

		/**
		 * s p1 p2 p3 c1 c2 c3 f
		 * 
		 * p1,p2,c1,c2,c3 :queue s ,p3,c3 :visited s ,p3 :path
		 */
	}	
	

	private void addVertex(int row, int col) {
		String id = id(row, col);
		Vertex v = vertices.get(id);
		if (v == null) {
			v = new Vertex(id);
			vertices.put(id, v);
		}
		
		addEdges(row, col);
	}

	private String id(int row, int col) {
		return row + "" + col;
	}

	private Vertex getVertex(int row, int col) {
		return vertices.get(id(row, col));
	}

	private void addEdges(int row, int col) {
		Vertex v = getVertex(row, col);
		adjList.put( v, new TreeSet<Vertex>() );

		addEdge(v, row, col + 1);
		addEdge(v, row, col - 1);
		addEdge(v, row + 1, col);
		addEdge(v, row - 1, col);
	}

	private void addEdge(Vertex v, int wRow, int wCol) {
		if ( !rangeCheck( wRow, wCol ) || matrix[wRow][wCol] == 0 )
			return;

		Vertex w = getVertex(wRow, wCol);
		if (w == null)
			w = new Vertex(id(wRow, wCol));
		adjList.get(v).add(w);
	}
	
	private boolean rangeCheck( int row, int col ) {
		return row >= 0 && row < rows
				&& col >= 0 && col < cols;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(Vertex v : vertices.values() )
		{
			sb.append(v.id).append(": " + ( adjList.get(v) == null ? " : null" : "" ) ) ;
			for(Vertex w : adjList.get(v) )
			{
				sb.append( w.id).append(" ");
			}
			sb.append("\n");
		}
		
//		sb.append("Meta information: " + numVertices + " vertices and " + numEdges + " edges \n");
		
		return new String(sb);
	}

}
