package com.hackerrank.warmup;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.text.NumberFormatter;

public class FillingJars {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
        int numJars = sc.nextInt();
        int numOperations = sc.nextInt();
        
        double total = 0.0;
        for(int operation = 1; operation <= numOperations ; operation++) {
            double startJar = sc.nextDouble();
            double endJar = sc.nextDouble();            
            double amount = sc.nextDouble();
            
            total += (endJar - startJar + 1 ) * amount;
        }
        
        DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        System.out.println( format.format( Math.floor(total / numJars) ) );
        
        
//		Double d = Double.MAX_VALUE;
//		System.out.println(format.format(d));
		
//		Scanner sc = new Scanner(System.in);
//        double n = sc.nextDouble();
//        double m = sc.nextDouble();
//        
//        double total = 0.0;
//        double a, b, k;
//        for(double caseNum = 0; caseNum < m; caseNum++)
//        {
//            a = sc.nextDouble();
//            b = sc.nextDouble();
//            k = sc.nextDouble();
//            total += (b - a + 1) * k;
//        }
//        
//        sc.close();
//        
//        DecimalFormat format = new DecimalFormat("#");
//		format.setMaximumFractionDigits(0);
//        System.out.println(format.format( total / n ) );

	}

}
