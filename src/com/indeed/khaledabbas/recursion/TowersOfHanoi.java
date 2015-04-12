package com.indeed.khaledabbas.recursion;

import java.util.ArrayList;

public class TowersOfHanoi {
	
		public static void main(String[] args) {
			TowersOfHanoi game = new TowersOfHanoi();
			game.solve();
		}

		private static class Plate {
			int diam;

			Plate(int diam) {
				this.diam = diam;
			}
		}

		/**
		 * 2 3 4 p1 p2 p3
		 * 
		 * count src dst spr 4 p1 p2 p3 3 p1 p3 p2 1 p1 p2 p3 3 p3 p2 p1
		 */

		// QUEUE: p1, p3, p2
		private ArrayList<Plate> p1 = new ArrayList<Plate>();
		private ArrayList<Plate> p2 = new ArrayList<Plate>();
		private ArrayList<Plate> p3 = new ArrayList<Plate>();

		private static final int DEFAULT_PLATES = 6;
		int numPlates;

		public TowersOfHanoi() {
			numPlates = DEFAULT_PLATES;
			int diam = 1;
			for (int i = 0; i < numPlates; i++)
				p1.add(new Plate(diam++));
		}

		public void solve() {
			solve(numPlates - 1, p1, p3, p2);
			solve(1, p1, p2, p3);
			solve(numPlates - 1, p3, p2, p1);
			
			for(Plate p : p2) {
				System.out.println(p.diam + " ");
			}
		}

		private void solve(int count, ArrayList<Plate> src, ArrayList<Plate> dst, ArrayList<Plate> buf) 
		{
			if (count == 1) {
//				printMove(src, dst);
				dst.add(src.remove(src.size()-1));
			}

			else {
				solve(count - 1, src, buf, dst);
				solve(1, src, dst, buf);
				solve(count - 1, buf, dst, src);
			}
		}
		
		private static void printMove(ArrayList<Plate> src, ArrayList<Plate> dst) {
			String s1 = String.valueOf(src.hashCode());
			String s2 = String.valueOf(dst.hashCode());
			System.out.println("moving " + src.get(src.size()-1).diam 
					+ " from " + s1.charAt(s1.length()-1)  
					+ " to " + s2.charAt(s2.length()-1));
		}

}
