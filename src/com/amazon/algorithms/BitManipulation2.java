package com.amazon.algorithms;

public class BitManipulation2 {
	
	public static void main(String[] args) {
		assert(count1s(Integer.parseInt("1000100", 2)) == 2);
		assert(count1s(Integer.parseInt("1111", 2)) == 4);
		System.out.println("OK!");
	}
	
	public static int count1s(int x) {
	    int numBits = 0;
	    while (x > 0) {
	        x = x & (x-1);
	        numBits++;
	    }
	    return numBits;
	}

	public static long swap(long x, int i, int j) {
	    // bits are different, otherwise do nothing
	    if (((x >>> i) & 1) != ((x >>> j) & 1)) {
	        x = x ^ (1 << i);
	        x = x ^ (1 << j);
	    }
	    
	    return x;
	}
	
	public static int numBitsConverstion(int a, int b) {
	    // 1s where a and b have different bits
	    int xor = a ^ b;
	    // count number of 1s
	    int count = 0;
	    while (xor > 0) {
	        int mask = xor - 1;
	        xor = xor & mask;
	        count++;
	    }
	    return count;
	}
	
}
