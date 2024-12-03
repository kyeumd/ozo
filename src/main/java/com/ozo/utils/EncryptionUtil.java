package com.ozo.utils;

import com.ozo.common.Error.ErrorCode;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final SecretKey SECRET_KEY = new SecretKeySpec("ozo.business!@*&".getBytes(), ALGORITHM);

    public static String encrypt(String data) {
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.ENCRYPTION_FAIL.getMessage(), e);
        }
    }

    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(ErrorCode.ENCRYPTION_FAIL.getMessage(), e);
        }
    }

    private static Cipher getCipher(int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, SECRET_KEY);
        return cipher;
    }

}
