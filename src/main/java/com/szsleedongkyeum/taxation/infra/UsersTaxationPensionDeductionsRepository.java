package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxationPensionDeductions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTaxationPensionDeductionsRepository extends JpaRepository<UsersTaxationPensionDeductions, Long> {
}
