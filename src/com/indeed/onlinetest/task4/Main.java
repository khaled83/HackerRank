package com.indeed.onlinetest.task4;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// 1, 2, 3
		// 
		
		Scanner sc = new Scanner(System.in);
		
		int numNodes = sc.nextInt();
		int numInstrcutions = sc.nextInt();
		
		HashMap<Pair, Integer> map = new HashMap<Pair, Integer>();
		ArrayList<String> instructionList = new ArrayList<String>();
		
		for(int order=1; order<=numInstrcutions; order++)
		{
			String instruction = sc.next();
			if(instruction == "make")
			{
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				int time = sc.nextInt();
				map.put(new Pair(n1, n2, time, order), time);
			}	
			else
			{
				instructionList.add(instruction);
			}
		}
		
		/***
		 * ran out of time, solution roadmap:
		 * 1. Create two ordered collections of connected node pairs, ordered by 1st/2nd nodes respectively
		 * 2. for each check:
		 * 		2.a. find the 1st node in either collection
		 * 		2.b. walk through the connected nodes and find if there is a path to the target node
		 *  	2.c. compare order and time againsts order and time requirement of the instruction along the path
		 *  3. THis could be solved as a graph problem.
		 */
		
		
		sc.close();

	}
	
	private static class Pair
	{
		// connected nodes
		int n1;
		int n2;
		
		int time;
		
		int order;
		
		Pair(int n1, int n2, int time, int order)
		{
			this.n1 = n1;
			this.n2 = n2;
			this.time = time;
			this.order = order;
		}
		
		@Override
		public int hashCode() {
			return (31*n1+n2);
		}
	}

}
