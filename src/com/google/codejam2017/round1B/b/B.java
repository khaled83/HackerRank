package com.google.codejam2017.round1B.b;

import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();

		for (int testCase = 0; testCase < numCases; testCase++) {
			/**
			 * R Y B O G V R B R Y Y B
			 * 
			 * - - RRBBYY
			 * 
			 * R -> BB YY G B -> RR YY O Y -> RR BB V
			 * 
			 * R B
			 */
			
			// r.put('B', B);
			// r.put('Y', Y);
			// r.put('G', G);
			//
			// y.put('R', R);
			// y.put('B', B);
			// y.put('V', V);
			//
			// b.put('R', R);
			// b.put('Y', Y);
			// b.put('O', O);
			//
			// o.put('B', V);
			// g.put('R', R);
			// v.put('Y', Y);
			

			int N = sc.nextInt();
			int R = sc.nextInt();
			int O = sc.nextInt();
			int Y = sc.nextInt();
			int G = sc.nextInt();
			int B = sc.nextInt();
			int V = sc.nextInt();

			char last = '0';
			StringBuilder sb = new StringBuilder();
			boolean impossible = false;
			for (int i = 0; i < N && !impossible; i++) {
//				System.out.println(last + " => " + sb);
//				System.out.println("R="+R+" O="+O+ " Y="+Y+" G="+G+" B="+B+" V="+V);
				switch (last) {
				case 'R':
					if (B > 0 && B > Y && B > G) {
						last = 'B';
						B--;
						sb.append(last);
					} else if (Y > 0 && Y > G) {
						last = 'Y';
						Y--;
						sb.append(last);
					} else if (G > 0) {
						last = 'G';
						G--;
						sb.append(last);
					} else
						impossible = true;
					break;
				case 'Y':
					if (R > 0 && R > B && R > V) {
						last = 'R';
						R--;
						sb.append(last);
					} else if (B > 0 && B > V) {
						last = 'B';
						B--;
						sb.append(last);
					} else if (V > 0) {
						last = 'V';
						V--;
						sb.append(last);
					} else
						impossible = true;
					break;
				case 'B':
					if (R > 0 && R > Y && R > O) {
						last = 'R';
						R--;
						sb.append(last);
					} else if (Y > 0 && Y > O) {
						last = 'Y';
						Y--;
						sb.append(last);
					} else if (O > 0) {
						last = 'O';
						O--;
						sb.append(last);
					} else
						impossible = true;
					break;
				case 'O':
					if (B > 0) {
						last = 'B';
						B--;
						sb.append(last);
					} else
						impossible = true;
					break;
				case 'G':
					if (R > 0) {
						last = 'R';
						R--;
						sb.append(last);
					} else
						impossible = true;
					break;
				case 'V':
					if (Y > 0) {
						last = 'Y';
						Y--;
						sb.append(last);
					} else
						impossible = true;
					break;
				default:
					if (V > 0) {
						last = 'V';
						V--;
						sb.append(last);
					} else if (G > 0) {
						last = 'G';
						G--;
						sb.append(last);
					} else if (B > 0) {
						last = 'B';
						B--;
						sb.append(last);
					} else if (O > 0) {
						last = 'O';
						O--;
						sb.append(last);
					} else if (Y > 0) {
						last = 'Y';
						Y--;
						sb.append(last);
					} else if (R > 0) {
						last = 'R';
						R--;
						sb.append(last);
					}  else
						impossible = true;

				}

			}
			
			if (impossible || sb.charAt(0) == sb.charAt(sb.length()-1))
				System.out.println("Case #"
						+ (testCase+1)
						+ ": IMPOSSIBLE");
			else
				System.out.println("Case #"
						+ (testCase+1)
						+ ": "
						+ sb.toString());
			
		}
		
		sc.close();
	}

}

