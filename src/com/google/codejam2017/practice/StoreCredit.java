package com.google.codejam2017.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class StoreCredit {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int curCase = 1; curCase <= n; curCase++) {
			int credit = sc.nextInt();
			int numItems = sc.nextInt();
			int[] items = new int[numItems];
			for (int i = 0; i < numItems; i++) {
				items[i] = sc.nextInt();
			}
			
			// solution
			
			
			// solution
		}
		sc.close();
	}
	
	private static class Graph {
		
		Node root = null;
		int[] items = null;
		
		public Graph(int credit, int[] items) {
			root = new Node(0, credit);
			this.items = items;
		}
		
		public ArrayList<Integer> solve() {
//			for(int i = 0; i < items.length; i++) {
//				Node node = new Node(items[i], root.credit-items[i]);
//			}
			return null;
		}
		
		private class Node implements Comparable<Node> {
			int price;
			int remaining;
			
			TreeSet<Integer> nodes = new TreeSet<Integer>();
			
			Node(int price, int remaining) {
				this.price = price;
				this.remaining = remaining;
			}
			
			@Override
			public int compareTo(Node o) {
				return Integer.valueOf(price).compareTo(o.price);
			}
		}
		
	}
	
}
