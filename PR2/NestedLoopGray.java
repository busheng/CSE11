/*************************************************************************
 *  Compilation:  javac -classpath '*':'.' NestedLoopGray.java
 *  Execution:    java -classpath '*':'.' NestedLoopGray.java 
 *  Requires: objectdraw.jar
 *
 *  prints filled circles Down and across the canvas
 *  - runs as  java program
 *************************************************************************/
import objectdraw.*;
import java.awt.*;

public class NestedLoopGray extends WindowController 
{
	private static final int WIN_SIZE = 300;
	private static final int DIAMETER = 15;
	private static final int FX=4, FY=54;
	private static final int GRAY = 50, COLORCHANGE=10;
	private Text instructions;
	
	public void begin()
	{
		instructions = new Text("Click mouse to draw Circles", 50,WIN_SIZE-100, canvas);
	}

	public void onMouseClick(Location point) 
	{
		instructions.hide();
		int row = 0; 		// which row are we on 
		while ( row < WIN_SIZE )
		{
			int hue = GRAY;
			int column = 0; // Start at the left
			while (column < WIN_SIZE)
			{
				FilledOval circle;
				circle = new FilledOval(column, row,
						DIAMETER,DIAMETER, canvas);
				circle.setColor(new Color(hue,hue,hue));
				column += DIAMETER;
				hue += COLORCHANGE; 
			}
			row += DIAMETER; // Go to the next row
		}
	}

	public static void main(String[] args) {	 
	 	NestedLoopGray myWindow;
 		myWindow = new NestedLoopGray();
		myWindow.startController(WIN_SIZE + FX,WIN_SIZE + FY); 
	} 
}
