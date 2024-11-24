package com.szsleedongkyeum.user.infra;

import com.szsleedongkyeum.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
