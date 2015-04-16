/** 
 *  FileName: Countdown.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 04/09/2015 
 */
import objectdraw.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Countdown extends WindowController {
  
  private final static int CDIAMETER = 10;
  
  private static int width, height, countdown;
  private final static int second = 1000;
  public void begin() {
    int Width = canvas.getWidth(), Height = canvas.getHeight();
    int cir_x = (Width - CDIAMETER)/2, cir_y = (Height - CDIAMETER)/2;
    new FilledOval(cir_x, cir_y, CDIAMETER, CDIAMETER, canvas).setColor(Color.blue);
    int length = Width > Height? Height:Width;
    double angle = 0;
    AngLine secondhand;

    while(countdown-- != 0) {
      angle = Math.PI * (countdown + 15) / 30; 
      secondhand = new AngLine(Width/2, Height/2, length/2, angle, canvas);
      try { TimeUnit.MILLISECONDS.sleep(second);}
      catch (InterruptedException e){};
      secondhand.hide();
    }
    secondhand = new AngLine(Width/2, Height/2, length/2, Math.PI/2, canvas);
  }
  
  public void onMouseClick(Location point) {
    System.exit(0);
  }
  
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    System.out.print("Enter clock width in pixels : ");
    width = scnr.nextInt();
    System.out.print("Enter clock height in pixels : ");
    height = scnr.nextInt();
    System.out.print("Enter countdown in seconds : ");
    countdown = scnr.nextInt();
    if ((width <(CDIAMETER + 20)) || (height < (CDIAMETER + 20)) || (countdown < 0)) {
      System.out.println("BAD INPUT");
      System.exit(1);
    }
    new Countdown().startController(width,height);
  }

}
