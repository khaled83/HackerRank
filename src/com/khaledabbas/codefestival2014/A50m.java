package com.khaledabbas.codefestival2014;

import java.text.DecimalFormat;
import java.util.Scanner;

public class A50m {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double seconds = sc.nextDouble();
		DecimalFormat format = new DecimalFormat("#");
		format.setMaximumFractionDigits(12);
		System.out.println( format.format( 50/seconds ) );
		sc.close();
	}

}
