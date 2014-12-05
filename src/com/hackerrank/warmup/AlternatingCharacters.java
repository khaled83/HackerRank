package com.hackerrank.warmup;

import java.util.Scanner;

public class AlternatingCharacters {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int numcaseNums = sc.nextInt();
        
        int[] results = new int[numcaseNums];
        
        for(int caseNum=0; caseNum<numcaseNums; caseNum++)
        {
            String s = sc.next();
            for(int i = 0; i < s.length() - 1; i++)
            {
                if(s.charAt(i) == s.charAt(i+1) )
                    results[caseNum]++;
            }
        }

	}

}
