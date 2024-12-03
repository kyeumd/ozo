package com.ozo.user.model.service;

import com.ozo.security.service.AuthenticationUserService;
import com.ozo.user.infra.UserRepository;
import com.ozo.user.model.domain.User;
import com.ozo.user.model.service.out.UserInfoResult;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResult getCurrentUserInfo() {
        String userId = AuthenticationUserService.getUserCreatorId();
        Optional<User> userOptional = userRepository.findByCreatorUserId(userId);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("유저 정보를 찾을 수 없습니다."));
        return UserInfoResult.from(user);
    }
}
