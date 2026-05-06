package RSA.decryption;

import java.math.BigInteger;

public class decrypt {
    private BigInteger message;
    public decrypt(BigInteger message) {
        this.message = message;
    }

    public BigInteger decryptMessage(BigInteger privateKey[]) {
        BigInteger d = privateKey[0];
        BigInteger n = privateKey[1];

        return message.modPow(d, n);    // y^d mod n
    }
}
