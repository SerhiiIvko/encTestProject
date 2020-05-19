package app.config;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class main {
    public static void main(String[] args)  {
        final String password = "Here is the password";
        String textToEncrypt = "Hello";

            String salt = KeyGenerators.string().generateKey();
            TextEncryptor encryptor = Encryptors.text(password, salt);
            String cipherText = encryptor.encrypt(textToEncrypt);
            String decryptedText = encryptor.decrypt(cipherText);
            System.out.println("Src: " + textToEncrypt);
            System.out.println("Cipher: " + cipherText);
            System.out.println("Decrypted: " + decryptedText);
            System.out.println("__________________");

    }
}
