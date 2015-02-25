package com.hackerrank.algorithms.arraysandsorting;

import java.util.Scanner;

public class InsertionSort2 {

public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        int[] arr = new int[arrSize];
        
        for(int i=0; i<arrSize; i++) {
            arr[i] = sc.nextInt();
        }
        
        insertSort(arr); 
        
        sc.close();
        
    }
    
    private static void insertSort(int[] arr)
    {
        for(int size=2; size<=arr.length; size++) {
            insertSort(arr, size);
            printArr(arr);
        }
    }
    
    private static void insertSort(int[] arr, int arrSize)
    {
        int lastIndx = arrSize-1;
        int tmp = arr[lastIndx ];
        
        int i = lastIndx - 1;
        for(i=lastIndx-1; i>=0; i--) {
            if(arr[i] > tmp) {
                arr[i+1] = arr[i];
            }
            else {
                break;
            }
        }
        
        arr[i+1] = tmp;
    }
    
    private static void printArr(int[] arr) 
    {
        for(int i=0; i<arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
