package com.booking.hackerrank.challenge.b;

import java.util.*;

public class Solution {

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        
        String wordsString = sc.nextLine();
        wordsString.replaceAll(".", "");
        wordsString.replaceAll(",", "");
        StringTokenizer tok = new StringTokenizer(wordsString, " ");
        HashSet<String> dic = new HashSet<String>();
        while (tok.hasMoreTokens()) {
        	dic.add(tok.nextToken().toLowerCase());
        }
        	
        
        int numReviews = sc.nextInt();
        
        // more than num of hotels, may be can be optimized
        Review[] arr = new Review[numReviews];
        for (int reviewIndx = 0; reviewIndx < numReviews; reviewIndx++) {
        	int id = sc.nextInt();
        	int count = 0;
        	sc.nextLine();
            String sentenceString = sc.nextLine();
            sentenceString.replaceAll(".", "");
            sentenceString.replaceAll(",", "");
            tok = new StringTokenizer(sentenceString, " ");
            while (tok.hasMoreTokens()) {
            	String word = tok.nextToken().toLowerCase();
            	if (dic.contains(word))
            		count++;
            }
            Review review = new Review(id, count);
            arr[reviewIndx++] = review;
        }
        
//        for (Sentence sentence : arr)
//        	System.out.println(sentence.id + " " + sentence.count);
        
        // maps hotel id to total of all reviews counts
        HashMap<Integer, Integer> hotelsMap = new HashMap<Integer, Integer>();
        for (Review review : arr) {
        	if (review != null) {
        		if (!hotelsMap.containsKey(review.id)) {
        			hotelsMap.put(review.id, 0);
        		}
        		int countSoFar = hotelsMap.get(review.id);
        		hotelsMap.put(review.id, countSoFar+review.count);
        	}
        }
        
        arr = new Review[hotelsMap.size()];
        int indx = 0;
        for (int hotelId : hotelsMap.keySet()) {
        	arr[indx++] = new Review(hotelId, hotelsMap.get(hotelId));
        }
        
        Arrays.sort(arr);
        
        StringBuilder result = new StringBuilder();
        for (Review review : arr)
        	result.append(review.id).append(" ");
        result.deleteCharAt(result.length()-1);
        
        System.out.println(result.toString());
        
        sc.close();
	}
	
	private static class Review implements Comparable<Review> {
		
		private Integer count;
		private Integer id; 
		
		Review(int id, int count) {
			this.id = id;
			this.count = count;
		}

		@Override
		public int compareTo(Review o) {
			int comp = this.count.compareTo(o.count);
			if (comp == 0) {
				comp = this.id.compareTo(o.id);
			}
			return comp;
		}
	}

}
