/*************************************************************************
 *  Compilation:  javac -classpath '*':'.' Hello2.java
 *  Execution:    java -classpath '*':'.' Hello2
 *  Requires: objectdraw.jar
 *
 *  Prints "Hello, World". By tradition, this is everyone's first program.
 *  - uses the objectdraw library to display in a graphical window
 *  - runs as  java program
 *************************************************************************/
import objectdraw.*;
import java.awt.*;

public class Hello2 extends WindowController 
{
        /* These are methods called when certain events occur */
	/* it is the objectdraw library that handles events   */
    	public void onMousePress(Location point) {
        	new Text("Hello World!", 40, 50, canvas );
	}

    	public void onMouseRelease(Location point) {
		canvas.clear();
	}

	public static void main(String[] args) {	 
 		new Hello2().startController(400,400); 
	} 
}
