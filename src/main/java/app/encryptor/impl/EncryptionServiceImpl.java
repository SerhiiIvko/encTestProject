package app.encryptor.impl;

import app.constants.ApplicationConstants;
import app.encryptor.EncryptionService;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private final TextEncryptor encryptor;

    public EncryptionServiceImpl() {
        String textMessage = ApplicationConstants.FIO;
        String saltString = KeyGenerators.string().generateKey();
        this.encryptor = Encryptors.text(textMessage, saltString);
    }

    @Override
    public String encrypt(String textToEncrypt) {
        return encryptor.encrypt(textToEncrypt);
    }

    @Override
    public String decrypt(String textToDecrypt) {
        return encryptor.decrypt(encrypt(textToDecrypt));
    }
}
