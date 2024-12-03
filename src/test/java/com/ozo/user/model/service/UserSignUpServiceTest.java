package com.ozo.user.model.service;

import static com.ozo.common.Error.ErrorCode.DUPLICATED_IDENTITY;
import static com.ozo.common.Error.ErrorCode.DUPLICATED_USER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ozo.user.model.domain.type.AllowedUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserSignUpServiceTest {

    @Autowired
    private UserSignUpService userSignUpService;

    @Test
    @DisplayName("중복된_id는_가입이_불가능하다.")
    void userSigUpDuplicateIdTest() {
        String name = AllowedUser.GWAN_WOO.getName();
        String regNo = AllowedUser.GWAN_WOO.getDecryptRegNo();
        String userCreatorId = "userIdTest999";
        String password = "testPassword";
        userSignUpService.singUp(userCreatorId, password, name, regNo);

        assertThatThrownBy(
            () -> userSignUpService.singUp(userCreatorId, password, AllowedUser.DONG_TAK.getName(), AllowedUser.DONG_TAK.getDecryptRegNo()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(DUPLICATED_USER_ID.getMessage());
    }

    @Test
    @DisplayName("중복된_회원은_가입이_불가능하다.")
    void userSigUpDuplicateUserTest() {
        String name = AllowedUser.JO_JO.getName();
        String regNo = AllowedUser.JO_JO.getDecryptRegNo();
        String userCreatorId = "userIdTest898";
        String password = "testPassword";
        userSignUpService.singUp(userCreatorId, password, name, regNo);

        assertThatThrownBy(
            () -> userSignUpService.singUp("anotherID", password, name, regNo))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(DUPLICATED_IDENTITY.getMessage());
    }

}