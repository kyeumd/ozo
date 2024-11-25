package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxationPensionDeductions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTaxationPensionDeductionsRepository extends JpaRepository<UsersTaxationPensionDeductions, Long> {

    List<UsersTaxationPensionDeductions> findAllByUserId(Long userId);
}
