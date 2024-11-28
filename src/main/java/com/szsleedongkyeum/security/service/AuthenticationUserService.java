package com.szsleedongkyeum.security.service;

import com.szsleedongkyeum.common.Error.ErrorCode;
import com.szsleedongkyeum.security.domain.dto.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUserService {

    public static String getUserCreatorId() {
        UserContext userContext = getUserContext();
        return userContext.getUserDto().getUserId();
    }

    public static Long getUserId() {
        UserContext userContext = getUserContext();
        return userContext.getUserDto().getId();
    }

    private static UserContext getUserContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserContext)) {
            throw new IllegalArgumentException(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
        }
        
        return (UserContext) principal;
    }
}
