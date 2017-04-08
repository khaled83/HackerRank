package com.hackerrank.warmup;

import java.util.*;

public class LonelyInteger {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        BitSet bset = new BitSet(101);
        for(int i=0; i<n; i++) {
            bset.flip( sc.nextInt() );
        }
        sc.close();
        
        for(int i=0; i<101; i++) {
            if(bset.get(i)) {
                System.out.println(i);
                return;
            }
            
        }
        
    }

}
