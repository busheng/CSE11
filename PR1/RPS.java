/** 
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746 
 */
import java.util.Random;
import java.util.Scanner;

public class RPS {
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
    player(player1);  
    System.out.print("Player 2 : ");  
    player(player2);  
    whowin(player1, player2);
    return;
  }
 
  private static void player(int player) {
    switch (player) {
      case 0:
        System.out.println("rock");
        break;
      case 1:
        System.out.println("scissors");
        break;
      case 2:
        System.out.println("paper");
        break;
      default:
        System.out.println("Error!");
        break;
    }
  }
 
  private static void whowin(int player1, int player2) {
    int temp = player1 - player2;
    if (temp == -1 || temp == 2) {
      System.out.println("Player 1 Wins");
    } else if (temp == 0) {
      System.out.println("Nobody Wins");
    } else {
      System.out.println("Player 2 Wins"); 
    }
  }
}	
