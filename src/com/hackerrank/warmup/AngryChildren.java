package com.hackerrank.warmup;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AngryChildren {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int numPackets = sc.nextInt();
		int numKids = sc.nextInt();

		int[] packets = new int[numPackets];

		for (int i = 0; i < numPackets; i++) {
			packets[i] = sc.nextInt();
		}

		Arrays.sort(packets);

		// find the minimal unfairness
		int min = packets[numPackets - 1] - packets[0];
		for (int i = 0; i < packets.length - numKids; i++) {
			int curMin = packets[i + numKids - 1] - packets[i];
			if (curMin < min)
				min = curMin;
		}

		sc.close();
		System.out.println(min);
	}

}
