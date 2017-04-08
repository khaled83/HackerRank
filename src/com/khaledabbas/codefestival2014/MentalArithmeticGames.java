package com.khaledabbas.codefestival2014;

import java.util.Scanner;

public class MentalArithmeticGames {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int[] numbers = new int[String.valueOf(number).length()];
		
		int indx = numbers.length - 1;
		
		do {
			int d = number % 10;
			numbers[indx--] = d;
			number = number / 10;
		} while(number > 0);
		
		int value = 0;
		boolean isOdd = true;
		for(int d : numbers ) {
			value = isOdd
					? value + d
					: value -d;
			isOdd = !isOdd;
		}
		
		System.out.println(value);
		
		sc.close();

	}

}
