package com.jobstreeet.qualification;
import java.util.*;

public class SungaiGombak {

	public static void main(String[] args) {
		// 3
		// R W R W W W R
		//     3            t
		//     t  
		// 0 
		// 3
		Scanner sc = new Scanner(System.in);
		
		int stepSize = sc.nextInt();
		String s = sc.next();
		char[] path = s.toCharArray();
		
		int steps = stepSize;
		
		int loc = 0;
		while( loc < path.length && steps > 0 ) {
			if( path[loc] == 'R' )
				steps = stepSize;
			else
				steps--;
			loc++;
		}
		
		if( loc == path.length )
			System.out.println("YES");
		else
			System.out.println("NO");
		
		sc.close();

	}

}
