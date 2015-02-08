import java.text.DecimalFormat;
import java.util.*;

public class Experiments {

	public static void main(String[] args) {
		
		String s;
		double[] arr = new double[100000];
		DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        System.out.println( format.format( Double.MAX_VALUE % Math.pow(10, 18) ) );
		System.out.println( format.format( Double.MAX_VALUE % Math.pow(10, 14) ) );
		
		System.out.println(Integer.toBinaryString(0xffffffff));
		/**
		 * abba
		 * 
		 * a	97
		 * b	98
		 * b	98
		 * a	97
		 * 
		 * a	97
		 * a	97
		 * a	97
		 * b	98
		 * b	98
		 * 
		 * ab
		 * bb
		 * ba
		 * 
		 * abb
		 * bba
		 * 
		 * 
		 */

	}

}
