package com.szsleedongkyeum.common.response;


import com.szsleedongkyeum.common.Error.ErrorCode;

public record Response<T>(
    boolean isSuccess,
    T data,
    String errorCode,
    String message
) {

    public static Response<Void> success() {
        return new Response<>(true, null, null, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(true, data, null, null);
    }

    public static Response<ErrorCode> fail(ErrorCode errorCode) {
        return new Response<>(false, null, errorCode.getErrorCode(), errorCode.getMessage());
    }

    public static Response<ErrorCode> fail(ErrorCode errorCode, String message) {
        return new Response<>(false, null, errorCode.getErrorCode(), message);
    }

}
