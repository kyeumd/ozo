package com.szsleedongkyeum.user.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
    @NotBlank(message = "id는 필수 입력입니다.")
    String userId,
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    String password,
    @NotBlank(message = "이름은 필수 입력입니다.")
    String name,
    @NotBlank(message = "주민번호는 필수 입력입니다.")
    @Pattern(regexp = "^[0-9]{6}-[0-9]{7}$", message = "주민번호 형식이 잘못되었습니다.")
    String regNo
) {
}
