/** 
 *  FileName: Rot13Crypt.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 05/14/2015 
 */
public class Rot13Crypt extends CryptStream {
  
  Rot13Crypt(StreamPair theStreams) {
    super(theStreams);
  }

  @Override
  protected byte[] cryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      new_data[len] = (byte)((data[len] + 13) % 256);
    }
    return new_data;
  }

  @Override
  protected byte[] decryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      if (data[len] < 13) {
        new_data[len] =(byte)(data[len] - 13 + 256);
      } else {
        new_data[len] =(byte)(data[len] - 13);
      }
    }
    return new_data;
  }
}
