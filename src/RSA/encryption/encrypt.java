package RSA.encryption;
import java.math.BigInteger;

public class encrypt {
    private BigInteger message;

    public encrypt(BigInteger message) {
        this.message = message;
    }

    public BigInteger encryptMessage(BigInteger publicKey[]) {
        BigInteger e = publicKey[0];
        BigInteger n = publicKey[1];

        return message.modPow(e, n);    // x^e mod n
    }
}
