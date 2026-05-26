package DiffieHellman_DSA;

import DiffieHellman_DSA.Alice.Alice;
import DiffieHellman_DSA.Bob.Bob;
import DiffieHellman_DSA.Signature.Signature;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SimulateCommunication {


    public static void main(String[] args) {
        // Choose p and alpha

        BigInteger p = BigInteger.valueOf(59);
        BigInteger alpha = BigInteger.valueOf(3);
        BigInteger q = BigInteger.valueOf(29);


        /*
        // Following the DSA parameter generation sizes:
        // 2^1023 < p < 2^1024
        BigInteger p = BigInteger.probablePrime(1024, new SecureRandom());

        BigInteger pMinus1 = p.subtract(BigInteger.ONE);

         // q prime divisor of p-1 and    2^159 < q < 2^160
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        do {
            q = BigInteger.probablePrime(160, new SecureRandom());
        } while (!pMinus1.mod(q).equals(BigInteger.ZERO));

        // ord(alpha) = q
        BigInteger alpha;
        do {
            alpha = new BigInteger(p.bitLength(), new SecureRandom()).mod(pMinus1).add(BigInteger.TWO);
        } while (!alpha.modPow(pMinus1.divide(q), p).equals(BigInteger.ONE.negate().mod(p))
                && !alpha.modPow(pMinus1.divide(q), p).equals(pMinus1));
        */


        //Alice and Bob generate their keys
        Alice alice = new Alice(alpha, p, q);
        Bob bob = new Bob(alpha, p, q);


        // Alice sends her public key to Bob
        BigInteger alicePublicKey = alice.getPublicKey();
        System.out.println("Alice's public key: " + alicePublicKey.toString());
        System.out.println("------> Sending to Bob: publicKey");
        System.out.println();


        // Bob sends his public key and encrypted signature to Alice
        bob.setCommonSecret(alice.getPublicKey());
        System.out.println("Bob's common secret: " + bob.getCommonSecret());

        BigInteger bobPublicKey = bob.getPublicKey();
        System.out.println("Bob's public key: " + bobPublicKey.toString());

        Signature bobSignature = bob.sign();
        System.out.println("Bob's signature: " + bobSignature.toString());
        byte[] bobsEncryptedSignature = bobSignature.encryptSignature(bob.getCommonSecret());
        System.out.println("Bob's encrypted signature: " + bobsEncryptedSignature.toString());
        System.out.println("------> Sending to Alice: publicKey, encryptedSignature");
        System.out.println();


        // Alice gets bobs public key and encrypted signature, decrypts signature and verifies it
        alice.setCommonSecret(bob.getPublicKey());
        System.out.println("Alice's common secret: " + alice.getCommonSecret());

        Signature bobsDecryptedSignature = Signature.decryptSignature(bobsEncryptedSignature, alice.getCommonSecret());
        System.out.println("Bob's decrypted signature: " + bobsDecryptedSignature.toString());

        Boolean isBobValid = alice.verifySignature(bobsDecryptedSignature);
        System.out.println("Bobs signature valid? " + isBobValid);

        if(!isBobValid) {
            System.out.println("Bob's signature is invalid");
            return;
        }


        // Alice sends bob her signature
        Signature aliceSignature = alice.sign();
        System.out.println("Alice's signature: " + aliceSignature.toString());

        byte[] alicesEncryptedSignature = aliceSignature.encryptSignature(alice.getCommonSecret());
        System.out.println("Alice's encrypted signature: " + alicesEncryptedSignature.toString());
        System.out.println("------> Sending to Bob: encryptedSignature");
        System.out.println();


        // bob verifies alices signature
        Signature alicesDecryptedSignature = Signature.decryptSignature(alicesEncryptedSignature, bob.getCommonSecret());
        System.out.println("Alice's decrypted signature: " + alicesDecryptedSignature.toString());
        Boolean isAliceValid = bob.verifySignature(alicesDecryptedSignature);
        System.out.println("Alice's signature valid? " + isAliceValid);
        System.out.println();

        if(!isAliceValid) {
            System.out.println("Alice's signature is invalid");
            return;
        }

        System.out.println("Success");



    }
}
