package com.ozo.user.model.domain.type;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ozo.common.Error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllowedUserTest {

    @Test
    @DisplayName("허용되지_않은_사용자는_가입이_불가능하다.")
    void testAllowedUser() {
        String userName = "anonymous";
        String regNo = "941130-1010110";

        assertThatThrownBy(() -> AllowedUser.isAllowedUser(userName, regNo))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.NOT_ALLOWED_USER.getMessage());
    }

}