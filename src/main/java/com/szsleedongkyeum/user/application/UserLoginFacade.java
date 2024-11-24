package com.szsleedongkyeum.user.application;

import com.szsleedongkyeum.user.api.request.LoginRequest;
import com.szsleedongkyeum.user.model.service.UserLoginService;
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
