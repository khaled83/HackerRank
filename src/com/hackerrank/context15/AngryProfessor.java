package com.hackerrank.context15;

import java.util.Scanner;

public class AngryProfessor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum=1; caseNum<=numCases; caseNum++)
        {
        	int numStudents = sc.nextInt();
        	int minStudents = sc.nextInt();
        	
        	int maxLate = numStudents - minStudents;
        	int numLate = 0;
        	
        	int[] arrivalTimes = new int[numStudents];
        	for(int i=0; i<numStudents; i++) {
        		arrivalTimes[i] = sc.nextInt();
        	}
        	
        	int i = 0;
        	for(i=0; i<arrivalTimes.length; i++) {
        		if(arrivalTimes[i] > 0) {
        			numLate++;
        		}
        	}
        	
        	if(numLate > maxLate) {
    			System.out.println("YES");
    		}
        	else {
        		System.out.println("NO");
        	}
        	
        	
        }
        
        sc.close();

	}

}
