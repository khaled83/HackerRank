package com.jobstreeet.qualification;

import java.util.*;

public class ShippingContainers {
	
	private static class Container implements Comparable<Container> {
		
		int weight;
		int price;
		int value;
		
		Container(int weight, int price) {
			this.weight = weight;
			this.price = price;
			value = price / weight;
		}
		
		@Override
		public int compareTo(Container o) {
			return Integer.valueOf( this.value ).compareTo( Integer.valueOf(o.value) );
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int weightRemaining = sc.nextInt(); 
		int numCases = sc.nextInt();
		
		List<Container> containers = new ArrayList<Container>();
		
		for( int t = 1; t <= numCases ; t++ ) 
		{
			int weight = sc.nextInt();
			int price = sc.nextInt();
			Container container = new Container(weight, price);
			containers.add( container );
		}
		
		Collections.sort(containers);
		
		int size = containers.size();
		int priceSoFar = 0;
		
		for( int i = size-1; i >= 0; i-- ) {
			Container c = containers.get(i);
			if( weightRemaining >= c.weight ) {
				weightRemaining -= c.weight;
				priceSoFar += c.price;
			}
		}
		
		System.out.println(priceSoFar);
		
		sc.close();

	}

}
