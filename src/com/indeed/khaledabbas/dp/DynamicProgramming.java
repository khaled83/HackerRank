package com.indeed.khaledabbas.dp;

import java.util.ArrayList;
import java.util.HashMap;

import com.indeed.khaledabbas.datastructures.arrays.ArrayUtils;

public class DynamicProgramming {

	public static void main(String[] args) {
		/**
		System.out.println( binomialCoefficient(4,2)  );
		*/
		System.out.println( bc(100,6)  );
		System.out.println( bc2(4,3)  );
		System.out.println( cachHit + " cach hits out of " + callbacks );

	}
	
	public static int bc2( int n, int k ) 
	{
		int[] table = new int[k+1];
		table[0] = 1; // C(0, 0)
		// C(i, j) = C(i-1, j) + C( i-1, j-1)
		for( int i = 1; i <= n; ++i ) {
			for( int j = Math.min(i, k); j >= 1; --j ) {
				table[j] = table[j] + table[j-1];
			}
			System.out.print("C("+i+","+k+"): ");
			ArrayUtils.printArray(table);
			table[0] = 1;
		}
		return table[k];
	}
	
	private static HashMap<Pair, Integer> cach = new HashMap<Pair, Integer>();
	private static int cachHit = 0;
	private static int callbacks = 0;

	private static class Pair {
		int n, k;
		
		Pair(int n, int k) { this.n = n; this.k = k; }

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair))
				return false;
			Pair p = (Pair) o;
			return this.n == p.n && this.k == p.k;
		}

		@Override
		public int hashCode() {
			int hash = 17;
			hash += hash * n;
			hash += hash * k;
			return hash;
		}
	}

	public static int bc(int n, int k) {

		callbacks++;
		Pair p = new Pair(n, k); // TODO; define constructor
		Integer value = cach.get(p);
		if (value != null) {
			cachHit++;
			return value;
		}

		if (n == k) {
			cach.put(p, 1);
			return 1;
		} else if (k == 1) {
			cach.put(p, n);
			return n;
		} else {
			Pair p1 = new Pair(n - 1, k - 1);
			Integer value1 = cach.get(p1);
			if (value1 == null) {
				value1 = bc(n - 1, k - 1);
				cach.put(p1, value1);
			}

			Pair p2 = new Pair(n - 1, k);
			Integer value2 = cach.get(p2);
			if (value2 == null) {
				value2 = bc(n - 1, k);
				cach.put(p2, value2);
			}
			return value1 + value2;
		}
	}
	
	public static int binomialCoefficient(int n, int k) {

		int num = n;
		int dem = k;

		float result = 0F;
		while (num >= (n - k + 1) && dem >= 1)
			result += (num--) / (dem--);

		while (num >= (n - k + 1))
			result *= (num--);

		while (dem >= 1)
			result /= (dem--);

		return (int) result;

	}

}
