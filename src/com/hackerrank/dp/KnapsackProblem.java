package com.hackerrank.dp;

import java.util.*;

public class KnapsackProblem {
	
	private static final List<Item> items = new ArrayList<Item>();
	static {
		items.add( new Item("red", 1, 1) );
		items.add( new Item("gray", 1, 2) );
		items.add( new Item("blue", 2, 2) );
		items.add( new Item("orange", 4, 10) );
		items.add( new Item("green", 12, 4) );
	}

	public static void main(String[] args) 
	{
		int minWeight = 1;
		
		int weightCapacity = 15;
		int valueSoFar = 0;
		int weightSoFar = 0;
		
		HashMap<String, Integer> solution = new HashMap<String, Integer>();
		ArrayList<HashMap<String, Integer>> solutions = new ArrayList<HashMap<String, Integer>>(); 
		
		solve(weightCapacity, valueSoFar, weightSoFar, solutions);
		
		while( ( weightSoFar + minWeight ) < weightCapacity  ) 
		{
			
		}

	}
	
	private void solve( int weightCapacity, int valueSoFar, int weightSoFar, HashMap<String, Integer> solution ) {
		
	}
	
	private static class Item {
		private int weight;
		private int value;
		String name;
		
		Item(String n, int w, int v) {
			name = n;
			weight = w;
			value = v;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

}
