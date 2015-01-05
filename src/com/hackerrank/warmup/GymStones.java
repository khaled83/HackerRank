package com.hackerrank.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GymStones {
	
	private static final int NUM_ELEMENTS = 26; // low-case letters
	
	public static void main(String args[])
	{
//		try {
//			BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
//		    // number of rocks
//		    int n = Integer.valueOf( in.readLine() );
//		    String rock;
//		    String[] rockArray = new String[n];
//		    int i = 0;
//		    while( ( rock = in.readLine() ) != null && !rock.equals("") )
//		    	rockArray[i++] = rock;
//		    System.out.println(Solution.solve(n, rockArray));
//		} catch (NumberFormatException | IOException e) {
//			System.err.println("Input format:\n3\nabcdde\nbaccd\neeabg");
//		}
		
		try {
			test(1000000, 30);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static void test(int numberOfRocks, int maxElements) throws NumberFormatException, IOException
	{
		String[] rockArray = new String[numberOfRocks];
		for(int i = 0; i < rockArray.length; i++)
		{
			StringBuilder sb = new StringBuilder();
			sb.append('a');
//			sb.append('b');
			for(int j=2; j<=maxElements; j++)
				sb.append( (char) ( (int) (Math.random() * 26) + 'a' ) );
			rockArray[i] = sb.toString();
		}
		
		for(int i=0; i<10;i++)
		{
			char[] arr = rockArray[i].toCharArray();
			Arrays.sort( arr );
			System.out.println(new String(arr));
		}
		System.out.println();
		
		long startTime;
		int outcome = 0;
		System.out.println("Solve with iterative:");
		startTime = System.currentTimeMillis();
		outcome = solveWithIterative(numberOfRocks, rockArray);
		System.out.println("Outcome="+outcome);
		System.out.println(System.currentTimeMillis() - startTime);
		
		System.out.println("Solve with vector:");
		startTime = System.currentTimeMillis();
		outcome = solveWithVector(numberOfRocks, rockArray);
		System.out.println("Outcome="+outcome);
		System.out.println(System.currentTimeMillis() - startTime);
		
		System.out.println("Solve with arrays:");
		startTime = System.currentTimeMillis();
		outcome = solveWithArrays(numberOfRocks, rockArray);
		System.out.println("Outcome="+outcome);
		System.out.println(System.currentTimeMillis() - startTime);
	}
	
	
	public static int solve(int n, String[] rockArray) throws NumberFormatException, IOException
	{
		return solveWithVector(n, rockArray);
	}
	
	public static int solveWithVector(int n, String[] rockArray) throws NumberFormatException, IOException
	{
	    // 2
	    // ab
	    // xxbaxxa
	    // aabxxxxz sort: O(m log( m ) )
	    // aabxxxxz helper array: O( 2 m ) = O( m )
	    
	    // [ a b ... y   z ]
	    // [ 0 1 ... 23 24 25 ] representation
	    // [ 1 0 ... 0  0  0  ] boolean helper reused for each rock
	    // [ 1 1 ... 1   0  0 ] counter (#rocks, unique per rock)
	    
	    int result = 0;
	    int[] elemCounter = new int[NUM_ELEMENTS]; // lower case letters
	    boolean[] rockElements = new boolean[NUM_ELEMENTS];
	    
	    for( String rock : rockArray)
	    {
	        // reset booleans
	        for(int i = 0; i < rockElements.length; i++)
	            rockElements[i] = false;
	    
	        for(char e : rock.toCharArray() )
	            rockElements[e - 'a'] = true;
	            
	        for(int i = 0; i < rockElements.length; i++ )
	            elemCounter[i]+= rockElements[i] ? 1 : 0;
	    }
	    
	    for(int count : elemCounter )
	        if( count == rockArray.length )
	            result++;
	    
	    return result;
	}
	
	public static int solveWithIterative(int n, String[] rockArray) throws NumberFormatException, IOException
	{
		// 2
		// ab
		// badab
		
		String smallestRock = rockArray[0];
		for(String rock : rockArray)
			if(rock.length() < smallestRock.length())
				smallestRock = rock;
		
		int result = 0;
		char[] arrTemp = smallestRock.toCharArray();
		// remove duplicates
		Arrays.sort(arrTemp);
		StringBuilder sb = new StringBuilder(arrTemp.length);
		sb.append(arrTemp[0]);
		char cur = arrTemp[0];
		for(char c : arrTemp)
			if(c != cur)
			{
				sb.append(c);
				cur = c;
			}
		
		// crate string with no duplicate elements
		char[] smallestRockArr = new char[sb.length()];
		for(int i=0; i<sb.length();i++)
			smallestRockArr[i] = sb.charAt(i);
		
		for(char e : smallestRockArr)
		{
			boolean foundInAll = true;
			
			for(int index = 0; index < rockArray.length && foundInAll; index++)
			{
				String anotherRock = rockArray[index];
				boolean foundInRock = false;
				for(char anotherElement : anotherRock.toCharArray())
					if(anotherElement == e)
					{
						foundInRock=true;
						break;
					}
				
				foundInAll = foundInRock;
			}
			
			if(foundInAll)
				result++;
				
		}
		
		return result;
	}

	public static int solveWithArrays(int n, String[] rockArray) throws NumberFormatException, IOException
	{
	    // number of unique element occurrences
        int[] elemCount = new int[NUM_ELEMENTS];
	    
	    for(String rock : rockArray)
	    {
	        char[] elements = rock.toCharArray();
	        // O( e log(e) )    e: number of elements in one rock
	        Arrays.sort(elements);
	        
	        // ['a', 'a', 'b']
	        char curr = elements[0];
	        elemCount[curr - 'a']++;
	        
	        for(char c : elements )
	        {
	            if( c != curr )
	            {
	                elemCount[c - 'a']++;
	                curr = c;
	            }
	        }
	    }
	    
	    // number of gym-elements
	    int result = 0;
	    for( int c : elemCount )
	    	if( c == n )
	            result++;
	    
	    return result;
	}

}
