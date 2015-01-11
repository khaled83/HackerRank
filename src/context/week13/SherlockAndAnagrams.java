package context.week13;

import java.util.*;

public class SherlockAndAnagrams {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        for(int caseNum=1; caseNum<=numCases; caseNum++)
        {
            int totalAnagrams = 0;
        	
            String s = sc.next();
            char[] sArr = s.toCharArray();  
            
            for(int subStrLength = 1; subStrLength < s.length(); subStrLength++)
            {
                int numSubStrings = sArr.length - subStrLength + 1;
                int[] subStrAscii = new int[numSubStrings];
                for(int i=0; i<subStrAscii.length;i++)
                	subStrAscii[i] = 0;
                
                for(int i=0; i <= sArr.length - subStrLength; i++)
                {
                	for(int j=i, k=0; j < i + subStrLength; j++, k++) // k<subStrAscii.length
                		subStrAscii[i]+= (int) sArr[j];
                }
                
                Arrays.sort(subStrAscii);
                
                int cur = subStrAscii[0];
                int n = 1;
                for(int i=1; i<subStrAscii.length; i++)
                {
                    if(subStrAscii[i] == cur)
                    {
                    	n++;
                    }
                    else
                    {
                    	cur = subStrAscii[i];
                    	// n! / k! (n-k)!
                    	if(n > 1)
                    	{
                    		totalAnagrams+= factorial(n) / ( factorial(2) * factorial(n - 2) );
                    	}
                    		
                    	n=1;
                    }
                }
                
                if( n > 1)
                {
                	totalAnagrams+= factorial(n) / ( factorial(2) * factorial(n - 2) );
                }
                
            }
            
            System.out.println(totalAnagrams);
            
        }
        
        sc.close();

	}
	
	private static int factorial(int n)
	{
		return n == 1 || n == 0 ? 1 : n * factorial(n-1);
	}

}
