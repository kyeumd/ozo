package com.ozo.user.api.request;

import com.ozo.support.ValidRequired;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
    @ValidRequired(fieldName = "아이디")
    String userId,
    @ValidRequired(fieldName = "비밀번호")
    String password,
    @ValidRequired(fieldName = "이름")
    String name,
    @ValidRequired(fieldName = "주민번호")
    @Pattern(regexp = "^[0-9]{6}-[0-9]{7}$", message = "주민번호 형식이 잘못되었습니다.")
    String regNo
) {
}
