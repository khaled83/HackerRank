package com.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

import sun.awt.dnd.SunDragSourceContextPeer;

public class Test {
	
	public static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> arr, int target) {
        ArrayList<ArrayList<Integer>> fourSums = new ArrayList<ArrayList<Integer>>();
        HashSet<ArrayList<Integer>> fourSumsSet = new HashSet<ArrayList<Integer>>();
        fourSum(arr, target, 0, arr.size(), fourSumsSet);
        for (ArrayList<Integer> res : fourSumsSet) {
        	fourSums.add(res);
        }
        Collections.sort(fourSums, new Comparator<ArrayList<Integer>>(){
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				for (int i = 0; i < o1.size(); i++) {
					int comp = o1.get(i).compareTo(o2.get(i));
					if (comp != 0) {
						return comp;
					}
				}
				return 0;
			}
			
		});
        return fourSums;
    }
    
    private static HashSet<ArrayList<Integer>> fourSum(ArrayList<Integer> arr, int target, int cur, int n, HashSet<ArrayList<Integer>> fourSums) {
    	HashSet<ArrayList<Integer>> res = new HashSet<ArrayList<Integer>>();
        if (n == 0) {
            return res;
        }
        
        int first = arr.get(cur);
        ArrayList<Integer> set = new ArrayList<Integer>();
        set.add(first);
        res.add(set);
        for (ArrayList<Integer> subset : fourSum(arr, target, cur + 1, n - 1, fourSums)) {
        	res.add(subset);
            set = new ArrayList<Integer>();
            set.add(first);
            set.addAll(subset);
            if (set.size() == 4) {
                int sum = 0;
                for (int x : set) {
                    sum += x;
                }
                if (sum == target) {
                    Collections.sort(set);
                    if (!fourSums.contains(set)) {
                    	fourSums.add(set);
                    }
                }
            }
            else if (!res.contains(set)) {
                res.add(set);
            }
        }
        return res;
    }
    
    

	public static void main(String[] args) {
		// [ 23, 20, 0, 21, 3, 38, 35, -6, 2, 5, 4, 21 ]
		ArrayList<Integer> in = new ArrayList<Integer>();
		in.add(23);
		in.add(20);
		in.add(0);
		in.add(21);
		in.add(3);
		in.add(38);
		in.add(35);
		in.add(-6);
		in.add(2);
		in.add(5);
		in.add(4);
		in.add(21);
		System.out.println(fourSum(in, 29));
		in.hashCode();
		
//		System.out.println(Long.toBinaryString(1 << 30));
//		System.out.println(Long.toBinaryString(1 << 31));
//		
//		int[] arr = {9,11,13,25,27,31,42,69,88,99};
//		System.out.println(Arrays.binarySearch(arr, 89));
//		
//		BitSet bset = new BitSet(32);
//		System.out.println(bset.toString());
//		bset.set(0, 30);
//		System.out.println(bset.toString());
//		System.out.println();
//		for(int i=0;i<32;i++)
//			System.out.print(i%10);
//		System.out.println();
//		System.out.println(Integer.toBinaryString( Integer.MAX_VALUE ));
//		System.out.println(Integer.toBinaryString( Integer.MIN_VALUE ));
//		
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
//		Collections.sort(res, new Comparator<ArrayList<Integer>>(){
//			@Override
//			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
//				for (int i = 0; i < o1.size(); i++) {
//					int comp = o1.get(i).compareTo(o2.get(i));
//					if (comp != 0) {
//						return comp;
//					}
//				}
//				return 0;
//			}
//			
//		});
//		
//		new ArrayList<Integer>().equals(null);
	}
	
	

}
