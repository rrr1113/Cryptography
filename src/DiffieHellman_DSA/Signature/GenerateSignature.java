package DiffieHellman_DSA.Signature;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class GenerateSignature {
    private BigInteger p;
    private BigInteger alpha;
    private BigInteger q;

    private BigInteger k;
    private BigInteger r;


    public GenerateSignature(BigInteger p, BigInteger alpha, BigInteger q) {
        this.p = p;
        this.alpha = alpha;
        this.q = q;

        setK();
        setR();
    }

    public void setK() {
        k = BigInteger.valueOf(10);
        // 1 < k < q
        /*do {
            k = new BigInteger(q.bitLength(), new SecureRandom());
        } while (k.compareTo(BigInteger.ONE) <= 0); */
    }

    public void setR() {
        r = alpha.modPow(k, p).mod(q);    // r = (alpha^k mod p) mod q
    }

    public Signature getDsaSignature(BigInteger signerPublicKey, BigInteger receiverPublicKey, BigInteger signerPrivateKey) throws Exception {
        String message = signerPublicKey + "," + receiverPublicKey;  // (alpha^y, alpha^x)

        MessageDigest hashF = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = hashF.digest(message.getBytes());
        BigInteger h = new BigInteger(1, hashBytes);

        BigInteger s =  (k.modInverse(q)).multiply(h.add(signerPrivateKey.multiply(r))).mod(q);
        return new Signature(r, s);
    }

}
