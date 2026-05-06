package RSA.key_generation;

import java.math.BigInteger;

public class key_gen {
    private BigInteger p;
    private BigInteger q;

    private BigInteger[] publicKey;
    private BigInteger[] privateKey;

    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public key_gen() {
        this.p = BigInteger.probablePrime(1024, new java.security.SecureRandom());  // Miller–Rabin by defaoult from Java
        this.q = BigInteger.probablePrime(1024, new java.security.SecureRandom());  // Miller–Rabin by defaoult from Java

        calculateN();
        calculateEuler();
        calculateE();
        calculateD();
        calcPublicKey();
        calcPrivateKey();
    }

    private void calculateN() {
        this.n = p.multiply(q);
    }

    private void calculateEuler() {
        this.phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    }

    private void calculateE(){
        BigInteger e = BigInteger.valueOf(65537); // Common choice for e
        boolean isCoprime = phi.gcd(e).equals(BigInteger.ONE);
        this.e = isCoprime ? e : BigInteger.valueOf(17);
    }


    private void calculateD() {
        this.d = e.modInverse(phi);     // e^(-1) mod phi
    }


    private void calcPublicKey() {
        this.publicKey = new BigInteger[]{e, n};
    }
    private void calcPrivateKey() {

        this.privateKey = new BigInteger[]{d, n};
    }

    public BigInteger[] getPublicKey() {
        return publicKey;
    }

    public BigInteger[] getPrivateKey() {
        return privateKey;
    }
}
