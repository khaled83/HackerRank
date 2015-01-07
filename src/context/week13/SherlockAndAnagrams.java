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
            for(int subStrLength = 1; subStrLength < s.length(); subStrLength++)
            {
                int numSubStrings = sArr.length - subStrLength + 1;
                int[] subStrAscii = new int[numSubStrings];
                System.out.println("subStrLength=" + subStrLength + ", subStrAscii.length="+subStrAscii.length);
                
                for(int i=0; i<sArr.length && i+subStrLength <= sArr.length; i++)
                {
                   for(int j=i, k=0; k<subStrAscii.length; j++, k++) {
                       System.out.println("i="+i+",j="+j);
                       System.out.println("k="+k);
                       System.out.println(subStrAscii[k]);
                       System.out.println(sArr[j]);
                	   subStrAscii[k]+= (int) sArr[j];
                   }
                }
                
                Arrays.sort(subStrAscii);
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
        
        System.out.println(totalAnagrams);
        
        sc.close();

	}

}
