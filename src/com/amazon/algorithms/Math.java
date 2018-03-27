package com.amazon.algorithms;

import java.util.*;

public abstract class Math {

	public static void main(String[] args) {
	}
	
	public static int pow(int x, int y) {
	    if (y == 0) {
	        return 1;
	    } else if (y < 0) {
	        throw new IllegalArgumentException("Negative power is not allowed: " + y);
	    }

	    // pow=1 => val=x    
	    Stack<Integer> powStack = new Stack<Integer>();
	    powStack.push(1);
	    Stack<Integer> valStack = new Stack<Integer>();
	    valStack.push(x);
	    
	    int pow = 2;
	    while (pow < y) {
	        // last calculated power
	        int lastVal = valStack.peek();
	        // calculate last ^ pow, pow = 2 * lastPow
	        int newVal = lastVal * lastVal;
	        valStack.push(newVal);
	        powStack.push(pow);
	        pow = pow << 2;
	    }
	    
	    // pow exceeded y, calculate result going backward
	    // remaining power to reach y
	    int remaining = y; 
	    int res = 1;
	    while (!powStack.isEmpty() & remaining > 0) {
	        pow = powStack.pop();
	        int val = valStack.pop();
	        while (remaining > pow) {
	            remaining -= pow;
	            res = res * val;
	        }
	    }
	    
	    // finish off remaining power lineary
	    while (remaining > 0) {
	        res = res * x;
	        remaining--;
	    }
	    
	    return res;
	}
	
	public static int excelColumnIntValue(String col) {
	    check(col);
	    int res = 0;
	    for (char c : col.toCharArray()) {
	        res = 26 * res + (int) (c - 'A') + 1;
	    }
	    return res;
	}

	private static void check(String col) {
	    for (char c : col.toCharArray()) {
	        check(c);
	    }
	}

	private static void check(char col) {
	    if (col < 'A' || col > 'Z') {
	        throw new IllegalArgumentException("Column must be between A and Z: " + col);
	    }
	}

	public static int[] mul(int[] x, int[] y) {
	    // calc sign
	    int sign = x[0] * y[0];
	    sign = sign < 0 ? -1 : 1;
	    x[0] = java.lang.Math.abs(x[0]);
	    y[0] = java.lang.Math.abs(y[0]);
	    
	    int[] res = new int[x.length + y.length];
	    
	    for (int i = x.length - 1; i >= 0; i--) {
	        for (int j = y.length - 1; j >= 0; j--) {
	            int loc = i + j + 1;
	            res[loc] += x[i] * y[j];
	            // carry over
	            res[loc - 1] += res[loc] / 10;
	            res[loc] = res[loc] % 10;
	        }
	    }
	    
	    res[0] *= sign;
	    return res;
	}
	
	public static boolean isPalyndrome(int x) {
		if (x <= 0) {
			return true;
		}
		
		int rev = 0;
		while (x > 0) {
			rev = 10 * rev + x % 10;
			if (x == rev) {
				return true;
			}
			if (rev == (x = x / 10)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPalyndrome2(int x) {
	    if (x <= 0) {
	        return true;
	    }
	    
	    int mask = (int) java.lang.Math.pow(10, java.lang.Math.floor(java.lang.Math.log10(x)));
	    while (x > 0) {
	        int left = x / mask;
	        int right = x % 10;
	        if (left != right) {
	            return false;
	        }
	        x = x % mask;
	        x = x / 10;
	        mask = mask / 100;
	    }
	    
	    return true;
	}
	
	public static int rand(int n) {
	    int res = 0;
	    // flip coin n times
	    for (int i = 0; i < n; i++) {
	        res = (res | coin()) << 1;
	    }
	    // all 1s
	    int max = (1 << (n + 1)) - 1;
	    // normalise res to 0..n-1
	    res = res / (max / n);
	    return res;
	}

	public static int coin() {
	    return java.lang.Math.random() >= 0.5 ? 1 : 0;
	}
	
	public static class Rectangle {
	    
	    private int startX, startY, endX, endY;
	    
	    // all params constructor
	    public Rectangle(int startX, int startY, int endX, int endY) {
	        this.startX = startX;
	        this.startY = startY;
	        this.endX = endX;
	        this.endY = endY;
	    }
	    
	    public Rectangle findOverlappingRectangle(Rectangle r) {
	        // check for overlap
	        if (!checkOverlaps(r)) {
	            return null;
	        }
	        
	        int startXNew = java.lang.Math.max(this.startX, r.startX);
	        int startYNew = java.lang.Math.max(this.startY, r.startY);
	        int endXNew = java.lang.Math.min(this.endX, r.endX);
	        int endYNew = java.lang.Math.min(this.endY, r.endY);
	        
	        return new Rectangle(startXNew, startYNew, endXNew, endYNew);
	    }
	    
	    public boolean checkOverlaps(Rectangle r) {
	        // overlapping on x axis
	        return r.endX > this.startX && r.startX < this.endX
	        // overlapping on y axis
	                && r.endY > this.startY && r.startY < this.endY;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	    		Rectangle r = (Rectangle) obj;
		    	return this.startX == r.startX
		    			&& this.startY == r.startY
		    			&& this.endX == r.endX
		    			&& this.endY == r.endY;
	    }
	    
	}
	
}

