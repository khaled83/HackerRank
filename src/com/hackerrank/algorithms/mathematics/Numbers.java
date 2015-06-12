package com.hackerrank.algorithms.mathematics;

import java.util.*;

public class Numbers {

	public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        
        int numT = sc.nextInt();
        
        for( int t = 1; t<=numT; t++) {
            long num = sc.nextLong();
            if( isFibo( num ) )
                System.out.println( "IsFibo " );
            else
                System.out.println( "IsNotFibo " );
        }
        
        sc.close();
    }
    
    private static final List<Long> fibosList = new ArrayList<Long>();
    private static final HashSet<Long> fibosSet = new HashSet<Long>();
    
    static {
        fibosList.add(0L);
        fibosList.add(1L);
        fibosSet.add(0L);
        fibosSet.add(1L);
    }
    
    // 0,1,1,2,3,5,8,13
    //    
    private static boolean isFibo( long x ) {
        int size = fibosList.size();
        if(  fibosSet.contains(x) )
            return true;
        else if( fibosList.get( size-1 ) < x ) {
            while( fibosList.get(size-1) < x ) {
                long fibo = fibosList.get(size-2) + fibosList.get(size-1);
                fibosList.add( fibo );
                fibosSet.add( fibo );
                size++;
            }
            
            if( fibosList.get(size-1) == x )
                return true;
        }
        return false;
    }

}
