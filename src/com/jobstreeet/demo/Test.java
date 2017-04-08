package com.jobstreeet.demo;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> input1 = new ArrayList<String>();
		ArrayList<String> input2 = new ArrayList<String>();
		
		for(int i=1; i<=20;i++)
			input1.add(sc.nextLine().trim());
		
		for(int i=1; i<=20;i++)
			input2.add(sc.nextLine().trim());
		
		boolean isEqual = true;
		
		ArrayList<String> unEqual = new ArrayList<String>();
		
		for(int i=0; i<20;i++)
			if(!input1.get(i).trim().equals(input2.get(i).trim()))
			{
				isEqual = false;
				unEqual.add(input1.get(i) + " : " + input2.get(i));
			}
				
		
		System.out.println("isEqual = " + isEqual);
		System.out.println(unEqual);
		
		sc.close();

	}
	
	@SuppressWarnings("unused")
	private static void method(Integer x)
	{
		x = x+1;
	}

}
