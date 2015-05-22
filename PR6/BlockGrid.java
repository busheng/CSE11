/** 
 *  FileName: BlockGrid.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 05/19/2015
 *  @author Busheng LOU
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

/**
 * This is class BlockGrid.
 * 
 * {@link MyWindow}
 * 
 * @author Busheng LOU
 * 
 */
public class BlockGrid {
        /**
         * This is the main method which run the program.
         * @param a width,height and pixels of the windows.
         */
	public static void main(String[] a) {
		int width = 40, height = 20, pixels = 20;
		if (a.length == 1 && a[0].equals("help")) help();
		if (a.length != 3 && a.length !=0 ) help();
		if (a.length !=0) {
		  try {
		    width = Integer.parseInt(a[0]);
		    height = Integer.parseInt(a[1]);
		    pixels = Integer.parseInt(a[2]);
		    if (width < 2 || height < 2 || pixels < 2) help();
	 	  }
		  catch (NumberFormatException e) {
		    help();  
		  }
		}
		MyWindow window = new MyWindow(width, height, pixels);
		SwingUtilities.invokeLater(window);
		try 
		{	
			System.out.format("Hit Return to exit program");
			System.in.read();
		}
		catch (IOException e){}
		window.dispatchEvent(new WindowEvent(window, 
			WindowEvent.WINDOW_CLOSING));
        	window.dispose();	
	}

   /** Print out the usage instructions
    */
   public static void help() {
	  System.out.println("Usage: BlockGrid [ width height pixels ]");
	  System.exit(0);
	}

}

/**
 * This is class MyWindow.
 * {@link Mover}
 * {@link Grid}
 * @author Busheng LOU
 */
class MyWindow extends JFrame implements Runnable
{
	private	Grid grid;
	private int width, height, pixels;
	
	/** Constructor
	 * @param w  width of the window
	 * @param h height of the window
	 * @param p pixels of the window
	 */
	public MyWindow(int w, int h, int p) {
		super();
		width = w;
		height = h;
		pixels = p;
		grid = new Grid(width, height, pixels);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(grid);
		pack();
		validate();
		setVisible(true);
	}

        /** run function which will new a Mover and 
	 *  run it on a new thread
	 */
	public void run() {
	  Mover mover = new Mover (grid, width, height);
	  Thread m = new Thread(mover);
	  m.start();
	}

}

/**
 * This is class Grid.
 * @author Busheng LOU
 */
class Grid extends JPanel {
	private ArrayList<Point> fillCells;
	private int width, height, pixels;
	
	/** Constructor
	 * @param w  width of the window
	 * @param h height of the window
	 * @param p pixels of the window
	 */
	public Grid(int w, int h, int p) {
		width = w * p;
		height = h * p;
		pixels = p;
		fillCells = new ArrayList<Point>();
	}

	@Override
	/** Get the right size of the dimension
	 * @return a dimension with 2 unit of both width and height
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(width+2, height+2);
	}

	@Override
	/** Paint the grahics on th window
	 * @param g the grahics that will be painted
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (fillCells == null) return;
		for (Point fillCell : fillCells) {
			int cellX = (fillCell.x * pixels);
			int cellY = (fillCell.y * pixels);
			g.setColor(Color.RED);
			g.fillRect(cellX, cellY,2*pixels,2* pixels);
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);

		for (int i = 0; i < width; i += pixels) {
			g.drawLine(i, 0, i, height);
		}

		for (int i = 0; i < height; i += pixels) {
			g.drawLine(0, i, width, i);
		}
	}

	/** add the cell that need to be drawn on list
	 * @param x value of x axis.
	 * @param y value of y axis.
	 */
	public void fillCell(int x, int y) {
		fillCells.add(new Point(x, y));
		repaint();
	}
	
	/** remove the first cell on the list
	 * @return Nothing
	 */
	public void clearCell() {
	        fillCells.remove(0);
		repaint();
	}

}

/**
 * This is class Mover.
 * @author Busheng LOU
 */
class Mover implements Runnable { 
  private final static int wait = 150;	
  Grid grid;
  int width, height;

  /** Constructor
  * @param g grid used to drawn on
  * @param w  width of the window
  * @param h height of the window
  */
  public Mover (Grid g, int w, int h) {
    grid = g;
    width = w;
    height = h;
  }
  
  /** run function which will do the loop
   *  to redraw the cells
   */
  public void run() {
    boolean LEFTTORIGHT = true;
    int w = width/2 - 1, h = height/2 - 1;
    while (w < width && w >= 0) {
      grid.fillCell(w, h);
      try { TimeUnit.MILLISECONDS.sleep(wait);}
      catch (InterruptedException e){};
      grid.clearCell();
      if (w >= width - 2 ) LEFTTORIGHT = false;
      if (w <= 0 ) LEFTTORIGHT =true;
      if (LEFTTORIGHT) {
	w++;
      } else {
        w--;
      }
      if (width == 2) w = 0;
    }
  } 

}
// vim: ts=4:sw=4:tw=78
