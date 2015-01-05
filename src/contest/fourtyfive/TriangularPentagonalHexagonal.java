package contest.fourtyfive;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TriangularPentagonalHexagonal
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner( System.in );
        double n = sc.nextDouble();
        int type1 = sc.nextInt();
        int type2 = sc.nextInt();
        
        boolean findT = type1 == 3 || type2 == 3;
        boolean findP = type1 == 5 || type2 == 5;
        boolean findH = type1 == 6 || type2 == 6;
        
        double max = Double.MAX_VALUE;
        System.out.println(max);
        System.out.println(new DecimalFormat("#0").format(max));
        
        solve(n, findT, findP, findH);
        
        sc.close();
    }
    
    private static void solve(double n, boolean findT, boolean findP, boolean findH)
    {
        HashMap<Double, Object> tMap = new HashMap<Double, Object>();
        HashMap<Double, Object> pMap = new HashMap<Double, Object>();
        HashMap<Double, Object> hMap = new HashMap<Double, Object>();
        
        if(findT)
        {
            double t = (1*(1+1))/2;
            tMap.put(t, null);
            for(double i=2; i<n && t<n; i++)
            {
                t = (i*(i+1))/2;
                tMap.put(t, null);
            }
        }
        
        if(findP)
        {
        	double p = (1*(3*1-1))/2;
            pMap.put(p, null);
            
            for(double i=2; i<n && p<n; i++)
            {
                p = (i*(3*i-1))/2;
                pMap.put(p, null);
            }
       }
        
       if(findH)
       {
    	   	double h = 1 * (2*1-1);
    	   	hMap.put(h, null);
			
			for (double i = 2; i < n && h < n; i++) 
			{
				h = i * (2 * i - 1);
				hMap.put(h, null);
			}
       }
       
       ArrayList<Double> results = new ArrayList<Double>();
       
       if(findT && findP)
       {
    	   for(Double p : pMap.keySet())
    		   if(tMap.containsKey(p))
    			   results.add(p);
       }
       
       if(findP && findH)
       {
    	   for(Double h : hMap.keySet() )
    		   if(pMap.containsKey(h))
    			   results.add(h);
       }
       
       Collections.sort(results);
       DecimalFormat df = new DecimalFormat("#0");
       
       for(Double d : results)
    	   System.out.println(df.format(d));
    }
    
}