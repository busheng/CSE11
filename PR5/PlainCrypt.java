/** 
 *  FileName: PlainCrypt.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 05/14/2015 
 */
import java.util.Arrays;
public class PlainCrypt extends CryptStream {
  PlainCrypt(StreamPair theStreams) {
    super(theStreams);
  }
  @Override
  protected byte[] cryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      new_data[len] = data[len];
    }
    return new_data;
  }

  @Override
  protected byte[] decryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      new_data[len] = data[len];
    }
    return new_data;
  }
}
