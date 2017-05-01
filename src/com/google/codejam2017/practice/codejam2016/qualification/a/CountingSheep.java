package com.google.codejam2017.practice.codejam2016.qualification.a;

import java.util.HashSet;
import java.util.Scanner;

public class CountingSheep {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for (int i = 0; i<numCases; i++) {
			long N = sc.nextLong();
			
			HashSet<Long> digits = new HashSet<Long>();
			boolean impossible = false;
			int mul = 1;
			
			if (N == 0)
				impossible = true;
			
			long result = N;
			while (!impossible) {
				if ((Long.MAX_VALUE - N) < N) {
					impossible = true;
					break;
				}
				long num = N * (mul++);
				result = num;
				long digit = -1;
				while (num > 0) {
					digit = num % 10;
					digits.add(digit);
					num = num / 10;
				}
				if (digits.size() == 10) {
					break;
				}
			}
			
			if (impossible)
				System.out.println("Case #" + (i+1) + ": INSOMNIA");
			else
				System.out.println("Case #" + (i+1) + ": " + result);
		}
		
		sc.close();
	}
	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		int numCases = sc.nextInt();
//		
//		int[] inputs = new int[numCases];
//		
//		for (int i=0; i<numCases; i++) {
//			inputs[i] = sc.nextInt(); 
//		}
//		
//		for (int i=0; i<numCases; i++) {
//			int x = inputs[i];
//			HashSet<Character> digits = new HashSet<Character>();
//			
//			if (x == 0) {
//				System.out.println("Case #" + (i + 1) + ": INSOMNIA");
//			}
//			else {
//				long sum = 0;
//				while(digits.size() < 10) {
//					sum += x;
//					for (char c : Long.toString(sum).toCharArray()) {
//						digits.add(c);
//					}
//				}
//				System.out.println("Case #" + (i + 1) + ": " + sum);
//			}
//		}
//		
//		sc.close();
//	}

}
