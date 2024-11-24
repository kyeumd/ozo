package com.szsleedongkyeum.user.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.szsleedongkyeum.user.model.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    @DisplayName("유저의_주민번호는_암호화되어_생성된다.")
    void userRegNoEncryptTest() {
        String originRegNo = "941130-1111111";
        
        User user = User.create("testUserId", "password", "name", originRegNo);

        assertNotEquals(originRegNo, user.getRegNo());
        assertEquals(originRegNo, user.getRegNoDecrypt());
    }

}