package com.szsleedongkyeum.security.service;

import com.szsleedongkyeum.common.Error.ErrorCode;
import com.szsleedongkyeum.security.domain.dto.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUserService {

    public static String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
        }
        Object principal = authentication.getPrincipal();
        UserContext userContext = (UserContext) principal;
        return userContext.getUserDto().getUserId();
    }
}
