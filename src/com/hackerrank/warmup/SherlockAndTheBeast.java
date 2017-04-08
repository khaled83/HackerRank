package com.hackerrank.warmup;

import java.text.DecimalFormat;

public class SherlockAndTheBeast {
	
	public static void main(String[] args)
	{
		double x = 0.0;
		for(int i=0; i<=100000;i++)
		{
			x+= (x*10)+5;
		}
		
		@SuppressWarnings("unused")
		String num5s;
		
		DecimalFormat format = new DecimalFormat("#");
		format.setMaximumFractionDigits(0);
		
		System.out.println(x);
		System.out.println(x >= Double.MAX_VALUE);
	}

}
