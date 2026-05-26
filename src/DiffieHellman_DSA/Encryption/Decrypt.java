package DiffieHellman_DSA.Encryption;

import java.math.BigInteger;

public class Decrypt {
    public static byte[] decrypt(byte[] data, BigInteger commonSecret) {
        return Encrypt.encrypt(data, commonSecret);
    }
}
