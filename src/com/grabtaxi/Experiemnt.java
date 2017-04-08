package com.grabtaxi;

public class Experiemnt {
	
	public static void main(String[] args) {
		System.out.println( isPalyndrome( "Abc" ) );
	}
	
	public static boolean isPalyndrome(String s ) {

	    int n = s.length();
	    
	    for( int head = 0, tail = n-1; 
	            head < tail ; 
	            head++, tail-- ) {
	        if( s.charAt( head ) != ( s.charAt(tail) ) ) {
	            return false;
	        }
	    }
	    
	    return true;

	}

}
