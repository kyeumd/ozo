package com.ozo.user.api.request;

import com.ozo.support.ValidRequired;

public record LoginRequest(
    @ValidRequired(fieldName = "아이디")
    String userId,
    @ValidRequired(fieldName = "비밀번호")
    String password
) {
}
