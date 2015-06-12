package com.jobstreeet.demo;
import java.util.*;

public class BirthDates {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		String minName = "", maxName = "";
		Calendar min  = Calendar.getInstance();
		min.setTime( new Date(Long.MAX_VALUE) );
		Calendar max  = Calendar.getInstance();
		max.setTime( new Date(Long.MIN_VALUE) );
		
		for( int t = 1; t <= numCases ; t++ ) 
		{
			String name = sc.next();
			int day = sc.nextInt();
			int month = sc.nextInt();
			int year = sc.nextInt();
			
			Calendar cur = Calendar.getInstance();
			cur.set( Calendar.DAY_OF_MONTH, day);
			cur.set( Calendar.MONTH, month);
			cur.set( Calendar.YEAR, year);
			
			if( cur.compareTo( min ) < 0  ) {
				min = cur;
				minName = name;
			}
			
			if( cur.compareTo( max ) > 0  ) {
				max = cur;
				maxName = name;
			}
			
		}
		
		System.out.println(maxName);
		System.out.println(minName);
		
		sc.close();

	}

}
