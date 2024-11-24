package com.szsleedongkyeum.user.infra;

import com.szsleedongkyeum.user.model.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByRegNo(String regNo);
}
