









import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.Signature;

public class SignatureTest {
    //public PublicKey pubKey = null;
    SignatureTest() throws Exception {
    
   }
 public static byte[] sign(String datafile, PrivateKey prvKey,
      String sigAlg) throws Exception {
    Signature sig = Signature.getInstance(sigAlg);
    sig.initSign(prvKey);
    FileInputStream fis = new FileInputStream(datafile);
    byte[] dataBytes = new byte[1024];
    int nread = fis.read(dataBytes);
    while (nread > 0) {
      sig.update(dataBytes, 0, nread);
      nread = fis.read(dataBytes);
    };
    fis.close();
    return sig.sign();
  }
   public static boolean verify(String datafile, PublicKey pubKey,
      String sigAlg, byte[] sigbytes) throws Exception {
    Signature sig = Signature.getInstance(sigAlg);
    sig.initVerify(pubKey);
    FileInputStream fis = new FileInputStream(datafile);
    byte[] dataBytes = new byte[1024];
    int nread = fis.read(dataBytes);
    while (nread > 0) {
      sig.update(dataBytes, 0, nread);
      nread = fis.read(dataBytes);
    };
    fis.close();
    return sig.verify(sigbytes);
  }
  
}
