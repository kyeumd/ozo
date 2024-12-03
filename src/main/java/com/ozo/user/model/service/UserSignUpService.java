package com.ozo.user.model.service;

import com.ozo.common.Error.ErrorCode;
import com.ozo.user.infra.UserRepository;
import com.ozo.user.model.domain.User;
import com.ozo.user.model.domain.type.AllowedUser;
import com.ozo.utils.EncryptionUtil;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;

    @Transactional
    public void singUp(String userId, String password, String name, String regNo) {
        AllowedUser.isAllowedUser(name, regNo);
        validatingIdentity(userId, regNo);
        User user = User.create(userId, password, name, regNo);
        userRepository.save(user);
    }

    private void validatingIdentity(String userId, String regNo) {
        Optional<User> userByUserId = userRepository.findByCreatorUserId(userId);
        Optional<User> userByRegNo = userRepository.findByRegNo(EncryptionUtil.encrypt(regNo));
        if (userByUserId.isPresent()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_USER_ID.getMessage());
        }
        if (userByRegNo.isPresent()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_IDENTITY.getMessage());
        }
    }
}
