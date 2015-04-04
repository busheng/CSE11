/** 
 *  Title: class Twist 
 *  Description: A sample code that reads input 
 *  @author Philip M. Papadopoulos
 *  @version 1.0 04-Jan-2015 
*/
 /***********************************************************************
 *  Compilation:  javac Twist.java
 *  Execution:    java Twist 
 *************************************************************************/
import java.util.Scanner;
public class Twist 
{
	/**
	 * @param args  command line arguments
	 */
	public static void main(String[] args) 
	{ 
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter your X value:");
		int xval, result;
		xval=scnr.nextInt();
		String formula;

		if ( (xval % 2) == 0 ) 
		{
			result = (xval + 2) * 7;
			formula = "(x + 2) * 7";
		}
		else
		{
			result = (xval + 3) * 9;
			formula = "(x + 3) * 9";
		}
		System.out.println("result is " + result + " [" + formula + "]");
	} 
}

