package crossover;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tester {
	
	/**
     * Complete the function below.
     * DO NOT MODIFY anything outside this method. 
     */
    static boolean[] twins(String[] a, String[] b) {
    		int n = a.length;
        boolean[] result = new boolean[n];
        
        for (int i = 0; i < n; i++) {
        		// stores number of even characters in a
        		Map<Character, Integer> even1 = new HashMap<Character, Integer>();
        		// stores number of even characters in b
        		Map<Character, Integer> even2 = new HashMap<Character, Integer>();
        		// stores number of odd characters in a
        		Map<Character, Integer> odd1 = new HashMap<Character, Integer>();
        		// stores number of odd characters in b
        		Map<Character, Integer> odd2 = new HashMap<Character, Integer>();
        		
        		String s1 = a[i];
        		String s2 = b[i];
        		
        		int length = s1.length();
        		if (length != s2.length()) {
        			result[i] = false;
        			break;
        		}
        		
        		// construct two hash tables with count of even and odd chars in each string
        		for (int j = 0; j < length; j++) {
        			// string a
        			char c1 = s1.charAt(j);
        			// string b
        			char c2 = s2.charAt(j);
        			if (j % 2 == 0) {
        				int count1 = even1.getOrDefault(c1, 0);
        				int count2 = even2.getOrDefault(c2, 0);
        				even1.put(c1, ++count1);
        				even2.put(c2, ++count2);
        			}
        			else {
        				int count1 = odd1.getOrDefault(c1, 0);
        				int count2 = odd2.getOrDefault(c2, 0);
        				odd1.put(c1, ++count1);
        				odd2.put(c2, ++count2);
        			}
        		}
        		
        		// assume its solvable unless proven otherwise
        		result[i] = true;
        		// compare counts of even chars in both strings
        		for (char c : even1.keySet()) {
        			int count1 = even1.get(c);
        			int count2 = even2.getOrDefault(c, 0);
        			if (count1 != count2) {
        				result[i] = false;
        				break;
        			}
        		}
        		
        		// compare counts of odd chars in both strings
        		if (result[i]) {
        			for (char c : odd1.keySet()) {
            			int count1 = odd1.get(c);
            			int count2 = odd2.getOrDefault(c, 0);
            			if (count1 != count2) {
            				result[i] = false;
            				break;
            			}
            		}
        		}
        }
        
        return result;
    }

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        int n = Integer.parseInt(in.nextLine().trim());
        String[] a = new String[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextLine();
        }
        
        int m = Integer.parseInt(in.nextLine().trim());
        String[] b = new String[m];
        for(int i = 0; i < m; i++) {
            b[i] = in.nextLine();
        }
        
        // call twins function
        boolean[] results = twins(a, b);
        
        for(int i = 0; i < results.length; i++) {
            System.out.println(results[i]? "Yes" : "No");
        }
    }

}
