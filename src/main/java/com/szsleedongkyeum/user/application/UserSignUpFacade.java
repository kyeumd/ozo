package com.szsleedongkyeum.user.application;

import com.szsleedongkyeum.user.api.request.SignUpRequest;
import com.szsleedongkyeum.user.model.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpFacade {

    private final UserSignUpService userService;

    public void execute(SignUpRequest signUpRequest) {
        userService.singUp(signUpRequest.userId(), signUpRequest.password(), signUpRequest.name(), signUpRequest.regNo());
    }
}
