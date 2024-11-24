package com.szsleedongkyeum.common.exception;



import static com.szsleedongkyeum.common.Error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.szsleedongkyeum.common.Error.ErrorCode.INVALID_PARAMETER;

import com.szsleedongkyeum.common.Error.ErrorCode;
import com.szsleedongkyeum.common.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public Response<ErrorCode> handleAuthenticationException(HttpServletRequest request, Exception e) {
        return Response.fail(INVALID_PARAMETER, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<ErrorCode> handleServerException(HttpServletRequest request, Exception e) {
        log.error("Error processing request: {}", e.getMessage(), e);
        return Response.fail(INTERNAL_SERVER_ERROR);
    }

}
