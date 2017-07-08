package com.google.codejam2017.round1A.a;

import java.util.ArrayList;
import java.util.Scanner;

public class AlphabetCake {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		int[] rows = new int[numCases];
		int[] cols = new int[numCases];
		
		ArrayList<char[][]> matrixList = new ArrayList<char[][]>();
		
		for (int i=0; i<numCases; i++) {
			rows[i] = sc.nextInt();
			cols[i] = sc.nextInt();
			
			char[][] arr = new char[rows[i]][cols[i]];
			for (int row=0; row <rows[i]; row++) {
				String rowVal = sc.next();
				int col = 0;
				for (char c : rowVal.toCharArray()) {
					arr[row][col++] = c;
				}
			}
			matrixList.add(arr);
		}
		
		
		/**
		G??
		?C?
		??J
		


		 * */
		for (int i=0; i<numCases; i++) {
			// go by rows forward
			char[][] arr = matrixList.get(i);
			for (int row=0; row<rows[i]; row++) {
				for (int first=0, second=1; first < cols[i]; second++) {
					if (second >= cols[i])
						break;
					if (arr[row][first] == '?' && arr[row][second] != '?') {
						while (first < second) {
							arr[row][first++] = arr[row][second];
						}
					}
					if (arr[row][first] != '?')
						first++;
				}
			}
			
			// go by rows backward
			for (int row=0; row<rows[i]; row++) {
				for (int first=cols[i]-1, second=cols[i]-2; first >= 0; second--) {
					if (second < 0)
						break;
					if (arr[row][first] == '?' && arr[row][second] != '?') {
						while (first > second) {
							arr[row][first--] = arr[row][second];
						}
					}
					if (arr[row][first] != '?')
						first--;
				}
			}
			
/***
col		0
		CODE  f s 
		????    
		JJAM   
		
0		
ABCD    s
????    
????  f

ABCD
ABCD
????
 
 */
			
			// go by cols forward
			for (int col=0; col<cols[i]; col++) {
				for (int first=0, second=1; first < rows[i]; second++) {
					if (second >= rows[i])
						break;
					if (arr[first][col] == '?' && arr[second][col] != '?') {
						while (first < second) {
							arr[first++][col] = arr[second][col];
						}
					}
					if (arr[first][col] != '?')
						first++;
				}
			}
			
			// go by cols backward
			for (int col=0; col<cols[i]; col++) {
				for (int first=rows[i]-1, second=rows[i]-2; first >= 0; second--) {
					if (second < 0)
						break;
					if (arr[first][col] == '?' && arr[second][col] != '?') {
						while (first > second) {
							arr[first--][col] = arr[second][col];
						}
					}
					
					if (arr[first][col] != '?')
						first--;
				}
			}
			
			System.out.println("Case #" + (i+1) + ":");
			printMatrix(arr);
		}
		sc.close();
	}
	
	private static void printMatrix(char[][] arr) {
		for (int i=0; i<arr.length; i++){
			for (int j=0; j<arr[i].length;j++)
				System.out.print(arr[i][j]);
			System.out.println();
		}
	}

}























