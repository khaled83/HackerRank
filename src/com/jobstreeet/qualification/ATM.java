package com.jobstreeet.qualification;

import java.util.*;

public class ATM {

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		int balance = sc.nextInt();
		int amountRequested = sc.nextInt();
		int amount = amountRequested;
		int result = balance;
		
		if( amount >= balance )
			System.out.println(balance);
		
		if( (amount % 20) == 0 || (amount % 50) == 0 )
			result = balance - amountRequested - 1;
		else {
			amount = amount % 100;
			amount = amount % 50;
			amount = amount % 20;
		}
		
		if( amount == 0 )
			result = balance - amountRequested - 1;
		
		System.out.println(result);
		
		sc.close();
	}
	
}
