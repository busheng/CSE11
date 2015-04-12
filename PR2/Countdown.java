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
  
  private static int width=800, height=800, countdown =26;
  private final static int second = 1000;
  public void begin() {
    int cir_x = (width - CDIAMETER)/2, cir_y = (height - CDIAMETER)/2;
    new FilledOval(cir_x, cir_y, CDIAMETER, CDIAMETER, canvas).setColor(Color.blue);
    int length = width > height? width:height;
    double angle = 0;
    AngLine secondhand;
    
    while(countdown-- != 0) {
      angle = Math.PI * (countdown + 15) / 30; 
      secondhand = new AngLine(width/2, height/2, length/2, angle, canvas);
      try { TimeUnit.MILLISECONDS.sleep(second);}
      catch (InterruptedException e){};
      secondhand.hide();
    }
    secondhand = new AngLine(width/2, height/2, length/2, angle, canvas);
  }
  
  public void onMouseClick(Location point) {
    System.exit(0);
  }
  
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
       /*
    System.out.print("Enter clock width in pixels : ");
    width = scnr.nextInt();
    System.out.print("Enter clock height in pixels : ");
    height = scnr.nextInt();
    System.out.print("Enter countdown in seconds : ");
    countdown = scnr.nextInt();
    */
    new Countdown().startController(width,height);
  }



}
