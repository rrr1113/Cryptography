package DiffieHellman_DSA.Signature;

import java.math.BigInteger;
import java.security.MessageDigest;

public class VerifySignature {
    Signature signature;

    private BigInteger alpha;
    private BigInteger q;
    private BigInteger p;

    private BigInteger w;
    private BigInteger u1;
    private BigInteger u2;
    private BigInteger v;

    private BigInteger signerPublicKey;
    private BigInteger receiverPublicKey;

    private String message;

    public VerifySignature(Signature signature, BigInteger signerPublicKey, BigInteger receiverPublicKey, BigInteger alpha,  BigInteger p, BigInteger q) throws Exception {
        this.alpha = alpha;
        this.q = q;
        this.p = p;
        this.signature = signature;
        this.signerPublicKey = signerPublicKey;
        this.receiverPublicKey = receiverPublicKey;

        this.message = signerPublicKey + "," + receiverPublicKey;

        setW();
        setU1();
        setU2();
        setV();
    }

    public void setW() {
        // w = s^-1 mod q
        w = signature.getS().modInverse(q);
    }
    public void setU1() throws Exception {
        // u1 = H(m) * w mod q
        BigInteger hm = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(message.getBytes()));
        u1 = hm.multiply(w).mod(q);
    }
    public void setU2() {
        // u2 = r * w mod q
        u2 = signature.getR().multiply(w).mod(q);
    }
    public void setV() {
        // v = (alpha^u1 * signerPublicKey^u2 mod p) mod q
        BigInteger left = alpha.modPow(u1, p);
        BigInteger right = signerPublicKey.modPow(u2, p);
        v = left.multiply(right).mod(p).mod(q);
    }
    public Boolean verify() {
        // Signature is valid if v == r
        return v.equals(signature.getR());
    }

    public void print() {
        System.out.println("Verify{ w=" + w + ", u1=" + u1 + ", u2=" + u2 + ", v=" + v + '}');
    }
}