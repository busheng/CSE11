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
      String command = scnr.nextLine();
      System.out.println(command);
      String [] arg = command.split("\\s+");
      switch (arg[0].toLowerCase()) {
        case "new":
	  array_new(arg);
	break;
        case "print":
	  array_print(arg);
	break;
        case "delete":
	  Array11.delete(Integer.parseInt(arg[1]));
	break;
        case "insert":
	  int index = Integer.parseInt(arg[1]);
	  int ele = Integer.parseInt(arg[2]);
	  if (Array11.insert(index, ele)) {
	   System.out.println(ok);
	  } else {
	   System.out.println(error);
	  }          
	break;
        case "reverse":
	  array_reverse(arg);
	break;
        case "set":
	  int index3 = Integer.parseInt(arg[1]);
	  int element = Integer.parseInt(arg[2]);
	  if (Array11.setElement(index3, element)) {
	   System.out.println(ok);
	  } else {
	   System.out.println(error);
	  }
	break;
        case "size":
          System.out.println( Array11.getNelem());
	break;
	case "swap":
	  int index1 = Integer.parseInt(arg[1]);
	  int index2 = Integer.parseInt(arg[2]);
          Array11.swap(index1, index2);
	break;
	case "exit":
	System.exit(0);
	break;
	default :
	System.out.println("BAD INPUT");
        break;
      }
    System.out.print(prompt);
    } 
  }

  public static void array_new (String[] arg) {
    if (arg.length == 1) {
      Array11 = new IntArray11(); 
    } else if (arg.length == 2) {
      Array11 = new IntArray11(Integer.parseInt(arg[1]));
    } else {
      int[] arrays = new int[arg.length-1];
      int i = 1;
      while (i < arg.length ) {
        arrays[i-1] = Integer.parseInt(arg[i]);
	Array11 = new IntArray11(arrays);
	i++;
      }
    }
  } 
  
  public static void array_print(String[] arg ) {
    if (arg.length == 1) {
      System.out.println(Array11.toString());  
    } else {
      System.out.println(Array11.getElement(Integer.parseInt(arg[1])));
    }
  }

  public static void array_reverse(String[] arg) {
    if (arg.length == 1) {
      Array11.reverse();
    } else {
     int start = Integer.parseInt(arg[1]), end = Integer.parseInt(arg[2]);
    Array11.reverse(start, end); 
    }
  }
}
