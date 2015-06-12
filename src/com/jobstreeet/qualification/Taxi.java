package com.jobstreeet.qualification;

import java.util.*;

public class Taxi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numGroups = sc.nextInt();
		int total = 0;
		
		for( int t = 1; t <= numGroups ; t++ ) 
		{
			total += sc.nextInt();
		}
		
		System.out.println( ( (int) Math.ceil( total / 4 ) ) );
		
		sc.close();

	}

}
