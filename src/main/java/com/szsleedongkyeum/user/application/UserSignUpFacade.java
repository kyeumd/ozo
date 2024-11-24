package com.szsleedongkyeum.user.application;

import com.szsleedongkyeum.user.api.request.SignUpRequest;
import com.szsleedongkyeum.user.model.service.UserSignUpService;
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
