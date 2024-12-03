package com.ozo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EncryptionUtilTest {

    @Test
    @DisplayName("AES_암호화_및_복호화_테스트")
    void testAesEncryptionAndDecryption() throws Exception {
        String originalText = "테스트 데이터";
        String encryptText = EncryptionUtil.encrypt(originalText);
        String decryptText = EncryptionUtil.decrypt(encryptText);

        assertEquals(originalText, decryptText);
    }
}