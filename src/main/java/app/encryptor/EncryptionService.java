package app.encryptor;

public interface EncryptionService {
    String encrypt(String textToEncrypt);
    String decrypt(String textToDecrypt);
}
