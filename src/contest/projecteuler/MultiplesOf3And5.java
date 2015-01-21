package contest.projecteuler;

import java.util.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;

public class MultiplesOf3And5 {
	
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        
        double n = 0.0;
        for(int caseNum = 1; caseNum <= numCases && sc.hasNext(); numCases++)
        {
            n = sc.nextDouble();
//            System.out.println( format.format( sumMultiplesOf3And5(n) ) );
            System.out.println( format.format( sumMultiplesOf3And5WithArithmetic(3, n)
            									+ sumMultiplesOf3And5WithArithmetic(5, n)
            									- sumMultiplesOf3And5WithArithmetic(15, n) ) );
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
    
    private static double sumMultiplesOf3And5WithArithmetic(double n, double N)
    {
    	// [(N - 1) / n] * ([(N - 1) / n] + 1) * n / 2.
    	// (1) the sum of all numbers divisible by 3, plus (2) the sum of all numbers divisible by 5, minus (3) the sum of all numbers divisible by 15.
    	return Math.floor((N-1) / n) * ( Math.floor((N-1)/n) + 1) * (n / 2);
    }

}


