package com.indeed.onlinetest.task2;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//A B X1 K M
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int X1 = sc.nextInt();
		int K = sc.nextInt();
		int M = sc.nextInt();
		
		int K2, K3, K4, K5;
		
		
		// calculate K1
		int K1 = X1;
		for(int i=1; i<K; i++)
		{
			K1 = rand(A, B, K1, M);
		}
		
		K2 = rand(A, B, K1, M);
		K3 = rand(A, B, K2, M);
		K4 = rand(A, B, K3, M);
		K5 = rand(A, B, K4, M);
		
		System.out.println(K1);
		System.out.println(K2);
		System.out.println(K3);
		System.out.println(K4);
		System.out.println(K5);
		
		sc.close();

	}
	
	private static int rand(int A, int B, int K, int M)
	{
		return (A*K+B)%M;
	}

}
