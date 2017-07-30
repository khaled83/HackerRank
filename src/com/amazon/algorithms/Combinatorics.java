package com.amazon.algorithms;

import java.util.*;

public class Combinatorics {
	
	public static void main(String[] args) {
		String in = "ABC";
		for (String s : perm(in))
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

	/**
	(()) ()()
	(())) (()()) (())() ()(()) ()()()

	1 => ()
	2 => ()()              (())
	3 => (()())  ()()()    ((()))    (())()    ()(())

	1 => 1    ~1
	2 => 2    ~3
	3 => 5    ~9
	4 => 13   ~27

	n * 3^(n - 1) => n 3^n
	
	[((())), ()(()), (())(), (()()), ()()(), ()()(), (()()), ()()(), ()()()]
	
	*/
	public static List<String> generateParen(int n) {
	    return generateParen(n, new HashSet<String>());
	}

	/**
	n    sub          sym            s       res
	-    ---          ---            -       ---
	3    (())         (),()()        ((()))  [((())),]
	                                 ()(())  [((())),()(())]
	                                 (())()  [((())),()(()),(())()]
	     ()()         (),()()        (()())  [((())),()(()),(())(),(()())]
	                  (),()(),()()() ()()()  [((())),()(()),(())(),(()()),()()()]
	         
	2    ()           ()             (())    [(()),]
	                  (),()()        ()()    [(()),()(),]
	1    [()]                        [(),]

	*/

	private static List<String> generateParen(int n, HashSet<String> sym) {
	    List<String> res = new ArrayList<String>();
	    if (n == 0) {
	        return res;
	    }
	    else if (n == 1) {
	        String s = "()";
	        res.add(s);
	        sym.add(s);
	        return res;
	    }
	        
	    for (String subset : generateParen(n - 1, sym)) {
	        // surround subset with paren: "(${subset})"
	        String s = "(" + subset + ")";
	        res.add(s);
	        // concatenate from left and right: "()${subset}" "${subset}()"
	        s = "()" + subset;
	        res.add(s);
	        // if string is not summetrical, concatenation from right gives another distinct result
	        if (!sym.contains(subset)) {
	            s = subset + "()";
	            res.add(s);
	        }
	        else {
	            sym.add(s);
	        }
	    }
	    
	    return res;
	}
	
}
