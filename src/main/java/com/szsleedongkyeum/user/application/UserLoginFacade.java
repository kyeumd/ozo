package com.szsleedongkyeum.user.application;

import com.szsleedongkyeum.user.api.request.LoginRequest;
import com.szsleedongkyeum.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginFacade {

    private final UserService userService;

    public void execute(LoginRequest loginRequest) {
        userService.login(loginRequest.userId(), loginRequest.password());
    }
}
