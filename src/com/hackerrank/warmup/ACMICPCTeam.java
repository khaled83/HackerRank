package com.hackerrank.warmup;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Scanner;

public class ACMICPCTeam {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int numPersons = sc.nextInt();
		int numTopics = sc.nextInt();
		sc.nextLine();
		
		BitSet[] bsetArr = new BitSet[numPersons];
		
		for(int personIndx = 0; personIndx < numPersons; personIndx++) {
			
			bsetArr[personIndx] = new BitSet(numTopics);
			String str = sc.nextLine();
			int indx = 0;
			for(char c : str.toCharArray()) {
				if(c == '1') {
					bsetArr[personIndx].set(indx);
				}
				indx++;
			}
		}
		
		ArrayList<Integer> teamSkills = new ArrayList<Integer>();
		for(int i=0; i<numPersons; i++) {
			for(int j=i+1; j<numPersons; j++) {
				BitSet tmp = BitSet.valueOf( bsetArr[i].toByteArray() );
				tmp.or(bsetArr[j]);
				teamSkills.add( tmp.cardinality());
			}
		}
		
		// 0 1 2 3 4 5
		Collections.sort(teamSkills);
		int largestSkills = teamSkills.get(teamSkills.size() - 1);
		System.out.println(largestSkills);
		for(int indx = teamSkills.size()-1; indx >=0; indx--) {
			if(teamSkills.get(indx) < largestSkills) {
				System.out.println( teamSkills.size() - indx - 1 );
				return;
			}
		}
		
		sc.close();

	}
	
	private static void printBitSet(BitSet bset, int size) {
		for(int i=0; i<size;i++)
			System.out.print(bset.get(i) ? "1" : "0");
		System.out.println();
	}

}
