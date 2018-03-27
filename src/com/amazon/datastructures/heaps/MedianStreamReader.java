package com.amazon.datastructures.heaps;

import java.util.*;

public class MedianStreamReader {
	
    PriorityQueue<Float> low;
    PriorityQueue<Float> high;
    
    private float median = 0;
    private List<Float> medians;
    boolean isOdd = false;
    
    public MedianStreamReader() {
        low  = new PriorityQueue<Float>(Collections.reverseOrder());
        high = new PriorityQueue<Float>();
        low.add(Float.MIN_VALUE);
        high.add(Float.MAX_VALUE);
        medians = new ArrayList<Float>();
    }
    
    public List<Float> read(float x) {
    		low.add(x);
    		
        if (low.size() > (high.size() + 1)) {
        		high.add(low.poll());
        }
        
        if (low.peek() > high.peek()) {
	        	high.add(low.poll());
        		low.add(high.poll());
        }
        
        float median = low.peek();
        if (low.size() == high.size()) {
            median = (low.peek() + high.peek()) / 2;
        }
        medians.add(median);
        return medians;
    }
}
