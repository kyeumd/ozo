package com.ozo.user.application;

import com.ozo.user.api.request.SignUpRequest;
import com.ozo.user.model.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpFacade {

    private final UserSignUpService userService;
    private final PasswordEncoder passwordEncoder;

    public void execute(SignUpRequest signUpRequest) {
        userService.singUp(signUpRequest.userId(), passwordEncoder.encode(signUpRequest.password()), signUpRequest.name(), signUpRequest.regNo());
    }
}
