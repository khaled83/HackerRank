package com.hackerrank.algorithms.string;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BiggerIsGreater {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		int numTestCases = sc.nextInt();
		
//		String[] results = new String[numTestCases];

		for (int testCase = 1; testCase <= numTestCases; testCase++) {
			String w = sc.nextLine().trim();
			// for some reason 1st case reads an empty line
			if (w.trim().length() == 0) {
				testCase--;
				continue;
			}

			int length = w.length();
			// right most smaller character
			char first = 0;
			int firstIndx = -1;
			// smallest character larger than first
			char second = 0;
			int secondIndx = -1;
			
			for (int i = length - 2; i >= 0; i--) {
				if (w.charAt(i) < w.charAt(i + 1)) {
					first = w.charAt(i);
					firstIndx = i;
					second = w.charAt(i + 1);
					secondIndx = i + 1;
					break;
				}
			}

			if (firstIndx < 0) {
				System.out.println("no answer");
//				results[testCase-1]= "no answer";
			}
			else
			{
				for (int i = secondIndx + 1; i < length; i++) {
					if (w.charAt(i) < second && w.charAt(i) > first) {
						second = w.charAt(i);
						secondIndx = i;
					}
				}
				
				StringBuilder sb = new StringBuilder(w);
				
				sb.setCharAt(firstIndx, second);
				sb.setCharAt(secondIndx, first);
				
				// sort characters after first in increasing order
				char[] remainingChars = new char[length-firstIndx-1]; 
				sb.getChars(firstIndx+1, length, remainingChars, 0);
				Arrays.sort(remainingChars);
				for(int src=0, dst=firstIndx+1; src<remainingChars.length; src++, dst++)
					sb.setCharAt(dst, remainingChars[src]);
				
				System.out.println(new String(sb));
//				results[testCase-1]= new String(sb);
			}

		}
		
//		System.out.println(" Deltas - - -");
//		Scanner fs = new Scanner(new File("C:/Development/prototype/workspace/HackerRank/bin/com/hackerrank/algorithms/string/output.txt"));
//		ArrayList<String> deltaList = new ArrayList<String>();
//		for(String result : results) {
//			String nextLine = fs.nextLine();
//			if(!result.trim().equals(nextLine.trim()) ) {
//				deltaList.add(result + " >> " + nextLine );
//			}
//		}
//		
//		for(String delta : deltaList)
//			System.out.println(delta);

		sc.close();
	}

}

/**
http://www.geeksforgeeks.org/lexicographic-permutations-of-string/
Steps to generate the next higher permutation:
1. Take the previously printed permutation and find the rightmost character in it, which is smaller than its next character. Let us call this character as �first character�.

2. Now find the ceiling of the �first character�. Ceiling is the smallest character on right of �first character�, which is greater than �first character�. Let us call the ceil character as �second character�.

3. Swap the two characters found in above 2 steps.

4. Sort the substring (in non-decreasing order) after the original index of �first character�.

Let us consider the string �ABCDEF�. Let previously printed permutation be �DCFEBA�. The next permutation in sorted order should be �DEABCF�. Let us understand above steps to find next permutation. The �first character� will be �C�. The �second character� will be �E�. After swapping these two, we get �DEFCBA�. The final step is to sort the substring after the character original index of �first character�. Finally, we get �DEABCF�.
 * */
