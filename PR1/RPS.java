/** 
 *  FileName: RPS.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 04/09/2015 
 */
import java.util.Random;
import java.util.Scanner;

public class RPS {
  
  public static final int rock = 0;
  public static final int scissors = 1;
  public static final int paper = 2;
  
  /** Main function
    */
  public static void main(String[] args) {
    int inputnum;
    Scanner scnr = new Scanner(System.in);
    System.out.print("Enter Seed : ");
    inputnum = scnr.nextInt();
    Random randnum = new Random();
    randnum.setSeed(inputnum);
    int player1 = randnum.nextInt(3);
    int player2 = randnum.nextInt(3);
    System.out.print("Player 1 : ");
    printplayer(player1);  
    System.out.print("Player 2 : ");  
    printplayer(player2);  
    whowin(player1, player2);
    return;
  }

  /** Print palyer output
    *
    * Input:
    *   player - input integer
    *
    * Output:
    *   print the player result   
    */
  private static void printplayer(int player) {
    switch (player) {
      case rock:
        System.out.println("rock");
        break;
      case scissors:
        System.out.println("scissors");
        break;
      case paper:
        System.out.println("paper");
        break;
      default:
        System.out.println("Error!");
        break;
    }
  }
  
  /** Print game result
    *
    * Input:
    *   player1 - input integer
    *   player2 - input integer
    *
    * Output:
    *   print the game result   
    */
  private static void whowin(int player1, int player2) {
    int temp = player1 - player2;
    if (temp == -1 || temp == 2) {    // 2 means paper - rock
      System.out.println("Player 1 Wins");
    } else if (temp == 0) {
      System.out.println("Nobody Wins");
    } else {
      System.out.println("Player 2 Wins"); 
    }
  }
}	
