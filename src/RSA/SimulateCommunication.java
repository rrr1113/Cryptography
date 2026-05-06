package RSA;

import RSA.decryption.decrypt;
import RSA.encryption.encrypt;
import RSA.key_generation.key_gen;

import java.math.BigInteger;
import java.util.Scanner;

public class SimulateCommunication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // I generate my keys
        key_gen me = new key_gen();

        // tylerDurden generates his keys
        key_gen tylerDurden = new key_gen();

        // I want to send a message to tylerDurden
        BigInteger message = sc.nextBigInteger();
        System.out.println("My original message: " + message);

        // I encrypt the message using tylerDurden public key
        encrypt enc = new encrypt(message);
        BigInteger encryptedMessage = enc.encryptMessage(tylerDurden.getPublicKey());
        System.out.println("Encrypted message: " + encryptedMessage);

        // tylerDurden decrypts the message using his private key
        decrypt dec = new decrypt(encryptedMessage);
        BigInteger decryptedMessage = dec.decryptMessage(tylerDurden.getPrivateKey());
        System.out.println("Decrypted message: " + decryptedMessage);

        System.out.println("Decryption successful: " + message.equals(decryptedMessage));
    }
}
