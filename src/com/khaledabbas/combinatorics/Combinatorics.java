package com.khaledabbas.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public class Combinatorics {

	public static void main(String[] args) {
		/**
		perm("abc");
		System.out.println(combi2( "abcd", 2 ));
		
		ArrayUtils.printArray( combi3(3, 2) );
		
		for( List<Integer> result : combi4(6, 2) ) 
		{
			for( Integer x : result )
				System.out.print(x);
		}
		System.out.println("callbacks=" + callbacks);
		
		
		
		System.out.println( combi2("abcdef", 2).size() );
		System.out.println("callbacks=" + callbacks2);
		 */
		combi5(4, 2);
		perm("abc");

	}
	

	/***
	 *	Elements of programming interviews 
	 */
	public static List<List<Integer>> combi4( int n, int k ) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		combi4( n, k, 1, ans, result );
		return result;
	}
	
	private static int callbacks = 0;
	private static void combi4( int n, int k, int start, List<Integer> ans, List<List<Integer>> result ) {
		callbacks++;
		
		if( ans.size() == k ) {
			System.out.println("adding result: " + ans);
			result.add( ans );
			return;
		}
		
		for( int i = start; i <= n && (k - ans.size() ) <= (n - i + 1); ++i ) {
			ans.add( i );
			combi4( n, k, i+1, ans, result );
			ans.remove( new Integer(i) );
		}
		
	}
	
	public static void combi5( int n, int k ) {
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		combi5( 1, n, k, results );
		for( ArrayList<Integer> result : results )
			System.out.println(result);
	}
	
	public static ArrayList<ArrayList<Integer>> combi5( int start, int n, int k, ArrayList<ArrayList<Integer>> resultsSoFar ) {
		if( start == ( n - k )  ) { 
	        ArrayList<Integer> result = new ArrayList<Integer>();
	        for( int i=start; i<=n; i++ ) {
	            result.add( i );
	            ArrayList<Integer> cur = new ArrayList<Integer>();
	            cur.add(start);
	            resultsSoFar.add( cur ); 
	        }
	            
	        resultsSoFar.add( result ); 
	    }
	    else {
	        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
	        ArrayList<ArrayList<Integer>> subresults = combi5( start + 1, n, k-1, resultsSoFar );
	        for( ArrayList<Integer> subresult : subresults ) {
	            if( subresult.size() < k ) { // k or originalK
	                subresult.add( start );
	                results.add( subresult );
	            }
	        }
	    }
		
		return resultsSoFar;
	}
	
	public static String[] combi3(int n, int k) {

		return combi3(1, n, k);
	}

	private static String[] combi3(int first, int last, int k) {

		if (first == last) {
			return new String[] { first+"" };
		} else {
			Object[] results = 
					ArrayUtils.union( 
						combine( new String[] {""}, combi3(first+1, last, k), k ),
						combine( new String[] {first+""}, combi3(first+1, last, k-1), k-1 ) );
			return Arrays.copyOf( results, results.length, String[].class );
						
//			return combine(combi3(first, first, k), combi3(first + 1, last, k), k);
		}

	}

	private static String[] combine(String[] combi1, String[] combi2, int k) {

		ArrayList<String> results = new ArrayList<String>();

		// add combi1 elements
		results.addAll(Arrays.asList(combi1));
		results.addAll(Arrays.asList(combi2));

		for (int i = 0; i < combi1.length; i++)
			for (int j = 0; j < combi2.length; j++) {
				if (combi1[i].length() + combi2[j].length() <= k) {
					results.add(combi1[i] + combi2[j]);
				}
			}

		return results.toArray(new String[0]);

	}
	
	private static int callbacks2 = 0;
	public static List<String> combi2(String s, int k) {

		// C( n, k ) = n!/(n-k)!k!
		List<String> combi = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		combi.add( "" );

		for (char c : s.toCharArray()) {
			int size = combi.size();
			for (int i = 0; i < size; i++) {
				if ( combi.get(i).length() < k) {
					callbacks2++;
					String s2 = combi.get(i) + c;
					combi.add( s2 );
					if( s2.length() == k )
						results.add(s2);
				}
			}
		}

		return results;
	}
	
	/**
	 * {@link http://www.growingwiththeweb.com/2013/06/algorithm-all-combinations-of-set.html}
	 * alt: {@link http://javahungry.blogspot.com/2014/03/algorithm-to-find-permutations-of-string-using-recursion-in-java.html}
	 */
    private static List<String> combi(String s)
    {
    	ArrayList<String> combi = new ArrayList<String>();
        for(int i=0; i<s.length();i++)
        {
        	int length = combi.size();
            for(int j=0; j<length; j++)
            {
                combi.add( combi.get(j) + s.charAt(i) );
            }
            combi.add(Character.toString( s.charAt(i)) );
        }
        return combi;
    }
	
	public static void perm(String s)
	{
	    perm(s.toCharArray(), 0, s.length() -1);
	}

	private static void perm(char[] chars, int start, int end)
	{
	    if(start == end)
	        System.out.println(new String(chars) );
	    for(int i=start; i<=end; i++)
	    {
	        swap(chars, i, start);
	        perm(chars, start+1, end);
	        swap(chars, i, start); // backtrack
	    }
	}
	
	private static void swap(char[] arr, int indx1, int indx2)
	{
		char tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}
	

}
