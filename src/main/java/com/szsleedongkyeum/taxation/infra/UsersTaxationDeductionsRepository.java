package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxationDeductions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTaxationDeductionsRepository extends JpaRepository<UsersTaxationDeductions, Long> {

    List<UsersTaxationDeductions> findAllByUserId(Long userId);

    List<UsersTaxationDeductions> findAllByUserIdAndDeductionYear(Long userId, int deductionYear);
}
