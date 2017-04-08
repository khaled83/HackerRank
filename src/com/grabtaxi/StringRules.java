package com.grabtaxi;
import java.util.*;

public class StringRules {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("BB").toString());
		StringBuilder sb = new StringBuilder(50000);
		Random rand = new Random();
		char[] chars = new char[] { 'A', 'B', 'C' };
		for( int i=0; i<50000;i++ ) {
			int indx = rand.nextInt(3);
			sb.append(chars[indx]);
		}
		System.out.println("START");
		
		long s = System.currentTimeMillis();
		
		System.out.println( solution(sb.toString()) );
		
		System.out.println( (System.currentTimeMillis() - s ) / ( 1000 ));
		
		
		/**
		   ABBCC
		   AABCC
		   AACCC
		   AACC
		   
		 */
		

	}
	
	private static final HashMap<String, String> rules = new HashMap<String, String>();
	static {
		rules.put("AB", "AA");
		rules.put("BA", "AA");
		rules.put("CB", "CC");
		rules.put("BC", "CC");
		rules.put("AA", "A");
		rules.put("CC", "C");
	}
	
	public static String solution(String S) 
	{
		if( S == null || S.length() == 0 )
			return "";
		
		StringBuilder sb = new StringBuilder(S);
		
		int indx = 0;
		while( indx >= 0 ) {
			for( String org : rules.keySet() ) {
				indx = sb.indexOf( org );
				if( indx >= 0 ) {
					sb.replace( indx, indx+org.length(), rules.get(org));
					break; // move to the next while iteration
				}
			}
		}
        
		return sb.toString();
    }

}
