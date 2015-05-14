/** Create a pair of InputStream/OutputStream  objects
 * based what was asked for in the constructor
 * @author Busheng LOU
 * @version 15 May 2015
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
public class StreamPair 
{       
        private InputStream input;
	private OutputStream output;

	/** Constructor
	 * @param infile  Name of the input file. "-" means standard input
	 * @param outfile  Name of the output file. "-" means standard output
	 * @throws IOException  file related exceptions
	 */
	public StreamPair(String infile, String outfile) throws IOException
	{ 
	  if (infile.equals("-")) {
            input = System.in;
	  } else {
	    File inf= new File(infile);
	    input = new FileInputStream(inf);
	  }
	  if (outfile.equals("-")) {
	    output = System.out;
	  } else {
	    File outf= new File(outfile);
	    output = new FileOutputStream(outf);
	  }
	}

	/** get a reference to this stream pair's input stream
	 * @return  reference to the input stream
	 */
	public InputStream getInput()
	{
		return input;
	}

	/** get a reference to this stream pair's output stream
	 * @return  reference to the output stream
	 */
	public OutputStream getOutput()
	{
		return output;
	}

	/** close both streams. Should not cause an error if invoked multiple times
	 */
	public void close()
	{ 
	  try { 
	    if (input != System.in) input.close();
	    if (output != System.out) output.close();
	  } catch (IOException e) {
	    System.err.println(e);
	  }
	}
}
// vim: ts=4:sw=4:tw=78
