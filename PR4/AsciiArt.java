/** 
 *  FileName: AsciiArt.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 04/30/2015 
 */
import java.util.Scanner;
import java.util.ArrayList;

public class AsciiArt {
   
  public static AsciiGrid grid11;
  
  public static void main(String[] args) {
    ArrayList<AsciiShape> shapelist = new ArrayList<AsciiShape>();
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
        case "triangle":
	  break;
        case "rectangle":
	  break;
        case "list":
	  break;
        case "new":
	  good_input = grid_new(arguments);
	  break;
        case "print":
	  if (len == 0) {
	    System.out.println(grid11.toString());
	    good_input = true;
	  }
	  break;
        case "place":
	  break;
        case "clear":
	  break;
        case "set":
	  break;
        case "exit":
          System.out.println(ok);
	  System.exit(0);
	  break;
        default:
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

  /** Create and return a int array of input arguments. 
   * @param inputs array of input strings 
   * @return a int array of arguments
  */
  public static int[] getArgs(String[] inputs) {
    int[] rel = new int[inputs.length - 1];
    int i = 1;
    while (i != inputs.length) {
      rel[i-1] = Integer.parseInt(inputs[i]);
      i++;
    }
    return rel;
  }

  /** Create a Shape object. 
   * @param args input arguments
   * @return true when a object is created
  */
  public static boolean grid_new (int[] args) {
    if (args.length == 0) {
      grid11 = new AsciiGrid();
    } else if (args.length == 2) {
      grid11 = new AsciiGrid(args[0], args[1]);
    } else {
      return false;
    }   
    return true;
  } 
}
