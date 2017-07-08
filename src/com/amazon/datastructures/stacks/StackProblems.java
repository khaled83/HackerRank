package com.amazon.datastructures.stacks;

import java.util.*;
import java.util.Stack;

public abstract class StackProblems {

	
	/***

in	[()[]{()()}]
	    *
	[({[]{}
	
st	[({[

		  11
012345678901
[()[]{()()}]

s	loc		cur		stack
-	---		---		-----
1	0		[		[
2	1		(		[(
3	2		)		[
4	3		[		[[
	4		]		[
	5		{		[{
	6		(		[{(
	7		)		[{
	8		(		[{(
	9		)		[{
	10		}		[
	11		]		-
	 */
	// Time: O(n) Space: O(1), n: number of characters
	public static boolean isWellFormed(String str) {
		Stack<Character> s = new Stack<Character>();
		HashMap<Character, Character> match = new HashMap<Character, Character>();
		match.put(')', '(');
		match.put(']', '[');
		match.put('}', '{');
		
		for (char c : str.toCharArray()) {
			// opening bracket
			if ("[{(".contains(Character.toString(c))) {
				s.push(c);
			} else if ("})]".contains(Character.toString(c))) {
				if (match.get(c) != s.pop()) {
					return false;
				}
			}
		}
		
		return s.isEmpty();
	}
	
}
