import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class Experiments {

	public static void main(String[] args) {
		
		double[] arr = new double[100000];
		DecimalFormat format = new DecimalFormat("#");
        format.setMaximumFractionDigits(0);
        System.out.println( format.format( Double.MAX_VALUE % Math.pow(10, 18) ) );
		System.out.println( format.format( Double.MAX_VALUE % Math.pow(10, 14) ) );
		
		System.out.println(Integer.toBinaryString(0xffffffff));
		
		System.out.println( ( Math.pow(10, 100) * ((int)'z') ) < Double.MAX_VALUE);
		
		String[] values = { "ehxxdsuhoowxpbxiwxjrhe", "ehxxdsuhoowxpbxiwxrjhe", "ehxxdsuhoowxpbxiwxrehj" };
		System.out.println("1");
		for(String s : values)
			System.out.println(s);
		System.out.println("2");
		for(String s : values)
			System.out.println(s);
		Arrays.sort(values);
		System.out.println("3");
		for(String s : values)
			System.out.println(s);
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
	
	private static BigDecimal fact(double n) {
		BigDecimal fact = new BigDecimal(1);
		for(double i=n; i>=1; i--) {
			fact = fact.multiply(new BigDecimal(i));
		}
		return fact;
	}

}
