package DiffieHellman_DSA.Bob;

import DiffieHellman_DSA.Signature.GenerateSignature;
import DiffieHellman_DSA.Signature.Signature;
import DiffieHellman_DSA.Signature.VerifySignature;

import java.math.BigInteger;

public class Bob {
    private BigInteger alpha;
    private final BigInteger p;
    private final BigInteger q;

    private BigInteger privateKey;  //y
    private BigInteger publicKey;   //alpha^y

    private BigInteger commonSecret;
    private BigInteger alicePublicKey;



    public Bob(BigInteger alpha, BigInteger p, BigInteger q) {
        this.alpha = alpha;
        this.p = p;
        this.q = q;

        generatePrivateKey();
        setPublicKey(p);
    }

    private void generatePrivateKey(){
        privateKey = new BigInteger(p.bitLength() - 1, new java.security.SecureRandom());   // 1 < x < p-1
    }

    public void setPublicKey(BigInteger p) {
        publicKey = alpha.modPow(privateKey, p);  // alpha^y mod p
    }

    public void setCommonSecret(BigInteger alicePublicKey) {
        this.alicePublicKey = alicePublicKey;
        commonSecret = alicePublicKey.modPow(privateKey, p);  // (alpha^x)^y mod p
    }

    public BigInteger getCommonSecret() {
        return commonSecret;
    }


    public BigInteger getPublicKey() {
        return publicKey;
    }



    public Signature sign() {
        GenerateSignature signatureGenerator = new GenerateSignature(p, alpha, q);
        Signature signature;
        try {
             signature = signatureGenerator.getDsaSignature(publicKey, alicePublicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signature;
    }


    public Boolean verifySignature(Signature s){
        VerifySignature verifySignature;
        try {
            verifySignature = new VerifySignature(s, alicePublicKey, publicKey, alpha, p, q);
            return verifySignature.verify();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    
}
