package com.google.codejam2017.round1B.a;

import java.util.*;

public class A {
	
	/**

	 0			10
	      2 4
	 
	*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		for (int testCase=0; testCase<numCases; testCase++) {
			
			double D = sc.nextDouble();
			int N = sc.nextInt();
			
//			int[] loc = new int[N];
//			int[] speed = new int[N];
			
			double max = Integer.MIN_VALUE;
			for (int i=0; i<N; i++) {
				double loc = sc.nextDouble();
				double speed = sc.nextDouble();
				
				double timeLeft = 0;
				if (speed != 0) {
					timeLeft = (D-loc) / speed;
//					if (testCase == 78) {
//						System.out.println("D="+D+" loc="+ loc + " (D-loc)=" + (D-loc) + " => " + ((D-loc) / speed) +  " speed="+speed + " timeLeft="+timeLeft+ " max="+max);
//					}
				}
				
				max = Math.max(max, timeLeft);
			}
			
			double result = 0;
			
			if (max != 0) {
				result = D / max;
			}
			System.out.println("Case #"
					+ (testCase+1)
					+ ": "
					+ result);
//			System.out.format("%f%n", result);
			
		}
		sc.close();
	}

}
