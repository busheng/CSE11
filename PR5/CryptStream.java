/** A class that encrypts/decrypts
 * @author Busheng LOU
 * @version 14/May/2015
 * Email: bulou@eng.ucsd.edu 
 * ID: A53080746
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

public abstract class CryptStream
{
	protected StreamPair streams;
	/** Constructor 
	 * @param theStreams a constructed StreamPair Instance
	 */
	public CryptStream(StreamPair theStreams)
	{
	  streams = theStreams;
	}
	/** Encrypt data in the byte array
	 * @param data - the data to encrypt
	 * @param len - how many bytes in the array to encrypt
	 * @return a byte array with data encrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] cryptData( byte [] data, int len); 
	/** Decrypt data in the byte array
	 * @param data - the data to decrypt
	 * @param len - how many bytes in the array to decrypt
	 * @return a byte array with data decrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] decryptData( byte [] data,int len);

	
	/** encrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int encrypt()
        {
	  int len = 0, num_byte = 0;
	  byte[] old_data = new byte[1024];
	  InputStream input = streams.getInput();
	  OutputStream output = streams.getOutput();
	  try {
	    while((len=input.read(old_data)) != -1) {
	      byte[] new_data = cryptData(old_data, len);
	      output.write(new_data, 0, len);
	      num_byte += len;
            }
	  } catch (IOException e) {
	    System.err.println(e);
	  }
	  return num_byte;
	}
	/** decrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int decrypt()
	{
	  int len = 0, num_byte = 0;
	  byte[] old_data = new byte[1024];
	  InputStream input = streams.getInput();
	  OutputStream output = streams.getOutput();
	  try {
	    while((len=input.read(old_data)) != -1) {
	      byte[] new_data = decryptData(old_data, len);
	      output.write(new_data, 0, len);
	      num_byte += len;
            }
	  } catch (IOException e) {
	    System.err.println(e);
	  }
	  return num_byte;
	}

}
// vim: ts=4:sw=4:tw=78
