package com.amazon.algorithms;

public class BitManipulation {
	
	/**
	 * 
	1       00000@N
	2       10011@M <<<
	3       10011   N | M  ; N | 0@M = N, 0@N | M = M 
	        j   i
	     9876543210
	N    1000000000
	M       10011
	*/
	public static int merge(int N, int M, int i, int j) {
		int mask = -1; // 1s
		for (int indx = i; indx <= j; indx++)
			mask = mask ^ (1 << indx);
		// mask = 1110000011
		// clear bits@N in range [i,j]
		N = N & mask;
		M = M << i;
		N = N | M;
		return N;
	}

	public static String doubleToBinary(double num) {
	    if (num >= 1 || num < 0)
	        return "ERROR";
	        
	    double fact = 0.5;
	    StringBuilder sb = new StringBuilder("0.");
	    while (num > 0) {
	        if (num >= fact) {
	            sb.append('1');
	            num -= fact;
	        }
	        else {
	            sb.append('0');
	        }
	        fact = fact / 2;
	    }
	    return sb.toString();
	}
	
}
