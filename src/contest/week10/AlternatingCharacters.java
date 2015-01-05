package contest.week10;

import java.util.Random;
import java.util.Scanner;

public class AlternatingCharacters
{
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        String[] inputs = new String[numCases];
        
        for(int i=0; i<numCases; i++)
            inputs[i] = sc.next();
        
        for(String str : inputs )    
            System.out.println( getNumOfSteps( str ) );
        
        sc.close();
        
//        String[] inputs = getTestData();
//        
//        long start = System.currentTimeMillis();
//        for(String str : inputs )    
//            System.out.println( getNumOfSteps( str ) );
//        System.out.println("time: " + ( System.currentTimeMillis() - start ));
        
    }
    
    public static int getNumOfSteps(String s)
    {
        int steps = 0;
        
        char[] chars = s.toCharArray();
        char cur = chars[0];
        for(int i=1; i<chars.length; i++)
            if(cur == chars[i])
            	steps++;
            else
                cur = chars[i];
        
        return steps;
    }
    
    public static String[] getTestData()
    {
    	int numCases = 10;
    	double maxLength = Math.pow(10, 5);
    	
    	String[] inputs = new String[numCases];
    	Random rand = new Random();
    	
    	for(int caseNum=0; caseNum<numCases; caseNum++)
    	{
    		StringBuilder sb = new StringBuilder();
    		for(int i=1; i<=maxLength; i++)
    			sb.append((char) ( rand.nextInt(2) + 65 ) );
    		inputs[caseNum] = sb.toString();
    	}
    	
    	return inputs;    	
    }

}	
