package com.amazon.datastructures.hashtable;

import java.util.*;

public class Hashing {
	
	/**

	select *      s1
	insert one    s2
	insert one    
	select *
	select two    s3
	select one
	select *
	select four   s4
	...


	Scan and count => HashMap
	s    key   val (count)
	-    ---   -----
	1    s1    1        
	2    s2    1
	3    s2    2
	4    s1    2
	5    s3    1
	6    s2    3
	7    s1    3
	8    s4    1

	Iterate Map and build K list
	Time: n + O ( n log(k) )
	Key   val    pQ       min    size        ops
	---   ---    -----    ---    ----        ---
	                      -Inf   0
	s4    1      [1]      1      1
	s3    2      [1,2]    1      2 (full)     
	s1    3      [2,3]    2                  del(1) add(2): O (log k)
	s2    3      [3,3]    3                  del(2) add(3)
	*/

	// Time: O(n + n log(k)) => O(n log(k))
	// memory: O(n + k) => O(n)
	public static List<String> mostFrequent(int k, Iterable<String> itr) {

	    HashMap<String, Integer> counts = new HashMap<String, Integer>();
	    // iterates over n elements: O(n) * 2 => O(n)
	    for (String s : itr) {
	        int count = counts.getOrDefault(s, 0); // O(1)
	        counts.put(s, count + 1);  // O(1)
	    }
	    
	    // minimum priority queue, min always appears at top
	    // K is initial capacity since pq commonly is array based
	    PriorityQueue<StringWithCount> pq = new PriorityQueue<StringWithCount>(k, new Comparator<StringWithCount>() {

	        public int compare(StringWithCount o1, StringWithCount o2) {
	            return Integer.valueOf(o1.count).compareTo(Integer.valueOf(o2.count));
	        }
	        
	    });
	    
	    // iterates over n elements: O(n log(k))
	    for (Map.Entry<String, Integer> e : counts.entrySet()) {
	        int min = Integer.MIN_VALUE;
	        if (pq.size() >= k) {
	            min = pq.peek().count; // O(1)
	        }
	        // bottleneck
	        if (pq.size() < k) {
	        	pq.add(new StringWithCount(e.getKey(), e.getValue())); // O(log(k))
	        }
	        else if (!pq.isEmpty() && e.getValue() > min) {
        		pq.remove(); // O(log(k))
	            pq.add(new StringWithCount(e.getKey(), e.getValue())); // O(log(k))
	        }
	    }
	    
	    ArrayList<String> res = new ArrayList<String>();
	    while (!pq.isEmpty()) {
	        res.add(pq.remove().s);
	    }
	    
	    return res;
	}

	private static class StringWithCount  {
	    private String s;
	    private int count;
	    
	    StringWithCount(String s, int count) {
	        this.s = s;
	        this.count = count;
	    }
	}

	/**
	("All", "work", "and", "no", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "results")
	                 *
	  
	loc    word    min    map
	---    ----    ---    ---
	0      all            {"All", }
	1      work           {"All", "work"=>1 }
	2      and            {"All", "work"=>1, "and"}
	3      no             {"All", "work"=>1, "and", "no"=>3 }        
	4      play           {"All", "work"=>1, "and", "no"=>3, "play" }  
	5      makes          {"All", "work"=>1, "and", "no"=>3, "play", "makes" }  
	6      for            {"All", "work"=>1, "and", "no"=>3, "play", "makes", "for" }  
	7      no      4      {"All", "work"=>1, "and", "no"=>7, "play", "makes", "for" }  (dist=4)
	8      work    4      {"All", "work"=>8, "and", "no"=>7, "play", "makes", "for" }  (dist=7)
	9      no      2      {"All", "work"=>8, "and", "no"=>9, "play", "makes", "for" }  (dist=2)
	*/
	/**
	 * returns the distance between closest matching pairs of words
	 * @param arr	array of words that may contain duplicates (matches)
	 */
	public static int closestMatch(String[] arr) {

	    HashMap<String, Integer> words = new HashMap<String, Integer>();
	    int min = Integer.MAX_VALUE;
	    
	    for (int loc = 0; loc < arr.length; loc++) {
	        String s = arr[loc];
	        if (words.containsKey(s)) {
	            int dist = loc - words.get(s);
	            min = Math.min(min, dist);
	        }
	        words.put(s, loc);
	    }

	    return min;
	}
	
}
