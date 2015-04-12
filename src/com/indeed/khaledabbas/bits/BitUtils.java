package com.indeed.khaledabbas.bits;

public class BitUtils {

	public static void main(String[] args) {
		
		init();
		System.out.println(parity(Integer.MAX_VALUE));
		System.out.println(parity(Integer.MAX_VALUE));
		System.out.println(counter);
	}
	
	private static int wordSize = 4;
	private static int bitMask = 0b1111;

	private static short[] cach = new short[16];


	private static void init() {
		for (int i = 0; i < 16; i++)
			cach[i] = -1;
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
