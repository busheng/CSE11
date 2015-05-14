/** 
 *  FileName: KeyCrypt.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 05/14/2015 
 */

public class KeyCrypt extends CryptStream {
  
  private byte key = 0;
  
  KeyCrypt(StreamPair theStreams, String input_key) {
    super(theStreams);
    if (input_key != null) {
      String key_value = new String(input_key);
      byte[] keybyte_array = key_value.getBytes();
      for (byte keybyte : keybyte_array) {
        key +=keybyte;
      }
    }
  }
  
  @Override
  protected byte[] cryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      new_data[len] = (byte)(key^data[len]);
    }
    return new_data;
  }

  @Override
  protected byte[] decryptData (byte[] data, int len) {
    byte[] new_data = new byte[len];
    while (len-- != 0) {
      new_data[len] = (byte)(key^data[len]); 
    }
    return new_data;
  }
}
