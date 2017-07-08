package com.amazon.algorithms;

import java.util.ArrayList;
import java.util.Collections;

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
	        
	    int left = magicIndx(arr, first, Math.min(arr[mid], mid-1));
	    if (left >= 0) {
	        return left;
	    }
	    
	    int right = magicIndx(arr, Math.max(mid+1, arr[mid]), last);
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
	
}
