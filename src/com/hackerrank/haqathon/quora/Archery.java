package com.hackerrank.haqathon.quora;

import java.util.Arrays;
import java.util.Scanner;

public class Archery {

	public static void main(String[] args)
    {
		Scanner sc = new Scanner(System.in);
        int numCircles = sc.nextInt();
        int[] radius = new int[numCircles];
        
        for(int i=0; i<numCircles; i++)
        {
            radius[i] = sc.nextInt();
        }
        
        Arrays.sort(radius);
        
        int numArrows = sc.nextInt();
        
        long numQs = 0;
        int curNumQs = 0;
        
        for(long i=0; i<numArrows; i++)
        {
            Arrow a = Arrow.newInstance(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            int r1 = findRadius( a.x1, a.y1 );
            int r2 = findRadius( a.x2, a.y2 );
            int temp = r1;
            r1 = r1 < r2 ? r1 : r2;
            r2 = temp < r2 ? r2 : temp;
            
            curNumQs = binarySearchCeil( radius, r2) - binarySearchCeil( radius, r1) + 1;
            numQs+= curNumQs > 1 ? curNumQs : 0;
        }
        
        System.out.println(numQs);
        
    }
    
    /** returns index of the first (smallest) value greather than the given key */
    public static int binarySearchCeil(int[] arr, int key)
    {
        int index = Arrays.binarySearch(arr, key);
        if(index < 0)
        {
            return -index;
        }
        else
        {
            while( arr[index] == key && index < arr.length )
            {
                index++;
            }
            return index;
        }
        
    }
    
    public static int findRadius(int x, int y)
    {
        return (int)( Math.pow(x, 2) + Math.pow(y, 2) );
    }
    
    public static class Arrow
    {
        int x1, y1, x2, y2;
        
        public static Arrow newInstance(int x1, int y1, int x2, int y2)
        {
            Arrow a = new Arrow();
            a.x1 = x1;
            a.y1 = y1;
            a.x2 = x2;
            a.y2 = y2;
            
            return a;
        }
    }

}
