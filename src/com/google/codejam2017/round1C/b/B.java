package com.google.codejam2017.round1C.b;

import java.util.Arrays;
import java.util.Scanner;

public class B {
	
	/**

	[  - - -  ]
	
	 0  1  2  3  4  5 
	    j  j           
	             c  c   
cX   0  0  0  0  1  1  
jX	 0  1  1  1  1  1
bX   0  1  1  1  2  2

cXB  0  0  0  0  1  1
jXB  0  1  1  1  2  2
   

S    c  c  c  j  j  j
	 
	*/
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		for (int testCase=0; testCase<numCases; testCase++) {
			
			int numC = sc.nextInt();
			int numJ = sc.nextInt();
			
			int numBoth = numC + numJ;
			
			double min = Integer.MAX_VALUE;
			double max = Integer.MIN_VALUE;
			
			int[] startTimesC = new int[numC];
			int[] endTimesC = new int[numC];
			
			int[] startTimesJ = new int[numJ];
			int[] endTimesJ = new int[numJ];
			
			int[] exC = new int[numC];
			int[] exJ = new int[numJ];
			
			for (int i=0; i< numC; i++) {
				startTimesC[i] = sc.nextInt();
				endTimesC[i] = sc.nextInt();
			}
			Arrays.sort(startTimesC);
			Arrays.sort(endTimesC);
			
			for (int i=0; i< numJ; i++) {
				startTimesJ[i] = sc.nextInt();
				endTimesJ[i] = sc.nextInt();
			}
			Arrays.sort(startTimesJ);
			Arrays.sort(endTimesJ);
			
			System.out.println((testCase+1) + " ======= numC="+numC + " numJ="+numJ+ " ======== ");
			
			// ========== calulate # exchanges if we start with Carmen =====  
			int exStartWithC = 0;
			boolean isCarmen = true;
			int lastEnd = 0, timeSoFarC = 0, timeSoFarJ = 0;
			
			int c=0, j=0;
			if (numC == 0 || startTimesC[0] == 0) {
				c = 1;
				lastEnd = endTimesC[0];
			}
			/**
			 
			 
			 
			 
			 * */
			System.out.println("--- starting from Carmen");
			int count = 0;
			for (; c < numC || j < numJ;)
			{
				count++;
				if (count > 10)
					break;
				// solved
				if (timeSoFarC >= 720 || timeSoFarJ >= 720)
					break;
				
				int startTimeC = c < numC ? startTimesC[c] : Integer.MAX_VALUE;
				int endTimeC = c < numC ? endTimesC[c] : Integer.MAX_VALUE;
				int startTimeJ = j < numJ ? startTimesJ[j] : Integer.MAX_VALUE;
				int endTimeJ = j < numJ ? endTimesJ[j] : Integer.MAX_VALUE;
//				System.out.println("isCarmen="+isCarmen+" startTimeC="+startTimeC+ " startTimeJ="+startTimeJ + " c="+c + " j="+j);
				if ((isCarmen && startTimeC > 0)) {
					if (lastEnd >= startTimeC) {
						c++;
						System.out.println("Carmen ==> reset lastStart="+endTimeC);
						lastEnd = endTimeC;
						continue;
					}
					isCarmen = false;
					exStartWithC++;
					timeSoFarC += startTimeC - lastEnd;
					System.out.println("Carmen works from " + lastEnd + " to " + (timeSoFarC > 720 ? (startTimeC - (timeSoFarC - 720)): startTimeC) + " => " + timeSoFarC);
					lastEnd = startTimeC;
					if (timeSoFarC > 720)
						lastEnd = startTimeC - (timeSoFarC - 720);
					c++;
				}
				else if (isCarmen && c == numC) {
					System.out.println("2");
					isCarmen = false;
					exStartWithC++;
				}
				else if ((!isCarmen && startTimeJ > 0)) {
					if (lastEnd >= startTimeJ) {
						j++;
						System.out.println("Jamie ==> reset lastStart="+endTimeJ);
						lastEnd = endTimeJ;
						continue;
					}
					isCarmen = true;
					exStartWithC++;
					timeSoFarJ += startTimeJ - lastEnd;
					System.out.println("Jamie works from "+ lastEnd + " to " + startTimeJ + " => " + timeSoFarJ);
					lastEnd = startTimeJ;
					if (timeSoFarJ > 720)
						lastEnd = startTimeJ - (timeSoFarJ - 720);
					j++;
				}
				else if (!isCarmen && j == numJ) {
					System.out.println("Jamie works");
					isCarmen = true;
					exStartWithC++;
				}
			}
			
//			int ex = exStartWithC[numBoth-1];
			if (timeSoFarC < 720 || timeSoFarJ < 720) {
				exStartWithC++;
			}
			System.out.println("#exchanges Caremen = " + exStartWithC);
			
			
			lastEnd = 0;
			timeSoFarC = 0;
			timeSoFarJ = 0;
			int exStartWithJ = 0;
			isCarmen = false;
			c=0; 
			j=0;
			if (numJ == 0 || startTimesJ[0] == 0) {
				j = 1;
				lastEnd = endTimesJ[0];
			}
			
			System.out.println("--- starting from Jamie");
//			for (; c < numC || j < numJ;)
//			{
//				// solved
//				if (timeSoFarC >= 720 || timeSoFarJ >= 720)
//					break;
//				
//				int startTimeC = c < numC ? startTimesC[c] : Integer.MAX_VALUE;
//				int endTimeC = c < numC ? endTimesC[c] : Integer.MAX_VALUE;
//				int startTimeJ = j < numJ ? startTimesJ[j] : Integer.MAX_VALUE;
//				int endTimeJ = j < numJ ? endTimesJ[j] : Integer.MAX_VALUE;
//			System.out.println("isCarmen="+isCarmen+" startTimeC="+startTimeC+ " startTimeJ="+startTimeJ + " c="+c + " j="+j);
//				if ((isCarmen && startTimeC > 0 && startTimeC < startTimeJ)) {
//					if (lastEnd >= startTimeC) {
//						c++;
//						lastEnd = endTimeC;
//						continue;
//					}
//					isCarmen = false;
//					exStartWithJ++;
//					timeSoFarC += startTimeC - lastEnd;
//					System.out.println("Carmen works from "+lastEnd + " to " + startTimeC + " => " + timeSoFarC);
//					lastEnd = endTimeC;
//					c++;
//				}
//				else if (isCarmen && c == numC) {
//					System.out.println("2");
//					isCarmen = false;
//					exStartWithJ++;
//				}
//				else if ((!isCarmen && startTimeJ > 0  && startTimeJ < startTimeC)) {
//					if (lastEnd >= startTimeJ) {
//						j++;
//						lastEnd = endTimeJ;
//						continue;
//					}
//					isCarmen = true;
//					exStartWithJ++;
//					timeSoFarJ += startTimeJ - lastEnd;
//					System.out.println("Jamie works from "+lastEnd + " to " + startTimeJ + " => " + timeSoFarJ);
//					lastEnd = endTimeJ;
//					j++;
//				}
//				else if (!isCarmen && j == numJ) {
//					System.out.println("Jamie works");
//					isCarmen = true;
//					exStartWithJ++;
//				}
//			}
//			
//			System.out.println("#exchanges Jamie = " + exStartWithJ);
			
		}
		sc.close();
	}

}




































