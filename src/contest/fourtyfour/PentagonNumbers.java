package contest.fourtyfour;

import java.util.HashMap;
import java.util.Scanner;

public class PentagonNumbers
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
//        int i = 1000000;
//        long p = ( i * (3 * i - 1) ) / 2;
//        System.out.println( p );
        
        long startTime = System.nanoTime();
        solveWithHash(n, k);
        System.out.println("Hash: " + ( System.nanoTime() - startTime) );
        
        startTime = System.nanoTime();
        solveWithIterative(n, k);
        System.out.println("Iterative: " + ( System.nanoTime() - startTime) );
    
    }
    
    private synchronized static void solveWithIterative(int n, int k)
    {
        // generate pentangonal numbers
        long[] pentagonal = new long[n];
        for( long i=1; i<=n; i++)
        	pentagonal[(int)i-1] = ( i * (3 * i - 1) ) / 2;
            
        
//        for(int i=0; i<n; i++)
//        	System.out.print(pentagonal[i] + ", ");
//        System.out.println();
            
        for(int i=k; i<n;i++)
        {
        	long diff = pentagonal[i] - pentagonal[i-k];
        	long sum = pentagonal[i] + pentagonal[i-k];
            // linear search 
            for(int j=i; j>=0 && pentagonal[j] >= diff; j--)
                if(pentagonal[j] == diff)
                    System.out.println( pentagonal[i] );
            for(int j=i+1; j<n && pentagonal[j] <= sum; j++)
                if(pentagonal[j] == sum)
                    System.out.println( pentagonal[i] );
        }
    }
    
    private synchronized static void solveWithHash(int n, int k)
    {
        // generate pentangonal numbers
    	long[] pentagonalArray = new long[n];
        HashMap<Long, Boolean> pentagonalMap = new HashMap<Long, Boolean>();
        
        for( long i=1; i<=n; i++)
        {
        	long pentangonalValue = ( i * (3 * i - 1) ) / 2;
        	pentagonalArray[(int)i-1] = pentangonalValue;
        	pentagonalMap.put( pentangonalValue, true);
        }
        
        for(int i=k; i<n;i++)
        {
        	long diff = pentagonalArray[i] - pentagonalArray[i-k];
        	long sum = pentagonalArray[i] + pentagonalArray[i-k];
            // linear search 
            if(pentagonalMap.containsKey(diff) )
                System.out.println( pentagonalArray[i] );
            if( pentagonalMap.containsKey(sum) )
                System.out.println( pentagonalArray[i] );
        }
    }
    
}

