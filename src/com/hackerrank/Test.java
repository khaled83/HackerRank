package com.hackerrank;

import java.util.Arrays;
import java.util.BitSet;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(Long.toBinaryString(1 << 30));
		System.out.println(Long.toBinaryString(1 << 31));
		
		int[] arr = {9,11,13,25,27,31,42,69,88,99};
		System.out.println(Arrays.binarySearch(arr, 89));
		
		BitSet bset = new BitSet(32);
		System.out.println(bset.toString());
		bset.set(0, 30);
		System.out.println(bset.toString());
		System.out.println();
		for(int i=0;i<32;i++)
			System.out.print(i%10);
		System.out.println();
		System.out.println(Integer.toBinaryString( Integer.MAX_VALUE ));
		System.out.println(Integer.toBinaryString( Integer.MIN_VALUE ));

	}

}
