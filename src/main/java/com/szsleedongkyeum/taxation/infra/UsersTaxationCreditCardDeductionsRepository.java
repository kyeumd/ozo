package com.szsleedongkyeum.taxation.infra;

import com.szsleedongkyeum.taxation.model.domain.UsersTaxationCreditCardDeductions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTaxationCreditCardDeductionsRepository extends JpaRepository<UsersTaxationCreditCardDeductions, Long> {

    List<UsersTaxationCreditCardDeductions> findAllByUserId(Long userId);
}
