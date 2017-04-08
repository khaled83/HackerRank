package com.hackerrank.strings;

import java.util.Scanner;

public class Cavity {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        
        for(int top=0; top<n; top++)
        {
            String line = sc.nextLine();
            if(line.trim().length() == 0)
            {
                top--;
                continue;    
            }
            
            char[] chars = line.toCharArray();
            for(int left=0; left<n; left++)
            {
                map[top][left] = (int)(chars[left] - '0');
            }
        }
        
        char[][] output = new char[n][n];
        
        for(int top=0; top<n; top++ )
        {
            for(int left=0; left<n; left++)
            {
                // outside borders: check for cavity
                if( top > 0 && top < (n-1) && left > 0 && left < (n-1) )
                {
                    int cur = map[top][left];
                    if( cur > map[top-1][left]
                        && cur > map[top+1][left]
                        && cur > map[top][left-1]
                        && cur > map[top][left+1]
                      )
                    {
                      output[top][left] = 'X';
                    }
                    else
                    	output[top][left] = (char)(map[top][left] + '0');
                }// border: no cavity
                else
                output[top][left] = (char)(map[top][left] + '0');
            }
        }
        
        for(int top=0; top<n; top++ )
        {
            for(int left=0; left<n; left++)
            {
                System.out.print( output[top][left] );
            }
            System.out.println();
        }
        
        sc.close();
    }
}