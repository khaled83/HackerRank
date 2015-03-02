package com.indeed.onlinetest.task3;

import java.util.*;

public class Main {

	/**
	 * Out of time. I couldn't clearly understand the examples given.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		// http://en.wikipedia.org/wiki/Expected_value
		
		double expected = 1;
		double factor = 0;
		for(int student=1; student<=n; student++)
		{
			String s = sc.next();
			double friendsCount = 0;

			for(char c : s.toCharArray()) 
			{
				if(c == 'Y')
					friendsCount++;
			}
			
			if(friendsCount == 0)
				break;
			
			expected = expected + ( (factor++) / (friendsCount) );
		}
		
		System.out.println(expected);
		
		sc.close();
	}

}
