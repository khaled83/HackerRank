package com.amazon.datastructures.hashtable;

import java.util.*;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

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
	  
	s    loc    dist    word    MIN    TABLE        
	-    ---    ----    ----    ---    -----
	1    0      x       All     x      All => 0
	2    1      x       work    x      All => 0, work => 1
	3    2      x       and     x      All => 0, work => 1, and => 2
	4    3      x       no      x      All => 0, work => 1, and => 2, no => 3
	5    4      x       play    x      All => 0, work => 1, and => 2, no => 3, play => 4
	     5      x       makes          All => 0, work => 1, and => 2, no => 3, play => 4, makes => 5
	     6      x       for     MAX    All => 0, work => 1, and => 2, no => 3, play => 4, makes => 5, for => 6
	8    7      4       no      4      All => 0, work => 1, and => 2, no => 7, play => 4, makes => 5, for => 6
	9    8      7       work    4      All => 0, work => 1, and => 2, no => 7, play => 4, makes => 5, for => 6      
	10   9      2       no      2      All => 0, work => 1, and => 2, no => 9, play => 4, makes => 5, for => 6        
	11   10     x       fun     2      All => 0, work => 1, and => 2, no => 9, play => 4, makes => 5, for => 6, fun => 10
	12   11     9       and     2      All => 0, work => 1, and => 11, no => 9, play => 4, makes => 5, for => 6, fun => 10
	13   12     3       no      2      All => 0, work => 1, and => 11, no => 9, play => 4, makes => 5, for => 6, fun => 10
	14   13     x       results 2      All => 0, work => 1, and => 11, no => 9, play => 4, makes => 5, for => 6, fun => 10, results => 13
	*/
	/**
	 * returns the distance between closest matching pairs of words
	 * @param arr	array of words that may contain duplicates (matches)
	 */
	public static int closestMatch(String[] arr) {
	    HashMap<String, Integer> lastLoc = new HashMap<String, Integer>();
	    int min = Integer.MAX_VALUE;
	    for (int loc = 0; loc < arr.length; loc++) {
	        String word = arr[loc];
	        if (lastLoc.containsKey(word)) {
	            int dist = loc - lastLoc.get(word);
	            min = Math.min(min, dist);
	        }
	        lastLoc.put(word, loc);
	    }
	    return min;
	}
	
	public static int smallestSubarray(String[] pg, String[] keywords) {
//		ArrayUtils.printArray(pg);
//		ArrayUtils.printArray(keywords);
	    String first = keywords[0];
	    int from = linearSearch(pg, first, 0);
	    // stores the location for each keyword found so far
	    HashSet<String> foundSoFar;
	    int min = Integer.MAX_VALUE;
	    // repeat algorithm starting from each occurence of first word
//	    System.out.println("from=" + from);
	    while (from >= 0) {
	        int targetIndx = 1;
	        foundSoFar = new HashSet<String>(2 * keywords.length);
	        foundSoFar.add(first);
	        
	        int distance = Integer.MAX_VALUE;
	        for (int indx = from + 1; indx < pg.length; indx++) {
	            if (pg[indx].equals(keywords[targetIndx])) {
	                foundSoFar.add(keywords[targetIndx]);
	                targetIndx++;
	                if (targetIndx == keywords.length) {
	                    distance = indx - from + 1;
	                    break; // terminate for loop
	                }
	            }
	        }
//	        System.out.println("dist="+distance);
	        if (foundSoFar.size() == keywords.length) {
	            min = Math.min(distance, min);
	        }
	        
	        from = linearSearch(pg, first + 1, 0);
//	        System.out.println("from=" + from);
	    }
	    
	    return min;
	}

	private static int linearSearch(String[] arr, String key, int from) {
	    for (int indx = from; indx < arr.length; indx++) {
	        if (arr[indx].equals(key)) {
	            return indx;
	        }
	    }
	    return -1;
	}

	// 'f', 's', 'f', 'e', 't', 'w', 'e', 'n', 'w', 'e', 't', 'f', 's'
	// 			 
	public static int longestDistinct(char[] arr) {
	    HashMap<Character, Integer> charLoc = new HashMap<Character, Integer>();
	    int max = Integer.MIN_VALUE;
	    int n = arr.length;
	    int first = 0, last = 0;
	    for (char c : arr) {
	    	int lastLoc = charLoc.getOrDefault(c, -1);
	        if (lastLoc >= first) {
	           int dist = last - first;
	           max = Math.max(max, dist);
	           // advance first one step past duplicate character
	           first = charLoc.get(c) + 1;
	        }
	        charLoc.put(c, last++);
	    }
	    
	    // compare last distinct path up to end of array
	    int dist = last - first;
	    max = Math.max(max, dist);
	    
	    return max;
	}

	public static int longestDistinct2(char[] arr) {
	    Map<Character, Integer> loc = new HashMap<Character, Integer>(arr.length);
	    // max value so far
	    int max = Integer.MIN_VALUE;
	    // unique distance so far
	    int d = 0;
	    // first element location for current unique range
	    int first = 0;
	    for (int i = 0; i < arr.length; i++) {
	    		char c = arr[i];
	        if (loc.containsKey(c) && loc.get(c) >= first) {
	            first = loc.get(c) + 1;
	        }
	        d = i - first + 1;
	        max = Math.max(max, d);
	        loc.put(c, i);
	    }
	    return max;
	}

	public static int longestDistinct3(char[] arr) {
	    HashMap<Character, Integer> lastIndx = new HashMap<Character, Integer>(arr.length);
	    int max = Integer.MIN_VALUE;
	    int first = 0;
	    for (int i = 0; i < arr.length; i++) {
	        char x = arr[i];
	        int indx = lastIndx.getOrDefault(x, -1);
	        if (indx >= first) {
	            first = indx + 1;
	        }
	        lastIndx.put(x, i);
	        max = Math.max(max, i - first + 1);
	    }
	    return max;
	}

	public static class Log {

	    PriorityQueue<Record> pq = new PriorityQueue<Record>();
	    HashMap<Character, Integer> h = new HashMap<Character, Integer>();
	    
	    // sort list every time kMost is called
	    // read: O(1)
	    // kMost => (1) sort list: O(n log(n)) (2) Get top k items
	    // no efficient way to find the previous records with same id
	    //ArrayList<Record> records = new ArrayList<Record>();

	    public void read(char id) {
	        int count = h.getOrDefault(id, 0);
	        h.put(id, ++count);
	    }
	    
	    public Iterable<Record> kMost(int k) {
	        pq = new PriorityQueue<Record>();
	        // O(u log(k))
	        for (Character id : h.keySet()) {
	        		if (pq.size() < k) {
	        			pq.add(new Record(id, h.get(id)));
	        		}
	        		else if (h.get(id).compareTo(pq.peek().getCount()) > 0) {
	                pq.poll();
	                pq.add(new Record(id, h.get(id)));
	            }
	        }
	        return pq;
	    }

	}

	public static class Record implements Comparable<Record> {
	    private char id; 
	    private int count;
	    
	    public char getId() { return id; }
	    
	    public int getCount() { return count; }
	    
	    public Record(char id, int count) {
	        this.id = id;
	        this.count = count;
	    }
	    
	    public int compareTo(Record obj) {
	        return Integer.valueOf(count).compareTo(Integer.valueOf(obj.count));
	    }
	
	    @Override
	    public String toString() {
	    		return "(" + id + "," + count + ")";	
	    }
	    
	}
	
	/** O(n log(n)) */
	public static int maxContainedInterval(int[] arr) {
	    // Step 1: sort array O(n log(n))
	    java.util.Arrays.sort(arr);
	    // Step 2: calc longest contained range
	    int lengthSoFar = 1;
	    int max = 1;  
	    for (int i = 1; i < arr.length; i++) {
	        int diff = arr[i] - arr[i - 1];
	        if (diff <= 1 && diff >= 0) {
	        		lengthSoFar++;
	        }
	        else {
	            max = Math.max(max, lengthSoFar);
	            lengthSoFar = 1;
	        }
	    }    
	    
	    // calculate last interval
	    max = Math.max(max, lengthSoFar);
	    
	    return max;
	}
	
	/** O(n) */
	public static int maxContainedInterval2(int[] arr) {
	    // initialize hash
	    HashSet<Integer> h = new HashSet<Integer>();
	    for (int x : arr) {
	        h.add(x);
	    }
	    // calc max
	    int max = Integer.MIN_VALUE;
	    for (int x : arr) {
	        int k = x;
	        int length = 0;
	        // serach left
	        while (h.contains(k)){
	            length++;
	            h.remove(k);
	            k--;
	        }
	        // search right
	        k = x + 1;
	        while (h.contains(k)){
	            length++;
	            h.remove(k);
	            k++;
	        }
	        max = Math.max(max, length);
	    }
	    return max;
	}
	
	public static String maxScore(String[] ids, int[] scores, final int NUM_SCORES) {
	    HashMap<String, PriorityQueue<Integer>> h = new HashMap<String, PriorityQueue<Integer>>();
	    
	    // store top 3 scores for each student
	    for (int i = 0; i < ids.length; i++) {
	        String id = ids[i];
	        int score = scores[i];
	        PriorityQueue<Integer> pq = h.getOrDefault(id, new PriorityQueue<Integer>());
	        if (pq.size() < NUM_SCORES) {
	            pq.add(score);    
	        }
	        else if (score > pq.peek()) {
	            pq.poll();
	            pq.add(score);
	        }
	        h.put(id, pq);
	    }
	    
	    // scan and calculate max average over top 3 scores
	    int max = Integer.MIN_VALUE;
	    String maxId = null;
	    for (String id : h.keySet()) {
	    		PriorityQueue<Integer> pq = h.get(id);
	    		System.out.println(id + "=>"+pq);
	    		int avg = 0;
	        if (pq.size() == NUM_SCORES) {
	            for (int score : pq) {
	                avg += score;
	            }
	            avg = avg / NUM_SCORES;
	        }
	        if (avg > max) {
	            max = avg;
	            maxId = id;
	        }
	    }
	    System.out.println("maxAvg="+max);
	    
	    return maxId;
	}
	
	// techdevguide.withgoogle: find-longest-word-in-dictionary-that-subsequence-of-given-string
	public static int longestWord(String s, String[] D) {
	    // construct hash table
	    HashMap<Character, Queue<Integer>> h = new HashMap<Character, Queue<Integer>>();
	    for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        Queue<Integer> q = h.getOrDefault(c, new LinkedList<Integer>());
	        q.add(i);
	        h.put(c, q);
	    }
	    
	    // check all words in dictionary and find lontest
	    int max = Integer.MIN_VALUE;
	    outerloop:
	    for (String w : D) {
	        HashMap<Character, Queue<Integer>> charIndx = new HashMap<Character, Queue<Integer>>();
	        for (char c : h.keySet()) {
		        	Queue<Integer> q = new LinkedList<Integer>();
	        		for (int x : h.get(c)) {
	        			q.add(x);
	        		}
	        		charIndx.put(c, q);
	        }
	        
	        // indx of last character found in string S
	        int lastIndx = -1;
	        for (char c : w.toCharArray()) {
	            if (charIndx.containsKey(c)) {
	                int indxInS = charIndx.get(c).poll();
	                if (indxInS < lastIndx) {
	                    // invalid word, jump to the next
	                    break outerloop;
	                } else {
	                    lastIndx = indxInS;
	                }
	            }
	        }
	        max = Math.max(max, w.length());
	    }
	    return max;
	}

	public static List<Integer> computeDecompositions(String sen, List<String> words) {
	    List<String> permutations = computePermutations(words);
	    // construct map from first character to list of permutations
	    HashMap<Character, HashSet<String>> firstCharPerms = new HashMap<Character, HashSet<String>>();
	    for (String perm : permutations) {
	        char firstChar = perm.charAt(0);
	        HashSet<String> permList = firstCharPerms.getOrDefault(firstChar, new HashSet<String>());
	        permList.add(perm);
	        firstCharPerms.put(firstChar, permList);
	    }
	    // scan through sentence and find matchng substring
	    List<Integer> res = new ArrayList<Integer>();
	    int tokenLen = words.get(0).length();
	    int concatLen = words.size() * tokenLen;
	    for (int i = 0; i < sen.length() - concatLen + 1; i++) {
	        char firstChar = sen.charAt(i);
	        String concat = sen.substring(i, i + concatLen);
	        if (firstCharPerms.containsKey(firstChar) && firstCharPerms.get(firstChar).contains(concat)) {
	            res.add(i);
	        }
	    }
	    return res;
	}

	private static List<String> computePermutations(List<String> words) {
	    return computePermutations(words, 0);
	}

	private static List<String> computePermutations(List<String> words, int first) {
	    List<String> res = new ArrayList<String>();
	    if (first == words.size() - 1) {
	        res.add(words.get(first));
	        return res;
	    }
	    
	    String cur = words.get(first);
	    char[] src = cur.toCharArray();
	    for (String perm : computePermutations(words, first + 1)) {
	        // perm has size $(size - 1), concat has size $(size)
	        int size = cur.length() + perm.length();
	        
	        for (int i = 0; i < size; i += cur.length()) {
	        		char[] chars = new char[size];
	        		System.arraycopy(perm.toCharArray(), 0, chars, 0, i);
	        		System.arraycopy(src, 0, chars, i, cur.length());
	        		System.arraycopy(perm.toCharArray(), i, chars, i + cur.length(), size - i - cur.length());
	            res.add(new String(chars));
	        }
	    }
	    
	    return res;
	}
	
	public static List<TreeSet<Integer>> sum4(int[] arr, int target) {
	    // stores a number with its index
	    HashMap<Integer, TreeSet<Integer>> h = new HashMap<Integer, TreeSet<Integer>>();
	    for (int i = 0; i < arr.length; i++) {
	    		TreeSet<Integer> set = new TreeSet<Integer>();
	    		set.add(i);
	        h.put(arr[i], set);
	    }
	    return sum(arr, 4, arr.length - 1, target, h);
	}

	private static List<TreeSet<Integer>> sum(int[] arr, int n, int lastIndex, int target, HashMap<Integer, TreeSet<Integer>> h) {
	    List<TreeSet<Integer>> res = new ArrayList<TreeSet<Integer>>();
	    // looking for 1 number equal to target
	    if (n == 1) {
	        // finds locations that are less than or equal to lastIndx
	        if (h.containsKey(target)) {
	            res.add((TreeSet<Integer>) h.get(target).headSet(lastIndex, true)); // TODO null check
	        }    
	        return res;
	    }
	    
	    for (int i = n - 1; i < arr.length; i++) {
	        int x = arr[i];
	        for (TreeSet<Integer> sublist : sum(arr, n - 1, i - 1, target - x, h)) {
	        		TreeSet<Integer> list = new TreeSet<Integer>();
	            list.addAll(sublist);
	            list.add(x);
	            res.add(list);
	        }
	    }
	    return res;
	}
	
}


