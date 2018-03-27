package com.amazon.algorithms;

import java.util.*;

public class Combinatorics {
	
	public static void main(String[] args) {
		String in = "ABC";
		for (String s : perm(in))
			System.out.println(s);
		
		System.out.println("PERMUTATION 2:");
		for (String s : perm2(in))
			System.out.println(s);
	}
	
	public static List<String> perm(String s) {
	    return perm(s.toCharArray(), 0, s.length() - 1);
	}


	private static List<String> perm(char[] arr, int first, int last) {
	    System.out.println("perm first="+first+" last="+last);
	    List<String> res = new ArrayList<String>();
	    
	    if (first >= arr.length) {
	        return res;
	    }
	    
	    if (first == last) {
	        res.add(arr[first] + "");
	        return res;
	    }

	    char firstChar = arr[first];   
	    
	    List<String> permList = perm(arr, first+1, last);
	    for (String perm : permList) {
	        for (int loc = 0; loc < perm.length()+1; loc++) {
	            int src = 0, dst = 0;
	            char[] newPerm = new char[perm.length() + 1];
	            while (src < loc) {
	                newPerm[dst++] = perm.charAt(src++);
	            }
	            newPerm[dst++] = firstChar;
	            while (src < perm.length()) {
	                newPerm[dst++] = perm.charAt(src++);
	            }
	            //String left = perm.substring(0, loc);
	            //String right = perm.substring(loc + 1, perm.length());
	            //res.add(left + firstChar + right);
	            res.add(new String(newPerm));
	        }
	    }
	    
	    System.out.println(res);
	    return res;
	}
	
	public static List<String> perm2(String s) {
	    return perm2(s, 0, s.length() - 1);
	}

	private static List<String> perm2(String s, int first, int last) {
	    List<String> res = new ArrayList<String>();
	    if (first == last) {
	        res.add(s.charAt(first) + "");
	        return res;
	    }
	    
	    char c = s.charAt(first);
	    for (String subset : perm2(s, first + 1, last)) {
	        int length = last - first + 1;
	        // position c into different locations withing subset
	        for (int i = 0; i < length; i++) {
	            String set = subset.substring(0, i) + c + subset.substring(i, length - 1);
	            res.add(set);
	        }
	    }
	    return res;
	}
	
	public static List<String> permOnline(String s) {
	    char[] arr = s.toCharArray();
	    return permOnline(arr, 0, arr.length);
	}

	// O (n^2) time complexity
	private static List<String> permOnline(char[] arr, int first, int last) {
		System.out.println(new String(arr, first, last));
	    List<String> res = new ArrayList<String>();

	    if (first == last) {
	        char c = arr[first];
	        res.add(c + "");
	        System.out.println(res);
	        return res;
	    }
	    
	    if (arr.length == 0)
	    	return new ArrayList<String>();
	    
	    char firstChar = arr[first];

	        
	    // generate string with all chars except first
	    StringBuilder sb = new StringBuilder();
	    sb.append(new String(arr, 0, first));
	    if (first >= 0 && first < arr.length)
	    	System.out.println("first="+first+ " last="+last);
	    if (last < arr.length)
	    	sb.append(new String(arr, first+1, last+1));
	    List<String> perm = perm(sb.toString().toCharArray(), 0, last-1);
	    
	    /**     
	              L
	            F R
	            B C
	            (BC, CB)
	            
	            
	               01          
	            A (BC, CB)
	            
	            
	            012
	       s    perm         first    res
	       -    ---          ----     ---  
	                                   dst
	            src                    012
	            B   C         A       [   ]
	            
	            0 1 2  3
	            B C D  (A)       A
	            
	            0 1 2   3
	            B C (A) D    
	            
	            0 1 2
	            B C D
	            
	            * [ ]
	            A B C
	            n, (n-1), (n-2) ...
	                
	            
	    */
	    
	    char[] s = new char[last-first+1];
	    System.out.println("last="+last);
	    
	    for (String permStr : perm) {
	        for (int loc = 0; loc < last; loc++) {
	            String left = permStr.substring(0, loc);
	            String right = permStr.substring(loc, last-1);
	            res.add(left + firstChar + right);
	        }
	    }
	    
	    System.out.println(res);
	    
	    return res;
	}

	public static List<int[]> perm(int[] arr) {
	    return perm(arr, 0, arr.length - 1);
	}

	/**
	       0 1 2
	arr    2 3 5

	f    l    partCombi                 loc    combi                                res
	-    -    ---------                 ---    -----                                ---
	0    2    [3,5]                     0      [2,3,5]                              {[2,3,5],}
	0    2    [3,5]                     1      [3,2,5]                              {[2,3,5],[3,2,5] }
	0    2    [3,5]                     2      [5,3,2]                              {[2,3,5],[3,2,5],[5,3,2]}
	0    2    [5,3]                     0      [2,5,3]                              {[2,3,5],[3,2,5],[5,3,2], [2,5,3], [5,2,3]}
	0    2    [5,3]                     1      [5,2,3]                              {[2,3,5],[3,2,5],[5,3,2], [2,5,3], [5,2,3]}
	0    2    [5,3]                     2      [3,5,2]                              {[2,3,5],[3,2,5],[5,3,2], [2,5,3],[5,2,3],[3,5,2]}
	1    2    [5]                       0      [3,5]                                {[3,5]}
	          [5]                       1      [5,3]                                {[3,5], [5,3]}
	2    2                                     [5]                                  {[5]}    (1)

	Complexity =====
	n=1    => 1
	n=2    => 2
	n=3    => 2  x n = 2 x 3 = 6
	n=4    => 6  x n = 6 x 4 = 24
	n=5    => 24 x n = 24 x 5 = 120

	Time: O(n!)
	Space: O(n n!)
	*/
	private static List<int[]> perm(int[] arr, int first, int last) {
	    List<int[]> res = new ArrayList<int[]>();
	    if (first == last) {
	        int[] combi = new int[1];
	        combi[0] = arr[first];
	        res.add(combi);
	    } 
	    else {
	        // get the permutations of elements after first
	        List<int[]> partialCombis = perm(arr, first + 1, last);
	        // merge first into permutations of sub by placing first into all possible locations
	        for (int[] partialCombi : partialCombis) {
	            // new combi size
	            int n = last - first + 1;
	            // create one new combi for each different location
	            for (int loc = 0; loc < n; loc++) {
	                // combi is one element larger than partial combi
	                int[] combi = new int[n];
	                System.arraycopy(partialCombi, 0, combi, 1, n - 1);
	                combi[0] = combi[loc];
	                combi[loc] = arr[first];
	                res.add(combi);
	            }            
	        }
	    }
	    return res;
	}
	
}
