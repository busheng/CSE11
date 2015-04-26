import java.lang.Math;
/** 
 * @author Philip Papadopoulos
 * @version CSE11 Spring 2015
 *
 * A simple class that creates ascii shapes (triangle, rectangle)
 */
public class AsciiShape 
{
	private char [][] shape; 
	private static final char DEFAULTSYMBOL = '*';
	private static final char NEWLINE = '\n';

	/** Constructor 
	 * Construct a single character shape 
	 */
	public AsciiShape()
	{
		shape = new char [1][1];
		initShape(DEFAULTSYMBOL);
	}
	/** Constructor 
	 * @param triangle  true if a triangle shape, else a rectangle
	 * @param height number of rows in the shape
	 * @param width  max width of the shape
	 * @param symbol symbol to use for printing the shape
	 */
	public AsciiShape(boolean triangle, int height, int width, 
			char symbol)
	{
		if ( height < 1 ) height = 1; 
		if ( width < 1 ) width = 1; 
		if (! triangle )
		{
			shape = new char[height][width];
		}
		else
		{
			// Triangles. First row is length 1, last row is width
			shape = new char[height][];
			shape[0] = new char[1]; 
			shape[height - 1 ] = new char[width];
			for (int i = 1; i < height-1; i++)
			{
				int w =(i * width)/height + 1; 
				shape[i] = new char[w]; 
			}
		}
		initShape(symbol);
	}

	/** Constructor 
	 * @param triangle  true if a triangle shape, else a rectangle
	 * @param height number of rows in the shape
	 * @param width  max width of the shape
	 */
	public AsciiShape(boolean triangle, int height, int width) 
	{
		// invoke the constructor with the explicit symbol
		this(triangle,height,width,DEFAULTSYMBOL);
	}

	/** set the symbol used for printing the shape
	 *  @param symbol the symbol itself
	 */
	private void initShape(char symbol)
	{
		for (int i = 0; i < shape.length; i++)
			for( int j = 0; j < shape[i].length; j++)
				shape[i][j] = symbol;
	}

	/** return a 2D array of the shape 
	 * This should be a full/deep copy, not a reference to internal
	 * storage
	 * @return array of chars 
	 */
	public char [][] getShape()
	{
		char [][] rarray = new char[shape.length][];
		for (int i = 0; i < shape.length; i++)
		{
			rarray[i] = new char[shape[i].length];
			for(int j = 0; j < shape[i].length; j++)
				rarray[i][j] = shape[i][j];
		}
		return rarray;
	}

	/**
	 * set the symbol used by the shape
	 * @param symbol the symbol to use
	 */
	public void setSymbol(char symbol)
	{
		initShape(symbol);
	}

	/**
	 * String representation of the shape
	 */
	@Override
	public String toString()
	{
		String output = "";
		int i,j;
		for (i = 0; i < shape.length; i++)
			output += new String(shape[i]) + NEWLINE;
		return output.substring(0,output.length()-1);
	}

}
// vim: ts=4:sw=4:tw=78
