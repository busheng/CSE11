import java.lang.Thread;
import java.lang.Runnable;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ThreadDemo implements Runnable
{
	private static final int RUNCOUNT=10;
	private static final int MAXMILLIS=5000;

	public void run()
	{
		Random myRnd = new Random();
		for (int i = RUNCOUNT; i > 0; i--)
		{
			System.out.format("Run Thread Iterations remaining: %d\n", i);
		 	try { TimeUnit.MILLISECONDS.sleep(myRnd.nextInt(MAXMILLIS));}
                	catch (InterruptedException e){};
		}
		System.out.format("Thread has completed execution\n");
		
	}		
	public static void main(String args[])
	{
		Scanner scnr = new Scanner(System.in);
		// create a Runnable instance
		ThreadDemo td = new ThreadDemo();
		// create child thread of this thread
		Thread t = new Thread(td);
		// start the thread execution (eventually invokes the run() method
		t.start();

	
		String inline = "";
		System.out.format("type \"exit\" to exit this loop");
		do 
		{
			System.out.format("> ");
			if (scnr.hasNextLine())
			{
				inline = scnr.nextLine();
				System.out.println(inline);
			}	
			else
				break;
		}
		while (! inline.equals("exit"));
	
	}		
}
	
