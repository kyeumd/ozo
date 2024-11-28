package com.szsleedongkyeum.user.api.request;

import com.szsleedongkyeum.support.ValidRequired;

public record LoginRequest(
    @ValidRequired(fieldName = "아이디")
    String userId,
    @ValidRequired(fieldName = "비밀번호")
    String password
) {
}
