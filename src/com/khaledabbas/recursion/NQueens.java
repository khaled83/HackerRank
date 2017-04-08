package com.khaledabbas.recursion;

import java.util.*;

public class NQueens {

	public static void main(String[] args) {
		List< List<String> > solutions = solutions(4);
		int indx = 1;
		for( List<String> solution : solutions ) {
			System.out.println("Solution #"+indx++);
			printSolution( solution );
			System.out.println();
		}
	}

	public static List<List<String>> solutions(int n) {

		List<List<String>> solutions = new ArrayList<List<String>>();

		// initialize
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				solutions.add( setQueen(row, col, n) );
			}
		}

		solutions(n, 1, solutions);
		
		List< List<String> > invalidSolutions = new ArrayList< List<String> >();
		for( List<String> solution : solutions ) 
			if( queenPlacementCount( solution ) < n )
				invalidSolutions.add(solution);
		solutions.removeAll(invalidSolutions);

		return solutions;
	}

	public static void solutions(int n, int queen, List<List<String>> solutions) {

		if (queen < n) {
			
			for (List<String> solution : solutions) {
				for (int row = 0; row < n; row++) {
					for (int col = 0; col < n; col++) {
						if (!hasQAttack(row, col, solution)) {
							setQueen(solution, row, col);
							for (int anotherQueen = queen + 1; anotherQueen < n; anotherQueen++)
								solutions(n, anotherQueen, solutions);
						}
					}
				}
			}
		}
	}
	
	private static int queenPlacementCount( List<String> solution ) {
		int count = 0;
		for( String row : solution )
			if( row.indexOf( 'Q' ) >= 0 )
				count++;
		return count;
	}

	private static boolean hasQAttack(int row, int col, List<String> solution) {
		return hasQAtRow(row, solution) || hasQAtCol(col, solution)
				|| hasQAtDiag(row, col, solution);
	}

	private static boolean hasQAtRow(int row, List<String> solution) {
		return solution.get(row).indexOf('Q') >= 0;
	}

	private static boolean hasQAtCol(int col, List<String> solution) {
		for (String row : solution) {
			if (row.indexOf('Q') == col)
				return true;
		}
		
		return false;
	}

	/**
	 * 0 1 2 3 . Q . . 0 . . . Q 1 Q . . . 2 . . Q . 3
	 */
	private static boolean hasQAtDiag(int row, int col, List<String> solution) {

		int n = solution.size();
		int r = row, c = col;
		while (r < n && c < n)
			if (hasQAtCell(r++, c++, solution))
				return true;

		r = row;
		c = col;
		while (r < n && c >= 0)
			if (hasQAtCell(r++, c--, solution))
				return true;

		r = row;
		c = col;
		while (r >= 0 && c < n)
			if (hasQAtCell(r--, c++, solution))
				return true;

		r = row;
		c = col;
		while (r >= 0 && c >= 0)
			if (hasQAtCell(r--, c--, solution))
				return true;

		return false;

	}

	private static boolean hasQAtCell(int row, int col, List<String> solution) {
		return solution.get(row).charAt(col) == 'Q';
	}

	private static void setQueen(List<String> solution, int row, int col) {
		solution.set(row, setCharAt(solution.get(row), col, 'Q'));
	}

	private static List<String> setQueen(int row, int col, int n) {
		List<String> solution = newSolution(n);
		
		setQueen(solution, row, col);
		return solution;
	}
	
	private static List<String> newSolution( int n ) {
		List<String> solution = new ArrayList<String>();
		for( int i=0; i<n; i++ ) {
			solution.add("....");
		}
		return solution;
	}

	private static String setCharAt(String org, int indx, char c) {
		char[] chars = org.toCharArray();
		chars[indx] = c;
		return new String(chars);
	}
	
	private static void printSolution( List<String> solution ) {
		for( String row : solution )
			System.out.println( row );
	}

}
