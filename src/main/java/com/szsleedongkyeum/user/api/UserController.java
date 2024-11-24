package com.szsleedongkyeum.user.api;

import com.szsleedongkyeum.common.response.Response;
import com.szsleedongkyeum.user.api.request.SignUpRequest;
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


    @PostMapping("/signup")
    public Response<Void> singUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return Response.success();
    }

}
