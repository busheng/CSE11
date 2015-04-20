/** 
 *  FileName: ArrayPlay.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 04/20/2015 
 */
import java.util.Scanner;
import java.util.Arrays;

public class ArrayPlay {
  
  public static IntArray11 Array11;
  
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    String prompt = "> ", ok = "OK", error = "BAD INPUT";
    System.out.print(prompt);
    
    while (scnr.hasNext()) {
      boolean good_input = false;
      String inputs = scnr.nextLine();
      String[] input = inputs.split("\\s+");
      String command = input[0].toLowerCase();
      int[] arguments = getArgs(input);
      int len = arguments.length;
      switch (command) {
        case "new":
	  good_input = array_new(arguments);
	  break;
        case "print":
	  good_input = array_print(arguments);
	  break;
        case "delete":
	  if (len == 1)
	  good_input = Array11.delete(arguments[0]);
	  break;
        case "insert":
	  if (len == 2)
	  good_input = Array11.insert(arguments[0], arguments[1]);
	  break;
        case "reverse":
	  good_input = array_reverse(arguments);
	  break;
        case "set":
	  if (len == 2)
	  good_input = Array11.setElement(arguments[0], arguments[1]);
	  break;
        case "size":
          System.out.println( Array11.getNelem());
	  good_input = true;
	  break;
	case "swap":
	  if(len == 2)
	  good_input = Array11.swap(arguments[0], arguments[1]);
	  break;
	case "exit":
	  System.exit(0);
	  break;
	default :
	  break;
      }
      if (good_input) {
        System.out.println(ok);
      } else {
        System.out.println(error);
      }
    System.out.print(prompt);
    } 
  }

  public static int[] getArgs(String[] inputs) {
    int[] rel = new int[inputs.length - 1];
    int i = 1;
    while (i != inputs.length) {
      rel[i-1] = Integer.parseInt(inputs[i]);
      i++;
    }
    return rel;
  }

  public static boolean array_new (int[] args) {
    if (args.length == 0) {
      Array11 = new IntArray11(); 
    } else if (args.length == 1) {
      Array11 = new IntArray11(args[0]);
    } else {
	Array11 = new IntArray11(args);
    }   
    return true;
  }
  
  public static boolean array_print(int[] args ) {
    if (args.length == 0) {
      System.out.println(Array11.toString());
      return true;
    } else if (args.length == 1) {
      System.out.println(Array11.getElement(args[0]));
      return true;
    } else {
      return false;
    }
  }

  public static boolean array_reverse(int[] args) {
    if (args.length == 0) {
      Array11.reverse();
      return true;
    } else if (args.length == 2){
      return Array11.reverse(args[0], args[1]); 
    } else {
      return false;
    }
  }
}
