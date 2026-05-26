package DiffieHellman_DSA.Encryption;

import java.math.BigInteger;

public class Encrypt {
    public static byte[] encrypt(byte[] data, BigInteger commonSecret) {
        byte[] keyBytes = commonSecret.toByteArray();
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
        }
        return result;
    }
}
