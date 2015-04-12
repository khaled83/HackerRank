package com.indeed.khaledabbas.datastructures.stack;

//import java.util.Stack;
import com.indeed.khaledabbas.datastructures.stack.node.Stack;

public class StackUtility {
	
	public static boolean isBalancedBraces(String s) throws StackException
    {
        boolean isBalancedSoFar = true;
        
        // { abc }}{a
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray())
        {
            if(c == '{')
                stack.push('{');
            else if(c =='}')
            {
                if(!stack.empty())
                    stack.pop();
                else
                {
                    isBalancedSoFar = false;
                    break;
                }
            }
        }
        
        return isBalancedSoFar && stack.empty(); 
    }
	
	public static boolean isPalyndrome(String s) throws StackException {
		Stack<Character> stack = new Stack<Character>();
		int size = s.length();
		int mid = size / 2;
		
		int indx;
		for(indx=0; indx<mid; indx++)
			stack.push(s.charAt(indx));
		// skip middle character
		indx += size%2;
		
		// abbcc
		boolean isPalyndrome = true;
		for(;indx<size; indx++) {
			if(s.charAt(indx) != stack.pop()) {
				isPalyndrome = false;
				break;
			}
		}
		
		return isPalyndrome;
	}
	
	// 1 4 1 2
	
	// 1 4 1 2
	// 		 t
	
	// 2
	// t
	
	/**
	 * @see	StackExtended#sort() 
	 */
	public static Stack<Comparable> sort(Stack<Comparable> stack) throws StackException
	{
		return null;
	}

}
