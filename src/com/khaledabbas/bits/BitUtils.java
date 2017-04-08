package com.khaledabbas.bits;

import java.util.*;
import java.util.BitSet;

public class BitUtils {

	public static void main(String[] args) {
		/**
		init();
		System.out.println(parity(Integer.MAX_VALUE));
		System.out.println(parity(Integer.MAX_VALUE));
		System.out.println(counter);
		
		System.out.println( Integer.toBinaryString( 0b100000000 ) );
		System.out.println( Integer.toBinaryString( 0b10011 ) );
		System.out.println( Integer.toBinaryString( insert( 0b1001111100, 0b10011, 2, 6 ) ) );
		**/
		for(int i=0; i<10; i++) {
			long x = 8441204555L;
			System.out.println(Long.toBinaryString(x));
			x = reverseWithCach(x);
			System.out.println(Long.toBinaryString(-1L));
			System.out.println(Long.toBinaryString(x));
		}
		
	}
	
	
	private static final short ADDRESS_BITS_PER_WORD = 16;
	private static final int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;

	private static int[] cach2 = new int[BITS_PER_WORD];

	public static long reverseWithCach(long org) {

		/**
		 * 00 10 11 01 ( org << 0 * 2 ) >> 3 * 2 = 00 :word1 ( org << 1 * 2 ) >>
		 * 3 * 2 = 10 :word2 ( org << 2 * 2 ) >> 3 * 2 = 11 :word3 ( org << 3 *
		 * 2 ) >> 3 * 2 = 01 :word4
		 */

		long word1 = (org << ( 0 * ADDRESS_BITS_PER_WORD ) ) >>> (3 * ADDRESS_BITS_PER_WORD);
		long word2 = (org << ( 1 * ADDRESS_BITS_PER_WORD ) ) >>> (3 * ADDRESS_BITS_PER_WORD);
		long word3 = (org << ( 2 * ADDRESS_BITS_PER_WORD ) ) >>> (3 * ADDRESS_BITS_PER_WORD);
		long word4 = (org << ( 3 * ADDRESS_BITS_PER_WORD ) ) >>> (3 * ADDRESS_BITS_PER_WORD);
		
		System.out.println();
		long reverse = reverse2(word4) << ( 3 * ADDRESS_BITS_PER_WORD )
						| reverse2(word3) << ( 2 * ADDRESS_BITS_PER_WORD )
						| reverse2(word2) << ( 1 * ADDRESS_BITS_PER_WORD )
						| reverse2(word1) << ( 0 * ADDRESS_BITS_PER_WORD );
		
		return reverse;
	}

	public static long reverse2(long org) {

		if (org == 0)
			return 0;

		long reverse = 0;
		int cachIndx = (int) org;
//		System.out.println( "org="+org + " : cachIndx="+cachIndx );
		if (cach2[cachIndx] != 0)
			return cach2[cachIndx];
		
		System.out.println("calculating reverse for " + org);

		short numBitsSoFar = 0;
		while (org != 0 && numBitsSoFar < 16) {
			// right most bit in org
			long nextBit = org & 1;
			// clear off processed bit
			org = org >>> 1;
			// append next bit to tail of reverse
			reverse = (reverse << 1) | nextBit;
			numBitsSoFar++;
		}

		int remainShifts = 16 - numBitsSoFar;
		reverse = reverse << remainShifts;
//		System.out.println("org=" + cachIndx + " >> reverse="+reverse);

		cach2[cachIndx] = (int) reverse;
		return reverse;

	}

	
//	public static long reverse(long org) {
//
//		long reverse = 0;
//
//		int numBitsSoFar = 0;
//		while (org != 0 && numBitsSoFar < 64) {
//			// right most bit in org
//			long nextBit = org & 1L;
//			// clear off processed bit
//			org = org >>> 1;
//			// append next bit to tail of reverse
//			reverse = (reverse << 1) | nextBit;
//			numBitsSoFar++;
//		}
//
//		// finish off remaining shifts
//		int remainShifts = 64 - numBitsSoFar;
//		reverse = reverse << remainShifts;
//
//		return reverse;
//
//	}
	
	private static int wordSize = 4;
	private static int bitMask = 0b1111;

	private static short[] cach = new short[16];


	private static void init() {
		for (int i = 0; i < 16; i++)
			cach[i] = -1;
	}
	
	public static int insert(int N, int M, int i, int j) {
		int length = j - i + 1;
		int mask = 1;
		while (mask < N)
			mask = (mask << 1) + 1;
		int windowOnes = ((1 << length) - 1) << i;
		mask ^= windowOnes;

		// clear bits [i..j]
		N &= mask;
		// shift M to [i..j]
		M <<= i;
		// insert M into N
		N |= M;
		
		return N;
	}


	public static int parity(int x)
	{
		// 1011 0101
		// 1111 0000
		
		int word1 = ( x & ( bitMask << ( 7 * wordSize ) ) )  >> (7 * wordSize );
		int word2 = ( x & ( bitMask << ( 6 * wordSize ) ) )  >> (6 * wordSize );
		int word3 = ( x & ( bitMask << ( 5 * wordSize ) ) )  >> (5 * wordSize );
		int word4 = ( x & ( bitMask << ( 4 * wordSize ) ) )  >> (4 * wordSize );
		int word5 = ( x & ( bitMask << ( 3 * wordSize ) ) )  >> (3 * wordSize );
		int word6 = ( x & ( bitMask << ( 2 * wordSize ) ) )  >> (2 * wordSize );
		int word7 = ( x & ( bitMask << ( 1 * wordSize ) ) )  >> (1 * wordSize );
		int word8 = ( x & ( bitMask ) );
		
		return ( cach[word1] > 0 ? cach[word1] : parityCalc(word1)
				+ ( cach[word2] > 0 ? cach[word2] : parityCalc(word2) )
				+ ( cach[word3] > 0 ? cach[word3] : parityCalc(word3) )
				+ ( cach[word4] > 0 ? cach[word4] : parityCalc(word4) )
				+ ( cach[word5] > 0 ? cach[word5] : parityCalc(word5) )
				+ ( cach[word6] > 0 ? cach[word6] : parityCalc(word6) )
				+ ( cach[word7] > 0 ? cach[word7] : parityCalc(word7) )
				+ ( cach[word8] > 0 ? cach[word8] : parityCalc(word8) )
				);
	}

	private static int counter = 0;
	
	private static int parityCalc(int x) {
		
		counter++;
		System.out.print("cach["+Integer.toBinaryString(x)+"]=");

		short count = 0;
		while (x > 0) {
			x &= (x - 1);
			count++;
		}
		
		cach[x] = count;
		System.out.println(count);

		return count;

	}

}
