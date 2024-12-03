package com.ozo.user.infra;

import com.ozo.user.model.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCreatorUserId(String userId);

    Optional<User> findByRegNo(String regNo);
}
