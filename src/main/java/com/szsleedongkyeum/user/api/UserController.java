package com.szsleedongkyeum.user.api;

import com.szsleedongkyeum.common.response.Response;
import com.szsleedongkyeum.user.api.request.LoginRequest;
import com.szsleedongkyeum.user.api.request.SignUpRequest;
import com.szsleedongkyeum.user.application.UserLoginFacade;
import com.szsleedongkyeum.user.application.UserSignUpFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/szs")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserSignUpFacade userSignUpFacade;
    private final UserLoginFacade userLoginFacade;

    @PostMapping("/signup")
    public Response<Void> singUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        userSignUpFacade.execute(signUpRequest);
        return Response.success("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public Response<Void> login(@Valid @RequestBody LoginRequest loginRequest) {
        
        return Response.success();
    }

}
