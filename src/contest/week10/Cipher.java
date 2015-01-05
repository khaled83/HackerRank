package contest.week10;

import java.util.BitSet;
import java.util.Scanner;

/**
 * 
0	0	0
0	1	1
1	1	0

n k
7 4

1001010   
 1001010  
  1001010 
   1001010
   
1110100110

x:	1001010
t:	100101
s:	1110100110
m:	1001010

 */

public class Cipher {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int sSize = n+k-1;
		
		sc.useDelimiter("");
		sc.next();
		
		BitSet s = new BitSet( n + k - 1);
		
		for(int i=0; i<sSize; i++)
			s.set(i, sc.nextInt() == 1 ? true : false);
		
//		List<Boolean> token = new LinkedList<Boolean>(); 
		BitSet token = new BitSet(k-1);
		BitSet message = new BitSet(n);
		BitSet xorResult = new BitSet(1);
		
		for(int i=0; i<n;i++)
		{
			xorResult.set(0, s.get(i));
			xorResult.set(0, xor(1, xorResult, k-1, token ) );
			message.set(i, xorResult.get(0) );
			token.set( i % (k-1), xorResult.get(0));
		}
		
		for(int i=0; i<n; i++)
			System.out.print(message.get(i) ? 1 : 0);
		
		sc.close();
    }
	
	@SuppressWarnings("unused")
	private static void print(int n, BitSet bs)
	{
		for(int i=0; i<n; i++)
			System.out.print(bs.get(i) ? 1 : 0);
//		System.out.println();
	}
	
	private static boolean xor(int n, BitSet s1, int m, BitSet s2)
	{
		boolean result = false;
		for(int i=0; i<n;i++)
			result = result ^ s1.get(i);
		for(int i=0; i<m;i++)
			result = result ^ s2.get(i);
		
		return result;
	}

}
