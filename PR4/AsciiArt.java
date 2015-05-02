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
  public static boolean grid_exist = false;
  private static final boolean TRIANGLE = true, RECTANGLE = false;
  private static final boolean PLACE = true, CLEAR = false;

  public static void main(String[] args) {
    ArrayList<AsciiShape> shapelist = new ArrayList<AsciiShape>();
    Scanner scnr = new Scanner(System.in);
    String prompt = "> ", ok = "OK", error = "BAD INPUT";
    System.out.print(prompt);

    while (scnr.hasNext()) {
      boolean first_int = false, good_input = false;
      String inputs = scnr.nextLine();
      String[] input = inputs.split("\\s+");
      String command = input[0].toLowerCase();
      int[] argus = new int[0];
      if ((input.length > 1 && input[1].matches("\\d+$"))
                                    || input.length == 1) { 
        argus = getArgs(input);
	first_int = true;
      }
      int len = argus.length;
      switch (command) {
        case "triangle":
	  good_input = shape_new (TRIANGLE, shapelist, input);
	  break;
        case "rectangle":
	  good_input = shape_new (RECTANGLE, shapelist, input);
	  break;
        case "list":
	  good_input = grid_list(first_int, argus, shapelist);
	  break;
        case "new":
	  good_input = grid_new(first_int, argus);
	  break;
        case "print":
	  good_input = grid_print(first_int, argus);
	  break;
        case "place":
          good_input = grid_place_clear(first_int, PLACE, shapelist, argus);	
	  break;
        case "clear":
          good_input = grid_place_clear(first_int, CLEAR, shapelist, argus);	
	  break;
        case "set":
	  good_input = grid_set(shapelist, input);
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
  
  /** Create Acsiishape object and add into arraylist 
   * @param trangle if true, create triangle
   * @param shapelist the shape list 
   * @param inputs array of input strings 
   * @return a int array of arguments     
   */
  public static boolean shape_new (boolean triangle,
    ArrayList<AsciiShape> shapelist, String[] inputs) {
    if (inputs.length == 3) {
      int row = Integer.parseInt(inputs[1]);
      int col = Integer.parseInt(inputs[2]);
      if (row <= 0 || col <= 0) return false;
      if (triangle) {
        shapelist.add(new AsciiShape(TRIANGLE, row, col));
      } else {
        shapelist.add(new AsciiShape(RECTANGLE, row, col));
      }
      return true;
    }
    if (inputs.length == 4) {
      char symbol = inputs[1].charAt(0);
      int row = Integer.parseInt(inputs[2]);
      int col = Integer.parseInt(inputs[3]);
      if (row <= 0 || col <= 0) return false;
      if (triangle) {
        shapelist.add(new AsciiShape(TRIANGLE, row, col, symbol));
      } else {
        shapelist.add(new AsciiShape(RECTANGLE, row, col, symbol));
      }
      return true;
    }
    return false; 
  }

  /** Create Asciigird object. 
   * @param fir_int true if first input is integer
   * @param args input arguments
   * @return true when a object is created
  */
  public static boolean grid_new (boolean fir_int, int[] args) {
    if (!fir_int) return false;
    if (args.length == 0) {
      grid11 = new AsciiGrid();
    } else if (args.length == 2 && args[0] > 0 && args[1] > 0) {
      grid11 = new AsciiGrid(args[0], args[1]);
    } else {
      return false;
    }
    grid_exist = true;
    return true;
  }

  /** Print the Asciigrid. 
   * @param fir_int true if first input is integer
   * @param args input arguments
   * @return true when print successfully
  */
  public static boolean grid_print(boolean fir_int, int[] args) {
    if (args.length != 0 || !fir_int || !grid_exist)
      return false;
    System.out.println(grid11.toString());
    return true;
  }
  
  /** List the shapes in the shapelist 
   * @param fir_int true if first input is integer
   * @param args input arguments
   * @param shapelist the shape list 
   * @return true when list the shapes successfully
  */
  public static boolean grid_list(boolean fir_int, int[] args, 
                              ArrayList<AsciiShape> shapelist) {
    if (args.length != 0 || !fir_int)
      return false;
    int i = 0;
    for (AsciiShape shape : shapelist) {
      System.out.println(i++ + ":");
      System.out.println(shape.toString());
    }
    return true;
  }

  /** Place or Clearthe shapes in the grid 
   * @param fir_int true if first input is integer
   * @param place true if user wang to place
   * @param shapelist the shape list 
   * @param args input arguments
   * @return true when place or clear the shapes successfully
  */
  public static boolean grid_place_clear (boolean fir_int, boolean place,
                             ArrayList<AsciiShape> shapelist, int[] args) {
    int nele = shapelist.size();
    if (!fir_int || args.length !=3 || args[0] >= nele || !grid_exist) {
      return false;
    }
    if (place) {
      return grid11.placeShape(shapelist.get(args[0]).getShape(),args[1],args[2]);
    } else {
      return grid11.clearShape(shapelist.get(args[0]).getShape(),args[1],args[2]);
    }
  }

  /** Set the symbol of shapes in the shapelist 
   * @param shapelist the shape list 
   * @param inputs array of input strings 
   * @return true when set the shapes successfully
  */
  public static boolean grid_set(ArrayList<AsciiShape> shapelist,
                                                 String[] inputs) {
    int index = Integer.parseInt(inputs[2]);
    if (inputs.length == 3 && index < shapelist.size()) {
      char symbol = inputs[1].charAt(0);
      shapelist.get(index).setSymbol(symbol);
      return true;
    } 
    return false;
  }
}
