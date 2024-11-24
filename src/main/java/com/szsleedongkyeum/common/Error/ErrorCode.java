package com.szsleedongkyeum.common.Error;


import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("E_500", "서버 오류"),
    INVALID_PARAMETER("E_400", "파라미터 오류"),
    ACCESS_DENIED("E_403", "권한이 없습니다."),

    ;

    private final String errorCode;
    private final String message;

    ErrorCode(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
