package com.facebook;

import java.util.Scanner;

public class CookingTheBooks {
	
	
	private static int smallest(int number, boolean is0Allowed)
	{
		if(number / 10 == 0)
			return number;
		
		int remaining = number;
		
		int smallest = 9;
		int indexSmallest = 0;
		
		int index = 0;
		
		do
		{
			int curInt = remaining % 10;
			if( curInt < smallest && ( is0Allowed || curInt != 0 ) )
			{
				smallest = curInt;
				indexSmallest = index;
			}
			
			remaining = remaining / 10;
			index++;
		} while( remaining > 0 );
		
		index--;
		
		int leftMost = (int) ( number / Math.pow(10, index) );
		
		if(!is0Allowed)
		{
			if(smallest == 0 || smallest == leftMost)
			{
				int leftMostMod = (int) ( leftMost * Math.pow(10, index) );
				System.out.println("recall smallest recursive with " + leftMost);
				return leftMostMod
						+ smallest( number % leftMostMod, true );
			}
		}
		
		int resultSmallest = (int)
								 ( number 
									- ( smallest * Math.pow(10, indexSmallest) ) 
									+ ( leftMost * Math.pow(10, indexSmallest) )
									- ( leftMost * Math.pow(10, index) )
									+ ( smallest * Math.pow( 10, index ) ) );
		
		return resultSmallest;
	
	}
	
	private static int largest(int number)
	{
		int remaining = number;
		
		int largest = 0;
		int indexLargest = 0;
		
		int index = 0;
		
		do
		{
			int curInt = remaining % 10;
				
			if( curInt > largest )
			{
				largest = curInt;
				indexLargest = index;
			}
			
			remaining = remaining / 10;
			index++;
		} while( remaining > 0 );
		
		index--;
		
		int leftMost = (int) ( number / Math.pow(10, index) );
		System.out.println("largest="+largest+", leftMost="+leftMost+", indexLargest="+indexLargest);
		
		if( largest == leftMost)
		{
				int leftMostMod = (int) ( leftMost * Math.pow(10, index) ); 
				System.out.println("recall largest recursive with leftMostMod = " + leftMostMod + ", number=" + number );
				return leftMostMod
						+ largest( number % leftMostMod );
		}
		
		int resultLargest = (int)
							 ( number 
								- ( largest * Math.pow(10, indexLargest) ) 
								+ ( leftMost * Math.pow(10, indexLargest) )
								- ( leftMost * Math.pow(10, index) )
								+ ( largest * Math.pow( 10, index ) ) );
		
		return resultLargest;
	}
	
	
	public static void main(String[] args) {
		System.out.println("My CookingTheBooks Application");
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt(); 
				
		for( int caseNum = 1; caseNum<=numCases; caseNum++)
		{
			int number = sc.nextInt();
			
			int resultSmallest = smallest(number, false);
			int resultLargest = largest(number);
			
			
			System.out.println("Case #" + caseNum + ": " + resultSmallest + " " + resultLargest);
		}
		
		sc.close();

	}

}
