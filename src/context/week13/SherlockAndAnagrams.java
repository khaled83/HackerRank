package context.week13;

import java.util.*;

public class SherlockAndAnagrams {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        String s;
        int totalAnagrams = 0;
        
        for(int caseNum=1; caseNum<=numCases; caseNum++)
        {
            s = sc.next();
            char[] sArr = s.toCharArray();  
            
            /**
             * abba
             * 0123
             * 
             * subStrLength
             * 1 .. 4
             * 
             * subStrLength
             * 2
             * 
             * i
             * 0 .. 2
             * 
             * 
             * subStrAscii.length=3
             * 
             * k
             * 0 1 2
             * 
             * j(min)
             * 0 1 2
             * 
             * j(max)
             * 2 3 4
             * 
             *  
             */
            
            for(int i=0; i<sArr.length;i++)
            	System.out.print(sArr[i] + ",");
            
            for(int subStrLength = 1; subStrLength < s.length(); subStrLength++)
            {
                int numSubStrings = sArr.length - subStrLength + 1;
                int[] subStrAscii = new int[numSubStrings];
                for(int i=0; i<subStrAscii.length;i++)
                	subStrAscii[i] = 0;
                
                System.out.println();
                System.out.println("subStrLength="+subStrLength+", numSubStrings="+numSubStrings);
                
                for(int i=0; i <= sArr.length - subStrLength; i++)
                {
                   for(int j=i, k=0; j <= sArr.length - subStrLength; j++, k++) // k<subStrAscii.length
                   {
                	   System.out.println("add sArr[" + j + "] to subStrAscii[" + i + "]");
                	   subStrAscii[i]+= (int) sArr[j];
                   }
                }
                
                Arrays.sort(subStrAscii);
                
                System.out.println();
                System.out.print("[");
                for(int i=0; i<subStrAscii.length;i++)
                	System.out.print(subStrAscii[i] + ",");
                System.out.println("]");
                
                int cur = subStrAscii[0];
                for(int i=1; i<subStrAscii.length; i++)
                {
                    if(subStrAscii[i] == cur)
                        totalAnagrams++;
                    else
                        cur = subStrAscii[i];
                }
            }
            
        }
        
        System.out.println("totalAnagrams=" + totalAnagrams);
        
        sc.close();

	}

}
