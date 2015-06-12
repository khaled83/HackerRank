import java.util.*;

public class IdentPairs {

	public static void main(String[] args) {
		int[] arr = new int[] { 10,11,3,3,6,3,6,3,6,6,7,3,7,7,7,7,6,1,2 };
		System.out.println( solution(arr) );
	}
	
	static public int solution(int[] A) {
        HashMap<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        
        for( int x : A ) {
        	Integer countSoFar = countMap.get(x);
        	if( countSoFar == null )
        		countSoFar = 0;
        	countMap.put(x, ++countSoFar);
        }
        
        int pairsCount = 0;
        for( Integer x : countMap.keySet() ) {
        	int n = countMap.get(x);
        	// pairs exist
        	if( n > 1 )
        		pairsCount += ( n * (n-1) ) / 2;
        }
        
        return pairsCount;
    }
	
	/**
  A[0] = 3
  A[1] = 5
  A[2] = 6
  A[3] = 3
  A[4] = 3
  A[5] = 5
  
  3 3 3 -> 3 
  3!/ 2! 1!
  3 (2) / 2 = 
  
  3 3 3 3 -> 6
  4! / 2! 2! =
  4 (3) / 2 = 
  
  3 3 3 3 3 -> 
  4 + 3 + 2 + 1 = 10
  5 * (5-1) / 2
  5! / 2! * 3!
  
  3 3 3 3 3 3
          *
  6 (5) / 2 = 15
	 */

}
