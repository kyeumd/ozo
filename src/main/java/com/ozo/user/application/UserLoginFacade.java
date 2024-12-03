package com.ozo.user.application;

import com.ozo.user.api.request.LoginRequest;
import com.ozo.user.model.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginFacade {

    private final UserLoginService userLoginService;

    public String execute(LoginRequest loginRequest) {
        return userLoginService.authenticateAndCreateToken(loginRequest.userId(), loginRequest.password());
    }
}
