package com.hackerrank.warmup;

public class UtopianTree {
	
	// initial height = h
	// 0:	1h  + 0		
	// 1:	2h	+ 0		0
	// 2:	2h	+ 1		
	// 3:	4h	+ 2		2
	// 4:	4h	+ 3		
	// 5:	8h	+ 6		6
	// 6:	8h	+ 7	
	// 7:	16h + 14	14
	// 8:	16h + 15
	// 9:	32h + 30	30	
	// 10:	32h + 31
	
	//			2 ^ (n+1)/2
	//			2 ^ (n / 2 - 1) - 2]
	//			2 ^ (n / 2) - 1
	
	public static int solveWithIterative(int n)
	{
		if(n == 0)
			return 1;
		
		int height = 1;
		
		for(int i=1; i<=n; i++)
			height = i % 2 == 1 ? 2 * height : height + 1;
		
		return height;
	}
	
	public static int solveWithEquation(int n)
	{
		int termMonsoon = (int) Math.pow(2, (n+1) / 2 );
		return 2 * termMonsoon - (n % 2 == 1 ? 2 : 1);
	}

}
