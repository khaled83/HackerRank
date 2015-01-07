package context.week13;

import java.text.DecimalFormat;
import java.util.*;

public class TaumAndBday {

	public static void main(String[] args) {
		
		DecimalFormat myFormatter = new DecimalFormat("###");
		
		Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        
        double b = 0, w = 0, x = 0, y = 0, z =0;
        double bCost = 0, wCost = 0; 
        double result = 0.0;
        
        for(int i=0; i<numCases; i++)
        {
            b = sc.nextInt();
            w = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            
            bCost = Math.min( b * x, 
            					( b * y ) + (b * z) );
            wCost = Math.min(w * y, 
            					( w * x ) + (w * z));
            
            result = bCost + wCost;
            System.out.println( myFormatter.format( result ) );
        }
        
        sc.close();
	}

}
