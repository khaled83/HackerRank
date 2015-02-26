package com.hackerrank.search;

import java.util.*;

public class Median {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int pivot = 0;
        int mid = n / 2;
        int swap = 0;
        
        do {
            swap(arr, 0, swap);
            pivot = findPivotIndx(arr);
            swap++;
        } while( pivot != mid );
        
        System.out.println(arr[0]);
        
        sc.close();
    }
    
    private static void swap(int[] arr, int indx1, int indx2) {
        int tmp = arr[indx1];
        arr[indx1] = arr[indx2];
        arr[indx2] = tmp;
    }
    
    static int partition(int[] ar) {
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();

        int p = ar[0];
        for(int i=1; i<ar.length; i++) {
            if(ar[i] < p)
                left.add(ar[i]);
            else
                right.add(ar[i]);
        }

        int i;
        for(i=0; i<left.size(); i++)
            ar[i] = left.get(i);

        int pIndx = i;
        ar[pIndx] = p;

        for( int arIndx=pIndx+1, listIndx=0; listIndx<right.size(); arIndx++, listIndx++)
            ar[arIndx] = right.get(listIndx);
        
        return pIndx;

    }  
    
    static int findPivotIndx(int[] ar) {
        int left = -1;

        int p = ar[0];
        for(int i=1; i<ar.length; i++)
            if(ar[i] < p)
                left++;

        return left+1;

    }  

}
