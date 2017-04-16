package com.google.codejam2017.c;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class BathroomStalls {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		long[] Ns = new long[numCases];
		long[] Ks = new long[numCases];
		
		for (int i=0; i<numCases; i++) {
			Ns[i] = sc.nextLong();
			Ks[i] = sc.nextLong();
		}
		
		for (int i=0; i<numCases; i++) {
			Pair result = optimalStalls(Ns[i], Ks[i]);
			System.out.println("Case #" + (i+1) + ": " + result.max + " " + result.min);
		}
		sc.close();
	}
	
	/**
	  ..*....   
	  0123456
	 O......O
	 
	    2+5/2=3
	    *
	  01234
	 0.....0
	  
	 
	 0..5
	 
	 2
	 
	  0123456789
	 X....O.....X
	 f   s
	 -1  1
	  1  4 -> 2
	  4  7
	 */
	public static Pair optimalStalls(long n, long k) {
		TreeSet<Long> locations = new TreeSet<Long>();
		long lastLocation = n-1;
		boolean fullyOccupied = false;
		
		// first case
		lastLocation = (n-1)/2;
		locations.add(lastLocation);
//		print(-1, locations, n, k);
		
		// after first case
		for (int i=1; i<k; i++) {
			if (locations.size() == n) {
				fullyOccupied = true;
				break;
			}
			
			long first = -1;
			long distance = 0;
			long maxLeftDistance = 0;
			long maxRightDistance = 0;
			long second = 0;
			Iterator<Long> iter = locations.iterator();
			while(iter.hasNext()) {
				second = iter.next();
				long curDistance = second - first - 1;
				if (curDistance > distance) {
					lastLocation = first;
					distance = curDistance;
					maxLeftDistance = curDistance;
					long higher = locations.higher(second) != null ? locations.higher(second) : n;
					maxRightDistance = higher - second - 1;
				}
				else if (curDistance == distance) {
					// maximize left/right
					long leftDistance = curDistance;
					long higher = locations.higher(second) != null ? locations.higher(second) : n;
					long rightDistance = higher - second - 1;
					long curMaxDistance = Math.max(leftDistance, rightDistance);
					if (curMaxDistance > Math.max(maxLeftDistance, maxRightDistance)) {
						lastLocation = first;
						distance = curDistance;
						maxLeftDistance = curDistance;
						maxRightDistance = rightDistance;
					}
				}
				first = second;
			}
			if ((n - second - 1) > distance)
				lastLocation = second;
			
			long lower = locations.lower(lastLocation) != null ? locations.lower(lastLocation) : -1;
			long higher = locations.higher(lastLocation) != null ? locations.higher(lastLocation) : n;
			long left = lastLocation - lower;
			long right = higher - lastLocation;
			
//			if (isCase(i, n, k)) 
//				troubleshoot(i, lastLocation, first, second, lower, higher, left, right);
			
			if (right > left)
				lastLocation = (lastLocation + higher) / 2;
			else
				lastLocation = (lower + lastLocation) / 2;

			locations.add(lastLocation);
//			print(-1, locations, n, k);
		}
		
		if (fullyOccupied)
			return new Pair();
		
		
		// find max(left, right)
//		long first = -1;
//		long second = 0;
//		long maxDistance = 0;
//		long minDistance = n-1;
//		Iterator<Long> iter = locations.iterator();
//		while(iter.hasNext()) {
//			second = iter.next();
//			long curDistance = second - first - 1;
//			if (curDistance > maxDistance) {
//				lastLocation = first;
//				maxDistance = second - first - 1;
//			}
//			
//			if (curDistance < minDistance) {
//				minDistance = second - first - 1;
//			}
//			first = second;
//		}
//		
//		// check last distance
//		long curDistance = n - second - 1;
//		if (curDistance > maxDistance)
//			maxDistance = curDistance;
//		
//		// check last distance
//		if (curDistance < minDistance)
//			minDistance = curDistance;
		
		long lower = locations.lower(lastLocation) != null ? locations.lower(lastLocation) : -1;
		long higher = locations.higher(lastLocation) != null ? locations.higher(lastLocation) : n;
		long left = lastLocation - lower - 1;
		long right = higher - lastLocation - 1;
		
		Pair result = new Pair();
		result.min = Math.min(left, right);
		result.max = Math.max(left, right);
		return result;
	}
	
	private static class Pair {
		long max = 0L;
		long min = 0L;
	}
	
	private static boolean isCase(int i, long n, long k) {
		return (i == -1 || i == 1) && n==11 && k>=1;
	}
	
	private static void print(int i, TreeSet<Long> locations, long n, long k) {
		if (isCase(i, n, k)) {
			System.out.print("X");
			for (long j=0; j<n; j++) {
				if (locations.contains(j))
					System.out.print("O");
				else
					System.out.print(".");
			}
			System.out.print("X");
			System.out.println();
		}
	}
	
	private static void troubleshoot(int i, long lastLocation, long first, long second, long lower, long higher, long left, long right) {
		System.out.println(
				"lastLocation="
				+ lastLocation
				+ " first="
				+ first
				+ " second="
				+ second
				+ " lower="
				+ lower
				+ " higher="
				+ higher
				+ " left="
				+ left
				+ " right="
				+ right);
	}

}
