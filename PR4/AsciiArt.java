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
  private static final boolean TRIANGLE = true;
  private static final boolean RECTANGLE = false;

  public static void main(String[] args) {
    ArrayList<AsciiShape> shapelist = new ArrayList<AsciiShape>();
    Scanner scnr = new Scanner(System.in);
    String prompt = "> ", ok = "OK", error = "BAD INPUT";
    System.out.print(prompt);

    while (scnr.hasNext()) {
      boolean first_int = false;
      boolean good_input = false;
      String inputs = scnr.nextLine();
      String[] input = inputs.split("\\s+");
      String command = input[0].toLowerCase();
      if (input.length > 1 && input[1].matches("\\d+$")) 
        first_int = true;
      int[] argus = getArgs(first_int,input);
      int len = argus.length;
      switch (command) {
        case "triangle":
	  if (!first_int) {
	    char symbol = input[1].charAt(0);
	    shapelist.add(new AsciiShape(TRIANGLE,argus[0],argus[1], symbol));
	  } else {
	    shapelist.add(new AsciiShape(TRIANGLE,argus[0],argus[1]));
	  }
	  break;
        case "rectangle":
	  if (!first_int) {
	    char symbol = input[1].charAt(0);
	    shapelist.add(new AsciiShape(RECTANGLE,argus[0],argus[1], symbol));
	  } else {
	    shapelist.add(new AsciiShape(RECTANGLE,argus[0],argus[1]));
	  }
	  break;
        case "list":
	  for (AsciiShape shape : shapelist) {
	    System.out.println(shape.toString());
	  }
	  break;
        case "new":
	  good_input = grid_new(argus);
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
  public static int[] getArgs(boolean first_int, String[] inputs) {
    if (inputs.length == 1) {
      return new int[0];
    }
    int [] rel;
    int shift;
    if (first_int) {
      rel = new int[inputs.length - 1];
      shift = 1;
    } else {
      rel = new int[inputs.length - 2];
      shift = 2;
    }
    int i = shift;
    while (i != inputs.length) {
      rel[i-shift] = Integer.parseInt(inputs[i]);
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
