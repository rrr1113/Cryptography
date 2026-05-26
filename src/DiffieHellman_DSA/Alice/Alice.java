package DiffieHellman_DSA.Alice;

import DiffieHellman_DSA.Signature.GenerateSignature;
import DiffieHellman_DSA.Signature.Signature;
import DiffieHellman_DSA.Signature.VerifySignature;

import java.math.BigInteger;

public class Alice {
    private BigInteger alpha;
    private final BigInteger p;
    private final BigInteger q;

    private BigInteger privateKey;  //x
    private BigInteger publicKey;   //alpha^x mod p = A
    private BigInteger commonSecret;

    private BigInteger bobPublicKey;

    public Alice(BigInteger alpha, BigInteger p, BigInteger q) {
         this.alpha = alpha;
         this.p = p;
         this.q = q;

        setPrivateKey();
        setPublicKey(p);
    }

    private void setPrivateKey(){
        privateKey = new BigInteger(p.bitLength() - 1, new java.security.SecureRandom());   // 1 < x < p-1
    }

    public void setPublicKey(BigInteger p) {
        publicKey = alpha.modPow(privateKey, p);  // alpha^x mod p
    }

    public void setCommonSecret(BigInteger bobPublicKey) {
        this.bobPublicKey = bobPublicKey;
        this.commonSecret = bobPublicKey.modPow(privateKey, p);  // (alpha^y)^x mod p
    }
    public BigInteger getCommonSecret() {
        return commonSecret;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }



    public Signature sign() {
        GenerateSignature signatureGenerator = new GenerateSignature(p, alpha, q);
        Signature signature = null;
        try {
            signature = signatureGenerator.getDsaSignature(publicKey, bobPublicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signature;
    }


    public Boolean verifySignature(Signature s){
        VerifySignature verifySignature = null;
        try {
            verifySignature = new VerifySignature(s, bobPublicKey, publicKey, alpha, p, q);
            verifySignature.print();
            return verifySignature.verify();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
