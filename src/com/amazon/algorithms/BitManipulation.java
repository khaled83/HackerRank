package com.amazon.algorithms;

import java.lang.Math;

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
	
	public static int flipOneZeroToProduceLongestAdjacent1sStream1(int x) {
	    int tmp = x;
	    int max = Integer.MIN_VALUE;
	    int maxLoc = -1;
	    for (int loc = 0; loc < 32 && tmp > 0; loc++) {
	        // check if bit @loc is 0 or 1
	        int mask = 1 << loc;
	        // found 0
	        if ((tmp & mask) == 0) {
	            // count 0s on the right
	            int right = 0;
	            for (int rightLoc = loc - 1; rightLoc >= 0; rightLoc--) {
	                mask = 1 << rightLoc;
	                if ((tmp & mask) > 0) {
	                    right++;
	                    // 0s on the right are not needed after this, unset to exit for loop earlier
	                    tmp = tmp ^ mask;
	                } else {
	                    break;
	                }
	            }
	            
	            // count 0s on the left
	            int left = 0;
	            for (int leftLoc = loc + 1; leftLoc < 32; leftLoc++) {
	                mask = 1 << leftLoc;
	                if ((tmp & mask) > 0) {
	                    left++;
	                    // tmp = tmp ^ mask; // keep for the next 0 on the left
	                } else {
	                    break;
	                }  
	            }
	            
	            int curCount = left + 1 + right;
	            if (curCount > max) {
	                max = curCount;
	                maxLoc = loc;
	            }
	        }
	    }
	    
	    return x ^ (1 << maxLoc);
	}

	public static int flipOneZeroToProduceLongestAdjacent1sStream2(int x) {
	    int[] count = new int[32];
	    int mask = 1;
	    int tmp = x;
	    int lastLoc1 = 0;
	    int loc = 0;
	    while (tmp > 0) {
	    	mask = 1 << loc;
	        if ((x & mask) > 0) {
	            count[loc] = 1;
	            lastLoc1 = loc;
	            tmp = tmp ^ mask;
	        }
	        loc++;
	    }
	    
	    // count number of 1s in sequence e.g. [1, 2, 3, 0, 1, 2, 0, 1]
	    for (loc = 1; loc <= lastLoc1; loc++) {
	        if (count[loc] > 0) {
	            count[loc] += count[loc-1];
	        }
	    }
	    
	    // unify number of 1s in sequence e.g. [3, 3, 3, 0, 2, 2, 0, 1]
	    for (loc = lastLoc1; loc >= 0; loc--) {
	        if (count[loc] > 0) {
	            count[loc] = Math.max(count[loc], count[loc + 1]);
	        }
	    }
	    
	    // find max location
	    int max = Integer.MIN_VALUE;
	    int maxLoc = -1;
	    for (loc = 0; loc < lastLoc1; loc++) {
	        if (count[loc] == 0) {
	            int right = loc > 0 ? count[loc - 1] : 0;
	            int left = loc < lastLoc1 ? count[loc + 1] : 0;
	            int curCount = right + 1 + left;
	            if ((curCount) > max) {
	                max = curCount;
	                maxLoc = loc;
	            }
	        }
	    }
	    
	    // flip to produce max 1s stream
	    return x ^ (1 << maxLoc);
	}

	/**
	1
	68421
	43210
	x            min            max
	-----        -------        ------
	00100 (4)    01000 (8)      10000 (16)
	00110 (6)    01010 (10)     10100 (20)
	10010 (18)   10100 (20)     11000 (24)
	 -
	10011                       11100

	min => move most significant 1 one location to the left
	                      [  max loss           ]      =>      [        min gain                      ]
	       fix:           most significant 1           to      least significant 0 location to the left
	max => move the least significant 1 to most significant location (31)
	                 [    min loss     ]          =>    [        max gain         ]
	       fix:      least significant 1          to     most significant 0 location
	       (loc 31 could be already set in original)
	*/
	/**
	        finding most significant 1
	        10000
	        ----- >>> 1
	        01000 
	        ----- >>> after 4 times
	        00000 loc=5
	        
	        10000
	        01111
	        -----
	        
    */
	public static int[] minAndMaxLarger(int x) {

		int[] res = new int[2];
		
	    if (x == Integer.MAX_VALUE || x == 0) {
	    	// default values when nothing can be done
	    	res[0] = x;
	    	res[1] = x;
	    	return res;
	    }
	        
	    // find most significant 1
	    // 00110 (-1) >> 00011 (0) >> 00001 (1) >> 00000 (2)
	    
	    // find first 0 from left to right
	    
	    
	    int tmp = x;
	    // location of first 0 from left to right
	    int loc0 = 30;
	    while ((tmp & (1 << loc0)) > 0) {
	        loc0--;
	    }
	    
	    // loc of most significant 1 that has some 0 on the left
	    int loc1 = loc0 - 1;
	    if (loc1 >= 0) {
	        tmp = x;
	        while ((tmp & (1 << loc1)) == 0) {
	            loc1--;
	        }
	    }
	    
	    /** calc min larger
	         00110            01010
	         00100 1<<loc     01000 1<<loc
	         -----+           -----+
	         01010            10010
	    */
	    
	    // move 1 from loc1 to loc1 + 1
	    if (loc1 >= 0) {
	        res[0] = x + (1 << loc1);
	    } else {
	    	res[0] = x;
	    }
	    
	    /**
	        calc max
	        00110
	        00101 (x-1)
	        ----- &
	        00100
	        10000 (1<<31)
	        ----- |
	        10100
	    */
	    
	    // unset least significant 1 then set 1 at most significant loc 31
	    
	    // APPROACH 1 => fails for some cases
	    // res[1] = (x & (x - 1)) | (1 << 31);
	    
	    // APPROACH 2
	    // count 1s
	    tmp = x;
	    int num1s = 0;
	    while (tmp > 0) {
	        num1s++;
	        tmp = tmp & (tmp - 1);
	    }
	    
	     
	    // res=0 num1s=3 loc=31 >> res=100.. num1s=2 loc=30 => res=110.. num1s=1 loc=29 => res=111.. num1s=0 loc=28
	    // 
	    int loc = 30;
	    while (num1s > 0) {
	        res[1] = res[1] | (1 << loc);
	        num1s--;
	        loc--;
	    }
	    
	    // TODO: when loc == 31
	    
	    return res;
	}
	
	public static int mul(int x, int y) {
	    // res = { x * (y - 1) + x    , y is odd    
	    //       { x * y              , y is even    
	    int res = x;
	    int tmp = y >>> 1;
	    while (tmp > 0) {
	        res = res << 1;
	        tmp = tmp >>> 1;
	    }
	    
	    // add x to res if y was odd
	    if ((y & 1) > 0) {
	        int loc = 0;
	        boolean carryOver = false;
	        while (loc < 31) {
	            boolean bitX = (x & (1 << loc)) > 0;
	            boolean bitRes = (res & (1 << loc)) > 0;
	            int sum = bitX ? 1 : 0;
	            sum = bitRes ? (sum + 1) : sum;
	            sum = carryOver ? (sum + 1) : sum;
	            // set bit at loc
	            if (sum == 1 || sum == 3) {
	                res = res | (1 << loc);
	            }
	            // unset bit at loc
	            else {
	                res = res & ~(1 << loc);
	            }
	            carryOver = sum > 1;
	            loc++;
	        }
	    }
	    
	    return res;
	    
	}
	
	/**
	 * @deprecated
	 * @param x
	 * @param y
	 * @return
	 */
	public static int div(int x, int y) {
	    boolean odd = (y & 1) > 0;
	    // x - y
	    if (odd) {
	        x = x - y;
	    }
	    // elimiate lsb (odd)
	    y = y >>> 1;
	    // divide by (y-1)
	    while(y > 0) {
	        x = x >>> 1;
	        y = y >>> 1;
	    }
	    if (odd) {
	        x = x - 1;
	    }
	    return x;
	}

	public static long reverse(long x) {
	    long res = 0;
        // process 32 bits of integer, shift bits up to 31 times
        int n = 32;
        while (x > 0) {
        	res = res << 1;
            // check LSB
            long lsb = x & 1;
            // set LSB in res if its set in x
            res = res | lsb;
            x   = x   >> 1;
            n--;
        }
        
        
        // no more set bits in x, shift res to the far end left
        if (n > 0) {
        	res = res << 1;
        	res = res << (n - 1);
        }
        
        return res;
	}
	
	private static final int INTEGER_BIT_SIZE = 32;
	
	public static int[] nextSmallestAndLargestInt(int x) {
	    int mask = 1;
	    int ones = 0;
	    for (int i = 0; i < (INTEGER_BIT_SIZE - 1); i++) {
	        if ((x & mask) > 0) {
	            ones++;
	        }
	        mask = mask << 1;
	    }
	    
	    // 1 << 3 => 1000 - 1 => 111
	    int min = (1 << ones) - 1;
	    // int(8): 111 << (7 - 3) => 0|1110000
	    int max = min << ((INTEGER_BIT_SIZE - 1) - ones);
	    
	    return new int[] {min, max};
	}

}
