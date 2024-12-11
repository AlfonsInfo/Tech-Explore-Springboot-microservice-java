package spring.template.crypto.service;


import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AesService {

    //Dynamic key generation
    private SecretKey secretKey;
    private final int KEY_SIZE = 128; // Possible Value 128, 192, 256
    private final int T_LENGTH = 128; // Possible Value 128, 120, 112, 104, 96

    private Cipher cipher;

    //Static Key
    private static final String key = "aesEncryptionKey";

    public void init() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        secretKey = generator.generateKey();
    }

    public String encrypt(String data) throws
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        byte[] messageInBytes = data.getBytes();
        Cipher encryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessageInBytes = encryptCipher.doFinal(messageInBytes);
        return encode(encryptedMessageInBytes);
    }

    public String decrypt(String data)
            throws IllegalBlockSizeException,
            BadPaddingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidAlgorithmParameterException
    {
        byte[] encryptedMessageInBytes = decode(data);
        Cipher decryptCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LENGTH, cipher.getIV());
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, decryptCipher.getParameters());
        byte[] decryptedMessageInBytes = decryptCipher.doFinal(encryptedMessageInBytes);
        return new String(decryptedMessageInBytes);
    }

    private String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
