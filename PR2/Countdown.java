/** 
 *  FileName: Countdown.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 04/16/2015 
 */
import objectdraw.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Countdown extends WindowController {
  
  private final static int CDIAMETER = 10;
  
  private final static int sechand_larger = 20, second = 1000;  
  private static int width, height, countdown;
  
  /** begin function :
   *
   * Drawing the clock and make it moves
   */
  public void begin() {
    
    int Width = canvas.getWidth(), Height = canvas.getHeight();
    int cir_x = (Width - CDIAMETER)/2, cir_y = (Height - CDIAMETER)/2;
    int length = Width > Height? Height:Width;
    double angle = 0;
    AngLine secondhand;

    new FilledOval(cir_x, cir_y, CDIAMETER, CDIAMETER, canvas).setColor(Color.blue); 
    if (length < CDIAMETER + sechand_larger) length = CDIAMETER +sechand_larger; //set length to minimum allowed

    /* loop for drawing secondhand*/
    while(countdown-- != 0) {
      angle = Math.PI/2 + Math.PI * (countdown + 1) / 30;  // caculate the angle of the line
      secondhand = new AngLine(Width/2, Height/2, length/2, angle, canvas);
      try { TimeUnit.MILLISECONDS.sleep(second);}
      catch (InterruptedException e){};
      secondhand.hide();
    }
    secondhand = new AngLine(Width/2, Height/2, length/2, Math.PI/2, canvas);//keep in 12'o clock position after stoppig
  }
  
  /** onMouseClick function :
   *
   * click and exit the program
   */
  public void onMouseClick(Location point) {
    System.exit(0);
  }

  /** main function :
   *
   * get user input and draw the windows
   */
  public static void main(String[] args) {

    Scanner scnr = new Scanner(System.in);
    System.out.print("Enter clock width in pixels : ");
    width = scnr.nextInt();
    System.out.print("Enter clock height in pixels : ");
    height = scnr.nextInt();
    System.out.print("Enter countdown in seconds : ");
    countdown = scnr.nextInt();
    if ((width <(CDIAMETER +  sechand_larger)) || (height < (CDIAMETER + sechand_larger)) || (countdown < 0)) {
      System.out.println("BAD INPUT");
      System.exit(1);
    }
    new Countdown().startController(width,height);
  }
}
