import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

/* Many "magic" numbers (bad!)  */

public class BlockGrid {

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

	public static void help() {
	  System.out.println("Usage: BlockGrid [ width height pixels ]");
	  System.exit(0);
	}

}

class MyWindow extends JFrame implements Runnable
{
	private	Grid grid;
	private int width, height, pixels;
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

	public void run() {
	  Mover mover = new Mover (grid, width, height);
	  Thread m = new Thread(mover);
	  m.start();
	}

}

class Grid extends JPanel {
	private ArrayList<Point> fillCells;
	private int width, height, pixels;
	public Grid(int w, int h, int p) {
		width = w * p;
		height = h * p;
		pixels = p;
		fillCells = new ArrayList<Point>();
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(width+2, height+2);
	}

	@Override
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

	public void fillCell(int x, int y) {
		fillCells.add(new Point(x, y));
		repaint();
	}
	public void clearCell() {
	        fillCells.remove(0);
		repaint();
	}

}

class Mover implements Runnable { 
  private final static int wait = 150;	
  Grid grid;
  int width, height;
  public Mover (Grid g, int w, int h) {
    grid = g;
    width = w;
    height = h;
  }

  public void run() {
    boolean LEFTTORIGHT = true;
    int w = width/2 - 1, h = height/2 - 1;
    while (w < width && w >= 0) {
      grid.fillCell(w, h);
      try { TimeUnit.MILLISECONDS.sleep(wait);}
      catch (InterruptedException e){};
      grid.clearCell();
      if (w == width - 2 ) LEFTTORIGHT = false;
      if (w == 0 ) LEFTTORIGHT =true;
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
