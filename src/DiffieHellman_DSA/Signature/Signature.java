package DiffieHellman_DSA.Signature;

import DiffieHellman_DSA.Encryption.Decrypt;
import DiffieHellman_DSA.Encryption.Encrypt;

import java.math.BigInteger;

public class Signature {
    private BigInteger r;
    private BigInteger s;

    public Signature(BigInteger r, BigInteger s) {
        this.r = r;
        this.s = s;
    }

    public BigInteger getR() { return r; }
    public BigInteger getS() { return s; }

    private byte[] toBytes() {
        String serialized = r + "|" + s;
        return serialized.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    private static Signature fromBytes(byte[] bytes) {
        String[] parts = new String(bytes, java.nio.charset.StandardCharsets.UTF_8).split("\\|");
        return new Signature(new BigInteger(parts[0]), new BigInteger(parts[1]));
    }

    public byte[] encryptSignature(BigInteger sharedSecret) {
        return Encrypt.encrypt(this.toBytes(), sharedSecret);
    }

    public static Signature decryptSignature(byte[] encryptedBytes, BigInteger sharedSecret) {
        return fromBytes(Decrypt.decrypt(encryptedBytes, sharedSecret));
    }

    public String toString() {
        return "Signature{ r=" + r + ", s=" + s + '}';
    }
}