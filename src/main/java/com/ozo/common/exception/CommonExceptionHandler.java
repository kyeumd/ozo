package com.ozo.common.exception;

import static com.ozo.common.Error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.ozo.common.Error.ErrorCode.INVALID_LOGIN_INFO;
import static com.ozo.common.Error.ErrorCode.INVALID_PARAMETER;

import com.ozo.common.Error.ErrorCode;
import com.ozo.common.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public Response<ErrorCode> handleRequestException(HttpServletRequest request, Exception e) {
        return Response.fail(INVALID_PARAMETER, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<ErrorCode> handleRequestBodyException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                               .getAllErrors()
                               .stream()
                               .map(ObjectError::getDefaultMessage) // 메시지만 가져오기
                               .collect(Collectors.joining(", ")); // 메시지를 쉼표로 연결

        return Response.fail(INVALID_PARAMETER, errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public Response<ErrorCode> handleBadCredentialException(HttpServletRequest request, Exception e) {
        log.error("Error processing request: {}", e.getMessage(), e);
        return Response.fail(INVALID_LOGIN_INFO);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<ErrorCode> handleServerException(HttpServletRequest request, Exception e) {
        log.error("Error processing request: {}", e.getMessage(), e);
        return Response.fail(INTERNAL_SERVER_ERROR);
    }

}
