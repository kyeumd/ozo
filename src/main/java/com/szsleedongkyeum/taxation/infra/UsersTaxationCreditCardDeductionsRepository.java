package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxationCreditCardDeductions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTaxationCreditCardDeductionsRepository extends JpaRepository<UsersTaxationCreditCardDeductions, Long> {
}
