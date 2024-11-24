package com.szsleedongkyeum.user.model.service;

import com.szsleedongkyeum.user.infra.UserRepository;
import com.szsleedongkyeum.user.model.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void singUp(String userId, String password, String name, String regNo) {
        User user = User.create(userId, password, name, regNo);
        userRepository.save(user);
    }
}
