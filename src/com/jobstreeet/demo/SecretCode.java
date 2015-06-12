package com.jobstreeet.demo;


import java.util.*;

public class SecretCode {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for( int t = 1; t <= numCases ; t++ ) 
		{
			String s = sc.nextLine().trim();
			if(s.trim().length()==0) {
				t--;
				continue;
			}
			
			int count = 0;
			char last = s.charAt(0);
			
			// AAAA
			for( int i  = 1; i < s.length(); i++ ) 
			{
				char cur = s.charAt(i);
				if( cur == last )
					count++;
				else {
					last = cur;
				}
			}
			
			System.out.println(count);
			
		}
		
		sc.close();

	}

}
