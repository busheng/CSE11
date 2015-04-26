/** define a 2D array of chars as a way to make ascii art.
 * can place and clear an arbitrary 2D array of chars in the grid
 * if asked-for array fits.
 * @author Busheng LOU
 * @version CSE11 Spring 2015
 */
public class AsciiGrid
{	
  	private char [][] grid;
	private int width = 0;
	private int height = 0;
 	private static final char EMPTY = ' ';
 	private static final int DEFAULTROW = 25;
 	private static final int DEFAULTCOL  = 40;
	private static final char NEWLINE = '\n';
	private static final char ROWFRAME = '=';
	private static final char COLFRAME = '|';
	
	/** Constructor 
	 */
	public AsciiGrid()
	{
	  this(DEFAULTROW, DEFAULTCOL);
	}
	/** Constructor 
	 * @param row number of rows in the ascii grid 
	 * @param col number of columns in the ascii grid 
	 */
	public AsciiGrid(int row, int col)
	{
	  grid = new char [row][col];
	  height = row;
	  width = col;
	}
	/** return a row x col array of the current char array  
	 * This should be a full/deep copy, not a reference to internal
	 * storage
	 * @return array of chars 
	 */
	public char [][] getChars()
	{
	  char [][] rarray = new char[height][width];
	  for (int i = 0; i < height; i++) {
	    rarray[i] = new char[width];
              for (int j = 0; j < width; j++)
	        rarray[i][j] = grid[i][j];
	  }
	  return rarray;
	}


	/** 
	 * place the 2D shape in the grid at location (r,c) 
	 * only if every row of the shape fits in the grid and
	 * doesn't intersect any non-empty spaces. Otherwise don't
	 * place the shape
	 * @param shape 2D array of chars to put on the Grid
	 * @param r row in the grid where to place the first row of the shape
	 * @param c column in the grid where to place the first column of the shape
	 * @return true, if successful false otherwise
	 */
	public boolean placeShape(char [][] shape,int r, int c)
	{
		return false;
	}
	
	/** 
	 * clear the elements in the grid  defined by the 2D shape 
	 * starting at grid at location (r,c). The contents of the 
	 * shape are irrelevant only the dimensions of each row are used.
	 * Clear only if every row of the shape fits in the grid. 
	 * Cleared elements in the grid are set to the EMPTY char.
	 *
	 * @param shape 2D array of chars. Defines the shape to  
	 * @param r row in the grid where to start the clearing 
	 * @param c column in the grid where to start the clearing 
	 * @return true, if successful false otherwise
	 */
	public boolean clearShape(char [][] shape,int r, int c)
	{
		return false;
	}
	
	/** Return the width and height of the grid 
	 *  @return array where index=0 is nrows, index=1 nrows 
	 */
	public int [] getSize()
	{      
          int [][] size = new int[2];
	  size[0] = height;
	  size[1] = width;
	  return size; 
	}

	/** create a nice, printable representation of the grid and
	 * filled coordinates
	 *
	 * the grid should be framed. A row of "=' (length = width of grid + 2)
	 * should be used to frame the top and bottom of the grid. The '|' should
	 * be used to frame the left and right side of each row of the grid. e.g 
	 * 1x1  empty grid      2 x 2 empty grid
	 * ===                  ====
	 * | |                  |  |
	 * ===                  |  |
	 *                      ====
	 */
	@Override
	public String toString()
	{
	  String output = "";
	  int i,j;
	  for (j = 0; j < width + 2; j++) 
	    output +=ROWFRAME;	  
	  output += NEWLINE;
          for (i = 0; i < height; i++) {
	    output += COLFRAME + new String(grid[i]) + COLFRAME + NEWLINE;
	  }
	  for (j = 0; j < width + 2; j++) 
	    output +=ROWFRAME;	  
	  output += NEWLINE;
          return output;
	}
	

}
// vim: ts=4:sw=4:tw=78
