package com.hackerrank.warmup;

import java.util.*;

public class SherlockAndSquares
{

	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum=1; caseNum<=numCases; caseNum++) 
        {
            int start = sc.nextInt();
            int end = sc.nextInt();
            
            int numPerfSqrts = 0;
            
//            for(int i=start; i<=end; i++) {
//                numPerfSqrts += isPerfectSquareRoot(i) ? 1 : 0;
//            }
//            
//            System.out.println(numPerfSqrts);
            
            // 16	35
            // 4	5.9
            
            // 15	36
            // 3.9	6
            
            // 
            // 2.0  3.0  4.0    5.0
            //    7    13
            //  4    9    16    25    36 
            
            //   71									188
            //	 8.4								13.7
            // 8	9	10		11		12		13		14
            // 64	81	100		121		144		169		196
            
            // 4 16
            // 2 4
            // 
            
            // METHOD 2 -----
            numPerfSqrts = 0;
            
            double sqrt1 = Math.sqrt(start);
            double sqrt2 = Math.sqrt(end);
            numPerfSqrts= (int) (Math.floor( sqrt2 ) - Math.ceil( sqrt1) ) + 1;
            numPerfSqrts = Math.max(0, numPerfSqrts);
            
//            if( Math.floor( sqrt1 ) == sqrt1 || Math.floor( sqrt2 ) != sqrt2 ) {
//                numPerfSqrts++;            
//            } 
            
            System.out.println(numPerfSqrts);
        }
        
        sc.close();
    }
    
    private static boolean isPerfectSquareRoot(int number)
    {
        // 0 1 4 9
        // 0000 0001 0100 1001
        int hex = 0b1111 & number;
        
        if( hex != 0 && hex != 1 && hex != 4 && hex > 9 ) {
            return false;
        }
        else {
            int perfSqrRoot = (int) Math.floor( Math.sqrt( number ) + 0.5 );
            return number == perfSqrRoot * perfSqrRoot;
        }
    }
    
    static long goodMask; // 0xC840C04048404040 computed below
    {
        for (int i=0; i<64; ++i) goodMask |= Long.MIN_VALUE >>> (i*i);
    }

    public static boolean isSquare(long x) {
        // This tests if the 6 least significant bits are right.
        // Moving the to be tested bit to the highest position saves us masking.
        if (goodMask << x >= 0) return false;
        final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);
        // Each square ends with an even number of zeros.
        if ((numberOfTrailingZeros & 1) != 0) return false;
        x >>= numberOfTrailingZeros;
        // Now x is either 0 or odd.
        // In binary each odd square ends with 001.
        // Postpone the sign test until now; handle zero in the branch.
        if ((x&7) != 1 | x <= 0) return x == 0;
        // Do it in the classical way.
        // The correctness is not trivial as the conversion from long to double is lossy!
        final long tst = (long) Math.sqrt(x);
        return tst * tst == x;
    }

}