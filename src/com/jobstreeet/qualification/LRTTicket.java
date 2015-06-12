package com.jobstreeet.qualification;
import java.util.*;

public class LRTTicket {
	
	private static class Pair implements Comparable<Pair> {
		
		int location;
		int delta;
		
		Pair(int l, int d) {
			location = l;
			delta = d;
		}
		
		@Override
		public boolean equals(Object obj) {
			if( !(obj instanceof Pair) )
				return false;
			Pair p = (Pair) obj;
			return super.equals(p.location == this.location);
		}
		
		@Override
		public int hashCode() {
			int hash = 31;
			hash = hash + 17 * Integer.valueOf(location).hashCode();
			hash = hash + 17 * Integer.valueOf(delta).hashCode();
			return hash;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.valueOf(this.delta).compareTo(Integer.valueOf(o.delta));
		}
		
		@Override
		public String toString() {
			return ( location + " >> " + delta );
		}
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
//		ArrayList<Integer> delta = new ArrayList<Integer>();
		// time saving <-> position
		HashSet<Pair> deltaMap = new HashSet<Pair>();
		int[] t = new int[N];
		HashSet<Integer> customersQueue = new HashSet<Integer>();
		int[] r = new int[N-1];
		
		for( int i = 0; i < N ; i++ ) 
		{
			t[i] = sc.nextInt();
			customersQueue.add(i);
		}
		
		for( int i = 0; i < (N-1) ; i++ ) 
		{
			r[i] = sc.nextInt();
		}
		
		for( int i = 1; i < N ; i++ ) 
		{
			int normal = t[i] + t[i-1];
			int merged = r[i-1];
			int delta = normal - merged;
			deltaMap.add( new Pair( i, delta ) );
		}
		
		List<Pair> deltaList = new ArrayList<Pair> ( deltaMap );
		
		Collections.sort(deltaList);
		
		int totalTime = 0;
		
		for( int i = deltaList.size()-1; i >=0; i-- ) 
		{
			Pair pair = deltaList.get(i);
			int loc = pair.location;
			int delta = pair.delta;
			
			if(delta < 0 )
				break;
			
			if( customersQueue.contains(loc) && customersQueue.contains(loc-1) ) {
				totalTime += r[loc-1];
				customersQueue.remove(loc);
				customersQueue.remove(loc-1);
			}
		}
		
		List<Integer> remainingCustomers = new ArrayList<Integer>( customersQueue );
		for(Integer customerLoc : remainingCustomers ) 
			totalTime += t[customerLoc];
		
		System.out.println(totalTime);
		
		sc.close();

	}
	
	public static void printArray( int[] arr ) {
		StringBuilder sb = new StringBuilder();
//		System.out.println(arr.length);
		sb.append("{ ");
		for(int x : arr)
			sb.append(x).append(" ");
		sb.append("}");
		System.out.println( sb );
	}
	
	public static void printArray( Object[] arr ) {
		StringBuilder sb = new StringBuilder();
//		System.out.println(arr.length);
		sb.append("{ ");
		for(Object x : arr)
			sb.append(x).append(",");
		sb.append("}");
		System.out.println( sb );
	}

}

























