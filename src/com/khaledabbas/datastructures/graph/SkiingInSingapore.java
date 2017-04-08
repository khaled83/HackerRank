package com.khaledabbas.datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class SkiingInSingapore {

	public static void main( String[] args ) throws Exception {
			
			Scanner sc = new Scanner( System.in );
			int rows = sc.nextInt();
			int cols = sc.nextInt();
			int[][] matrix = new int[rows][cols];
			
			for (int row = 0; row < rows; row++)
				for (int col = 0; col < cols; col++) {
					matrix[row][col] = sc.nextInt();
				}
			
			Graph G = new Graph( matrix, rows, cols );
//			System.out.println(G.toString());
			Graph.Path longestPath = G.longestPath();
			System.out.println( longestPath.src.id + " -> " + longestPath.dst.id 
								+ " : length=" + longestPath.length 
								+ " : steep=" + longestPath.steep );
			
			sc.close();
		}
	
	public static class Graph {
		
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();

		HashMap<Vertex, TreeSet<Vertex>> adjList = new HashMap<Vertex, TreeSet<Vertex>>();

		int[][] matrix;
		int rows, cols;

		private static class Vertex implements Comparable<Vertex> {
			String id;
			int height;

			public Vertex(String id, int height) {
				this.id = id;
				this.height = height;
			}

			@Override
			public int compareTo(Vertex o) {
				return Integer.valueOf( this.height ).compareTo( o.height );
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
				return "(" + id + ":" + height + ")";
			}
			
		}

		public Graph(int[][] matrix, int rows, int cols) {
			this.matrix = matrix;
			this.rows = rows;
			this.cols = cols;
			
			for (int row = 0; row < rows; row++)
				for (int col = 0; col < cols; col++) {
					addVertex(row, col);
				}
		}
		
		private static class Path implements Comparable<Path> {
			int length;
			int steep;
			Vertex src;
			Vertex dst;
			
			@Override
			public int compareTo(Path o) {
				int comp = Integer.valueOf( this.length ).compareTo( o.length );
				
				if( comp == 0 )
					comp  = Integer.valueOf( this.steep ).compareTo( o.steep );
				return comp;
			}
		}
		
		public Path longestPath() {
			
			HashSet<Vertex> visited = new HashSet<Vertex>();
			Path longestPath = new Path();
			
			for( Vertex unvisited : vertices.values() ) {
				if( visited.contains( unvisited ) )
					continue;
					
				// depth-first search starting from each unvisited vertex
				Stack<Path> stack = new Stack<Path>();
				Path path = new Path();
				path.src = unvisited;
				path.dst = unvisited;
				path.length = 1;
				stack.add( path );
				
				while( ! stack.empty() ) {
					Path lastPath = stack.pop();
					Vertex v = lastPath.dst;
					visited.add(v);
					
					for( Vertex w : adjList.get(v) ) {
						Path nextPath = new Path();
						nextPath.src = lastPath.src;
						nextPath.dst = w;
						nextPath.length = lastPath.length + 1;
						nextPath.steep = nextPath.src.height - nextPath.dst.height;
						stack.push(nextPath);
						
						if( nextPath.compareTo( longestPath ) > 0 ) {
							longestPath.length = nextPath.length;
							longestPath.steep = nextPath.steep;
							longestPath.src = nextPath.src;
							longestPath.dst = nextPath.dst;
						}
					}
				}
			}
			
			return longestPath;
		}
		
		private void addVertex(int row, int col) {
			String id = id(row, col);
			int height = matrix[row][col];
			Vertex v = vertices.get(id);
			if (v == null) {
				v = new Vertex(id, height);
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
			if ( !rangeCheck( wRow, wCol ) )
				return;

			Vertex w = getVertex(wRow, wCol);
			if (w == null)
				w = new Vertex( id(wRow, wCol), matrix[wRow][wCol] );
			
			if( v.height <= w.height )
				return;
			
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
				sb.append(v.toString()).append(": " + ( adjList.get(v) == null ? " : null" : "" ) ) ;
				for(Vertex w : adjList.get(v) )
				{
					sb.append( w.toString()).append(" ");
				}
				sb.append("\n");
			}
			
//			sb.append("Meta information: " + numVertices + " vertices and " + numEdges + " edges \n");
			
			return new String(sb);
		}

}

}