/** 
 *  FileName: Cryptor.java
 *  Name: Busheng LOU	 
 *  Email: bulou@eng.ucsd.edu 
 *  ID: A53080746
 *  Data: 05/14/2015 
 */

import java.io.IOException;
public class Cryptor {
  
  private final static String USAGE = "Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]\n   algorithms: plain rot13 key";
  private final static String INFILE = "-i", OUTFILE = "-o", DECRY = "-d", ENCRY = "-e", _KEY = "-k";
  private final static String PLAIN = "plain", ROT13 = "rot13", KEY = "key";
  private final static boolean ENCRYPT = true, DECRYPT = false;
  
  private static String algorithm = PLAIN, key_value = null;
  private static boolean activity = ENCRYPT;

  public static void main(String[] args) {
    int i = 0, j;
    if (args.length !=0 && args[0].equals("help")) {
      help();
    }
    String infile = "-", outfile= "-";
    while (i < args.length-1 ) {
      if (!args[i].startsWith("-")) {
	System.out.println("Wrong usage. Please follow the instructions.");
	help();
      }
      switch (args[i]) {
        case INFILE:
	  infile  = args[++i]; 
	  break;
        case OUTFILE:
	  outfile  = args[++i];
	  break;
        case DECRY:
	  algorithm = args[++i];
	  activity = DECRYPT;
	  break;
        case ENCRY:
	  algorithm = args[++i];
	  activity = ENCRYPT;
	  break;
        case _KEY:
	  key_value = args[++i]; 
	  break;
	default:
	  System.out.println("unknown flag: '" + args[i] + "'");
	  help();
      }
      i++;
    }
    if (i != args.length) {
      System.out.println("Wrong usage. Please follow the instructions.");
      help();
    }
    try {
      StreamPair streampair = new StreamPair(infile,outfile);
      if (algorithm.equals(PLAIN)) { 
        PlainCrypt plain = new PlainCrypt(streampair);
        action (plain, activity);
      } else if (algorithm.equals(ROT13)) {
        Rot13Crypt rot13 = new Rot13Crypt(streampair);
        action (rot13, activity);
      } else if (algorithm.equals(KEY)) {
	if (key_value == null) {
	  System.out.println("please use flag -k to input a key for key algorithm");
	  help();
	}
        KeyCrypt key = new KeyCrypt(streampair, key_value);	
        action (key, activity);
      } else {
        System.out.println("unknown algorithm: '" + algorithm + "'");
	help();
      }
      streampair.close();
    } catch (IOException e) {
      System.err.println(e);
      help();
    }
  }

  private static <Type extends CryptStream>
  void action (Type crypt, boolean activity) {
    if (activity == ENCRYPT) {
      crypt.encrypt();
    } else {
      crypt.decrypt();
    }
  }

  private static void help() {
    System.out.println(USAGE); 
    System.exit(0);
  }
}
