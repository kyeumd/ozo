package com.szsleedongkyeum.user.model.service;

import com.szsleedongkyeum.common.Error.ErrorCode;
import com.szsleedongkyeum.user.infra.UserRepository;
import com.szsleedongkyeum.user.model.domain.User;
import com.szsleedongkyeum.user.model.domain.type.AllowedUser;
import com.szsleedongkyeum.utils.EncryptionUtil;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void singUp(String userId, String password, String name, String regNo) {
        AllowedUser.isAllowedUser(name, regNo);
        validatingIdentity(userId, regNo);
        User user = User.create(userId, password, name, regNo);
        userRepository.save(user);
    }

    private void validatingIdentity(String userId, String regNo) {
        Optional<User> userByUserId = userRepository.findByUserId(userId);
        Optional<User> userByRegNo = userRepository.findByRegNo(EncryptionUtil.encrypt(regNo));
        if (userByUserId.isPresent()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_USER_ID.getMessage());
        }
        if (userByRegNo.isPresent()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_IDENTITY.getMessage());
        }
    }
}
