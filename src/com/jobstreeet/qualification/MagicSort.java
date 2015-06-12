package com.jobstreeet.qualification;
import java.util.*;

public class MagicSort {
	
	private static class String2 implements Comparable<String2> 
	{
		
		String s;
		
		String2(String s ) {
			this.s = s;
		}

		@Override
		public int compareTo(String2 o) {
			
			int i=0;
			for( i=0; i<s.length(); i++) {
				char c1 = s.charAt(i);
				if( i > (o.s.length()-1))
					return -1;
				char c2 = o.s.charAt(i);
				if( orderMap.get(c1) > orderMap.get(c2))
					return 1;
				else if( orderMap.get(c1) < orderMap.get(c2))
					return -1;
			}
			
			if( i <= o.s.length())
				return 1;

			return 0;
		}
		
	}
	
	private static final HashMap<Character, Integer> orderMap = new HashMap<Character, Integer>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		String orderStr = sc.next(); // "ZOTFISENWABCDGHJKLMPQRUVXY";
		int hashCode = 0;
		for( char c : orderStr.toCharArray())
			orderMap.put(c, hashCode++);
		
		List<String2> list = new ArrayList<String2>();
		for( int t = 1; t <= numCases ; t++ ) 
		{
			String s = sc.next();
			list.add(new String2(s));
		}
		
		Collections.sort(list);
		for( String2 s2 : list )
			System.out.println( s2.s );
		
		sc.close();

	}

}
