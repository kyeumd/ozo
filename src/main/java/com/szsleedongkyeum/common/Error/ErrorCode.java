package com.szsleedongkyeum.common.Error;


import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("E_500", "서버 오류"),
    INVALID_PARAMETER("E_400", "파라미터 오류"),
    ACCESS_DENIED("E_403", "권한이 없습니다."),
    NOT_ALLOWED_USER("E_407", "허용되지 않은 사용자입니다."),
    ENCRYPTION_FAIL("E_506", "암호화에 실패하였습니다."),

    ;

    private final String errorCode;
    private final String message;

    ErrorCode(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
