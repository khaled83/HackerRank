package com.facebook;

import java.util.Scanner;

public class CookingTheBooks {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt(); 
				
		for( int caseNum = 1; caseNum<=numCases; caseNum++)
		{
			
			int number = sc.nextInt();
			int remaining = number;
			
			int smallest = number % 10, largest = number % 10;
			int indexSmallest = 0, indexLargest = 0;
			
			int index = 0;
			
			do
			{
				int curInt = remaining % 10;
				if( curInt < smallest && curInt != 0 )
				{
					smallest = curInt;
					indexSmallest = index;
				}
					
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
			
			int resultSmallest = smallest == 0
								 ? number 
								 : (int)
									 ( number 
										- ( smallest * Math.pow(10, indexSmallest) ) 
										+ ( leftMost * Math.pow(10, indexSmallest) )
										- ( leftMost * Math.pow(10, index) )
										+ ( smallest * Math.pow( 10, index ) ) );
			
			int resultLargest = largest == 0 
								? number
								: (int)
									 ( number 
										- ( largest * Math.pow(10, indexLargest) ) 
										+ ( leftMost * Math.pow(10, indexLargest) )
										- ( leftMost * Math.pow(10, index) )
										+ ( largest * Math.pow( 10, index ) ) );
			
			System.out.println("Case #" + caseNum + ": " + resultSmallest + " " + resultLargest);
		}
		
		sc.close();

	}

}
