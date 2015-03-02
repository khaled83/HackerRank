package com.indeed.onlinetest.practice;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		
		String s = sc.next();
		
		System.out.println( (x+y+z) + " " + s);
		
		sc.close();

	}

}
