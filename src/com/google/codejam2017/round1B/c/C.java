package com.google.codejam2017.round1B.c;

import java.util.*;

import com.amazon.datastructures.queues.Queue;
import com.amazon.datastructures.queues.QueuePointerBased;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		for (int testCase=0; testCase<numCases; testCase++) {
			int N = sc.nextInt();
			int Q = sc.nextInt();
			
			Graph g = new Graph(N);
			for (int i=0; i<N; i++) {
				g.setDistance(i, sc.nextFloat());
				g.setSpeed(i, sc.nextFloat());
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					float dist = sc.nextFloat();
					if (dist != -1)
						g.addEdge(i, j, dist);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for (int pair = 0; pair < Q; pair++) {
				int v = sc.nextInt();
				int w = sc.nextInt();
				sb.append(g.shortest(v, w)).append(" ");
				sb.deleteCharAt(sb.length()-1);
			}
			System.out.println("Case #"
					+ (testCase+1)
					+ ": "
					+ sb.toString());
			
		}
		sc.close();
	}
	
	public static class Graph {

		protected int E;
		protected final int V;

		protected HashMap<Integer, Float>[] adj;
		
		protected float[] maxDist;  
		protected float[] speed;  

		public Graph(int V) {
			this.V = V;
			adj = (HashMap<Integer, Float>[]) new HashMap[V];
			for (int i = 0; i < V; i++)
				adj[i] = new HashMap<Integer, Float>();
			maxDist = new float[V];
			speed = new float[V];
		}

		public void addEdge(int v, int w, float dist) {
			checkVertex(v);
			checkVertex(w);
			E++;
			adj[v].put(w, dist);
//			adj[w].add(v);
		}
		
		public void setDistance(int v, float value) {
			maxDist[v] = value;
		}
		
		public void setSpeed(int v, float value) {
			speed[v] = value;
		}

		public Iterable<Integer> adj(int v) {
			checkVertex(v);
			return adj[v].keySet();
		}

		public int degree(int v) {
			checkVertex(v);
			return adj[v].size();
		}

		public int V() {
			return V;
		}

		public int E() {
			return E;
		}

		protected void checkVertex(int v) {
			if (v < 0 || v >= V)
				throw new IllegalArgumentException();
		}

		private char toChar(int v) {
	        return (char)('a'+v);
	    }
		
		protected void visit(int v, StringBuilder sb) {
			sb.append(toChar(v)).append(' ');
//		    System.out.println(toChar(v));
		}
		
		public float shortest(int src, int dst) {
			
			float curSpeed = speed[src];
			float distRem = maxDist[src];
			float time = 0;
			int next = -1;
			
			int v = src;
			while (v != dst) {
				float min = Float.MAX_VALUE;
				
				if (speed[v] > curSpeed) {
					curSpeed = speed[v];
					distRem  = maxDist[v];
				}
				
//				int next = -1;
				for (Integer w : adj[v].keySet()) {
					if (w == dst) {
						next = dst;
					}
					else {
						float thisTime = (adj[v].get(w) / curSpeed);
						if (speed[w] > curSpeed)
							thisTime+= ((distRem -  adj[v].get(w))/speed[w]);
						else
							thisTime+= ((distRem -  adj[v].get(w))/curSpeed);
						
						if (thisTime < min) {
							min = thisTime;
							next = w;
						}
					}
				}
				
				distRem -= adj[v].get(next);
				time += adj[v].get(next) / curSpeed;
				v = next;
				
			}
			
			return time;
			
		}
		
	}

}












































