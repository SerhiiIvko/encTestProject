package app.encryptor.impl;

import app.encryptor.EncryptionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionServiceImplTest {
    private EncryptionService encryptionService;
    String textToEncrypt1;
    String textToEncrypt2;
    String textToEncrypt3;

    @Before
    public void setUp(){
        encryptionService = new EncryptionServiceImpl();
        textToEncrypt1 = "Hello";
        textToEncrypt2 = "";
        textToEncrypt3 = null;
    }

    @Test
    public void encryptIfAllParamsAreCorrect() {
        String encrypt = encryptionService.encrypt(textToEncrypt1);
        assertNotNull(encrypt);
    }

    @Test
    public void encryptIfTextForEncryptionIsEmpty() {
        String encrypt = encryptionService.encrypt(textToEncrypt2);
        assertNotNull(encrypt);
    }

    @Test(expected = NullPointerException.class)
    public void encryptIfTextForEncryptionIsNull() {
        encryptionService.encrypt(textToEncrypt3);
    }

    @Test
    public void decryptIfAllParamsAreCorrect() {
        String decrypt = encryptionService.decrypt(textToEncrypt1);
        assertNotNull(decrypt);
    }

    @Test
    public void decryptIfTextForDecryptionIsEmpty() {
        String decrypt = encryptionService.decrypt(textToEncrypt2);
        assertNotNull(decrypt);
    }

    @Test(expected = NullPointerException.class)
    public void encryptIfTextForDecryptionIsNull() {
        encryptionService.decrypt(textToEncrypt3);
    }
}