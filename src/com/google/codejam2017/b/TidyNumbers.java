package com.google.codejam2017.b;

import java.util.Scanner;

public class TidyNumbers {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		long[] inputs = new long[numCases];
		
		for (int i=0; i<numCases; i++) {
			inputs[i] = sc.nextLong();
		}
		
		for (int i=0; i<numCases; i++) {
			System.out.println("Case #" + (i+1) + ": " + lastTidyNumber(inputs[i]));
		}
		sc.close();
	}
	
	/**
	 8, 123, 555, and 224488

	 012
	 ---
	 198
	 197
	 196
	 195
	 194
	 193
	 192
	 191
	 190
	 189
	 */
	public static long lastTidyNumber(long n) {
		while (n >= 0) {
			// find decimal location first untidy from left
			String s = Long.toString(n);
			int loc = -1;
			for (int i = 1; i < s.length(); i++) {
				if (s.charAt(i) < s.charAt(i-1)) {
					loc = i;
					break;
				}
			}
			if (loc < 0) {
				return n;
			}
			else {
				// string solution is simpler than numberic
				// int decFactor = s.length() - loc - 1;
				// n = n % (10*decFactor);
				StringBuilder newVal = new StringBuilder();
				newVal.append(s.substring(0, loc)).append(Character.getNumericValue(s.charAt(loc)));
				// fill 0s on the right side
				for (int i = loc+1; i < s.length(); i++)
					newVal.append('0');
				n = Long.valueOf(newVal.toString()) - 1;
			}
		}
		return 0;
	}

}
