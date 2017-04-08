package com.google.codejam2017.practice;

import java.util.HashSet;
import java.util.Scanner;

public class CountingSheep {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		int[] inputs = new int[numCases];
		
		for (int i=0; i<numCases; i++) {
			inputs[i] = sc.nextInt(); 
		}
		
		for (int i=0; i<numCases; i++) {
			int x = inputs[i];
			HashSet<Character> digits = new HashSet<Character>();
			
			if (x == 0) {
				System.out.println("Case #" + (i + 1) + ": INSOMNIA");
			}
			else {
				long sum = 0;
				while(digits.size() < 10) {
					sum += x;
					for (char c : Long.toString(sum).toCharArray()) {
						digits.add(c);
					}
				}
				System.out.println("Case #" + (i + 1) + ": " + sum);
			}
		}
		
		sc.close();
	}

}
