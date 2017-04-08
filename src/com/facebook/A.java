package com.facebook;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class A {
//	private static BufferedReader br;
//	private static StringTokenizer st;
//	private static PrintWriter pw;
	private static Scanner sc;

	public static void main(String[] args) throws IOException	{
//		br = new BufferedReader(new InputStreamReader(System.in));
		sc = new Scanner(System.in);
//		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		final int MAX_NUM_CASE = sc.nextInt();
		for(int qq = 1; qq <= MAX_NUM_CASE; qq++)	{
			System.out.print("Case #" + qq + ": ");
			String s = sc.next().trim();
			TreeSet<Long> set = new TreeSet<Long>();
			set.add(Long.parseLong(s));
			for(int i = 0; i < s.length(); i++) {
				for(int j = i+1; j < s.length(); j++) {
					String next = s.substring(0,i) + s.charAt(j) + s.substring(i+1, j) + s.charAt(i) + s.substring(j+1);
					if(next.charAt(0) != '0') {
						set.add(Long.parseLong(next));
					}
				}
			}
			System.out.println(set.first() + " " + set.last());
		}
//		pw.close();
	}

	/* NOTEBOOK CODE */

//	private static long readLong() throws IOException	{
//		return Long.parseLong(nextToken());
//	}
//
//	private static double readDouble() throws IOException	{
//		return Double.parseDouble(nextToken());
//	}
//
//	private static int readInt() throws IOException	{
//		return Integer.parseInt(nextToken());
//	}
//
//	private static String nextToken() throws IOException	{
//		while(st == null || !st.hasMoreTokens())	{
//			if(!br.ready())	{
//				pw.close();
//				System.exit(0);
//			}
//			st = new StringTokenizer(sc.nextLine().trim());
//		}
//		return st.nextToken();
//	}
}