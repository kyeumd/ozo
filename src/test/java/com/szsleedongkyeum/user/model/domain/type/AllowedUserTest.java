package com.szsleedongkyeum.user.model.domain.type;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.szsleedongkyeum.common.Error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllowedUserTest {

    @Test
    @DisplayName("허용된_사용자를_확인한다.")
    void testAllowedUser() {
        String userName = "anonymous";
        String regNo = "941130-1010110";

        assertThatThrownBy(() -> AllowedUser.isAllowedUser(userName, regNo))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorCode.NOT_ALLOWED_USER.getMessage());
    }

}