package contest.projecteuler;

import java.util.*;
import java.math.BigDecimal;
import java.text.*;

/**
 * Failed!
 * @author yousKH
 * @deprecated
 */
public class MultiplesOf3And5 {
	
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        
        long n = 0;
        for(int caseNum = 1; caseNum <= numCases; caseNum++)
        {
        	System.out.println("--- Case " + caseNum + " ---");
            n = sc.nextLong();
            System.out.println( format.format( sumMultiplesOf3And5(n) ) );
            System.out.println( format.format( sumMultiplesOf3And5WithArithmeticProgression((long)n)));
            System.out.println( format.format( 
            						sumMultiplesOf3And5WithArithmetic(3, n)
									+ sumMultiplesOf3And5WithArithmetic(5, n)
									- sumMultiplesOf3And5WithArithmetic(15, n) 
            		   		  ) );
            System.out.println(format.format( sumMultiplesOf3And5WithArithmetic2(n) ));
            
        }
        
        sc.close();
    }
    
    private static double sumMultiplesOf3And5(double n)
    {
        double value = 0.0;
        for(int i=0; i<n; i+=3) {
            value+=i;
        }
        
        for(int i=0; i<n; i+=5) {
        	if(i%3 != 0)
        		value+=i;
        }
        return value;
    }        
    
    private static long sumMultiplesOf3And5WithArithmeticProgression(long N)
    {
    	long sum1=0,sum2=0,sum3=0,sum=0;
    	long no3=0,no5=0,no15=0;

        //find the no of terms 
		no3=(N-1)/3;
		no5=(N-1)/5;
		no15=(N-1)/15;

        //find the sum of the terms 

		sum1=no3*(6+(no3-1)*3)/2 ;
		sum2=no5*(10+(no5-1)*5)/2;
		sum3=no15*(30+(no15-1)*15)/2;
		sum=sum1+sum2-sum3;
		
		return sum;
    }
    
    private static double sumMultiplesOf3And5WithArithmetic(long n, long N)
    {
    	// [(N - 1) / n] * ([(N - 1) / n] + 1) * n / 2.
    	// (1) the sum of all numbers divisible by 3, plus (2) the sum of all numbers divisible by 5, minus (3) the sum of all numbers divisible by 15.
    	
    	return Math.floor((N-1) / n) * ( Math.floor((N-1)/n) + 1) * (n / 2);
    	
    }
    
    private static long sumMultiplesOf3And5WithArithmetic2(long N) {
    	long nr = N;
        nr--;
        long x3 = nr/3;
        long x5 = nr/5;
        long x15 = nr/15;

        long sum1 = 3*x3*(x3+1); 
        long sum2 = 5*x5*(x5+1);
        long sum3 = 15*x15*(x15+1);
        
        long sum = (sum1+sum2-sum3)/2;
        return sum;
    }
    
    /**
2
1000
1000000000
999999999
999999999
     * */

}


