package com.szsleedongkyeum.common.Error;


import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("E_500", "서버 오류"),
    INVALID_PARAMETER("E_400", "파라미터 오류"),
    ACCESS_DENIED("E_403", "권한이 없습니다."),
    AUTHENTICATION_NOT_FOUND("E_404", "권한 정보를 찾을 수 없습니다."),
    INVALID_LOGIN_INFO("E_406", "아이디 혹은 패스워드가 잘못되었습니다."),
    NOT_ALLOWED_USER("E_407", "허용되지 않은 사용자입니다."),
    DUPLICATED_USER_ID("E_408", "이미 존재하는 ID 입니다."),
    DUPLICATED_IDENTITY("E_409", "이미 가입된 회원입니다."),
    ENCRYPTION_FAIL("E_506", "암호화에 실패하였습니다."),

    ;

    private final String errorCode;
    private final String message;

    ErrorCode(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
