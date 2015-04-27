package com.indeed.khaledabbas.dp;

public class DynamicProgramming {

	public static void main(String[] args) {
		System.out.println( binomialCoefficient(4,2)  );

	}
	
	public static int binomialCoefficient(int n, int k) {

		int num = n;
		int dem = k;

		float result = 0F;
		while (num >= (n - k + 1) && dem >= 1)
			result += (num--) / (dem--);

		while (num >= (n - k + 1))
			result *= (num--);

		while (dem >= 1)
			result /= (dem--);

		return (int) result;

	}

}
