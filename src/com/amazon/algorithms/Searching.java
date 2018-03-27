package com.amazon.algorithms;

import java.util.*;

public class Searching {
	
	public static int magicIndxDist(int[] arr, int n) {
	    return magicIndxDist(arr, 0, n-1);
	}
	
	public static int magicIndx(int[] arr, int n) {
		return magicIndx(arr, 0, n-1);
	}
	
	/**
	 * magic indx is where the value and index are identical
	 * @param	arr	array of elements with all distinct values
	 */
	private static int magicIndxDist(int[] arr, int first, int last) {
	    
	    if (last < first)
	        return -1;
	    
	    int mid = (first + last) / 2; 
	    if (arr[mid] == mid)
	        return mid;
	    else if (arr[mid] > mid)
	        return magicIndxDist(arr, first, mid-1);
	    else
	        return magicIndxDist(arr, mid+1, last);
	}

	/**
	 * magic indx is where the value and index are identical
	 * @param	arr	array of elements with NON distinct values
	 */
	private static int magicIndx(int[] arr, int first, int last) {
	    
	    if (last < first)
	        return -1;
	    
	    int mid = (first + last) / 2; 
	    if (arr[mid] == mid)
	        return mid;
	        
	    int left = magicIndx(arr, first, java.lang.Math.min(arr[mid], mid-1));
	    if (left >= 0) {
	        return left;
	    }
	    
	    int right = magicIndx(arr, java.lang.Math.max(mid+1, arr[mid]), last);
	    return right;
	}

	
	/**
	 * search a rotated sorted array
	 * @deprecated still buggy
	 */
	public static int searchRotatedArray(int[] arr, int key) {
	    int first = 0;
	    int last = arr.length - 1;
	    return searchRotatedArray(arr, key, first, last);
	}

	private static int searchRotatedArray(int[] arr, int key, int first, int last) {
	    if (last < first)
	        return -1;
	        
	    int mid = (first + last) / 2;
	    if (arr[mid] == key)
	        return mid;
	    
	    // first >> mid    (values increasing between first and mid)
	    if (arr[mid] > arr[first]) {
	        if (key < arr[mid] && key >= arr[first]) {
	            return searchRotatedArray(arr, key, first, mid-1); // search left
	        }
	    }
	    // first >> max >> min >> mid    (all values less than mid are between first and mid)
	    else if (arr[mid] < arr[first]) {
	        if (key < arr[mid]) {
	            return searchRotatedArray(arr, key, first, mid-1); // search left
	        }
	    }    
	    // mid >> last
	    else if (arr[mid] < arr[last]) {
	        if (key > arr[mid] && key <= arr[last]) {
	            return searchRotatedArray(arr, key, mid+1, last); // search right
	        }
	    }
	    // mid >> max >> min >> last
	    else if (arr[mid] > arr[last]) {
	        if (key > arr[mid]) {
	            return searchRotatedArray(arr, key, mid+1, last); // search right
	        }
	    }
	    
        return -1;
	}

	public static class Listy<E extends Comparable<E>> {

	    private ArrayList<E> items = new ArrayList<E>();
	    
	    public void add(E e) {
	        items.add(e);
	        Collections.sort(items);
	    }
	    
	    public E elementAt(int indx) {
	        if (indx >= items.size())
	            return null;
	        
	        return items.get(indx);
	    }

	}

	public static int sortedSearchNoSize(Listy<Integer> list, int key) {

	    int first = 0;
	    // assuming nature of data with almost even distribution of positive integers over the list with each elem appearing once
	    int last = key * 2;
	    
	    return sortedSearchNoSize(list, key, first, last);
	}

	private static int sortedSearchNoSize(Listy<Integer> list, int key, int first, int last) {
	    if (first > last) {
	        return -1;
	    }
	        
	    int mid = (first + last) / 2;
	        
	    if (list.elementAt(mid) == null  // special case: beyond boundary (*1)
	    		|| list.elementAt(mid) > key) // classic BS
	    {
	    	return sortedSearchNoSize(list, key, first, mid - 1);
	    }
	    else if (list.elementAt(mid) == key) { // classic BS
	        return mid;
	    }
	    else { // classic BS
	        // special case: (*2) we need to expand search domain further to the right
	        if (first == last) {
	            last += key; // new binary search
	        }
	        return sortedSearchNoSize(list, key, mid + 1, last);
	    }
	}
	
	/** */
	public static int bsFirstOccurence(int[] arr, int key)  {
	    return bsFirstOccurence(arr, key, 0, arr.length - 1, -1);
	}

	private static int bsFirstOccurence(int[] arr, int key, int first, int last, int res) {
	    int mid = (first + last) / 2;
	    if (arr[mid] == key) {
	        res = mid;
	    }
	    
	    if (first >= last) {
	        return res;
	    }
	    
	    if (arr[mid] >= key) {
	        return bsFirstOccurence(arr, key, first, mid - 1, res);
	    }
	    else {
	        return bsFirstOccurence(arr, key, mid + 1, last, res);
	    }
	}
	
	public static boolean searchSorted(int[][] arr, int key) {
	    // find possible columns
	    int col = binarySearchSmallerOrEqual(arr[0], key);
	    
	    if (arr[0][col] > key) {
	        return false;
	    }
	    else {
	        for (int c = 0; c < col; c++) {
	            // binary search column
	            if (binarySearchColumn(arr, key, c, 0, arr.length - 1) >= 0) {
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}

	private static int binarySearchSmallerOrEqual(int[] arr, int key) {
	    return binarySearchSmallerOrEqual(arr, key, 0, arr.length - 1);     
	}

	private static int binarySearchSmallerOrEqual(int[] arr, int key, int first, int last) {
	    if (first >= last) {
	        return first;
	    }

	    int mid = (first + last) / 2;
	    if (arr[mid] > key) {
	        return binarySearchSmallerOrEqual(arr, key, first, mid - 1);    
	    } else {
	        return binarySearchSmallerOrEqual(arr, key, mid + 1, last);
	    }
	}

	private static int binarySearchColumn(int[][] arr, int key, int col, int first, int last) {
	    if (first > last) {
	        return -1;
	    }
	    
	    int mid = (first + last) / 2;
	    if (arr[mid][col] == key) {
	        return mid;
	    }
	    else if (arr[mid][col] > key) {
	        return binarySearchColumn(arr, key, col, first, mid - 1);
	    }
	    else {
	        return binarySearchColumn(arr, key, col, mid + 1, last);
	    }
	}
	
	public static Interval[] merge(Interval[] arr, Interval x) {
	    int firstIndx = bsStartAtOrBefore(arr, x.start, 0, arr.length - 1);
	    int lastIndx = bsStartAtOrBefore(arr, x.end, 0, arr.length - 1);
	    Interval first = arr[firstIndx];
	    Interval last = arr[lastIndx];
	    
	    Interval[] res = null;
	    
	    if (first.start > x.start) {
	        // add x to the start
	    		res = new Interval[arr.length + 1];
	        System.arraycopy(arr, 0, res, 1, arr.length);
	        res[0] = x;
	    }
	    else if (x.start > last.end) {
	        // add x to the end
	        res = new Interval[arr.length + 1];
	        System.arraycopy(arr, 0, res, 0, arr.length);
	        res[res.length - 1] = x;
	    }
	    else {        
	        res = new Interval[arr.length - (lastIndx - firstIndx)];
	        System.out.println("firstIndx = " + firstIndx + " lastIndx="+lastIndx);
	        System.arraycopy(arr, 0, res, 0, firstIndx + 1);
	        res[firstIndx].end = java.lang.Math.max(last.end, x.end);
	        
	        // shift remaining intervals to the left
	        int src = lastIndx + 1, dst = firstIndx + 1;
	        while (src < arr.length) {
	            res[dst++] = arr[src++];
	        }        
	    }
	    
	    return res;
	}

	public static int bsStartAtOrBefore(Interval[] arr, int key) {
	    return bsStartAtOrBefore(arr, key, 0, arr.length - 1);
	}

	private static int bsStartAtOrBefore(Interval[] arr, int key, int first, int last) {
	    if (first >= last) {
	        return first;
	        // > key ? arr[first - 1] : arr[firs];
	    }
	    
	    int mid = (first + last) / 2;
	    if (key == arr[mid].start) {
	        return mid;
	    }
	    else if (key > arr[mid].start) {
	        return bsStartAtOrBefore(arr, key, mid + 1, last);
	    }
	    else {
	        return bsStartAtOrBefore(arr, key, first, mid - 1);
	    }
	}

	public static class Interval {
	    int start, end;
	    
	    public Interval (int start, int end) {
	    		this.start = start;
	    		this.end = end;
	    }
	    
	    @Override
	    public String toString() {
	    		// TODO Auto-generated method stub
	    		return "[" + this.start + "," + this.end + "]";
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	    		Interval another = null;
	    		try {
	    			another = (Interval) obj;
	    		} catch (ClassCastException e) {
	    			return false;
	    		}
		    	return this.start == another.start && this.end == another.end;
	    }
	}

	public static int[] minMax(int[] arr) {
	    int[] res = new int[2];
	    int min = Integer.MAX_VALUE;
	    int max = Integer.MIN_VALUE;
	    
	    for (int i = 0; i < arr.length; i += 2) {
	        int low = arr[i];
	        int high = arr[i];
	        if (i < arr.length - 1) {
	            high = arr[i + 1];
	            if (low > high) {
	                // swap
	                low = arr[i + 1];
	                high = arr[i];
	            }
	        }
	        
	        min = java.lang.Math.min(min, low);
	        max = java.lang.Math.max(max, high);
	    }
	    
	    res[0] = min;
	    res[1] = max;
	    
	    return res;
	}
	
	public static class Range implements Comparable<Range> {
        
        int start;
        int end;
        
        public Range (int first, int last) {
            this.start = first;
            this.end = last;
        }
        
        public int compareTo(Range r2) {
            int comp = Integer.valueOf(start).compareTo(Integer.valueOf(r2.start));
            if (comp == 0) {
            		comp = Integer.valueOf(end).compareTo(Integer.valueOf(r2.end));
            }
            return comp;
        }
        
        public boolean equals(Range r1, Range r2) {
            return r1.start == r2.start && r1.end == r2.end;
        }
        
        public int hashCode() {
            return Objects.hash(start, end);
        }
        
        @Override
        public String toString() {
	        	return start + " => " + end;
        }
        
    }

    public static class IPReader {

    public int findMissingIp(Iterator<Integer> itr) {
        TreeSet<Range> set = new TreeSet<Range>();
        while (itr.hasNext()) {
            int next = itr.next();
            if (!findMatchingRange(set, next)) {
                set.add(new Searching.Range(next, next));
            }
        }
        
        int cur = 0;
        for (Range r : set) {
        		System.out.println("RANGE: " + r.start + " => " + r.end);
                if (r.start == cur) {
                    cur = r.end + 1;
                } else {
                    return cur;
                }
        }
        
        return -1;
        
    }
    
    // add(6); add(7); add(8); add(4); add(2); add(1); add(3); add(0); 
    
    public boolean findMatchingRange(TreeSet<Range> set, int x) {
    		boolean foundMatch = false;
        Range cur = new Range(x, x);
        // find the first range that starts just before x
        Range floor = set.floor(cur);
        // merge x into range
        if (floor != null && (floor.end > x || floor.end == (x - 1))) {
            floor.end = java.lang.Math.max(floor.end, x);
            foundMatch = true;
        }
        //find the first range that starts just after x
        Range ceiling = set.ceiling(cur);
        // merge range
        if (ceiling != null && ceiling.start == (x + 1)) {
            ceiling.start = x;
            if (floor != null && foundMatch) {
            		ceiling.start = floor.start;
            		set.remove(floor);
            }
            foundMatch = true;
        }
        
        return foundMatch;
    }

}
	
}
