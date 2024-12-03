package com.ozo.user.api;

import com.ozo.common.response.Response;
import com.ozo.user.api.request.LoginRequest;
import com.ozo.user.api.request.SignUpRequest;
import com.ozo.user.api.response.LoginResponse;
import com.ozo.user.application.UserLoginFacade;
import com.ozo.user.application.UserSignUpFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ozo")
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
    public Response<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userLoginFacade.execute(loginRequest);
        return Response.success(new LoginResponse(loginRequest.userId(), token));
    }

}
