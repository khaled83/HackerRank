package com.jobstreeet.qualification;
import java.util.*;


public class FriendRecommendations {
	
	static class Graph {
		
		HashMap<Vertex, TreeSet<Vertex>> adjList;
		HashMap<String, Vertex> vertices;
		
		static class Vertex implements Comparable<Vertex> {
			
			boolean done = false;
			
			public final String name;
			
			public Vertex(String name)
			{
				this.name = name;
			}
			
			@Override
			public int hashCode() {
				return name.hashCode();
			}
			
			@Override
			public boolean equals(Object obj) {
				return obj instanceof Vertex 
						&& this.name.equals( ((Vertex) obj).name );
			}
			
			@Override
			public int compareTo(Vertex o)
			{
				return this.name.compareTo(o.name);
			}
			
			@Override
			public String toString() {
				return name;
			}

		}
		
		private static final TreeMap<Vertex, Integer> EMPTY_MaP = new TreeMap<Vertex, Integer>();
		private static final TreeSet<Vertex> EMPTY_SET = new TreeSet<Vertex>();
		
		private int numVertices = 0;
		private int numEdges = 0;
		
		public Graph()
		{
			adjList = new HashMap<Vertex, TreeSet<Vertex>>();
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
				adjList.put(v, new TreeSet<Vertex>());
			}
			return v;
		}
		
		public boolean hasEdge(String from, String to)
		{
			Vertex v = vertices.get(from);
			Vertex w = vertices.get(to);
			
			return vertices.containsKey(from)
					&& vertices.containsKey(to)
					&& adjList.get(v).contains( w );
		}
		
		public void addEdge(String from, String to)
		{
			Vertex v = vertices.get(from);
			Vertex w = vertices.get(to);
			
			if(v == null)
				v = addVertex(from);
			if(w == null)
				w = addVertex(to);
			
			// adds or updates existing value
			adjList.get(v).add(w);
			
			numEdges++;
		}
		
		public void addEdgeBiDirection(String from, String to)
		{
			addEdge(from, to);
			addEdge(to, from);
		}
		
		public Iterable<Vertex> adjacentTo(String name)
		{
			if(!hasVertex(name))
				return EMPTY_SET;
			
			return adjList.get( vertices.get(name) );
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
		
		public int numFriendInvitations() {
			int result = 0;
			for( Vertex v : vertices.values() ) {
				for( Vertex w : adjList.get(v) ) {
					if( !w.done ) {
						for( Vertex r : adjList.get(w)) {
							if( !adjList.get(v).contains(r) && !r.equals(v) )
								result += 1;
						}
					}
					
				}
				v.done = true;
			}
			return result;
		}
		
		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			
			for(Vertex v : vertices.values() )
			{
				sb.append(v.name).append(": ");
				for(Vertex w : adjList.get(v) )
				{
					sb.append(w.name).append(" ");
				}
				sb.append("\n");
			}
			
			sb.append("Meta information: " + numVertices + " vertices and " + numEdges + " edges \n");
			
			return new String(sb);
		}
		
	}

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		Graph g = new Graph();
		System.out.println();
		
		for( int t = 0; t < count ; t++ ) 
		{
			String from = t + "";
			Graph.Vertex v = g.addVertex( from );
			for( int toIndx = 0; toIndx < count; toIndx++ ) {
				int isLinked = sc.nextInt();
				if(toIndx != t) {
					String to = toIndx + "";
					if( isLinked > 0 ) {
						g.addEdgeBiDirection(from, to);
					}
				}
			}
		}
		
		System.out.println(g.numFriendInvitations());
//		System.out.println(g.toString());
		
		sc.close();

	}

}
























