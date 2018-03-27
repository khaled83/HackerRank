package com.amazon;

public class Utils<T extends Comparable> {
	
	public boolean forallGreaterThan(T key, T ... a) {
        for (T x : a) {
            if (x.compareTo(key) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean forallSmallerThan(T key, T ... a) {
        for (T x : a) {
            if (x.compareTo(key) > 0) {
                return false;
            }
        }
        return true;
    }

}
